import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject

// --- MỞ TRÌNH DUYỆT ---
WebUI.openBrowser('')

// --- VÀO TRELLO ---
WebUI.navigateToUrl('https://trello.com/')

// --- LOGIN ---
WebUI.click(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Capture, organize, and tackle your to-_17a2f5/a_Resources_Buttonsstyles__Button-sc-1jwidx_3e5bb7'))
WebUI.setText(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'),
    'nguyenvy310804@gmail.com')
WebUI.click(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))
WebUI.setEncryptedText(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'),
    'aZi2sxaWmGoqEKxBwh9bCg==')
WebUI.click(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))
WebUI.delay(2)


// --- MỞ BOARD ---
String boardName = "My Trello board"
TestObject boardDemo = new TestObject("boardDemo")
boardDemo.addProperty("xpath", ConditionType.EQUALS,
    "//span[text()='" + boardName + "']/ancestor::a")
WebUI.waitForElementClickable(boardDemo, 15)
WebUI.click(boardDemo)
WebUI.delay(2)


// --- MỞ THẺ ---
String cardTitle = "Card Thêm mô tả chứa emoji hoặc ký tự Unicode"

TestObject cardToEdit = new TestObject("cardToEdit")
cardToEdit.addProperty("xpath", ConditionType.EQUALS,
    "//a[@data-testid='card-name' and normalize-space(text())='" + cardTitle + "']")

WebUI.waitForElementClickable(cardToEdit, 15)
WebUI.click(cardToEdit)
WebUI.delay(1)


// --- NHẤN “Add a more detailed description” ---
TestObject addDescriptionButton = new TestObject("addDescriptionButton")
addDescriptionButton.addProperty("xpath", ConditionType.EQUALS, "//button[@data-testid='description-button']")
WebUI.waitForElementClickable(addDescriptionButton, 10)
WebUI.click(addDescriptionButton)


// --- NHẬP EMOJI / UNICODE ---
TestObject editor = new TestObject("editor")
editor.addProperty("xpath", ConditionType.EQUALS, "//div[@id='ak-editor-textarea']")
WebUI.waitForElementVisible(editor, 10)

String unicodeText = "⋆𐙚❅🦌*°⋆❆.⛸️ Đây là mô tả chứa emoji 🎄✨🌙"

// Dùng JS để chèn Unicode (ổn định nhất, không lỗi encoding)
String jsSetText =
    """
    var el = document.getElementById('ak-editor-textarea');
    if(el){
        el.focus();
        el.innerText = `${unicodeText}`;
        var evt = document.createEvent('HTMLEvents');
        evt.initEvent('input', true, false);
        el.dispatchEvent(evt);
    }
    """

WebUI.executeJavaScript(jsSetText, null)
WebUI.delay(1)


// --- NHẤN SAVE ---
TestObject saveBtn = new TestObject("saveBtn")
saveBtn.addProperty("xpath", ConditionType.EQUALS, "//button[contains(text(),'Save')]")

WebUI.waitForElementClickable(saveBtn, 10)
WebUI.click(saveBtn)
WebUI.delay(2)


// --- CLOSE POPUP ---
TestObject closeBtn = new TestObject("closeBtn")
closeBtn.addProperty("xpath", ConditionType.EQUALS, "//span[@data-testid='CloseIcon']")
WebUI.waitForElementClickable(closeBtn, 10)
WebUI.click(closeBtn)
WebUI.delay(1)


// --- MỞ LẠI THẺ ĐỂ VERIFY ---
WebUI.click(cardToEdit)
WebUI.delay(1)


// --- LẤY TEXT SAU KHI LƯU ---
TestObject descriptionContent = new TestObject("descriptionContent")
descriptionContent.addProperty("xpath", ConditionType.EQUALS,
    "//div[contains(@class,'ak-renderer-document')]")

WebUI.waitForElementVisible(descriptionContent, 10)
String actualText = WebUI.getText(descriptionContent).trim()

// LOG
WebUI.comment("EXPECTED: " + unicodeText)
WebUI.comment("ACTUAL  : " + actualText)


// --- VERIFY CHƯA BỊ MẤT EMOJI / UNICODE ---
if (actualText.contains("🦌") &&
    actualText.contains("❆") &&
    actualText.contains("⋆") &&
    actualText.contains("⛸️"))
{
    KeywordUtil.markPassed("✅ PASS: Emoji/Unicode hiển thị đầy đủ và đúng format")
} else {
    KeywordUtil.markFailed("❌ FAIL: Emoji/Unicode bị mất hoặc bị lỗi encoding!\nThực tế: " + actualText)
}


// --- ĐÓNG BROWSER ---
WebUI.closeBrowser()
