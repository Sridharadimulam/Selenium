package hu.euronics.euronicsautomation.application;



import hu.euronics.euronicsautomation.common.utils.TestException;
import hu.euronics.euronicsautomation.webdriver.utils.WebDriverConstants;

public class OnlinePayment extends Application {

	private static final String PROPERTY_TEXTBOX_CARDNUMBER = "CARD_NUMBER";
	private static final String PROPERTY_TEXTBOX_CARDNAME = "CARD_NAME";
	private static final String PROPERTY_TEXTBOX_EXPIRYMONTH = "EXPIRY_MONTH";
	private static final String PROPERTY_TEXTBOX_EXPIRYYEAR = "EXPIRY_YEAR";
	private static final String PROPERTY_TEXTBOX_BANKNAME = "BANK_NAME";
	private static final String PROPERTY_TEXTBOX_CODE = "VALID_CODE";
	private static final String PROPERTY_TEXTBOX_BUYERNAME = "BUYER_NAME";
	private static final String PROPERTY_SUBMIT_OTP_BUTTON="SUBMITBUTTONFOROTP";
	private static final String PROPERTY_SUBMIT_OTP_BUTTON1="SUBMITBUTTONFOROTP1";
	
	public void execute() throws TestException {

		String cardNumber = getParameter(mapInputParameters, CARDNUMBER);
		String cardName = getParameter(mapInputParameters, CARDNAME);
		String expiryMonth = getParameter(mapInputParameters, EXPIRYMONTH);
		String expiryYear = getParameter(mapInputParameters, EXPIRYYEAR);
		String bankName = getParameter(mapInputParameters, BANKNAME);
		String validCode = getParameter(mapInputParameters, VALIDCODE);
		String buyerName = getParameter(mapInputParameters, BUYERNAME);
		String paymentType = getParameter(mapInputParameters, PAYMENTTYPE); 
		
		euronicsSeleniumHandler.waitForAjaxToLoad();
		euronicsSeleniumHandler.waitForAjaxToLoad();
		euronicsSeleniumHandler.waitForAjaxToLoad();
		
		euronicsSeleniumHandler.jsSendKeys( WebDriverConstants.Locators.ID,
				objectMap.getTestProperty(PROPERTY_TEXTBOX_CARDNUMBER),cardNumber,"enter card number");
		euronicsSeleniumHandler.jsSendKeys(WebDriverConstants.Locators.XPATH,
				objectMap.getTestProperty(PROPERTY_TEXTBOX_CARDNAME), cardName, "Enter name on Card");
		
		euronicsSeleniumHandler.jsSendKeys(WebDriverConstants.Locators.XPATH,
				objectMap.getTestProperty(PROPERTY_TEXTBOX_EXPIRYMONTH), expiryMonth, "enter expiry date");
		euronicsSeleniumHandler.jsSendKeys(WebDriverConstants.Locators.XPATH,
				objectMap.getTestProperty(PROPERTY_TEXTBOX_EXPIRYYEAR), expiryYear, "enter expiry date");
		euronicsSeleniumHandler.jsSendKeys(WebDriverConstants.Locators.XPATH,
				objectMap.getTestProperty(PROPERTY_TEXTBOX_BANKNAME), bankName, "enter bank name ");
		
		euronicsSeleniumHandler.jsSendKeys(WebDriverConstants.Locators.XPATH,
				objectMap.getTestProperty(PROPERTY_TEXTBOX_CODE), validCode, "valid code");
		euronicsSeleniumHandler.jsSendKeys(WebDriverConstants.Locators.XPATH,
				objectMap.getTestProperty(PROPERTY_TEXTBOX_BUYERNAME), buyerName, "buyer name");
		
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.XPATH,objectMap.getTestProperty(PROPERTY_SUBMIT_OTP_BUTTON), "Submitbutton Onlinepayment");
		euronicsSeleniumHandler.waitForAjaxToLoad();
		
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.XPATH,objectMap.getTestProperty(PROPERTY_SUBMIT_OTP_BUTTON1), "Submitbutton Onlinepayment");
		euronicsSeleniumHandler.waitForAjaxToLoad();
		
		//euronicsSeleniumHandler.jsSendKeys(WebDriverConstants.Locators.ID,
		//		objectMap.getTestProperty(PROPERTY_TEXTBOX_ADDRESSLINE1_ID), line1, "Address Line1");
		//euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_BUTTON_SUBMIT_ID),
		//		"Submit Delivery method");
		
	
}}
