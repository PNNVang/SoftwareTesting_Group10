import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys
// Mở trình duyệt và đăng nhập

// Mở trình duyệt và đăng nhập
WebUI.openBrowser('')
WebUI.navigateToUrl('https://trello.com/')

WebUI.click(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Capture, organize, and tackle your to-_17a2f5/a_Resources_Buttonsstyles__Button-sc-1jwidx_3e5bb7'))

WebUI.setText(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'),
	'hana.voca@gmail.com')

WebUI.click(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))

WebUI.setEncryptedText(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'),
	'O9Oay7Ar5esKuw+uE0v1Cw==')

WebUI.click(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))

WebUI.click(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Boards  Trello/div_Upgrade_gWl3Xe9ruADN19 SzliHsDO_R43nR'))

WebUI.delay(5)

// Mở card TC1
TestObject cardTC1 = createDynamicObject('//a[@data-testid=\'card-name\' and contains(@href, \'c/0BHIJzSR/11-tc1\') and text()=\'tc1\']')
WebUI.click(cardTC1)

WebUI.delay(2)

// Đếm số lượng items trước khi chỉnh sửa
TestObject checklistItems = createDynamicObject('//li[@data-testid=\'check-item-container\']')
int itemCountBefore = WebUI.findWebElements(checklistItems, 5).size()
WebUI.comment("Số lượng items trước khi chỉnh sửa: " + itemCountBefore)

// Lấy item đầu tiên trong checklist để chỉnh sửa
TestObject firstItem = createDynamicObject('(//div[@data-testid=\'check-item-name\'])[1]')

// Lấy text hiện tại của item trước khi chỉnh sửa
String oldText = WebUI.getAttribute(firstItem, 'aria-label')
WebUI.comment("Text cũ của item: " + oldText)

// Click vào item để chỉnh sửa
WebUI.click(firstItem)

WebUI.delay(1)

// Xác định textarea edit
TestObject editTextarea = createDynamicObject('//textarea[contains(@id, \'edit-checkitem-\')]')

// Xóa text hiện tại
WebUI.click(editTextarea)
WebUI.sendKeys(editTextarea, Keys.chord(Keys.CONTROL, 'a').toString())
WebUI.sendKeys(editTextarea, Keys.BACK_SPACE.toString())

WebUI.delay(0.5)

// Nhập text mới là khoảng trắng
String newText = "                        "
WebUI.sendKeys(editTextarea, newText)
WebUI.delay(1)

// Tạo TestObject cho nút Save
TestObject saveButton = createDynamicObject('//button[@data-testid=\'check-item-edit-save-button\']')

// Click nút Save
WebUI.click(saveButton)

WebUI.delay(2)

// Kiểm tra nút Save vẫn còn hiển thị (không submit được)
boolean isSaveButtonStillVisible = WebUI.verifyElementVisible(saveButton, FailureHandling.OPTIONAL)

if (isSaveButtonStillVisible) {
	WebUI.comment("PASS: Nút Save vẫn hiển thị - Không cho phép submit với khoảng trắng")
	KeywordUtil.markPassed("Test case PASSED: Hệ thống không cho phép submit checklist item chỉ có khoảng trắng")
} else {
	WebUI.comment("FAIL: Nút Save đã biến mất - Hệ thống đã submit")
	KeywordUtil.markFailed("Test case FAILED: Hệ thống đã cho phép submit checklist item chỉ có khoảng trắng")
}

// Kiểm tra thêm: Verify textarea vẫn còn hiển thị (chế độ edit vẫn đang mở)
boolean isTextareaStillVisible = WebUI.verifyElementVisible(editTextarea, FailureHandling.OPTIONAL)

if (isTextareaStillVisible) {
	WebUI.comment("PASS: Textarea vẫn hiển thị - Chế độ edit vẫn đang mở")
} else {
	WebUI.comment("INFO: Textarea đã đóng")
}

// Đóng chế độ edit bằng cách nhấn ESC
WebUI.sendKeys(editTextarea, Keys.ESCAPE.toString())

WebUI.delay(1)

// Đóng card
WebUI.click(createDynamicObject('//button[@data-testid=\'CloseIcon\']'))

WebUI.closeBrowser()

// Hàm tạo dynamic object
TestObject createDynamicObject(String xpath) {
	TestObject obj = new TestObject()
	obj.addProperty('xpath', ConditionType.EQUALS, xpath)
	return obj
}