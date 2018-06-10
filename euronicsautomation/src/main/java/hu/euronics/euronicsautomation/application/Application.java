package hu.euronics.euronicsautomation.application;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import hu.euronics.euronicsautomation.common.utils.PropertyLoader;
import hu.euronics.euronicsautomation.common.utils.TestException;

public class Application {
	private static final Logger logger = Logger.getLogger(Application.class.getSimpleName());

	protected static final String BROWSER = "BROWSER";
	protected static final String CAPABILITIES = "CAPABILITIES";
	protected static final String DRIVERSPATH = "DRIVERSPATH";
	protected static final String URL = "URL";
	protected static final String PRODUCTID = "PRODUCTID";
	protected static final String EXPECTEDMESSAGE = "EXPECTEDMESSAGE";
	protected static final String PRODUCTNAME = "PRODUCTNAME";
	protected static final String UPDATEQTY = "UPDATEQTY";
	protected static final String ADDFROMCUSTOMERBOUGHTSECTION = "ADDFROMCUSTOMERBOUGHTSECTION";
	protected static final String ADDFROMLASTMINUTETREATSECTION = "ADDFROMLASTMINUTETREATSECTION";
	protected static final String CONFIRMATIONMESSAGE = "CONFIRMATIONMESSAGE";
	protected static final String ADDFREESAMPLE = "ADDFREESAMPLE";
	protected static final String ADDGIFT = "ADDGIFT";
	protected static final String GIFTBOX = "GIFTBOX";
	protected static final String GIFTMESSAGE = "GIFTMESSAGE";
	protected static final String EMAIL = "EMAIL";
	protected static final String ACCOUNT = "ACCOUNT";
	protected static final String ADDRESSLINE1 = "ADDRESSLINE1";
	protected static final String ADDRESSLINE2 = "ADDRESSLINE2";
	protected static final String TELEPHONE = "TELEPHONE";
	protected static final String DELIVERYDATE = "DELIVERYDATE";
	protected static final String TITLE = "TITLE";
	protected static final String FIRSTNAME = "FIRSTNAME";
	protected static final String LASTNAME = "LASTNAME";
	protected static final String CITY = "CITY";
	protected static final String STATE = "STATE";
	protected static final String COUNTRY = "COUNTRY";
	protected static final String POSTCODE = "POSTCODE";
	protected static final String PAYMENTTYPE = "PAYMENTTYPE";
	protected static final String CARDTYPE = "CARDTYPE";
	protected static final String CARDNUMBER = "CARDNUMBER";
	protected static final String EXPIRYMONTH = "EXPIRYMONTH";
	protected static final String EXPIRYYEAR = "EXPIRYYEAR";
	protected static final String SECURITYCODE = "SECURITYCODE";
	protected static final String ADDRESS = "ADDRESS";
	protected static final String THANKYOUMESSAGE = "THANKYOUMESSAGE";
	protected static final String ORDERNUMBERMESSAGE = "ORDERNUMBERMESSAGE";
	protected static final String PAYPALNAME = "PAYPALNAME";
	protected static final String PAYPALADDRESS = "PAYPALADDRESS";
	protected static final String PAYPALCOUNTRY = "PAYPALCOUNTRY";
	protected static final String UPDATEGRT = "UPDATEGRT";
	protected static final String DELETECART = "DELETECART";
	protected static final String GRT = "GRT";
	protected static final String XPATH_ROOT = "/html/body";
	protected static final String DELIVERYTYPE = "DELIVERYTYPE";
	protected static final String SHOP = "SHOP";
	protected static final String PPP = "PPP";
	protected static final String COURIER = "COURIER";
	
	protected static final String BUYERNAME="BUYERNAME";
	protected static final String BANKNAME="BANKNAME";
	protected static final String VALIDCODE="VALIDCODE";
	protected static final String CARDNAME="CARDNAME";
	/**
	 * Map that contains data that are saved while executing scenarios Saved
	 * data in this map are used later
	 */
	private static Map<String, String> mapOutParams = new HashMap<String, String>();

	/**
	 * Map loaded with excel sheet data @ start of each TestCase
	 */
	protected Map<String, String> mapInputParameters = new HashMap<String, String>();

	/**
	 * Handler that handles all JSF components
	 */
	protected EuronicsSeleniumHandler euronicsSeleniumHandler;

	protected PropertyLoader objectMap;

	/**
	 * sets ATG Selenium Handler
	 * 
	 * @param jsfHandler
	 */
	public void setEuronicsSeleniumHandler(EuronicsSeleniumHandler euronicsSeleniumHandler) {
		this.euronicsSeleniumHandler = euronicsSeleniumHandler;
	}

	/**
	 * return JSFSeleniumHandler
	 * 
	 * @return
	 */
	public EuronicsSeleniumHandler getEuronicsSeleniumHandler() {
		return this.euronicsSeleniumHandler;
	}
	
	public PropertyLoader getObjectMap() {
		return objectMap;
	}

	public void setObjectMap(PropertyLoader objectMap) {
		this.objectMap = objectMap;
	}

	/**
	 * Setting Map with excel sheet data required for test case
	 * 
	 * @param inMap
	 */
	public void setMapInputParameters(Map<String, String> inMap) {
		this.mapInputParameters = inMap;
	}

	protected String getParameter(Map<String, String> pmapArguments, String key) {
		return ((pmapArguments.get(key) == null) ? "" : pmapArguments.get(key));
	}

	protected void saveParameter(String key, String value) {
		mapOutParams.put(key, value);
	}

	protected void appendParameter(String key, String value) {
		if (StringUtils.isBlank(value)) {
			return;
		}
		if (mapOutParams.containsKey(key)) {
			value = mapOutParams.get(key) + "," + value;
		}
		mapOutParams.put(key, value);
	}

	protected String getParameter(String key) {
		String result = "";
		if (mapOutParams.containsKey(key)) {
			result = mapOutParams.get(key);
		}
		return result;
	}

	protected void assertNotNull(String value, String message) throws TestException {
		if (StringUtils.isBlank(value)) {
			String filePath = euronicsSeleniumHandler.captureScreenShoot();
			//logger.error(message + " is Null");
			throw new TestException(message + ", please find the snapshot attached at location ", filePath);
		}
		//logger.debug(message + " is " + value);

	}

	protected void assertValue(String expectedValue, String actualValue, String field) throws TestException {
		if (StringUtils.isNotBlank(expectedValue)) {
			if (!expectedValue.equalsIgnoreCase(actualValue)) {
				String filePath = euronicsSeleniumHandler.captureScreenShoot();
				throw new TestException("expected value of " + field + " is---->" + expectedValue
						+ "  but actual value  is " + actualValue + ", please find the snapshot attached at location ",
						filePath);
			}
			logger.info("expected value and actual value of field " + field + " are " + actualValue);
		}
	}

	protected void assertContains(String expectedValue, String actualValue, String field) throws TestException {
		if (StringUtils.isNotBlank(expectedValue)) {
			String expected = expectedValue.toUpperCase();
			String actual = actualValue.toUpperCase();
			if (!actual.contains(expected)) {
				String filePath = euronicsSeleniumHandler.captureScreenShoot();
				throw new TestException("expected value of " + field + " is---->" + expectedValue
						+ "  but actual value  is " + actualValue + ", please find the snapshot attached at location ",
						filePath);
			}
			logger.info("expected value and actual value of field " + field + " are " + expectedValue);
		}
	}

	/**
	 * Used to clear the Map(OutMap) @ end of every execution;
	 */
	protected void clearMaps() {
		mapOutParams.clear();
		mapInputParameters.clear();
	}

	public void tearDown() {
		clearMaps();
		euronicsSeleniumHandler.tearDown();
	}

	
}
