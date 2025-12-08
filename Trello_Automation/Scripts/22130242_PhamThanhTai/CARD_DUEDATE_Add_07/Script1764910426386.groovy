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
import com.kms.katalon.core.testobject.ConditionType as ConditionType

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

TestObject cardTC1 = createTestObject('cardTC1', '//a[@data-testid=\'card-name\' and contains(@href, \'c/qrcBaEjv/7-tc7\') and text()=\'tc7\']')

WebUI.click(cardTC1)

WebUI.delay(2)

TestObject btnDates = createTestObject('btnDates', '//button[@type=\'button\' and contains(@class, \'Qa0qCr_1_yQKR9\') and contains(text(), \'Dates\')]')

WebUI.waitForElementVisible(btnDates, 10)

WebUI.click(btnDates)

WebUI.delay(1)

String inputDate = '12/6/2025'

TestObject inputDueDateField = createTestObject('inputDueDateField', '//input[@data-testid=\'due-date-field\']')

WebUI.click(inputDueDateField)

WebUI.clearText(inputDueDateField)

WebUI.setText(inputDueDateField, inputDate)

WebUI.delay(0.5)

String inputTimeValue = '23:59 PM'

TestObject inputTimeField = createTestObject('inputTimeField', '//input[@placeholder=\'Add time\' and @aria-placeholder=\'Add time\']')

WebUI.click(inputTimeField)

WebUI.clearText(inputTimeField)

WebUI.setText(inputTimeField, inputTimeValue)

WebUI.delay(0.5)

TestObject btnSave = createTestObject('btnSave', '//button[@data-testid=\'save-date-button\' and @type=\'submit\']')

WebUI.click(btnSave)

WebUI.delay(2)

String expectedDateDisplay = convertDateFormat(inputDate)

String expectedFullText = (expectedDateDisplay + ', ') + inputTimeValue

TestObject dueDateBadge = createTestObject('dueDateBadge', '//button[@data-testid=\'due-date-badge-with-date-range-picker\']')

if (WebUI.verifyElementPresent(dueDateBadge, 5, FailureHandling.OPTIONAL)) {
    String actualText = WebUI.getText(dueDateBadge).trim()

    WebUI.comment('Actual displayed: ' + actualText)

    if (actualText.equals(expectedFullText)) {
        WebUI.comment('✓ PASS: Date and time displayed correctly - ' + actualText)
    } else {
        WebUI.comment('✗ FAIL: Date mismatch!')

        WebUI.comment('  Expected: ' + expectedFullText)

        WebUI.comment('  Actual: ' + actualText)

        WebUI.takeScreenshot()
    }
} else {
    WebUI.comment('✗ FAIL: Date badge not found')

    WebUI.takeScreenshot()
}

WebUI.closeBrowser()

def createTestObject(String id, String xpathValue) {
    TestObject to = new TestObject(id)

    to.addProperty('xpath', ConditionType.EQUALS, xpathValue)

    return to
}

def convertDateFormat(String dateStr) {
    try {
        def dateParts = dateStr.split('/')

        def month = (dateParts[0]).toInteger()

        def day = (dateParts[1]).toInteger()

        def monthNames = ['', 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']

        return ((monthNames[month]) + ' ') + day
    }
    catch (Exception e) {
        WebUI.comment('Error converting date format: ' + e.message)

        return null
    } 
}

