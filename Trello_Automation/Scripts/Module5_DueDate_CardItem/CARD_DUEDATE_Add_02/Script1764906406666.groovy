import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
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

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
WebUI.openBrowser('')

WebUI.navigateToUrl('https://trello.com/')

WebUI.click(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Capture, organize, and tackle your to-_17a2f5/a_Resources_Buttonsstyles__Button-sc-1jwidx_3e5bb7'))

WebUI.setText(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'),
	'hana.voca@gmail.com')

WebUI.click(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))

WebUI.setEncryptedText(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'),
	'O9Oay7Ar5esKuw+uE0v1Cw==')

WebUI.click(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))

WebUI.click(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Boards  Trello/div_Upgrade_gWl3Xe9ruADN19 SzliHsDO_R43nR'))


def createTestObject(String id, String xpathValue) {
	TestObject to = new TestObject(id)
	to.addProperty("xpath", ConditionType.EQUALS, xpathValue)
	return to
}



TestObject cardTC1 = createTestObject("cardTC1", "//a[@data-testid='card-name' and contains(@href, 'c/o6T9eKsg/2-tc2') and text()='tc2']")
WebUI.click(cardTC1)
WebUI.delay(2)

TestObject btnDates = createTestObject("btnDates", "//button[@type='button' and contains(@class, 'Qa0qCr_1_yQKR9') and contains(text(), 'Dates')]")
WebUI.waitForElementVisible(btnDates, 10)
WebUI.click(btnDates)

WebUI.delay(1)

String inputDate = "12/6/2020"
TestObject inputDueDateField = createTestObject("inputDueDateField", "//input[@data-testid='due-date-field']")
WebUI.click(inputDueDateField)
WebUI.clearText(inputDueDateField)
WebUI.setText(inputDueDateField, inputDate)

WebUI.delay(0.5)

String inputTimeValue = "11:03 PM"
TestObject inputTimeField = createTestObject("inputTimeField", "//input[@placeholder='Add time' and @aria-placeholder='Add time']")
WebUI.click(inputTimeField)
WebUI.clearText(inputTimeField)
WebUI.setText(inputTimeField, inputTimeValue)

WebUI.delay(0.5)

TestObject btnSave = createTestObject("btnSave", "//button[@data-testid='save-date-button' and @type='submit']")
WebUI.click(btnSave)

WebUI.delay(2)

def convertDateFormat(String dateStr) {
	try {
		def dateParts = dateStr.split('/')
		def month = dateParts[0].toInteger()
		def day = dateParts[1].toInteger()
		def monthNames = ['', 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
		return monthNames[month] + ' ' + day
	} catch (Exception e) {
		return null
	}
}

def isOverdue(String dateStr, String timeStr) {
	try {
		def dateParts = dateStr.split('/')
		def month = dateParts[0].toInteger()
		def day = dateParts[1].toInteger()
		def year = dateParts[2].toInteger()
		
		def calendar = Calendar.getInstance()
		calendar.set(year, month - 1, day)
		
		def timeParts = timeStr.replaceAll('[^0-9:]', '').split(':')
		def hour = timeParts[0].toInteger()
		def minute = timeParts[1].toInteger()
		
		if (timeStr.toUpperCase().contains('PM') && hour != 12) {
			hour += 12
		} else if (timeStr.toUpperCase().contains('AM') && hour == 12) {
			hour = 0
		}
		calendar.set(Calendar.HOUR_OF_DAY, hour)
		calendar.set(Calendar.MINUTE, minute)
		
		def now = Calendar.getInstance()
		return calendar.before(now)
	} catch (Exception e) {
		return false
	}
}

String expectedDateDisplay = convertDateFormat(inputDate)
String expectedFullText = expectedDateDisplay + ", " + inputTimeValue
boolean shouldBeOverdue = isOverdue(inputDate, inputTimeValue)

TestObject dueDateBadge = createTestObject("dueDateBadge", "//button[@data-testid='due-date-badge-with-date-range-picker']")

if (WebUI.verifyElementPresent(dueDateBadge, 5, FailureHandling.OPTIONAL)) {
	String fullText = WebUI.getText(dueDateBadge).trim()
	
	boolean isOverdueDisplayed = fullText.contains("Overdue")
	String dateTimeText = fullText.replace("Overdue", "").trim()
	
	boolean dateTimeMatch = dateTimeText.equals(expectedFullText)
	boolean overdueMatch = (shouldBeOverdue == isOverdueDisplayed)
	
	if (dateTimeMatch && overdueMatch) {
		WebUI.comment("PASS: " + fullText)
	} else {
				throw new Exception("Test case failed: Date/Time or Overdue status mismatch")
	}
} else {
	WebUI.comment("FAIL: Date badge not found")
	WebUI.takeScreenshot()
	throw new Exception("Test case failed: Date badge not found")
}

WebUI.closeBrowser()

