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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testobject.ConditionType as ConditionType

// Gọi test case login
WebUI.callTestCase(findTestCase('Module4_Label_Checklist/TC_LOGIN'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/Module4_Label_Checklist/Page_Boards  Trello/Test_Board'))

WebUI.click(findTestObject('Object Repository/Module4_Label_Checklist/Page_Software Testing  Trello/div_card'))

WebUI.click(findTestObject('Object Repository/Module4_Label_Checklist/Page_New test on Software Testing  Trello/button_Delete'))

TestObject deletePopupBtn = new TestObject("deletePopupBtn")
deletePopupBtn.addProperty( "xpath", ConditionType.EQUALS, "//button[normalize-space()='Delete checklist']")

WebUI.waitForElementVisible(deletePopupBtn, 10)

TestObject closePopupBtn = new TestObject("closePopupBtn")
closePopupBtn.addProperty( "xpath", ConditionType.EQUALS, "//button[@aria-label='Close popover']" )

WebUI.waitForElementClickable(closePopupBtn, 10)

WebUI.executeJavaScript( "arguments[0].click();", Arrays.asList(WebUI.findWebElement(closePopupBtn)))

WebUI.waitForElementNotPresent(deletePopupBtn, 10)

TestObject stillExistChecklist = new TestObject("stillExistChecklist")
stillExistChecklist.addProperty( "xpath", ConditionType.EQUALS, "//h3" )

if (WebUI.verifyElementPresent(stillExistChecklist, 5, FailureHandling.OPTIONAL)) {
	KeywordUtil.logInfo("✅ Hủy xóa thành công – Checklist vẫn còn!")
} else {
	KeywordUtil.markFailedAndStop("❌ Checklist đã bị xóa – HỦY KHÔNG THÀNH CÔNG!")
}

// Đóng browser
WebUI.closeBrowser()