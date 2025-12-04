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

//1. Truy cập trang web trello.com
WebUI.openBrowser('')

WebUI.navigateToUrl('https://trello.com/')

//2. Đăng nhập thành công vào hệ thống, và chuyển đến Dashboard
WebUI.click(findTestObject('Object Repository/Page_Capture, organize, and tackle your to-_17a2f5/a_Resources_Buttonsstyles__Button-sc-1jwidx_3e5bb7 (2)'))

WebUI.setText(findTestObject('Object Repository/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1 (2)'), 
    '22130321@st.hcmuaf.edu.vn')

WebUI.click(findTestObject('Object Repository/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o (2)'))

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password (2)'), 
    'by3i+AA12UcTlQm2wTyUag==')

WebUI.click(findTestObject('Object Repository/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))


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

//4. Nhấn vào "..." bên trên góc phải, hiện modal
TestObject btnOverflow = new TestObject("btnOverflow")
btnOverflow.addProperty("xpath", ConditionType.EQUALS,
	"//span[@data-testid='OverflowMenuHorizontalIcon']")

WebUI.waitForElementClickable(btnOverflow, 10)
WebUI.click(btnOverflow)
WebUI.delay(0.5)

//5. Nhấn vào dòng “- Close board”, hiện modal đóng bảng
TestObject btnCloseBoard = new TestObject("btnCloseBoard")
btnCloseBoard.addProperty("xpath", ConditionType.EQUALS,
	"//div[text()='Close board']")

WebUI.waitForElementClickable(btnCloseBoard, 10)
WebUI.click(btnCloseBoard)
WebUI.delay(0.5)

//6. Nhấn nút “Close"
TestObject btnConfirmClose = new TestObject("btnConfirmClose")
btnConfirmClose.addProperty("xpath", ConditionType.EQUALS,
	"//button[@data-testid='popover-close-board-confirm']")

WebUI.waitForElementClickable(btnConfirmClose, 10)
WebUI.click(btnConfirmClose)
WebUI.delay(1)

// Kiểm tra có hiện thông báo This board is closed. Reopen the board to make changes. sau khi đóng bảng Demo_11
TestObject txtClosedMsg = new TestObject("txtClosedMsg")
txtClosedMsg.addProperty("xpath", ConditionType.EQUALS,
	"//p[contains(text(),'This board is closed')]")

WebUI.verifyElementText(txtClosedMsg, "This board is closed. Reopen the board to make changes.")

