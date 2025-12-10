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


// 1) Click nút "Write a comment…" để mở editor
TestObject writeCommentBtn = new TestObject('writeCommentBtn')
writeCommentBtn.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//button[@data-testid='card-back-new-comment-input-skeleton']"
)

WebUI.waitForElementClickable(writeCommentBtn, 15)
WebUI.click(writeCommentBtn)

// Sau bước mở editor:
TestObject commentEditor = new TestObject("commentEditor")
commentEditor.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//div[@id='ak-editor-textarea']"
)

// Wait editor visible
WebUI.waitForElementVisible(commentEditor, 10)

// Không nhập gì — simulate click only
WebUI.click(commentEditor)
WebUI.delay(1)

// SAVE BUTTON
TestObject saveBtn = new TestObject("saveBtn")
saveBtn.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//button[@data-testid='card-back-comment-save-button']"
)

// Chờ nút render ra
WebUI.waitForElementVisible(saveBtn, 10)

// KIỂM TRA NÚT SAVE CÓ DISABLED KHÔNG
String isDisabled = WebUI.getAttribute(saveBtn, "disabled")

if (isDisabled != null) {
	println("✔️ KẾT QUẢ ĐÚNG: Nút Save đang bị disabled khi comment rỗng.")
} else {
	println("❌ LỖI: Nút Save không disabled dù comment rỗng.")
	WebUI.takeScreenshot()
	assert false
}
