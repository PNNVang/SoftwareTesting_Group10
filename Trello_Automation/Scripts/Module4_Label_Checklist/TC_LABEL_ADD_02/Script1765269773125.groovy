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

// Gọi test case login
WebUI.callTestCase(findTestCase('Module4_Label_Checklist/TC_LOGIN'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/Module4_Label_Checklist/Page_Boards  Trello/Test_Board'))

WebUI.click(findTestObject('Object Repository/Module4_Label_Checklist/Page_Software Testing  Trello/Menu_Button'))

TestObject labelBtn = findTestObject('Object Repository/Module4_Label_Checklist/Page_Software Testing  Trello/button_Labels')

WebUI.waitForElementVisible(labelBtn, 10)

WebUI.executeJavaScript( "arguments[0].scrollIntoView({block:'center'});", Arrays.asList(WebUI.findWebElement(labelBtn)))

WebUI.executeJavaScript( "arguments[0].click();", Arrays.asList(WebUI.findWebElement(labelBtn)))

TestObject createNewLabelBtn = new TestObject()
createNewLabelBtn.addProperty( "xpath", ConditionType.EQUALS, "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'create') and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'label')]")

WebUI.waitForElementVisible(createNewLabelBtn, 15)
WebUI.executeJavaScript("arguments[0].scrollIntoView(true); arguments[0].click();", Arrays.asList(WebUI.findWebElement(createNewLabelBtn)))

WebUI.setText(findTestObject('Object Repository/Module4_Label_Checklist/Page_Software Testing  Trello/input_Title_edit-label-title-input'), '')

WebUI.click(findTestObject('Object Repository/Module4_Label_Checklist/Page_Software Testing  Trello/button_Select a color_Sky'))

WebUI.click(findTestObject('Object Repository/Module4_Label_Checklist/Page_Software Testing  Trello/button_Create'))

TestObject tooltipSky = new TestObject('dynamicTooltip')
tooltipSky.addProperty("xpath", ConditionType.EQUALS,
    "//*[@aria-label='Color: sky, title: none']"
)

WebUI.verifyElementPresent(tooltipSky, 10)

// Đóng browser
WebUI.closeBrowser()