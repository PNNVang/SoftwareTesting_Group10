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

// ----- OPEN MEMBERS POPUP VIA ADD → ASSIGN MEMBERS -----
TestObject addButton = new TestObject('addButton')
addButton.addProperty('xpath', ConditionType.EQUALS, "//button[@data-testid='card-back-add-to-card-button']")
WebUI.waitForElementClickable(addButton, 10)
WebUI.click(addButton)

TestObject assignMembers = new TestObject('assignMembers')
assignMembers.addProperty('xpath', ConditionType.EQUALS, "//span[text()='Assign members']")
WebUI.waitForElementClickable(assignMembers, 10)
WebUI.click(assignMembers)

WebUI.delay(1) // chờ popup render

// ----- SEARCH MEMBERS -----
String memberName = "Lê Thị Thanh Ngân"

TestObject searchField = new TestObject("searchField")
searchField.addProperty("xpath", ConditionType.EQUALS, "//input[@placeholder='Search members']")

WebUI.waitForElementVisible(searchField, 10)
WebUI.setText(searchField, memberName)
WebUI.delay(1)

// ----- CLICK RESULT -----
TestObject searchResult = new TestObject("searchResult")
searchResult.addProperty("xpath", ConditionType.EQUALS,
        "//button[@data-testid='choose-member-item-add-member-button']//div[text()='" + memberName + "']")

WebUI.waitForElementClickable(searchResult, 10)
WebUI.click(searchResult)

// ----- VERIFY CLICK SUCCESS -----
WebUI.delay(1)
WebUI.verifyTextPresent(memberName, false)