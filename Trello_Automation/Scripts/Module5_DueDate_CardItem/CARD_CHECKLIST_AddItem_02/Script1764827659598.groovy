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

WebUI.navigateToUrl('https://trello.com')

WebUI.switchToWindowTitle('Capture, organize, and tackle your to-dos from anywhere | Trello')

WebUI.click(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Capture, organize, and tackle your to-_17a2f5/a_Resources_Buttonsstyles__Button-sc-1jwidx_3e5bb7'))

WebUI.setText(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Log in to continue - Log in with Atlas_6762ee/input_Email_username-uid1'), 
    email)

WebUI.click(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o'))

WebUI.setText(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Log in to continue - Log in with Atlas_6762ee/input_Password_password'), 
    password)

WebUI.click(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Log in to continue - Log in with Atlas_6762ee/span_Remember me_css-178ag6o_1'))

WebUI.click(findTestObject('Object Repository/22130242_PhamThanhTai/Page_Boards  Trello/div_Recently viewed_gWl3Xe9ruADN19 SzliHsDO_R43nR'))

WebUI.click(findTestObject('Object Repository/22130242_PhamThanhTai/Page_T1  Trello/a_ang lm_Ns0Sj0nEGMQ7un'))

WebUI.click(findTestObject('Object Repository/22130242_PhamThanhTai/Page_tc1 on T1  Trello/button_Delete_fix47QBt61Ou3M ybVBgfOiuWZJtD_89b05e'))

WebUI.setText(findTestObject('Object Repository/22130242_PhamThanhTai/Page_tc1 on T1  Trello/textarea_Delete_add-checkitem-6931020b84e85_d5f0b6_1'), 
    input)

WebUI.click(findTestObject('Object Repository/22130242_PhamThanhTai/Page_tc1 on T1  Trello/button_t5_eGOaKqiDQtJGcI ybVBgfOiuWZJtD oro_77adb8'))

WebUI.verifyElementText(findTestObject('22130242_PhamThanhTai/Page_tc1 on T1  Trello/div_t6_checkitem'), input)

