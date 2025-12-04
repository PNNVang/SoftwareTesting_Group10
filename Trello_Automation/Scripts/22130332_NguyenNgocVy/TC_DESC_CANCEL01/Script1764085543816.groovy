import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
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
String oldTitle = "Thẻ này để test phần mô tả"
TestObject cardToEdit = new TestObject('cardToEdit')
cardToEdit.addProperty('xpath', ConditionType.EQUALS, "//h2[@data-testid='list-name'][.='Kiểm thử trello']/ancestor::div[@data-testid='list']//a[@data-testid='card-name' and text()='" + oldTitle + "']")

WebUI.waitForElementVisible(cardToEdit, 10)
WebUI.waitForElementClickable(cardToEdit, 10)
WebUI.click(cardToEdit)

// --- Click nút Edit để mở editor ---
TestObject editDescriptionButton = new TestObject('editDescriptionButton')
editDescriptionButton.addProperty('xpath', ConditionType.EQUALS, "//button[@data-testid='description-edit-button']")

WebUI.waitForElementVisible(editDescriptionButton, 10)
WebUI.click(editDescriptionButton)

// --- Chọn editor mô tả ---
TestObject descriptionEditor = new TestObject('descriptionEditor')
descriptionEditor.addProperty('xpath', ConditionType.EQUALS, "//div[@id='ak-editor-textarea']")

WebUI.waitForElementVisible(descriptionEditor, 10)
WebUI.click(descriptionEditor)

// Xóa toàn bộ nội dung cũ
WebUI.sendKeys(descriptionEditor, Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE)

// --- Nội dung mô tả thay đổi nhưng không lưu mà hủy thay đổi ---
WebUI.sendKeys(descriptionEditor, 'Nội dung mô tả thay đổi nhưng không lưu mà hủy thay đổi')

// --- Click nút Cancel để hủy chỉnh sửa ---
TestObject cancelButton = new TestObject('cancelButton')
cancelButton.addProperty('xpath', ConditionType.EQUALS, "//button[@data-testid='description-cancel-button']")
WebUI.waitForElementVisible(cancelButton, 10)
WebUI.click(cancelButton)
WebUI.delay(2)

// --- Click nút Close trước khi đóng trình duyệt ---
TestObject closeButton = new TestObject('closeButton')
closeButton.addProperty('xpath', ConditionType.EQUALS, "//span[@data-testid='CloseIcon']")

WebUI.waitForElementVisible(closeButton, 10)
WebUI.click(closeButton)

// ===== VERIFY MÔ TẢ KHÔNG BỊ THAY ĐỔI SAU KHI BẤM CANCEL =====

// Mô tả ban đầu (trước khi sửa)
String originalDescription = "Mô tả của thẻ này đã được thêm"

// Mở lại card để verify
TestObject verifyCard = new TestObject('verifyCard')
verifyCard.addProperty('xpath', ConditionType.EQUALS,
	"//h2[@data-testid='list-name'][.='Kiểm thử trello']" +
	"/ancestor::div[@data-testid='list']" +
	"//a[@data-testid='card-name' and text()='" + oldTitle + "']")

WebUI.waitForElementClickable(verifyCard, 10)
WebUI.click(verifyCard)

// Locate lại nội dung mô tả (DOM thật)
TestObject descriptionContent = new TestObject('descriptionContent')
descriptionContent.addProperty('xpath', ConditionType.EQUALS,
	"//div[contains(@class,'ak-renderer-document')]")

WebUI.waitForElementVisible(descriptionContent, 10)

String actualDescription = WebUI.getText(descriptionContent)

// Log ra cho chắc chắn nhìn thấy
WebUI.comment("👉 Mô tả hiện tại sau khi bấm Cancel: [" + actualDescription + "]")

// ✅ VERIFY: phải GIỐNG mô tả ban đầu
if (actualDescription.trim() == originalDescription.trim()) {
	WebUI.comment("✅ PASS: Bấm Cancel → mô tả KHÔNG bị thay đổi!")
} else {
	KeywordUtil.markFailed("❌ FAIL: Bấm Cancel nhưng mô tả đã bị thay đổi!")
}

// Đóng lại card sau khi verify
TestObject closeAfterVerify = new TestObject('closeAfterVerify')
closeAfterVerify.addProperty('xpath', ConditionType.EQUALS, "//span[@data-testid='CloseIcon']")
WebUI.click(closeAfterVerify)


// --- Đóng trình duyệt ---
WebUI.closeBrowser()

