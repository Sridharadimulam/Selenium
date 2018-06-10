package hu.euronics.euronicsautomation.testdefinitions;

import java.util.Map;
import java.util.logging.Logger;

import hu.euronics.euronicsautomation.application.AddProduct;
import hu.euronics.euronicsautomation.application.Application;
import hu.euronics.euronicsautomation.application.CheckOut;
import hu.euronics.euronicsautomation.application.Delivery;
import hu.euronics.euronicsautomation.application.EuronicsApplicationConstants;
import hu.euronics.euronicsautomation.application.ExpressCheckout;
import hu.euronics.euronicsautomation.application.LaunchApplication;
import hu.euronics.euronicsautomation.application.ManageCart;
import hu.euronics.euronicsautomation.application.OnlinePayment;
import hu.euronics.euronicsautomation.application.Payment;
import hu.euronics.euronicsautomation.application.PlaceOrder;
import hu.euronics.euronicsautomation.application.SearchProduct;
import hu.euronics.euronicsautomation.application.SelectDeliveryMode;
import hu.euronics.euronicsautomation.application.SelectProduct;
import hu.euronics.euronicsautomation.application.ViewBasket;
import hu.euronics.euronicsautomation.common.utils.TestException;

public class TestStepHandler {

	private static final Logger logger = Logger.getLogger(TestSuite.class.getSimpleName());
	private Application euronicsApplication;
	private TestStepDefinition testStep;

	/**
	 * Constructor ; Initializes WSPublishing & TestStepDefinition
	 * 
	 * @param wsPublishing
	 * @param testStep
	 */
	public TestStepHandler(Application euronicsApplication, TestStepDefinition testStep) {
		super();
		this.euronicsApplication = euronicsApplication;
		this.testStep = testStep;
	}


	private void handlerSearchProduct() throws TestException {
		String strAction = testStep.getAction().trim();
		Map<String, String> mapArguments = testStep.getMapArguments();
		SearchProduct searchProduct = new SearchProduct();
		searchProduct.setEuronicsSeleniumHandler(euronicsApplication.getEuronicsSeleniumHandler());
		searchProduct.setObjectMap(euronicsApplication.getObjectMap());
		searchProduct.setMapInputParameters(mapArguments);
		if (EuronicsApplicationConstants.ACTION_SEARCH.equalsIgnoreCase(strAction)) {
			searchProduct.execute();
		} 

	}
	
	private void handlerSelectProduct() throws TestException {
		String strAction = testStep.getAction().trim();
		Map<String, String> mapArguments = testStep.getMapArguments();
		SelectProduct selectProduct = new SelectProduct();
		selectProduct.setEuronicsSeleniumHandler(euronicsApplication.getEuronicsSeleniumHandler());
		selectProduct.setObjectMap(euronicsApplication.getObjectMap());
		selectProduct.setMapInputParameters(mapArguments);
		if (EuronicsApplicationConstants.ACTION_SELECT.equalsIgnoreCase(strAction)) {
			selectProduct.execute();
		}

	}
	
	
	
	private void handlerAddProduct() throws TestException {
		String strAction = testStep.getAction().trim();
		Map<String, String> mapArguments = testStep.getMapArguments();
		AddProduct addProduct = new AddProduct();
		addProduct.setEuronicsSeleniumHandler(euronicsApplication.getEuronicsSeleniumHandler());
		addProduct.setObjectMap(euronicsApplication.getObjectMap());
		addProduct.setMapInputParameters(mapArguments);
		if (EuronicsApplicationConstants.ACTION_ADDTOBASKET.equalsIgnoreCase(strAction)) {
			addProduct.execute();
		}
	}
	
	private void handlerCheckOut() throws TestException {
		String strAction = testStep.getAction().trim();
		Map<String, String> mapArguments = testStep.getMapArguments();
		CheckOut checkOut = new CheckOut();
		checkOut.setEuronicsSeleniumHandler(euronicsApplication.getEuronicsSeleniumHandler());
		checkOut.setObjectMap(euronicsApplication.getObjectMap());
		checkOut.setMapInputParameters(mapArguments);
		if (EuronicsApplicationConstants.ACTION_CHECKOUT.equalsIgnoreCase(strAction)) {
			checkOut.execute();
		}

	}
	private void handlerManageCart() throws TestException {
		String strAction = testStep.getAction().trim();
		Map<String, String> mapArguments = testStep.getMapArguments();
		ManageCart checkOut = new ManageCart();
		checkOut.setEuronicsSeleniumHandler(euronicsApplication.getEuronicsSeleniumHandler());
		checkOut.setObjectMap(euronicsApplication.getObjectMap());
		checkOut.setMapInputParameters(mapArguments);
		if (EuronicsApplicationConstants.ACTION_MANAGECART.equalsIgnoreCase(strAction)) {
			checkOut.execute();
		}

	}
	
	private void handlerDeliveryMethod() throws TestException {
		String strAction = testStep.getAction().trim();
		Map<String, String> mapArguments = testStep.getMapArguments();
		SelectDeliveryMode checkOut = new SelectDeliveryMode();
		checkOut.setEuronicsSeleniumHandler(euronicsApplication.getEuronicsSeleniumHandler());
		checkOut.setObjectMap(euronicsApplication.getObjectMap());
		checkOut.setMapInputParameters(mapArguments);
		if (EuronicsApplicationConstants.ACTION_SELECTDELIVERYMODE.equalsIgnoreCase(strAction)) {
			checkOut.execute();
		}

	}
	
	private void handlerDeliveryAddress() throws TestException {
		String strAction = testStep.getAction().trim();
		Map<String, String> mapArguments = testStep.getMapArguments();
		Delivery delivery = new Delivery();
		delivery.setEuronicsSeleniumHandler(euronicsApplication.getEuronicsSeleniumHandler());
		delivery.setObjectMap(euronicsApplication.getObjectMap());
		delivery.setMapInputParameters(mapArguments);
		if (EuronicsApplicationConstants.ACTION_DELIVERY.equalsIgnoreCase(strAction)) {
			delivery.execute();
		}

	}
	
	private void handlerPayment() throws TestException {
		String strAction = testStep.getAction().trim();
		Map<String, String> mapArguments = testStep.getMapArguments();
		Payment payment = new Payment();
		payment.setEuronicsSeleniumHandler(euronicsApplication.getEuronicsSeleniumHandler());
		payment.setObjectMap(euronicsApplication.getObjectMap());
		payment.setMapInputParameters(mapArguments);
		if (EuronicsApplicationConstants.ACTION_PAYMENT.equalsIgnoreCase(strAction)) {
			payment.execute();
		}

	}
	
	private void handlerPlaceOrder() throws TestException {
		String strAction = testStep.getAction().trim();
		Map<String, String> mapArguments = testStep.getMapArguments();
		PlaceOrder placeOrder = new PlaceOrder();
		placeOrder.setEuronicsSeleniumHandler(euronicsApplication.getEuronicsSeleniumHandler());
		placeOrder.setObjectMap(euronicsApplication.getObjectMap());
		placeOrder.setMapInputParameters(mapArguments);
		if (EuronicsApplicationConstants.ACTION_CONFIRMATION.equalsIgnoreCase(strAction)) {
			placeOrder.execute();
		}

	}
	private void handlerOnlinePayment() throws TestException {
		String strAction = testStep.getAction().trim();
		Map<String, String> mapArguments = testStep.getMapArguments();
		OnlinePayment onlinePayment = new OnlinePayment();
		onlinePayment.setEuronicsSeleniumHandler(euronicsApplication.getEuronicsSeleniumHandler());
		onlinePayment.setObjectMap(euronicsApplication.getObjectMap());
		onlinePayment.setMapInputParameters(mapArguments);
		if (EuronicsApplicationConstants.ACTION_ONLINEPAYMENT.equalsIgnoreCase(strAction)) {
			onlinePayment.execute();
		} 
	}
	private void handlerViewBasket() throws TestException {
		String strAction = testStep.getAction().trim();
		Map<String, String> mapArguments = testStep.getMapArguments();
		ViewBasket viewBasket = new ViewBasket();
		viewBasket.setEuronicsSeleniumHandler(euronicsApplication.getEuronicsSeleniumHandler());
		viewBasket.setObjectMap(euronicsApplication.getObjectMap());
		viewBasket.setMapInputParameters(mapArguments);
		if (EuronicsApplicationConstants.ACTION_VIEWBASKET.equalsIgnoreCase(strAction)) {
			viewBasket.execute();
		}

	}
		
	
	private void handlerLaunchApplication() throws TestException {
		String strAction = testStep.getAction().trim();
		Map<String, String> mapArguments = testStep.getMapArguments();
		LaunchApplication launch = new LaunchApplication();
		launch.setEuronicsSeleniumHandler(euronicsApplication.getEuronicsSeleniumHandler());
		launch.setObjectMap(euronicsApplication.getObjectMap());
		launch.setMapInputParameters(mapArguments);
		if (EuronicsApplicationConstants.ACTION_LAUNCH.equalsIgnoreCase(strAction)) {
			launch.execute();
		}

	}

	private void handlerExpresscheckout() throws TestException {
		String strAction = testStep.getAction().trim();
		Map<String, String> mapArguments = testStep.getMapArguments();
		ExpressCheckout Expresscheckout =new ExpressCheckout();
		Expresscheckout.setEuronicsSeleniumHandler(euronicsApplication.getEuronicsSeleniumHandler());
		Expresscheckout.setObjectMap(euronicsApplication.getObjectMap());
		Expresscheckout.setMapInputParameters(mapArguments);
		if (EuronicsApplicationConstants.ACTION_EXPRESSCHECKOUT.equalsIgnoreCase(strAction)) {
			Expresscheckout.execute();
		}
	}

	/**
	 * Maps the Input sheet data to the corresponding handlers
	 * 
	 * @throws TestException
	 */
	public void handle() throws TestException {
		String sheetname = testStep.getSheetName();
		if (sheetname.equalsIgnoreCase(EuronicsApplicationConstants.LAUNCHAPPLICATION_SHEET)) {
			handlerLaunchApplication();
		}else if (sheetname.equalsIgnoreCase(EuronicsApplicationConstants.SEARCHPRODUCT_SHEET)) {
			handlerSearchProduct();
		} 
		else if (sheetname.equalsIgnoreCase(EuronicsApplicationConstants.SELECTPRODUCT_SHEET)) {
			handlerSelectProduct();
		}
		else if (sheetname.equalsIgnoreCase(EuronicsApplicationConstants.ADDPRODUCT_SHEET)) {
			handlerAddProduct();
		}
		else if (sheetname.equalsIgnoreCase(EuronicsApplicationConstants.VIEWBASKET_SHEET)) {
			handlerViewBasket();
		}
		else if (sheetname.equalsIgnoreCase(EuronicsApplicationConstants.CHECKOUT_SHEET)) {
			handlerCheckOut();
		}
		else if (sheetname.equalsIgnoreCase(EuronicsApplicationConstants.MANAGECART_SHEET)) {
			handlerManageCart();
		}
		else if (sheetname.equalsIgnoreCase(EuronicsApplicationConstants.SELECTDELIVERYMODE_SHEET)) {
			handlerDeliveryMethod();
		}
		else if (sheetname.equalsIgnoreCase(EuronicsApplicationConstants.DELIVERY_SHEET)) {
			handlerDeliveryAddress();
		}
		else if (sheetname.equalsIgnoreCase(EuronicsApplicationConstants.PAYMENT_SHEET)) {
			handlerPayment();
		}
		else if (sheetname.equalsIgnoreCase(EuronicsApplicationConstants.CONFIRMATION_SHEET)) {
			handlerPlaceOrder();
		}
		else if (sheetname.equalsIgnoreCase(EuronicsApplicationConstants.ONLINEPAYEMENT_SHEET)) {
			handlerOnlinePayment();
		}
		else if (sheetname.equalsIgnoreCase(EuronicsApplicationConstants.EXPRESSCHECKOUT_SHEET)) {
			handlerExpresscheckout();
		}
		else
		{
			//logger.error("Invalid test data sheet name " + sheetname + ",please check your test data file");
			throw new TestException("Invalid test data sheet name " + sheetname + ",please check your test data file");
		}
	}

}
