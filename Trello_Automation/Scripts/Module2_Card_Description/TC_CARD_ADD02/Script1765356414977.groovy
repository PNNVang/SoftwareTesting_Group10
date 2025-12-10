import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.WebElement

// --- Cấu hình ---
String boardName = "My Trello board"
String listAriaLabel = "Kiểm thử trello"         // tên list nơi sẽ tạo thẻ
String cardTitle = "Card Kiểm thử"           // tiêu đề trùng muốn tạo

// Mở trình duyệt và vào Trello
WebUI.openBrowser('')
WebUI.navigateToUrl('https://trello.com/')

// (Phần login của bạn giữ nguyên — chèn các findTestObject login tương ứng)
WebUI.click(findTestObject('Object Repository/Module2_Card_Description/Page_Capture, organize, and tackle your to-_17a2f5/a_Resources_Buttonsstyles__Button-sc-1jwidx_3e5bb7'))
WebUI.setText(findTestObject('Object Repository/Module2_Card_Description/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'), 'nguyenvy310804@gmail.com')
WebUI.click(findTestObject('Object Repository/Module2_Card_Description/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))
WebUI.setEncryptedText(findTestObject('Object Repository/Module2_Card_Description/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'), 'aZi2sxaWmGoqEKxBwh9bCg==')
WebUI.click(findTestObject('Object Repository/Module2_Card_Description/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))
WebUI.delay(2)

// Mở board theo tên (dynamic)
TestObject boardDemo = new TestObject("boardDemo")
boardDemo.addProperty("xpath", ConditionType.EQUALS,
	"//span[text()='" + boardName + "']/ancestor::a")
WebUI.waitForElementClickable(boardDemo, 10)
WebUI.click(boardDemo)
WebUI.delay(2)

// ---------- Đếm số card trước khi tạo ----------
TestObject cardsWithTitle = new TestObject("cardsWithTitle")
cardsWithTitle.addProperty("xpath", ConditionType.EQUALS,
	"//ol[@data-testid='list-cards']//a[@data-testid='card-name' and normalize-space(text())='" + cardTitle + "']")

// Hàm find elements trả về List<WebElement>
List<WebElement> beforeList = WebUI.findWebElements(cardsWithTitle, 5)
int beforeCount = beforeList == null ? 0 : beforeList.size()
println("Số lượng thẻ chứa tiêu đề '${cardTitle}' trước khi tạo: " + beforeCount)

// ---------- Thao tác tạo thẻ trùng ----------
TestObject addCardBtn = new TestObject('btnAddCard')
addCardBtn.addProperty("xpath", ConditionType.EQUALS,
	"//button[@aria-label='Add a card in " + listAriaLabel + "']")

WebUI.waitForElementClickable(addCardBtn, 10)
WebUI.click(addCardBtn)

TestObject inputCard = new TestObject('inputCard')
inputCard.addProperty('xpath', ConditionType.EQUALS,
	"//textarea[@data-testid='list-card-composer-textarea']")
WebUI.setText(inputCard, cardTitle)

TestObject btnConfirm = new TestObject('btnConfirm')
btnConfirm.addProperty('xpath', ConditionType.EQUALS,
	"//button[@data-testid='list-add-card-button']")
WebUI.click(btnConfirm)

// Đợi Trello xử lý
WebUI.delay(3)

// ---------- Đếm số card sau khi tạo ----------
List<WebElement> afterList = WebUI.findWebElements(cardsWithTitle, 5)
int afterCount = afterList == null ? 0 : afterList.size()
println("Số lượng thẻ chứa tiêu đề '${cardTitle}' sau khi tạo: " + afterCount)

// ---------- Đánh giá kết quả theo expected output ----------
if (afterCount == beforeCount + 1) {
	println("✅ Hệ thống CHO PHÉP tạo thẻ trùng. (beforeCount=${beforeCount} -> afterCount=${afterCount})")
} else if (afterCount == beforeCount) {
	// Không tăng, coi như hệ thống NGĂN tạo trùng
	KeywordUtil.markFailed("❌ Hệ thống KHÔNG TẠO thẻ trùng. (beforeCount=${beforeCount} -> afterCount=${afterCount})")
} else if (afterCount > beforeCount + 1) {
	// Trường hợp bất thường: tạo nhiều hơn 1 mới
	KeywordUtil.markFailed("❌ Kết quả bất thường: số thẻ tăng lên nhiều hơn 1 (before=${beforeCount}, after=${afterCount})")
} else {
	// afterCount < beforeCount (bất thường)
	KeywordUtil.markFailed("❌ Kết quả bất thường: afterCount < beforeCount (before=${beforeCount}, after=${afterCount})")
}

// Đóng browser
WebUI.closeBrowser()
