import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.testobject.ConditionType as ConditionType
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

TestObject boardTile = new TestObject('boardTile')

boardTile.addProperty('xpath', ConditionType.EQUALS, '//span[text()=\'My Trello board\']/ancestor::a')

WebUI.waitForElementVisible(boardTile, 10)

WebUI.click(boardTile)

String cardName = 'test'

// Tạo TestObject trỏ đến thẻ <a> chứa tên card
TestObject cardLink = new TestObject('cardLink')

cardLink.addProperty('xpath', ConditionType.EQUALS, ('//h2[@data-testid=\'list-name\'][.=\'Today\']/ancestor::div[@data-testid=\'list\']//a[@data-testid=\'card-name\' and text()=\'' + 
    cardName) + '\']')

WebUI.waitForElementVisible(cardLink, 10)

WebUI.waitForElementClickable(cardLink, 10)

WebUI.click(cardLink)

// 1) Click Add
TestObject addButton = new TestObject('addButton')

addButton.addProperty('xpath', ConditionType.EQUALS, '//button[@data-testid=\'card-back-add-to-card-button\']')

WebUI.waitForElementClickable(addButton, 10)

WebUI.click(addButton)

// 2) Chọn "Assign members"
TestObject assignMembers = new TestObject('assignMembers')

assignMembers.addProperty('xpath', ConditionType.EQUALS, '//span[text()=\'Assign members\']')

WebUI.waitForElementClickable(assignMembers, 10)

WebUI.click(assignMembers)

TestObject clickMembers = new TestObject('clickMembers')

clickMembers.addProperty('xpath', ConditionType.EQUALS, '//*[@id=\'card-back-add-members\']')

WebUI.waitForElementClickable(clickMembers, 10)

WebUI.click(clickMembers)

String memberName = 'Lê Thị Thanh Ngân'

TestObject chooseMember = new TestObject('chooseMember')

chooseMember.addProperty('xpath', ConditionType.EQUALS, ('//button[@data-testid=\'choose-member-item-add-member-button\']//div[text()=\'' + 
    memberName) + '\']')

WebUI.waitForElementVisible(chooseMember, 10)

WebUI.waitForElementClickable(chooseMember, 10)

WebUI.click(chooseMember)

// 4) Đóng popover
TestObject closePopover = new TestObject('closePopover')
closePopover.addProperty('xpath', ConditionType.EQUALS, "//button[@aria-label='Close popover']")

WebUI.waitForElementClickable(closePopover, 10)
WebUI.click(closePopover)

// KHÔNG ĐƯỢC CLICK chooseMember LẦN 2
// WebUI.click(chooseMember)  <-- XOÁ DÒNG NÀY

// ===============================
// BẮT SỰ KIỆN THÊM THÀNH VIÊN
// ===============================

String memberTitle = "Lê Thị Thanh Ngân (leththanhngan4)"

TestObject avatarAdded = new TestObject("avatarAdded")
avatarAdded.addProperty("xpath", ConditionType.EQUALS,
    "//button[@data-testid='card-back-member-avatar' and @title='" + memberTitle + "']")

boolean isAdded = WebUI.waitForElementVisible(avatarAdded, 10)

if (isAdded) {
    WebUI.comment("✔ Thành viên '" + memberTitle + "' đã xuất hiện trong danh sách members.")
} else {
    WebUI.comment("✖ Không tìm thấy avatar sau khi thêm thành viên!")
    WebUI.takeScreenshot()
    WebUI.verifyMatch("Found", "NotFound", false)
}
