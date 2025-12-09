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
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions as ChromeOptions
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
// --- CẤU HÌNH ---
String userDataDir = 'D:\\ChromeTest'

String profileName = 'Profile 2'

String pathDriver = 'C:\\Users\\ASUS\\.katalon\\packages\\KSE-10.4.1\\configuration\\resources\\drivers\\chromedriver_win32\\chromedriver.exe'

// Lấy đường dẫn Driver tự động
if (!(pathDriver)) {
    KeywordUtil.markFailedAndStop('LỖI: Chưa update WebDrivers. Vào Tools > Update WebDrivers > Chrome.')
}

System.setProperty('webdriver.chrome.driver', pathDriver)

ChromeOptions options = new ChromeOptions()

options.addArguments('--user-data-dir=' + userDataDir)

options.addArguments('--profile-directory=' + profileName)

// --- CÁC DÒNG QUAN TRỌNG ĐỂ FIX LỖI DEVTOOLS ---
options.addArguments('--no-sandbox')

options.addArguments('--disable-dev-shm-usage')

options.addArguments('--remote-allow-origins=*')

// Set port cố định để tránh xung đột (nếu vẫn lỗi, hãy thử đổi 9222 thành 9223)
options.addArguments('--remote-debugging-port=9222')

options.setExperimentalOption('useAutomationExtension', false)

options.setExperimentalOption('excludeSwitches', Collections.singletonList('enable-automation'))

WebDriver driver = null

try {
    driver = new ChromeDriver(options)

    DriverFactory.changeWebDriver(driver)
}
catch (Exception e) {
    KeywordUtil.markFailedAndStop(('LỖI KHỞI TẠO: ' + e.message) + '\n--> HÃY CHẠY LỆNH \'taskkill /F /IM chrome.exe /T\' TRONG CMD ĐỂ FIX.')
} 





String key = 'Plan'

WebUI.navigateToUrl('https://trello.com/')

WebUI.click(findTestObject('Object Repository/Module7_WorkSpaceManagement_CardFilter/item_board'))

WebUI.click(findTestObject('Object Repository/Module7_WorkSpaceManagement_CardFilter/Page_Boards  Trello/div_recent_board'))

TestObject btnClearAll = findTestObject('Object Repository/Module7_WorkSpaceManagement_CardFilter/btn_clear_filter')

if (WebUI.waitForElementVisible(btnClearAll, 2, FailureHandling.OPTIONAL)) {

	WebUI.click(btnClearAll)
}

WebUI.click(findTestObject('Object Repository/Module7_WorkSpaceManagement_CardFilter/Page_Chat_App  Trello/span_filter'))

WebUI.setText(findTestObject('Object Repository/Module7_WorkSpaceManagement_CardFilter/Page_Chat_App  Trello/input_textfield_filter'), 
    key)
WebUI.delay(1)

List<WebElement> cards = driver.findElements(By.cssSelector('li[data-testid="list-card"]'))


boolean allMatch = cards.findAll { it.isDisplayed() }.every { card ->
    card.findElement(By.cssSelector('a[data-testid="card-name"]')).getText().toLowerCase().contains(key.toLowerCase())
}

if (!allMatch) {
	print(cards)
	KeywordUtil.markFailedAndStop("Có thẻ không chứa từ khóa xuất hiện "+ cards.toString())
}

WebUI.closeBrowser()

