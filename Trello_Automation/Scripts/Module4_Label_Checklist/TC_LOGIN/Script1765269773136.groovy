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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://trello.com/')

WebUI.click(findTestObject('Object Repository/Module4_Label_Checklist/Page_Ghi li, sp xp v gii quyt vic cn lm t b_870fc3/a_ng nhp'))

WebUI.setText(findTestObject('Object Repository/Module4_Label_Checklist/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'), '22130322@st.hcmuaf.edu.vn')

WebUI.executeJavaScript('arguments[0].click()', Arrays.asList(WebUI.findWebElement(findTestObject('Object Repository/Module4_Label_Checklist/Page_Log in to continue - Log in with Atlas_6762ee/button_Continue'))))

WebUI.setEncryptedText(findTestObject('Object Repository/Module4_Label_Checklist/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'), 'aFcaGm1Wb65TLkh1BB+eyg==')

WebUI.executeJavaScript('arguments[0].click()', Arrays.asList(WebUI.findWebElement(findTestObject('Object Repository/Module4_Label_Checklist/Page_Log in to continue - Log in with Atlas_6762ee/button_Log in'))))

//WebUI.closeBrowser()

