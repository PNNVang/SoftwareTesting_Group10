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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://trello.com/vi')

WebUI.click(findTestObject('Object Repository/22130188_DangAnhNguyen/Page_Ghi li, sp xp v gii quyt vic cn lm t b_870fc3/a_Ti liu_Buttonsstyles__Button-sc-1jwidxo-0 kTwZBr'))

WebUI.setText(findTestObject('Object Repository/22130188_DangAnhNguyen/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'), 
    '22130188@st.hcmuaf.edu.vn')

WebUI.click(findTestObject('Object Repository/22130188_DangAnhNguyen/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))

WebUI.setEncryptedText(findTestObject('Object Repository/22130188_DangAnhNguyen/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'), 
    '74aGk+CJTjePLhCojrePTQ==')

WebUI.click(findTestObject('Object Repository/22130188_DangAnhNguyen/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))

WebUI.click(findTestObject('Object Repository/22130188_DangAnhNguyen/Page_Boards  Trello/div_Upgrade_gWl3Xe9ruADN19 SzliHsDO_R43nR'))

WebUI.click(findTestObject('Object Repository/22130188_DangAnhNguyen/Page_Nhm Cy Siu Tc  Trello/span_Switch boards__1e0c1o8l _1o9zidpf _vyf_4a6f64'))

// WebUI.click(findTestObject('Object Repository/22130188_DangAnhNguyen/Page_Nhm Cy Siu Tc  Trello/span_Report__1e0c1o8l _vchhusvi _1o9zidpf __11dc2a'))

WebUI.click(findTestObject('Object Repository/22130188_DangAnhNguyen/Page_Nhm Cy Siu Tc  Trello/button_Settings_ybVBgfOiuWZJtD orotyyeYQx_t_0269c7'))

WebUI.click(findTestObject('Object Repository/22130188_DangAnhNguyen/Page_Nhm Cy Siu Tc  Trello/a_Categories_WGY9hGsS5muj12 lfyh85m1qpLXvy'))

WebUI.click(findTestObject('Object Repository/22130188_DangAnhNguyen/Page_Nhm Cy Siu Tc  Trello/a_Analytics  reporting_WGY9hGsS5muj12 lfyh8_1b2992'))

WebUI.click(findTestObject('Object Repository/22130188_DangAnhNguyen/Page_Nhm Cy Siu Tc  Trello/a_Automation_WGY9hGsS5muj12 lfyh85m1qpLXvy'))

WebUI.click(findTestObject('Object Repository/22130188_DangAnhNguyen/Page_Nhm Cy Siu Tc  Trello/a_Board utilities_WGY9hGsS5muj12 lfyh85m1qpLXvy'))

WebUI.click(findTestObject('Object Repository/22130188_DangAnhNguyen/Page_Nhm Cy Siu Tc  Trello/a_Communication  collaboration_WGY9hGsS5muj_c18fa6'))

WebUI.closeBrowser()

