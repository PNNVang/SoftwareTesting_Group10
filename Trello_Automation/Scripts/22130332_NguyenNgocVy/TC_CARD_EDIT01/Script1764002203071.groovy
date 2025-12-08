import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testobject.ConditionType
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

// --- Mở trình duyệt mới ---
WebUI.openBrowser('')

// --- Điều hướng đến trang Trello ---
WebUI.navigateToUrl('https://trello.com/')

// --- Click vào nút "Login" trên trang chủ ---
WebUI.click(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Capture, organize, and tackle your to-_17a2f5/a_Resources_Buttonsstyles__Button-sc-1jwidx_3e5bb7'))

// --- Nhập email đăng nhập ---
WebUI.setText(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'), 
    'nguyenvy310804@gmail.com')
WebUI.click(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))

// --- Nhập mật khẩu (mã hóa) ---
WebUI.setEncryptedText(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'), 
    'aZi2sxaWmGoqEKxBwh9bCg==')

// --- Click nút Login/Continue để đăng nhập ---
WebUI.click(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))

// MỞ BOARD DYNAMIC THEO TÊN
String boardName = "My Trello board"

TestObject boardDemo = new TestObject("boardDemo")
boardDemo.addProperty("xpath", ConditionType.EQUALS,
	"//span[text()='" + boardName + "']/ancestor::a")

WebUI.waitForElementClickable(boardDemo, 10)
WebUI.click(boardDemo)
WebUI.delay(2)

// --- Chọn card cần sửa tiêu đề ---
String oldTitle = "Card test tự động"
TestObject cardToEdit = new TestObject('cardToEdit')
cardToEdit.addProperty('xpath', ConditionType.EQUALS, "//h2[@data-testid='list-name'][.='Kiểm thử trello']/ancestor::div[@data-testid='list']//a[@data-testid='card-name' and text()='" + oldTitle + "']")

WebUI.waitForElementVisible(cardToEdit, 10)
WebUI.waitForElementClickable(cardToEdit, 10)
WebUI.click(cardToEdit)

// --- Chọn textarea tiêu đề card ---
TestObject titleTextarea = new TestObject('titleTextarea')
titleTextarea.addProperty('xpath', ConditionType.EQUALS, "//textarea[@data-testid='card-back-title-input']")

WebUI.waitForElementVisible(titleTextarea, 10)
WebUI.click(titleTextarea)
WebUI.setText(titleTextarea, '') // xóa tiêu đề cũ
WebUI.setText(titleTextarea, 'Sửa tiêu đề thẻ') // nhập tiêu đề mới
WebUI.sendKeys(titleTextarea, Keys.chord(Keys.ENTER))
WebUI.delay(1)

// --- Đóng popup card bằng JS để tránh bị header che ---
TestObject closeButton = new TestObject('closeButton')
closeButton.addProperty('xpath', ConditionType.EQUALS, "//span[@data-testid='CloseIcon']")

WebUI.waitForElementVisible(closeButton, 10)

// CLICK BẰNG JAVASCRIPT (KHÔNG BỊ HEADER CHẶN)
WebUI.executeJavaScript(
	"arguments[0].click();",
	Arrays.asList(WebUI.findWebElement(closeButton, 10))
)


// ===== VERIFY CARD ĐÃ ĐƯỢC ĐỔI TIÊU ĐỀ THÀNH CÔNG =====

String newTitle = "Sửa tiêu đề thẻ"

TestObject verifyEditedCard = new TestObject("verifyEditedCard")
verifyEditedCard.addProperty("xpath", ConditionType.EQUALS,
	"//h2[@data-testid='list-name'][.='Kiểm thử trello']/ancestor::div[@data-testid='list']//a[@data-testid='card-name' and normalize-space(text())='" + newTitle + "']")

boolean isEdited = WebUI.verifyElementPresent(verifyEditedCard, 10, FailureHandling.OPTIONAL)

if (isEdited) {
	println("✅ PASS: Card đã được đổi tiêu đề thành công!")
} else {
	KeywordUtil.markFailed("❌ FAIL: KHÔNG tìm thấy card sau khi đổi tiêu đề!")
}


// --- Đóng trình duyệt ---
WebUI.closeBrowser()

