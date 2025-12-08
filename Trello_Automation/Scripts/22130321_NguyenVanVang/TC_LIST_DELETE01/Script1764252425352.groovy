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
WebUI.click(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Capture, organize, and tackle your to-_17a2f5/a_Resources_Buttonsstyles__Button-sc-1jwidx_3e5bb7'))
WebUI.setText(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'), 
    '22130321@st.hcmuaf.edu.vn')
WebUI.click(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))
WebUI.setEncryptedText(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'), 
    'by3i+AA12UcTlQm2wTyUag==')
WebUI.click(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))


//3. Nhấn tab "Boards", chọn bảng ví dụ "Demo_11" sau đó vào bảng
TestObject btnBoards = new TestObject("btnBoards")
btnBoards.addProperty("xpath", ConditionType.EQUALS,
    "//span[contains(@class,'QEGH0t6lsxm4C9') and text()='Boards']")

WebUI.waitForElementClickable(btnBoards, 10)
WebUI.click(btnBoards)
WebUI.delay(1) 

//4. Chọn board "Demo_11" ---
TestObject boardDemo = new TestObject("boardDemo")
boardDemo.addProperty("xpath", ConditionType.EQUALS,
    "//span[contains(@class,'GOPk_J9hMP7py5') and text()='Demo_11']")

WebUI.waitForElementClickable(boardDemo, 10)
WebUI.click(boardDemo)
WebUI.delay(1) 

//5. Ví dụ tại danh sách "Công việc hả", nhấn vào "..." bên trên góc phải danh sách hiện modal
TestObject btnOverflow = new TestObject("btnOverflow")
btnOverflow.addProperty("xpath", ConditionType.EQUALS,
    "//button[@data-testid='list-edit-menu-button' and @aria-label='More actions on Công việc hả']")

WebUI.waitForElementClickable(btnOverflow, 10)
WebUI.click(btnOverflow)
WebUI.delay(0.5) 

//6. Nhấn vào dòng "Archive this list" hiện thông báo bên dưới góc trái
TestObject btnArchive = new TestObject("btnArchive")
btnArchive.addProperty("xpath", ConditionType.EQUALS,
    "//span[contains(text(),'Archive this list')]")

WebUI.waitForElementClickable(btnArchive, 10)
WebUI.click(btnArchive)
WebUI.delay(0.5) 

//5. Nhấn vào "X"
TestObject btnAlertClose = new TestObject("btnAlertClose")
btnAlertClose.addProperty("xpath", ConditionType.EQUALS,
    "//div[contains(@class,'bfb0aNnc3caWwf')]//button[contains(@class,'F9EOc0_yHVJ11x')]")

if (WebUI.verifyElementPresent(btnAlertClose, 5, FailureHandling.OPTIONAL)) {
    WebUI.click(btnAlertClose)
    WebUI.delay(0.5)
}
WebUI.delay(1)

// Kiểm tra danh sách tên "Công việc hả" không còn tồn tại
TestObject checkList = new TestObject("checkList")
checkList.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//span[normalize-space(text())='Công việc hả']"
)

WebUI.waitForElementVisible(checkList, 5)
WebUI.verifyElementNotPresent(checkList, 5)


WebUI.closeBrowser()
