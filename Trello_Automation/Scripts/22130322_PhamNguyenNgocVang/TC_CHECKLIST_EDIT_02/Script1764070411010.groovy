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
WebUI.callTestCase(findTestCase('22130322_PhamNguyenNgocVang/TC_LOGIN'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(5)

// Mở board và card
WebUI.click(findTestObject('Object Repository/22130322_PhamNguyenNgocVang/Page_Boards  Trello/Test_Board'))
WebUI.click(findTestObject('Object Repository/22130322_PhamNguyenNgocVang/Page_Software Testing  Trello/div_card'))

// Tạo TestObject cho checklist H3
TestObject checklistH3 = new TestObject("checklistH3")
checklistH3.addProperty("xpath", ConditionType.EQUALS, "//h3[@data-testid='checklist-title']")

WebUI.waitForElementClickable(checklistH3, 10)

// Lấy tên checklist ban đầu
TestObject checklistTitle = new TestObject("checklistTitle")
checklistTitle.addProperty("xpath", ConditionType.EQUALS, "//h3[@data-testid='checklist-title']")

WebUI.waitForElementVisible(checklistTitle, 10)
String oldName = WebUI.getText(checklistTitle)
println("Tên checklist ban đầu: " + oldName)

// Click để edit và gửi rỗng
WebUI.click(checklistH3)
WebUI.delay(1)

TestObject checklistEditInput = new TestObject("checklistEditInput")
checklistEditInput.addProperty("xpath", ConditionType.EQUALS, "//textarea[@data-testid='checklist-edit-name-input']")

WebUI.waitForElementVisible(checklistEditInput, 10)
WebUI.sendKeys(checklistEditInput, "" + Keys.chord(Keys.ENTER))
WebUI.delay(1)

// Lấy tên checklist sau khi cập nhật
String updatedName = WebUI.getText(checklistTitle)
println("Tên checklist sau khi cập nhật rỗng: " + updatedName)

// Verify kết quả
if (updatedName == oldName) {
    println("✅ Checklist tên rỗng không được chấp nhận, giữ nguyên tên cũ.")
} else if (updatedName == "") {
    println("❌ Checklist tên rỗng được chấp nhận (không mong muốn).")
} else {
    println("❌ Kết quả không mong đợi: " + updatedName)
}

// Assertion Katalon
WebUI.verifyEqual(updatedName, oldName)

// Đóng browser
WebUI.closeBrowser()
