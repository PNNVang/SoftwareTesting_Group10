import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Keys
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

// Mở trình duyệt
WebUI.openBrowser('')

// Vào Trello	
WebUI.navigateToUrl('https://trello.com/')

// Click Login
WebUI.click(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Capture, organize, and tackle your to-_17a2f5/a_Resources_Buttonsstyles__Button-sc-1jwidx_3e5bb7'))

// Nhập email và password
WebUI.setText(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'), 'nguyenvy310804@gmail.com')
WebUI.click(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))
WebUI.setEncryptedText(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'), 'aZi2sxaWmGoqEKxBwh9bCg==')

// Click Login
WebUI.click(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))

// --- Mở bảng ---
String boardName = "My Trello board"
TestObject boardDemo = new TestObject("boardDemo")
boardDemo.addProperty("xpath", ConditionType.EQUALS,
    "//span[text()='" + boardName + "']/ancestor::a")
WebUI.waitForElementClickable(boardDemo, 10)
WebUI.click(boardDemo)
WebUI.delay(2)

// ===== STEP 1: CHỌN CARD CẦN MOVE =====
String cardName = "Card move"

TestObject cardToMove = new TestObject("cardToMove")
cardToMove.addProperty("xpath", ConditionType.EQUALS,
    "//h2[@data-testid='list-name'][.='Kiểm thử trello']/ancestor::div[@data-testid='list']//a[@data-testid='card-name' and normalize-space(text())='" + cardName + "']")

WebUI.waitForElementVisible(cardToMove, 10)
WebUI.waitForElementClickable(cardToMove, 10)
WebUI.click(cardToMove)

// ===== STEP 2: MỞ POPUP MOVE CARD =====
TestObject listNameBtn = new TestObject("listNameBtn")
listNameBtn.addProperty("xpath", ConditionType.EQUALS,
    "//button[@title='Kiểm thử trello']")

WebUI.waitForElementClickable(listNameBtn, 10)
WebUI.click(listNameBtn)

// ===== STEP 3: CHỌN LIST ĐÍCH ("To do") =====
String targetList = "To do"

TestObject selectListInput = new TestObject("selectListInput")
selectListInput.addProperty("xpath", ConditionType.EQUALS,
    "//input[@id='move-card-list-select']")

WebUI.waitForElementVisible(selectListInput, 10)
WebUI.click(selectListInput)
WebUI.setText(selectListInput, targetList)
WebUI.sendKeys(selectListInput, Keys.chord(Keys.ENTER))

// ===== STEP 4: CLICK MOVE =====
TestObject moveBtn = new TestObject("moveBtn")
moveBtn.addProperty("xpath", ConditionType.EQUALS,
    "//button[@data-testid='move-card-popover-move-button']")

WebUI.waitForElementClickable(moveBtn, 10)
WebUI.click(moveBtn)

// --- Đóng popup card bằng JS để tránh bị header che ---
TestObject closeButton = new TestObject('closeButton')
closeButton.addProperty('xpath', ConditionType.EQUALS, "//span[@data-testid='CloseIcon']")

WebUI.waitForElementVisible(closeButton, 10)

// CLICK BẰNG JAVASCRIPT (KHÔNG BỊ HEADER CHẶN)
WebUI.executeJavaScript(
	"arguments[0].click();",
	Arrays.asList(WebUI.findWebElement(closeButton, 10))
)
WebUI.delay(2)


// ===== VERIFY CARD XUẤT HIỆN TRONG LIST 'To do' =====

TestObject verifyMoved = new TestObject("verifyMoved")
verifyMoved.addProperty("xpath", ConditionType.EQUALS,
    "//h2[@data-testid='list-name']/button/span[text()='To do']" +
    "/ancestor::div[@data-testid='list']//a[@data-testid='card-name' and normalize-space(text())='" + cardName + "']"
)

WebUI.delay(2)  
boolean isPresent = WebUI.verifyElementPresent(verifyMoved, 10, FailureHandling.OPTIONAL)

if (isPresent) {
    KeywordUtil.logInfo("✅ PASS: Card '" + cardName + "' đã xuất hiện trong list 'To do'!")
} else {
    KeywordUtil.markFailed("❌ FAIL: KHÔNG tìm thấy card '" + cardName + "' trong list 'To do' sau khi move!")
}


// --- Đóng trình duyệt ---
WebUI.closeBrowser()
