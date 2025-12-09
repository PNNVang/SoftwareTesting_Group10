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

// Mở board và card
WebUI.click(findTestObject('Object Repository/Module4_Label_Checklist/Page_Boards  Trello/Test_Board'))

WebUI.click(findTestObject('Object Repository/Module4_Label_Checklist/Page_Software Testing  Trello/div_card'))

TestObject checklistH3 = new TestObject("checklistH3")
checklistH3.addProperty("xpath", ConditionType.EQUALS, "//h3[@data-testid='checklist-title']")

WebUI.waitForElementClickable(checklistH3, 10)
WebUI.click(checklistH3)
WebUI.delay(3)

// Tạo TestObject cho input dynamic
TestObject checklistEditInput = new TestObject("checklistEditInput")
checklistEditInput.addProperty("xpath", ConditionType.EQUALS, "//textarea[@data-testid='checklist-edit-name-input']")

// Chờ element xuất hiện
WebUI.waitForElementVisible(checklistEditInput, 15)
WebUI.sendKeys(checklistEditInput, "Danh Mục Cập Nhật" + Keys.chord(Keys.ENTER))

WebUI.delay(1)

// Verify tên checklist sau khi cập nhật
String newName = "Danh Mục Cập Nhật"
TestObject checklistTitle = new TestObject("updatedChecklistTitle")
checklistTitle.addProperty("xpath", ConditionType.EQUALS, "//h3[@data-testid='checklist-title']")

WebUI.waitForElementVisible(checklistTitle, 10)
String actualName = WebUI.getText(checklistTitle)

WebUI.verifyEqual(actualName, newName)

// Đóng browser
WebUI.closeBrowser()
