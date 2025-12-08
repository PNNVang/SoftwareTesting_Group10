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

// Mở Board cần thao tác
WebUI.click(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Boards  Trello/div_Settings_gWl3Xe9ruADN19 nu1exix5zZb9dt'))

// ===== ĐẾM SỐ CARD TRƯỚC KHI ADD =====
TestObject allCards = new TestObject('allCards')
allCards.addProperty("xpath", ConditionType.EQUALS,
	"//h2[@data-testid='list-name'][.='Kiểm thử trello']/ancestor::div[@data-testid='list']//a[@data-testid='card-name']")

int beforeCount = WebUI.findWebElements(allCards, 10).size()
println("Số card trước khi add: " + beforeCount)

// Click đúng nút “Add a card” trong list Kiểm thử trello
TestObject addCardBtn = new TestObject('btnAddCard')
addCardBtn.addProperty('xpath', ConditionType.EQUALS,
    "//h2[@data-testid='list-name'][.='Kiểm thử trello']/ancestor::div[@data-testid='list']//button[@data-testid='list-add-card-button']")
WebUI.click(addCardBtn)

// Bấm Add
TestObject btnConfirm = new TestObject('btnConfirm')
btnConfirm.addProperty('xpath', ConditionType.EQUALS,
    "//button[@data-testid='list-add-card-button']")
WebUI.click(btnConfirm)

// ===== ĐẾM LẠI SỐ CARD SAU KHI ADD =====
int afterCount = WebUI.findWebElements(allCards, 10).size()
println("Số card sau khi add: " + afterCount)


// ===== VERIFY KẾT QUẢ =====
if (afterCount == beforeCount) {
	println("✅ PASS: Không tạo card khi KHÔNG nhập tiêu đề")
} else {
	KeywordUtil.markFailed("❌ FAIL: Hệ thống vẫn tạo card khi KHÔNG nhập tiêu đề!")
}

// Đóng browser
WebUI.closeBrowser()