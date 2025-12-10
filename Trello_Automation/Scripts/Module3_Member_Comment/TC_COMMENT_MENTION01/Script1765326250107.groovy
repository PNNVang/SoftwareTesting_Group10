import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import org.openqa.selenium.WebElement
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

// ===============================
// MỞ Ô COMMENT
// ===============================
TestObject openCommentBox = new TestObject("openCommentBox")
openCommentBox.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//button[@data-testid='card-back-new-comment-input-skeleton']"
)

WebUI.waitForElementClickable(openCommentBox, 10)
WebUI.click(openCommentBox)
WebUI.delay(1)


// ===============================
// TRỎ VÀO INPUT THẬT (ProseMirror <p>)
// ===============================
TestObject commentInput = new TestObject("commentInput")
commentInput.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//div[contains(@class,'ProseMirror')]//p"
)

WebUI.waitForElementVisible(commentInput, 10)
WebUI.click(commentInput)


// ===============================
// GÕ @ để mở danh sách gợi ý MENTION
// ===============================
WebUI.sendKeys(commentInput, "@")
WebUI.delay(2)


// ===============================
// CHỌN NGƯỜI DÙNG TRONG DANH SÁCH
// ===============================
String memberName = "Lê Thị Thanh Ngân"   // đổi tên theo đúng user trên Trello

TestObject mentionOption = new TestObject("mentionOption")
mentionOption.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//div[@role='option']//span[contains(text(),'" + memberName + "')]"
)

WebUI.waitForElementClickable(mentionOption, 10)
WebUI.click(mentionOption)

WebUI.delay(1)


// ===============================
// NHẤN ENTER ĐỂ GỬI COMMENT
// ===============================
WebUI.sendKeys(commentInput, Keys.chord(Keys.ENTER))
WebUI.delay(2)


// ===============================
// VERIFY COMMENT ĐÃ CÓ MENTION
// ===============================
TestObject mentionDisplayed = new TestObject("mentionDisplayed")
mentionDisplayed.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//div[@data-testid='comment-container']//span[contains(text(),'" + memberName + "')]"
)

WebUI.waitForElementVisible(mentionDisplayed, 10)
println("✅ Mention @" + memberName + " THÀNH CÔNG!")
