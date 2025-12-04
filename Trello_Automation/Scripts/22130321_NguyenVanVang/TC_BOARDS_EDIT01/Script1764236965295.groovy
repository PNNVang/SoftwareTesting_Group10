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
WebUI.delay(5)


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
	"//span[contains(@class,'GOPk_J9hMP7py5') and text()='Demo_1']")

WebUI.waitForElementClickable(boardDemo, 10)
WebUI.click(boardDemo)
WebUI.delay(1) 

//4. Cập nhật tên bảng, click vào tên bảng “Demo_1” bên góc trái trên header 
TestObject boardNameContainer = new TestObject("boardNameContainer")
boardNameContainer.addProperty("xpath", ConditionType.EQUALS,
    "//div[@data-testid='board-name-container']")

WebUI.waitForElementClickable(boardNameContainer, 10)
WebUI.click(boardNameContainer)
WebUI.delay(0.5) 

TestObject boardNameInput = new TestObject("Demo_1")
boardNameInput.addProperty("xpath", ConditionType.EQUALS,
    "//input[@data-testid='board-name-input']")

WebUI.waitForElementVisible(boardNameInput, 10)
WebUI.waitForElementClickable(boardNameInput, 10)

//5. Thực hiện đổi tên ví dụ đổi thành “Demo_11”
WebUI.sendKeys(boardNameInput, Keys.chord(Keys.CONTROL, "a"))
WebUI.sendKeys(boardNameInput, "Demo_11")
WebUI.sendKeys(boardNameInput, Keys.chord(Keys.ENTER))

WebUI.delay(1)

//6. Nhấn "..." bên góc phải trên header hiện modal
TestObject btnOverflow = new TestObject("btnOverflow")
btnOverflow.addProperty("xpath", ConditionType.EQUALS,
    "//span[@data-testid='OverflowMenuHorizontalIcon']")

WebUI.waitForElementClickable(btnOverflow, 10)
WebUI.click(btnOverflow)
WebUI.delay(0.5)


//7. Nhấn dòng "Visibility" hiện form
//8. Chọn quyền mới ví dụ “Private”
TestObject visibilityPublic = new TestObject("visibilityPublic")
visibilityPublic.addProperty("xpath", ConditionType.EQUALS,
    "//div[contains(@class,'XTd91xrSkjN3zk') and contains(text(),'Visibility: Public')]")

WebUI.waitForElementClickable(visibilityPublic, 10)
WebUI.click(visibilityPublic)
WebUI.delay(0.5)

TestObject selectVisibility = new TestObject("selectVisibility")
selectVisibility.addProperty("xpath", ConditionType.EQUALS,
    "//span[@data-testid='PrivateIcon' or @data-testid='PublicIcon']/ancestor::span[contains(@class,'g4oicM70jgqP7r')]")

WebUI.waitForElementClickable(selectVisibility, 10)
WebUI.click(selectVisibility)
WebUI.delay(0.5)


//9. Nhấn dòng "Change background" hiện form
TestObject btnChangeBackground = new TestObject("btnChangeBackground")
btnChangeBackground.addProperty("xpath", ConditionType.EQUALS,
    "//div[contains(@class,'XTd91xrSkjN3zk') and normalize-space(text())='Change background']")

WebUI.waitForElementClickable(btnChangeBackground, 10)
WebUI.click(btnChangeBackground)
WebUI.delay(0.5)


//10. Chọn ô có tên “Colors” hiện form 
TestObject btnColors = new TestObject("btnColors")
btnColors.addProperty("xpath", ConditionType.EQUALS,
    "//button[@data-testid='board-background-color-section']//span[text()='Colors']")

WebUI.waitForElementClickable(btnColors, 10)
WebUI.click(btnColors)
WebUI.delay(0.5)


//11. Chọn ô màu xanh lam ngay dòng 2 cột 1
TestObject gradientOcean = new TestObject("gradientOcean")
gradientOcean.addProperty("xpath", ConditionType.EQUALS,
    "//button[@data-testid='board-background-select-gradient-gradient-ocean']")

WebUI.waitForElementClickable(gradientOcean, 10)
WebUI.click(gradientOcean)
WebUI.delay(2)

// Click nút X 
TestObject closeIcon = new TestObject("closeIcon")
closeIcon.addProperty("xpath", ConditionType.EQUALS, "(//span[contains(@class,'_1e0c1o8l')])[1]")
WebUI.waitForElementClickable(closeIcon, 10)
WebUI.click(closeIcon)


// Kiểm tra + click icon khóa 
TestObject privateIcon = new TestObject("privateIcon")
privateIcon.addProperty("xpath", ConditionType.EQUALS, "//*[@data-testid='PrivateIcon']")
WebUI.waitForElementVisible(privateIcon, 10)
WebUI.verifyElementPresent(privateIcon, 10)
WebUI.click(privateIcon)


// Kiểm tra nút Add a list màu xanh + click 
TestObject addListBtn = new TestObject("addListBtn")
addListBtn.addProperty("xpath", ConditionType.EQUALS, "//*[@data-testid='list-composer-button']")
WebUI.waitForElementClickable(addListBtn, 10)
WebUI.verifyElementPresent(addListBtn, 10)
WebUI.click(addListBtn)

