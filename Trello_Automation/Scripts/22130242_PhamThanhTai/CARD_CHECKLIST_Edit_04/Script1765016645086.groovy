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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty

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

WebUI.comment('Số lượng items trước khi chỉnh sửa: ' + itemCountBefore)

// Lấy item đầu tiên trong checklist để chỉnh sửa
TestObject firstItem = createDynamicObject('(//div[@data-testid=\'check-item-name\'])[1]')

// Lấy text hiện tại của item trước khi chỉnh sửa
String oldText = WebUI.getAttribute(firstItem, 'aria-label')

WebUI.comment('Text cũ của item: ' + oldText)

// Click vào item để chỉnh sửa
WebUI.click(firstItem)

WebUI.delay(1)

// Xác định textarea edit (tìm textarea đang hiển thị)
TestObject editTextarea = createDynamicObject('//textarea[contains(@id, \'edit-checkitem-\')]')

// Xóa text hiện tại bằng cách select all và delete
WebUI.click(editTextarea)

// Select all text (Ctrl+A)
WebUI.sendKeys(editTextarea, Keys.chord(Keys.CONTROL, 'a').toString())

// Delete selected text (Backspace)
WebUI.sendKeys(editTextarea, Keys.BACK_SPACE.toString())

WebUI.delay(0.5)

WebUI.sendKeys(editTextarea, newText)

WebUI.delay(1)

// Click nút Save
TestObject saveButton = createDynamicObject('//button[@data-testid=\'check-item-edit-save-button\' and @type=\'submit\']')

WebUI.click(saveButton)

WebUI.delay(2)

// Đếm số lượng items sau khi chỉnh sửa
int itemCountAfter = WebUI.findWebElements(checklistItems, 5).size()

WebUI.comment('Số lượng items sau khi chỉnh sửa: ' + itemCountAfter)

// Verify số lượng items không thay đổi
if (itemCountBefore == itemCountAfter) {
    WebUI.comment(((('✓ PASS: Số lượng items không thay đổi (Before: ' + itemCountBefore) + ', After: ') + itemCountAfter) + 
        ')')
} else {
    WebUI.comment(((('✗ FAIL: Số lượng items đã thay đổi (Before: ' + itemCountBefore) + ', After: ') + itemCountAfter) + 
        ')')
}

// Verify text mới được hiển thị đúng
TestObject updatedItem =createDynamicObject('//div[@data-testid=\'check-item-name\' and @aria-label=\'' + newText + '\']//a[text()=\'' + newText + '\']')

try {
    boolean isPresent = WebUI.verifyElementPresent(updatedItem, 5)

    WebUI.comment(('✓ PASS: Text mới \'' + newText) + '\' đã được hiển thị đúng')
}
catch (Exception e) {
    WebUI.comment(('✗ FAIL: Text mới \'' + newText) + '\' không được hiển thị')
} 

// Verify text cũ không còn tồn tại
TestObject oldItemCheck = createDynamicObject(('//div[@data-testid=\'check-item-name\' and @aria-label=\'' + oldText) + 
    '\']')

try {
    boolean isOldPresent = WebUI.verifyElementPresent(oldItemCheck, 3)

    WebUI.comment(('✗ FAIL: Text cũ \'' + oldText) + '\' vẫn còn tồn tại')
}
catch (Exception e) {
    WebUI.comment(('✓ PASS: Text cũ \'' + oldText) + '\' đã bị thay thế')
} 

WebUI.delay(2)

// Đóng trình duyệt
WebUI.closeBrowser() // Hàm tạo dynamic object

TestObject createDynamicObject(String xpath) {
    TestObject to = new TestObject()

    to.addProperty('xpath', ConditionType.EQUALS, xpath)

    return to
}

