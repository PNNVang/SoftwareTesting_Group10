import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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
WebUI.click(findTestObject('Object Repository/Module2_Card_Description/Page_Capture, organize, and tackle your to-_17a2f5/a_Resources_Buttonsstyles__Button-sc-1jwidx_3e5bb7'))

// Nhập email và password
WebUI.setText(findTestObject('Object Repository/Module2_Card_Description/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'), 'nguyenvy310804@gmail.com')
WebUI.click(findTestObject('Object Repository/Module2_Card_Description/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))
WebUI.setEncryptedText(findTestObject('Object Repository/Module2_Card_Description/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'), 'aZi2sxaWmGoqEKxBwh9bCg==')

// Click Login
WebUI.click(findTestObject('Object Repository/Module2_Card_Description/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))

// MỞ BOARD DYNAMIC THEO TÊN
String boardName = "My Trello board"

TestObject boardDemo = new TestObject("boardDemo")
boardDemo.addProperty("xpath", ConditionType.EQUALS,
	"//span[text()='" + boardName + "']/ancestor::a")

WebUI.waitForElementClickable(boardDemo, 10)
WebUI.click(boardDemo)
WebUI.delay(2)


// Click đúng nút “Add a card” trong list Kiểm thử trello
TestObject addCardBtn = new TestObject('btnAddCard')
addCardBtn.addProperty("xpath", ConditionType.EQUALS,
	"//button[@aria-label='Add a card in Kiểm thử trello']")

WebUI.waitForElementClickable(addCardBtn, 10)
WebUI.click(addCardBtn)


// Nhập tên card mới
TestObject inputCard = new TestObject('inputCard')
inputCard.addProperty('xpath', ConditionType.EQUALS,
    "//textarea[@data-testid='list-card-composer-textarea']")
WebUI.setText(inputCard, "Card test tự động")

// Bấm Add
TestObject btnConfirm = new TestObject('btnConfirm')
btnConfirm.addProperty('xpath', ConditionType.EQUALS,
    "//button[@data-testid='list-add-card-button']")
WebUI.click(btnConfirm)
// CHỜ Trello lưu
WebUI.delay(3)

// ===== VERIFY CARD TRONG LIST-CARDS =====

TestObject checkCard = new TestObject("checkCard")
checkCard.addProperty("xpath", ConditionType.EQUALS,
	"//ol[@data-testid='list-cards']//a[@data-testid='card-name' and normalize-space(text())='Card test tự động']")

boolean isExist = WebUI.verifyElementPresent(checkCard, 10, FailureHandling.OPTIONAL)

if (isExist) {
	println("✅ Card 'Card test tự động' đã tồn tại trong list-cards!")
} else {
	KeywordUtil.markFailed("❌ KHÔNG tìm thấy Card 'Card test tự động' trong list-cards!")
}

// Đóng browser
WebUI.closeBrowser()
