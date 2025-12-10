import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil
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
WebUI.click(findTestObject('Object Repository/Module2_Card_Description/Page_Capture, organize, and tackle your to-_17a2f5/a_Resources_Buttonsstyles__Button-sc-1jwidx_3e5bb7'))

// --- Nhập email đăng nhập ---
WebUI.setText(findTestObject('Object Repository/Module2_Card_Description/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'),
	'nguyenvy310804@gmail.com')
WebUI.click(findTestObject('Object Repository/Module2_Card_Description/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))

// --- Nhập mật khẩu (mã hóa) ---
WebUI.setEncryptedText(findTestObject('Object Repository/Module2_Card_Description/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'),
	'aZi2sxaWmGoqEKxBwh9bCg==')

// --- Click nút Login/Continue để đăng nhập ---
WebUI.click(findTestObject('Object Repository/Module2_Card_Description/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))

// MỞ BOARD DYNAMIC THEO TÊN
String boardName = "My Trello board"

TestObject boardDemo = new TestObject("boardDemo")
boardDemo.addProperty("xpath", ConditionType.EQUALS,
	"//span[text()='" + boardName + "']/ancestor::a")

WebUI.waitForElementClickable(boardDemo, 10)
WebUI.click(boardDemo)
WebUI.delay(2)

// --- Chọn card cần sửa tiêu đề ---
String oldTitle = "Card Thêm mô tả chứa URL hoặc link"
TestObject cardToEdit = new TestObject('cardToEdit')
cardToEdit.addProperty('xpath', ConditionType.EQUALS, "//h2[@data-testid='list-name'][.='Kiểm thử trello']/ancestor::div[@data-testid='list']//a[@data-testid='card-name' and text()='" + oldTitle + "']")

WebUI.waitForElementVisible(cardToEdit, 10)
WebUI.waitForElementClickable(cardToEdit, 10)
WebUI.click(cardToEdit)

// --- Chọn nút "Add a more detailed description..." ---
TestObject addDescriptionButton = new TestObject('addDescriptionButton')
addDescriptionButton.addProperty('xpath', ConditionType.EQUALS, "//button[@data-testid='description-button']")

WebUI.waitForElementVisible(addDescriptionButton, 10)
WebUI.click(addDescriptionButton)

// --- Chọn editor và nhập URL ---
TestObject descriptionEditor = new TestObject('descriptionEditor')
// Editor có id 'ak-editor-textarea' trong ví dụ trước của bạn
descriptionEditor.addProperty('xpath', ConditionType.EQUALS, "//div[@id='ak-editor-textarea']")

WebUI.waitForElementVisible(descriptionEditor, 10)

// URL cần chèn (theo yêu cầu)
String urlToInsert = "https://trello.com/vi"

// Dán/nhập URL vào editor
// Sử dụng executeJavaScript để đặt nội dung trực tiếp tránh flaky với sendKeys
String jsSetText = "var el = document.getElementById('ak-editor-textarea');" +
    "if(el){ el.focus(); el.innerText = '" + urlToInsert + "'; " +
    "var evt = document.createEvent('HTMLEvents'); evt.initEvent('input', true, false); el.dispatchEvent(evt);}"

// Thực hiện JS
WebUI.executeJavaScript(jsSetText, null)
WebUI.delay(1)

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

// ================== VERIFY MÔ TẢ VỪA THÊM ==================

// --- MỞ LẠI CARD để VERIFY (an toàn hơn) ---
WebUI.waitForElementClickable(cardToEdit, 10)
WebUI.click(cardToEdit)
WebUI.delay(1)

// --- Vùng hiển thị nội dung mô tả ---
TestObject descriptionContent = new TestObject('descriptionContent')
descriptionContent.addProperty('xpath', ConditionType.EQUALS, "//div[contains(@class,'ak-renderer-document')]")

WebUI.waitForElementVisible(descriptionContent, 10)

// --- VERIFY: kiểm tra có anchor <a> chứa href = urlToInsert ---
TestObject linkInDescription = new TestObject('linkInDescription')
linkInDescription.addProperty('xpath', ConditionType.EQUALS,
    "//div[contains(@class,'ak-renderer-document')]//a[contains(@href,'" + urlToInsert + "') or normalize-space(text())='" + urlToInsert + "']")

boolean linkPresent = WebUI.verifyElementPresent(linkInDescription, 10, FailureHandling.OPTIONAL)

if (linkPresent) {
    // Lấy href và log ra
    String actualHref = WebUI.getAttribute(linkInDescription, "href")
    WebUI.comment("Expected URL: " + urlToInsert)
    WebUI.comment("Found href: " + actualHref)
    if (actualHref != null && actualHref.contains("trello.com/vi")) {
        KeywordUtil.logInfo("✅ PASS: URL được lưu và hiển thị dưới dạng link với href chứa 'trello.com/vi'")
    } else {
        KeywordUtil.markFailed("❌ FAIL: Link tồn tại nhưng href không đúng: " + actualHref)
    }
} else {
    KeywordUtil.markFailed("❌ FAIL: Không tìm thấy link chứa '" + urlToInsert + "' trong mô tả")
}

// --- Đóng popup và trình duyệt ---
if (WebUI.waitForElementVisible(closeButton, 5, FailureHandling.OPTIONAL)) {
    WebUI.click(closeButton)
}


// --- Đóng trình duyệt ---
WebUI.closeBrowser()

