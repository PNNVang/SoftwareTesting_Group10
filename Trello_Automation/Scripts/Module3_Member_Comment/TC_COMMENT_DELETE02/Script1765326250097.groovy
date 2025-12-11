import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testobject.ConditionType
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

WebUI.click(findTestObject('Object Repository/Module3_Member_Comment/Page_Capture, organize, and tackle your to-_17a2f5/a_Log in'))

WebUI.setText(findTestObject('Object Repository/Module3_Member_Comment/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'), 
    'ktv.thanhngan@gmail.com')

WebUI.click(findTestObject('Object Repository/Module3_Member_Comment/Page_Log in to continue - Log in with Atlas_6762ee/span_Continue'))

WebUI.click(findTestObject('Object Repository/Module3_Member_Comment/Page_Log in to continue - Log in with Atlas_6762ee/span_Continue'))

WebUI.setEncryptedText(findTestObject('Object Repository/Module3_Member_Comment/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'), 
    'h3YAgS6cNwIolJvH95hWIQ==')

WebUI.click(findTestObject('Object Repository/Module3_Member_Comment/Page_Log in to continue - Log in with Atlas_6762ee/span_Log in'))

WebUI.delay(2)

TestObject boardTile = new TestObject("boardTile")
boardTile.addProperty("xpath", ConditionType.EQUALS, "//span[text()='My Trello board']/ancestor::a")

WebUI.waitForElementVisible(boardTile, 10)
WebUI.click(boardTile)

String cardName = "test"

// Tạo TestObject trỏ đến thẻ <a> chứa tên card
TestObject cardLink = new TestObject('cardLink')
cardLink.addProperty('xpath', ConditionType.EQUALS, "//h2[@data-testid='list-name'][.='Today']/ancestor::div[@data-testid='list']//a[@data-testid='card-name' and text()='" + cardName + "']")

WebUI.waitForElementVisible(cardLink, 10)
WebUI.waitForElementClickable(cardLink, 10)
WebUI.click(cardLink)

String commentText = "aa"

// 1) Hover comment để hiện menu Delete
TestObject commentContainer = new TestObject("commentContainer")
commentContainer.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//div[@data-testid='comment-container'][.//p[text()='" + commentText + "']]"
)

WebUI.waitForElementVisible(commentContainer, 10)
WebUI.mouseOver(commentContainer)
WebUI.delay(1)


// 2) Click Delete (KHÔNG nằm trong comment-container)
TestObject deleteBtn = new TestObject("deleteBtn")
deleteBtn.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "(//a[normalize-space()='Delete'])[1]"
)

WebUI.waitForElementClickable(deleteBtn, 10)
WebUI.click(deleteBtn)

WebUI.delay(1)


// 3) Bấm nút đóng popover (Cancel Delete)
TestObject cancelDeleteBtn = new TestObject("cancelDeleteBtn")
cancelDeleteBtn.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//button[@aria-label='Close popover']"
)

WebUI.waitForElementClickable(cancelDeleteBtn, 10)
WebUI.click(cancelDeleteBtn)

WebUI.delay(1)


// 4) Kiểm tra comment còn tồn tại
WebUI.verifyElementPresent(commentContainer, 10)

println("✅ HUỶ XOÁ comment thành công – Comment vẫn còn hiển thị!")
