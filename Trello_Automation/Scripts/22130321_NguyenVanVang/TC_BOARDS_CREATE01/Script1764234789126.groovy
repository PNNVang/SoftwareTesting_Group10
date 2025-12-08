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
WebUI.click(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Capture, organize, and tackle your to-_17a2f5/a_Resources_Buttonsstyles__Button-sc-1jwidx_3e5bb7'))
WebUI.setText(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'), 
    '22130321@st.hcmuaf.edu.vn')
WebUI.click(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))
WebUI.setEncryptedText(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'), 
    'by3i+AA12UcTlQm2wTyUag==')
WebUI.click(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))

//3. Nhấn nút "Create" trên thanh header
//4.  Nhấn dòng "Create board” hiện form
WebUI.click(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Boards  Trello/p_troubleshooting guide_jzNA5uVDhk7V5S'))

WebUI.click(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Boards  Trello/span_View all closed boards_g4oicM70jgqP7r'))

WebUI.click(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Boards  Trello/input__nch-textfield__input a3omnNx_j558kc _229800'))


//4. Nhập tên bảng: "Demo_1"
WebUI.setText(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Boards  Trello/input__nch-textfield__input a3omnNx_j558kc _57218e_5'), 
    'Demo_1')

//5. Chọn không gian làm việc (nếu có)
//6. Chỉnh quyền xem có thể là (Private hoặc Public)
WebUI.click(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Boards  Trello/div_Visibility_react-select-3-single-value'))

WebUI.click(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Boards  Trello/div'))

WebUI.click(findTestObject('Object Repository/22130321_NguyenVanVang/Page_Boards  Trello/button_Change to public_ybVBgfOiuWZJtD orot_ed26f4'))

// 7. Nhấn nút "Create" để tạo mới bảng
TestObject btnCreateBoard = new TestObject("btnCreateBoard")
btnCreateBoard.addProperty("xpath", ConditionType.EQUALS,
    "//button[@data-testid='create-board-submit-button' and text()='Create']")

WebUI.waitForElementClickable(btnCreateBoard, 10)
WebUI.click(btnCreateBoard)
WebUI.delay(15)

// Kiểm tra xem bảng mở có đúng tên Demo_1
TestObject boardName = new TestObject("Demo_1")
boardName.addProperty("xpath", ConditionType.EQUALS, "//*[@data-testid='board-name-display']")

WebUI.waitForElementClickable(boardName, 10)
WebUI.click(boardName)

WebUI.closeBrowser()





