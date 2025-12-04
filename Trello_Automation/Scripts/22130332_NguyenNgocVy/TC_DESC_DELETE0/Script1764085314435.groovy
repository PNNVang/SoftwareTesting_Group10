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

// --- Click nút Save ---
TestObject saveDescriptionButton = new TestObject('saveDescriptionButton')
saveDescriptionButton.addProperty('xpath', ConditionType.EQUALS, "//button[contains(text(),'Save')]")

if (WebUI.waitForElementVisible(saveDescriptionButton, 5, FailureHandling.OPTIONAL)) {
    WebUI.click(saveDescriptionButton)
	WebUI.delay(2)
}



// --- Click nút Close trước khi đóng trình duyệt ---
TestObject closeButton = new TestObject('closeButton')
closeButton.addProperty('xpath', ConditionType.EQUALS, "//span[@data-testid='CloseIcon']")

WebUI.waitForElementVisible(closeButton, 10)
WebUI.click(closeButton)

// ================== VERIFY XÓA MÔ TẢ ==================

// Mở lại card
TestObject verifyCard = new TestObject('verifyCard')
verifyCard.addProperty('xpath', ConditionType.EQUALS,
	"//h2[@data-testid='list-name'][.='Kiểm thử trello']" +
	"/ancestor::div[@data-testid='list']" +
	"//a[@data-testid='card-name' and text()='" + oldTitle + "']")

WebUI.waitForElementClickable(verifyCard, 10)
WebUI.click(verifyCard)

WebUI.delay(2)

// ✅ CASE 1: Nếu div mô tả vẫn tồn tại => FAIL
TestObject descriptionContent = new TestObject('descriptionContent')
descriptionContent.addProperty('xpath', ConditionType.EQUALS,
	"//div[contains(@class,'ak-renderer-document')]")

boolean isDescriptionExist = WebUI.verifyElementPresent(
	descriptionContent,
	3,
	FailureHandling.OPTIONAL
)

if (isDescriptionExist) {
	String actualDescription = WebUI.getText(descriptionContent)
	KeywordUtil.markFailed("❌ FAIL: Mô tả vẫn còn tồn tại: [" + actualDescription + "]")
}
else {
	// ✅ CASE 2: Kiểm tra placeholder
	TestObject placeholder = new TestObject('placeholder')
	placeholder.addProperty('xpath', ConditionType.EQUALS,
		"//button[@data-testid='description-button']")

	boolean isPlaceholderVisible = WebUI.verifyElementVisible(
		placeholder,
		FailureHandling.OPTIONAL
	)

	if (isPlaceholderVisible) {
		WebUI.comment("✅ PASS: Xóa mô tả thành công & placeholder hiển thị đúng!")
	}
	else {
		KeywordUtil.markFailed("❌ FAIL: Không tìm thấy mô tả và cũng không thấy placeholder!")
	}
}

// Đóng card sau khi verify
TestObject closeAfterVerify = new TestObject('closeAfterVerify')
closeAfterVerify.addProperty('xpath', ConditionType.EQUALS, "//span[@data-testid='CloseIcon']")
WebUI.click(closeAfterVerify)


// --- Đóng trình duyệt ---
WebUI.closeBrowser()

