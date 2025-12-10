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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://trello.com/vi')

WebUI.click(findTestObject('Object Repository/Module6_Attachment_PowerUp/Page_Ghi li, sp xp v gii quyt vic cn lm t b_870fc3/a_Ti liu_Buttonsstyles__Button-sc-1jwidxo-0 kTwZBr'))

WebUI.setText(findTestObject('Object Repository/Module6_Attachment_PowerUp/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'), 
    '22130188@st.hcmuaf.edu.vn')

WebUI.click(findTestObject('Object Repository/Module6_Attachment_PowerUp/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))

WebUI.setEncryptedText(findTestObject('Object Repository/Module6_Attachment_PowerUp/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'), 
    '74aGk+CJTjePLhCojrePTQ==')

WebUI.click(findTestObject('Object Repository/Module6_Attachment_PowerUp/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))

WebUI.click(findTestObject('Object Repository/Module6_Attachment_PowerUp/Page_Boards  Trello/div_Recently viewed_gWl3Xe9ruADN19 SzliHsDO_R43nR'))

WebUI.click(findTestObject('Object Repository/Module6_Attachment_PowerUp/Page_Dat_Ve_Xem_Phim  Trello/div_Doing_LqgHiubcZnMWmo'))

WebUI.click(findTestObject('Object Repository/Module6_Attachment_PowerUp/Page_Sequence Thanh ton H tr cc phng thc th_95d3d6/button_Attachments_zBgH6YQTy7dRbo ybVBgfOiu_fd9184'))

WebUI.click(findTestObject('Object Repository/Module6_Attachment_PowerUp/Page_Sequence Thanh ton H tr cc phng thc th_95d3d6/div_Suggestions will appear below as you ty_5d571a'))

WebUI.setText(findTestObject('Object Repository/Module6_Attachment_PowerUp/Page_Sequence Thanh ton H tr cc phng thc th_95d3d6/input_Search or paste a link_url-uid123'), 
    'mimi')

WebUI.click(findTestObject('Object Repository/Module6_Attachment_PowerUp/Page_Sequence Thanh ton H tr cc phng thc th_95d3d6/span_Cancel__v564g17y _1reo15vq _18m915vq __d3fc97'))


TestObject noResultMsg = new TestObject()
noResultMsg.addProperty("xpath", ConditionType.EQUALS,
	"//h2[contains(., 'We couldn’t find')]")

boolean isNoResult = WebUI.verifyElementPresent(noResultMsg, 3, FailureHandling.OPTIONAL)

if (!isNoResult) {
	WebUI.comment("❌ Không tìm thấy kết quả — TEST FAIL")
	WebUI.verifyEqual(false, true)  // Force fail
} else {
	WebUI.comment("✔ Có kết quả — TEST PASS")
}

WebUI.closeBrowser()

