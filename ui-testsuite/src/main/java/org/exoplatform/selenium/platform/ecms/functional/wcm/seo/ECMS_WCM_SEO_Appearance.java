package org.exoplatform.selenium.platform.ecms.functional.wcm.seo;

import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * @author: Lientm
 * @date: 22/10/2012
 */
public class ECMS_WCM_SEO_Appearance extends EcmsBase {

	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";


	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with "+DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() throws Exception {
		info("Logout ECMS");
		logoutEcms();
		driver.manage().deleteAllCookies();
		driver.quit();
		actions = null;
	}

	/*case01: Default color of SEO icon when metadata doesn't exist 
	 * check default color of SEO icon is red on acme/news page
	 */
	@Test
	public void test01_CheckDefaulColorOfSEOWhenMetadataNotExit(){

		//check default color of SEO icon is red
		info("Check color of SEO icon default of acme/news page");
		goToNews();
		mouseOver(ELEMENT_MENU_EDIT_LINK, true);
		mouseOver(ELEMENT_MENU_PAGE_LINK, true);
		captureScreen("ECMS_WCM_SEO_Appearance_01_check_SEO_icon_default_is_red.jpg");
	}

	/*case02: Color of SEO icon when user inputs data but not fully completed
	 * check color of SEO icon when edit on acme home page
	 */
	@Test
	public void test02_CheckColorOfSEOWhenInputDataNotFully(){
		String DATA_DESCRIPTION = "ECMS_WCM_SEO_Appearance_decription_02";

		//go to seo management
		goToSeoManagement();

		//input data not complete
		inputDataSeo(DATA_DESCRIPTION, "", "INDEX", "FOLLOW", true, "Always", "1");

		//check Color of SEO icon is orange
		info("Check color of SEO icon when input not full data of acme home page");
		mouseOver(ELEMENT_MENU_EDIT_LINK, true);
		mouseOver(ELEMENT_MENU_PAGE_LINK, true);
		captureScreen("ECMS_WCM_SEO_Appearance_02_check_SEO_icon_is_orange.jpg");		
	}

	//	case03: Color of SEO icon when user inputs data for all fields
	@Test
	public void test03_CheckColorOfSEOWhenFullData(){
		String DATA_DESCRIPTION = "ECMS_WCM_SEO_Appearance_decription_03";
		String DATA_KEYWORD = "ECMS_WCM_SEO_Appearance_keyword_03";

		//go to seo management
		goToSeoManagement();

		//input full data
		inputDataSeo(DATA_DESCRIPTION, DATA_KEYWORD, "INDEX", "FOLLOW", true, "Always", "1");

		//check Color of SEO icon is orange
		info("Check color of SEO icon when input full data of acme home page");
		mouseOver(ELEMENT_MENU_EDIT_LINK, true);
		mouseOver(ELEMENT_MENU_PAGE_LINK, true);
		captureScreen("ECMS_WCM_SEO_Appearance_03_check_SEO_icon_is_green.jpg");		
	}

	/*case04: Color of SEO icon when user open an administration page 
	 * check color of SEO icon on site explorer page
	 */
	@Test
	public void test04_CheckColorOfSEOWhenOpenAnAdminPage(){

		//go to site explorer
		goToSiteExplorer();

		//check Color of SEO icon is grey
		info("Check color of SEO icon");
		mouseOver(ELEMENT_MENU_EDIT_LINK, true);
		mouseOver(ELEMENT_MENU_PAGE_LINK, true);
		captureScreen("ECMS_WCM_SEO_Appearance_04_check_SEO_icon_is_grey.jpg");	

		//check cannot open SEO management
		goToSeoManagement();
		waitForElementNotPresent(ELEMENT_SEO_FORM);
		info("Can not open SEO Management of an administration page");
	}

	/*case05: Screen of SEO management when user open a portal page
	 * check screen of SEO management on Overview Page
	 */
	@Test
	public void test05_CheckSEOManagementWhenOpenPortalPage(){

		//go to OverView Page
		goToOverView();

		//check screen SEO management
		goToSeoManagement();
		waitForElementPresent(ELEMENT_SEO_FORM);
		assert isElementPresent(ELEMENT_DESCRIPTION_TEXTAREA):"SEO management form is wrong";
		assert isElementPresent(ELEMENT_KEYWORD_TEXTAREA):"SEO management form is wrong";
		assert isElementPresent(ELEMENT_ROBOT_INDEX_COMBOX):"SEO management form is wrong";
		assert isElementPresent(ELEMENT_ROBOT_FOLLOW_COMBOX):"SEO management form is wrong";
		assert isElementPresent(ELEMENT_SITE_MAP_CHECKBOX):"SEO management form is wrong";
		assert isElementPresent(ELEMENT_FREQUENCY_COMBOX):"SEO management form is wrong";
		assert isElementPresent(ELEMENT_PRIORITY):"SEO management form is wrong";
		info("SEO management form of overview page displays true");		
	}

	/*case06: Screen of SEO management when user opens a content page 
	 * check screen of SEO Management form of News Form
	 */
	@Test
	public void test06_CheckSEOManagementWhenOpenContentPage(){

		//go to News page
		goToNews();
		click(By.linkText("Fire"));

		//check screen of SEO management
		goToSeoManagement();
		waitForElementPresent(ELEMENT_SEO_FORM);
		assert isElementPresent(ELEMENT_DESCRIPTION_TEXTAREA):"SEO management form is wrong";
		assert isElementPresent(ELEMENT_KEYWORD_TEXTAREA):"SEO management form is wrong";
		waitForElementNotPresent(ELEMENT_ROBOT_INDEX_COMBOX);
		isElementNotPresent(ELEMENT_ROBOT_FOLLOW_COMBOX);
		isElementNotPresent(ELEMENT_SITE_MAP_CHECKBOX);
		isElementNotPresent(ELEMENT_FREQUENCY_COMBOX);
		isElementNotPresent(ELEMENT_PRIORITY);
		info("SEO management form of News page displays true");	
	}
}
