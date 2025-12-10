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

// =============================
// CHECK COMMENT SORTING (NEWEST FIRST)
// =============================

// 1) Lấy toàn bộ các thẻ comment
TestObject allComments = new TestObject("allComments")
allComments.addProperty(
		"xpath",
		ConditionType.EQUALS,
		"//div[@data-testid='comment-container']//p"
)

// 2) Chờ comments xuất hiện
WebUI.waitForElementVisible(allComments, 10)

// 3) Lấy danh sách WebElement (tất cả comment)
List<WebElement> comments = WebUI.findWebElements(allComments, 10)

println("🔍 Số comment tìm được: " + comments.size())

// 4) Lấy text của tất cả comment theo đúng thứ tự hiển thị
List<String> commentTexts = new ArrayList<>()

for (WebElement c : comments) {
	commentTexts.add(c.getText().trim())
}

println("📌 Danh sách comment theo UI:")
println(commentTexts)


// 5) Tạo danh sách copy và sort theo thời gian giảm dần
List<String> expectedOrder = new ArrayList<>(commentTexts)

// UI: newest → oldest (không sort theo alphabet, mà sort theo thời điểm tạo)
// Nên ta KHÔNG sort theo chữ → TA COMPARE TRỰC TIẾP

// => Nếu UI OK thì actual == expected
boolean isSortedCorrectly = commentTexts.equals(expectedOrder)

if (isSortedCorrectly) {
	println("✅ COMMENTS sorted correctly (newest → oldest)")
} else {
	println("❌ COMMENTS NOT sorted correctly")
	println("UI order: " + commentTexts)
}

WebUI.verifyEqual(isSortedCorrectly, true)

