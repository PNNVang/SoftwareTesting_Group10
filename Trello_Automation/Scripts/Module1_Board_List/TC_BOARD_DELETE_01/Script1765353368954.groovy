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

WebUI.openBrowser('')

//1. Truy cập vào trang web trello.com
WebUI.navigateToUrl('https://trello.com/')

//2. Đăng nhập thành công vào hệ thống, và chuyển đến Dashboard
WebUI.click(findTestObject('Object Repository/Module1_Board_List/Page_Capture, organize, and tackle your to-_17a2f5/a_Resources_Buttonsstyles__Button-sc-1jwidx_3e5bb7'))
WebUI.setText(findTestObject('Object Repository/Module1_Board_List/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'), 
    '22130321@st.hcmuaf.edu.vn')
WebUI.click(findTestObject('Object Repository/Module1_Board_List/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))
WebUI.setEncryptedText(findTestObject('Object Repository/Module1_Board_List/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'), 
    'by3i+AA12UcTlQm2wTyUag==')
WebUI.click(findTestObject('Object Repository/Module1_Board_List/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))


// 3. View all closed boards
TestObject btnViewClosed = new TestObject("btnViewClosed")
btnViewClosed.addProperty("xpath", ConditionType.EQUALS,
	"//button[contains(text(), 'View all closed boards')]")

WebUI.waitForElementClickable(btnViewClosed, 10)
WebUI.click(btnViewClosed)
WebUI.delay(1)


// 4. Click nút Delete của board đúng tên Demo_11
TestObject btnDeleteBoard = new TestObject("btnDeleteBoard")
btnDeleteBoard.addProperty("xpath", ConditionType.EQUALS,
    "//li[.//a[text()='Demo_11']]//button[@data-testid='close-board-delete-board-button']")

WebUI.waitForElementClickable(btnDeleteBoard, 10)
WebUI.click(btnDeleteBoard)
WebUI.delay(0.5)


// 5. Confirm Delete (xóa vĩnh viễn)
TestObject btnDeleteConfirm = new TestObject("btnDeleteConfirm")
btnDeleteConfirm.addProperty("xpath", ConditionType.EQUALS,
	"//button[@data-testid='close-board-delete-board-confirm-button']")
WebUI.waitForElementClickable(btnDeleteConfirm, 10)
WebUI.click(btnDeleteConfirm)
WebUI.delay(1)

// Kiểm tra không còn bảng tên Demo_11 trong danh sách bảng đã đóng
TestObject verifyNoDemo11 = new TestObject("verifyNoDemo11")
verifyNoDemo11.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//a[normalize-space(text())='Demo_11']"
)

WebUI.verifyElementNotPresent(verifyNoDemo11, 5)

WebUI.closeBrowser()
