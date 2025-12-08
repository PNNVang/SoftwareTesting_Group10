import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

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
import com.kms.katalon.core.testobject.ConditionType


// --- Mở trình duyệt mới ---
WebUI.openBrowser('')

// --- Điều hướng đến trang chủ Trello ---
WebUI.navigateToUrl('https://trello.com/')

// --- Click vào nút "Login" trên trang chủ ---
WebUI.click(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Capture, organize, and tackle your to-_17a2f5/a_Resources_Buttonsstyles__Button-sc-1jwidx_3e5bb7'))

// --- Nhập email vào trường đăng nhập ---
WebUI.setText(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'), 
    'nguyenvy310804@gmail.com')
WebUI.click(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))

// --- Nhập mật khẩu (đã mã hóa) ---
WebUI.setEncryptedText(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'), 
    'aZi2sxaWmGoqEKxBwh9bCg==')

// --- Click nút Login/Continue để đăng nhập ---
WebUI.click(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))

// --- Mở Board cần thao tác (bảng Trello) ---
WebUI.click(findTestObject('Object Repository/22130332_NguyenNgocVy/Page_Boards  Trello/div_Settings_gWl3Xe9ruADN19 nu1exix5zZb9dt'))

// --- Chọn card muốn xóa dựa trên tên ---
String cardToDelete = "Đây là thẻ copy"

// Tạo TestObject trỏ đến thẻ <a> chứa tên card
TestObject cardLinkDelete = new TestObject('cardLinkDelete')
cardLinkDelete.addProperty('xpath', ConditionType.EQUALS, "//h2[@data-testid='list-name'][.='Kiểm thử trello']/ancestor::div[@data-testid='list']//a[@data-testid='card-name' and text()='" + cardToDelete + "']")

WebUI.waitForElementVisible(cardLinkDelete, 10)
WebUI.waitForElementClickable(cardLinkDelete, 10)
WebUI.click(cardLinkDelete)

// --- Click "Actions" (mở menu) ---
TestObject actionsButtonDel = new TestObject('actionsButtonDel')
actionsButtonDel.addProperty('xpath', ConditionType.EQUALS, "//button[@data-testid='card-back-actions-button']")
WebUI.click(actionsButtonDel)

// --- Click "Archive card" ---
TestObject archiveButton = new TestObject('archiveButton')
archiveButton.addProperty('xpath', ConditionType.EQUALS, "//button[@data-testid='card-back-archive-button']")
WebUI.click(archiveButton)

// --- Click "Delete card" ---
TestObject deleteCardButton = new TestObject('deleteCardButton')
deleteCardButton.addProperty('xpath', ConditionType.EQUALS, "//button[@data-testid='card-back-delete-card-button']")
WebUI.click(deleteCardButton)

// --- Xác nhận Delete trong popup ---
TestObject confirmDeleteButton = new TestObject('confirmDeleteButton')
confirmDeleteButton.addProperty('xpath', ConditionType.EQUALS, "//button[@data-testid='popover-confirm-button']")
WebUI.click(confirmDeleteButton)

// ===== VERIFY CARD ĐÃ BỊ XÓA =====

TestObject checkDeletedCard = new TestObject("checkDeletedCard")
checkDeletedCard.addProperty("xpath", ConditionType.EQUALS,
	"//ol[@data-testid='list-cards']//a[@data-testid='card-name' and normalize-space(text())='" + cardToDelete + "']")

boolean isStillExist = WebUI.verifyElementPresent(checkDeletedCard, 5, FailureHandling.OPTIONAL)

if (!isStillExist) {
	println("✅ Card '" + cardToDelete + "' đã bị XÓA thành công khỏi list-cards!")
} else {
	KeywordUtil.markFailed("❌ Card '" + cardToDelete + "' VẪN còn tồn tại – XÓA THẤT BẠI!")
}


// --- Đóng trình duyệt sau khi thao tác xong ---
WebUI.closeBrowser()
