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
import com.kms.katalon.core.testobject.ConditionType


// --- Mở trình duyệt mới ---
WebUI.openBrowser('')

// --- Điều hướng đến trang Trello ---
WebUI.navigateToUrl('https://trello.com/')

// --- Click nút "Login" trên trang chủ ---
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

// --- Mở Board cần thao tác ---
WebUI.click(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Boards  Trello/div_Recently viewed_gWl3Xe9ruADN19 nu1exix5zZb9dt'))

// --- Chờ board load ---
WebUI.delay(2)

// --- Chọn card muốn copy dựa trên tên ---
String cardName = "Card test tự động"

// Tạo TestObject trỏ đến thẻ <a> chứa tên card
TestObject cardLink = new TestObject('cardLink')
cardLink.addProperty('xpath', ConditionType.EQUALS, "//h2[@data-testid='list-name'][.='Kiểm thử trello']/ancestor::div[@data-testid='list']//a[@data-testid='card-name' and text()='" + cardName + "']")

WebUI.waitForElementVisible(cardLink, 10)
WebUI.waitForElementClickable(cardLink, 10)
WebUI.click(cardLink)

// --- Click "Actions" (mở menu) ---
TestObject actionsButton = new TestObject('actionsButton')
actionsButton.addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, "//button[@data-testid='card-back-actions-button']")
WebUI.click(actionsButton)

// --- Click "Copy card" ---
TestObject copyButton = new TestObject('copyButton')
copyButton.addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, "//button[@data-testid='card-back-copy-card-button']")
WebUI.click(copyButton)

// --- Xóa tên cũ và nhập tên mới ---
TestObject nameTextarea = new TestObject('nameTextarea')
nameTextarea.addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, "//textarea[@data-testid='card-copy-textarea']")
WebUI.setText(nameTextarea, '') // xóa
WebUI.setText(nameTextarea, 'Đây là thẻ copy')

// --- Click "Create card" ---
TestObject createButton = new TestObject('createButton')
createButton.addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, "//button[@data-testid='move-card-popover-move-button']")
WebUI.click(createButton)

// ===== VERIFY CARD COPY TRONG LIST-CARDS =====

String newCardName = "Đây là thẻ copy"

TestObject checkCopiedCard = new TestObject("checkCopiedCard")
checkCopiedCard.addProperty("xpath", ConditionType.EQUALS,
	"//ol[@data-testid='list-cards']//a[@data-testid='card-name' and normalize-space(text())='" + newCardName + "']")

boolean isCopiedExist = WebUI.verifyElementPresent(checkCopiedCard, 10, FailureHandling.OPTIONAL)

if (isCopiedExist) {
	println("✅ Card COPY '" + newCardName + "' đã được tạo thành công!")
} else {
	KeywordUtil.markFailed("❌ KHÔNG tìm thấy card COPY '" + newCardName + "'!")
}


// --- Đóng trình duyệt ---
WebUI.closeBrowser()