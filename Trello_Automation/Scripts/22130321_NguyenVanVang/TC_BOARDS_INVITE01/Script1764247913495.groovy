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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys

//1. Truy cập vào trang web trello.com
WebUI.openBrowser('')

WebUI.navigateToUrl('https://trello.com/')

//2. Đăng nhập thành công vào hệ thống với quyền quản lý bảng, và chuyển đến Dashboard
WebUI.click(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Capture, organize, and tackle your to-_17a2f5/a_Resources_Buttonsstyles__Button-sc-1jwidx_3e5bb7'))
WebUI.setText(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'), 
    '22130321@st.hcmuaf.edu.vn')
WebUI.click(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))
WebUI.setEncryptedText(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'), 
    'by3i+AA12UcTlQm2wTyUag==')
WebUI.click(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))
WebUI.delay(15)

//3. Nhấn tab "Boards", chọn bảng ví dụ "Demo_11" sau đó vào bảng
TestObject btnBoards = new TestObject("btnBoards")
btnBoards.addProperty("xpath", ConditionType.EQUALS,
	"//span[contains(@class,'QEGH0t6lsxm4C9') and text()='Boards']")

WebUI.waitForElementClickable(btnBoards, 10)
WebUI.click(btnBoards)
WebUI.delay(1) 

// --- 2. Chọn board "Demo_11" ---
TestObject boardDemo = new TestObject("boardDemo")
boardDemo.addProperty("xpath", ConditionType.EQUALS,
	"//span[contains(@class,'GOPk_J9hMP7py5') and text()='Demo_11']")

WebUI.waitForElementClickable(boardDemo, 10)
WebUI.click(boardDemo)
WebUI.delay(1) 

//4. Nhấn nút "Share" bên góc phải trên header, và hiện modal
TestObject btnShare = new TestObject("btnShare")
btnShare.addProperty("xpath", ConditionType.EQUALS,
	"//span[text()='Share']")

WebUI.waitForElementClickable(btnShare, 10)
WebUI.click(btnShare)
WebUI.delay(0.5)

//5. Nhập tên người cần mời (ví dụ: Vidugmail.com)
TestObject inputAddMembers = new TestObject("inputAddMembers")
inputAddMembers.addProperty("xpath", ConditionType.EQUALS,
	"//input[@data-testid='add-members-input']")
WebUI.waitForElementVisible(inputAddMembers, 10)
WebUI.setText(inputAddMembers, "Vidugmail.com")
WebUI.delay(1.5)

//Kiểm tra hiện đúng thông báo ở ngay dưới ô nhập tên : "Looks like that person isn't a
//Trello member yet. Add their email address to invite them."
TestObject inviteWarning = new TestObject("inviteWarning")
inviteWarning.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//div[contains(@class,'is-empty-text') and contains(.,\"Looks like that person isn't a Trello member yet.\")]"
)
WebUI.verifyElementPresent(inviteWarning, 5)

WebUI.closeBrowser()
