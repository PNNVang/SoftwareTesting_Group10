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

WebUI.click(findTestObject('Object Repository/22130179_LeThiThanhNgan/Page_Capture, organize, and tackle your to-_17a2f5/a_Log in'))

WebUI.setText(findTestObject('Object Repository/22130179_LeThiThanhNgan/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'), 
    'ktv.thanhngan@gmail.com')

WebUI.click(findTestObject('Object Repository/22130179_LeThiThanhNgan/Page_Log in to continue - Log in with Atlas_6762ee/span_Continue'))

WebUI.click(findTestObject('Object Repository/22130179_LeThiThanhNgan/Page_Log in to continue - Log in with Atlas_6762ee/span_Continue'))

WebUI.setEncryptedText(findTestObject('Object Repository/22130179_LeThiThanhNgan/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'), 
    'h3YAgS6cNwIolJvH95hWIQ==')

WebUI.click(findTestObject('Object Repository/22130179_LeThiThanhNgan/Page_Log in to continue - Log in with Atlas_6762ee/span_Log in'))

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

// 2) Click vào vùng editor ProseMirror
TestObject commentBox = new TestObject("commentBox")
commentBox.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//div[contains(@class,'ProseMirror') and @role='textbox']"
)

WebUI.waitForElementVisible(commentBox, 20)
WebUI.waitForElementClickable(commentBox, 20)
WebUI.click(commentBox)

// Gõ nội dung bình luận
WebUI.sendKeys(commentBox, "This is an automated comment.")

// 3) Nhấn nút đính kèm
TestObject attachButton = new TestObject("attachButton")
attachButton.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//button[@aria-label='Attach and insert link']"
)

WebUI.waitForElementClickable(attachButton, 15)
WebUI.click(attachButton)

// 4) Upload file
String filePath = "D://Kiểm thử"

TestObject fileInput = new TestObject("fileInput")
fileInput.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//input[@id='filepicker' and @data-testid='editor-insert-attachment-file-input']"
)

WebUI.uploadFile(fileInput, filePath)

// Chờ upload hoàn tất
WebUI.delay(4)

// 5) Nhấn Save
TestObject saveBtn = new TestObject("saveBtn")
saveBtn.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//button[@data-testid='card-back-comment-save-button' and not(@disabled)]"
)

WebUI.waitForElementClickable(saveBtn, 20)
WebUI.click(saveBtn)

// 6) Verify comment xuất hiện
TestObject verifyComment = new TestObject("verifyComment")
verifyComment.addProperty("xpath", ConditionType.EQUALS,
	"//p[contains(text(),'This is an automated comment.')]")

WebUI.verifyElementVisible(verifyComment)
WebUI.comment("✔ Comment + File upload thành công!")