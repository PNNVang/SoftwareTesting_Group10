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
import com.kms.katalon.core.util.KeywordUtil

// Gọi test case login
WebUI.callTestCase(findTestCase('22130322_PhamNguyenNgocVang/TC_LOGIN'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/22130322_PhamNguyenNgocVang/Page_Boards  Trello/Test_Board'))

WebUI.click(findTestObject('Object Repository/22130322_PhamNguyenNgocVang/Page_Software Testing  Trello/div_card'))

WebUI.click(findTestObject('Object Repository/22130322_PhamNguyenNgocVang/Page_New test on Software Testing  Trello/button_Add an item_After_Have_Item'))

// Tạo TestObject động
TestObject dynamicTextarea = new TestObject('dynamicTextarea')
dynamicTextarea.addProperty('xpath', ConditionType.EQUALS, "//textarea[contains(@placeholder, 'Add an item')]")

// Đếm số item trước khi add
TestObject allItems = new TestObject("allChecklistItems")
allItems.addProperty("xpath", ConditionType.EQUALS, "//span[contains(@class, 'checklist-item-details-text')]")

int beforeCount = WebUI.findWebElements(allItems, 5).size()
WebUI.comment("Số lượng item trước khi add: " + beforeCount)

// Verify element present
if (WebUI.verifyElementPresent(dynamicTextarea, 10, FailureHandling.OPTIONAL)) {
	WebUI.setText(dynamicTextarea, '')
} else {
	KeywordUtil.markFailedAndStop("Không tìm thấy textarea theo XPATH dynamic.")
}

WebUI.click(findTestObject('Object Repository/22130322_PhamNguyenNgocVang/Page_New test on Software Testing  Trello/button_Add_Item_Checklist'))

WebUI.delay(2)

// Đếm lại số item
int afterCount = WebUI.findWebElements(allItems, 5).size()
WebUI.comment("Số lượng item sau khi add: " + afterCount)

// Verify không có item mới được thêm vào
WebUI.verifyEqual(afterCount, beforeCount)

// Đóng browser
WebUI.closeBrowser()