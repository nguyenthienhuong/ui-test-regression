package org.exoplatform.selenium.platform.ecms.functional.wcm.seo;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.inputDataSeo;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * @author: Lientm
 * @date: 22/10/2012
 */

public class ECMS_WCM_SEO_Metadata extends EcmsBase{

	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";
	
	public static final By ELEMENT_HEADER = By.id("head");
	public static final By ELEMENT_DESCRIPTION = By.xpath("//meta[@name='description']");
	public static final By ELEMENT_KEYWORD = By.xpath("//meta[@name='keywords']");
	public static final By ELEMENT_ROBOT = By.xpath("//meta[@name='robots']");
	
	
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
	
	public static WebElement waitForElementPresentNotDisplay(Object locator, int... opParams) {
		WebElement elem = null;
		int timeout = opParams.length>0 ? opParams[0] : DEFAULT_TIMEOUT;
		int isAssert = opParams.length > 1 ? opParams[1]: 1;

		for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
			elem = getElement(locator);
			if (null != elem) return elem;
			pause(WAIT_INTERVAL);
		}
		if (isAssert == 1)
			assert false: ("Timeout after " + timeout + "ms waiting for element present: " + locator);
		return null;
	}
	
	public static WebElement waitForElementNotPresentNotDisplay(Object locator, int... opParams) {
		WebElement elem = null;
		int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;
		int isAssert = opParams.length > 1 ? opParams[1]: 1;

		for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
			elem = getElement(locator);

			if (null == elem) return null;
			pause(WAIT_INTERVAL);
		} 

		if (isAssert == 1)
			assert false: ("Timeout after " + timeout + "ms waiting for element not present: " + locator);
		return elem;
	}
	
	public static WebElement waitForAndGetElementNotDisplay(Object locator, int... opParams) {
		WebElement elem = null;
		int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;
		int isAssert = opParams.length > 1 ? opParams[1]: 1;

		for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
			elem = getElement(locator);

			if (null != elem){
				return elem;}
			pause(WAIT_INTERVAL);
		}
		if (isAssert == 1)		
			assert false: ("Timeout after " + timeout + "ms waiting for element present: " + locator);
		return null;
	}
		
	/*case01: Check default metadata when user hasn't added SEO data 
	 * check default metadata of page acme/new that has not added SEO
	 */
	@Test
	public void test01_CheckDefaultMetadataWhenNotAddSEOData(){
		goToNews();
		waitForElementNotPresentNotDisplay(ELEMENT_HEADER);
		waitForElementNotPresentNotDisplay(ELEMENT_DESCRIPTION);
		waitForElementNotPresentNotDisplay(ELEMENT_KEYWORD);
		waitForElementNotPresentNotDisplay(ELEMENT_ROBOT);
		info("Metadata default is true");
	}
	
	/*case02: Check metadata through View Page Source when user input value for Description, robots = INDEX & FOLLOW
	 * 
	 */
	@Test
	public void test02_CheckMetadataWhenInputDescriptionRobotsIsIndexFollow(){
		String DATA_DESCRIPTION = "ECMS_WCM_SEO_Metadata_description_02";
				
		//create new SEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, "", "INDEX", "FOLLOW", true, "Always", "1");
		clearCache();
		
		//check metadata
		assert waitForAndGetElementNotDisplay(ELEMENT_DESCRIPTION).getAttribute("content").contentEquals(DATA_DESCRIPTION):"description of metadata is not true";
		waitForElementNotPresentNotDisplay(ELEMENT_KEYWORD);
		assert waitForAndGetElementNotDisplay(ELEMENT_ROBOT).getAttribute("content").contentEquals("INDEX, FOLLOW"):"robots of metadata is not true";
		info("Metadata of acme homepage is true");
	}
	
	/*case03: Check metadata through View Page Source when user input value for Description, robots = NOINDEX & FOLLOW
	 * 
	 */
	@Test
	public void test03_CheckMetadataWhenInputDescriptionRobotsIsNoIndexFollow(){
		String DATA_DESCRIPTION = "ECMS_WCM_SEO_Metadata_description_03";
				
		//create new SEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, "", "NOINDEX", "FOLLOW", true, "Always", "1");
		clearCache();
		
		//check metadata
		assert waitForAndGetElementNotDisplay(ELEMENT_DESCRIPTION).getAttribute("content").contentEquals(DATA_DESCRIPTION):"description of metadata is not true";
		waitForElementNotPresentNotDisplay(ELEMENT_KEYWORD);
		assert waitForAndGetElementNotDisplay(ELEMENT_ROBOT).getAttribute("content").contentEquals("NOINDEX, FOLLOW"):"robots of metadata is not true";
		info("Metadata of acme homepage is true");
	}
	
	/*case04: Check metadata through View Page Source when user input value for Description, robots = INDEX & NOFOLLOW
	 * 
	 */
	@Test
	public void test04_CheckMetadataWhenInputDescriptionRobotsIsIndexNoFollow(){
		String DATA_DESCRIPTION = "ECMS_WCM_SEO_Metadata_description_04";
				
		//create new SEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, "", "INDEX", "NOFOLLOW", true, "Always", "1");
		clearCache();
		
		//check metadata
		assert waitForAndGetElementNotDisplay(ELEMENT_DESCRIPTION).getAttribute("content").contentEquals(DATA_DESCRIPTION):"description of metadata is not true";
		waitForElementNotPresentNotDisplay(ELEMENT_KEYWORD);
		assert waitForAndGetElementNotDisplay(ELEMENT_ROBOT).getAttribute("content").contentEquals("INDEX, NOFOLLOW"):"robots of metadata is not true";
		info("Metadata of acme homepage is true");
	}
	
	/*case05: Check metadata through View Page Source when user input value for Description, robots = NOINDEX & NOFOLLOW
	 * 
	 */
	@Test
	public void test05_CheckMetadataWhenInputDescriptionRobotsIsNoIndexNoFollow(){
		String DATA_DESCRIPTION = "ECMS_WCM_SEO_Metadata_description_05";
				
		//create new SEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, "", "NOINDEX", "NOFOLLOW", true, "Always", "1");
		clearCache();
		
		//check metadata
		assert waitForAndGetElementNotDisplay(ELEMENT_DESCRIPTION).getAttribute("content").contentEquals(DATA_DESCRIPTION):"description of metadata is not true";
		waitForElementNotPresentNotDisplay(ELEMENT_KEYWORD);
		assert waitForAndGetElementNotDisplay(ELEMENT_ROBOT).getAttribute("content").contentEquals("NOINDEX, NOFOLLOW"):"robots of metadata is not true";
		info("Metadata of acme homepage is true");
	}
	
	/*case06: Check metadata through View Page Source when user input value for Description, robots = INDEX & FOLLOW
	 * 
	 */
	@Test
	public void test06_CheckMetadataWhenInputKeywordRobotsIsIndexFollow(){
		String DATA_KEYWORD = "ECMS_WCM_SEO_Metadata_keyword_06";
				
		//create new SEO for acme home page
		goToSeoManagement();
		inputDataSeo("", DATA_KEYWORD, "INDEX", "FOLLOW", true, "Always", "1");
		clearCache();
		
		//check metadata
		assert waitForAndGetElementNotDisplay(ELEMENT_KEYWORD).getAttribute("content").contentEquals(DATA_KEYWORD):"Keyword of metadata is not true";
		waitForElementNotPresentNotDisplay(ELEMENT_DESCRIPTION);
		assert waitForAndGetElementNotDisplay(ELEMENT_ROBOT).getAttribute("content").contentEquals("INDEX, FOLLOW"):"robots of metadata is not true";
		info("Metadata of acme homepage is true");
	}
	
	/*case07: Check metadata through View Page Source when user input value for Description, robots = NOINDEX & FOLLOW
	 * 
	 */
	@Test
	public void test07_CheckMetadataWhenInputKeywordRobotsIsNoIndexFollow(){
		String DATA_KEYWORD = "ECMS_WCM_SEO_Metadata_keyword_07";
				
		//create new SEO for acme home page
		goToSeoManagement();
		inputDataSeo("", DATA_KEYWORD, "NOINDEX", "FOLLOW", true, "Always", "1");
		clearCache();
		
		//check metadata
		assert waitForAndGetElementNotDisplay(ELEMENT_KEYWORD).getAttribute("content").contentEquals(DATA_KEYWORD):"Keyword of metadata is not true";
		waitForElementNotPresentNotDisplay(ELEMENT_DESCRIPTION);
		assert waitForAndGetElementNotDisplay(ELEMENT_ROBOT).getAttribute("content").contentEquals("NOINDEX, FOLLOW"):"robots of metadata is not true";
		info("Metadata of acme homepage is true");
	}
	
	/*case08: Check metadata through View Page Source when user input value for Description, robots = INDEX & NOFOLLOW
	 * 
	 */
	@Test
	public void test08_CheckMetadataWhenInputKeywordRobotsIsIndexNoFollow(){
		String DATA_KEYWORD = "ECMS_WCM_SEO_Metadata_keyword_08";
				
		//create new SEO for acme home page
		goToSeoManagement();
		inputDataSeo("", DATA_KEYWORD, "INDEX", "NOFOLLOW", true, "Always", "1");
		clearCache();
		
		//check metadata
		assert waitForAndGetElementNotDisplay(ELEMENT_KEYWORD).getAttribute("content").contentEquals(DATA_KEYWORD):"Keyword of metadata is not true";
		waitForElementNotPresentNotDisplay(ELEMENT_DESCRIPTION);
		assert waitForAndGetElementNotDisplay(ELEMENT_ROBOT).getAttribute("content").contentEquals("INDEX, NOFOLLOW"):"robots of metadata is not true";
		info("Metadata of acme homepage is true");
	}
	
	/*case09: Check metadata through View Page Source when user input value for Description, robots = NOINDEX & NOFOLLOW
	 * 
	 */
	@Test
	public void test09_CheckMetadataWhenInputKeywordRobotsIsNoIndexNoFollow(){
		String DATA_KEYWORD = "ECMS_WCM_SEO_Metadata_keyword_09";
				
		//create new SEO for acme home page
		goToSeoManagement();
		inputDataSeo("", DATA_KEYWORD, "NOINDEX", "NOFOLLOW", true, "Always", "1");
		clearCache();
		
		//check metadata
		assert waitForAndGetElementNotDisplay(ELEMENT_KEYWORD).getAttribute("content").contentEquals(DATA_KEYWORD):"Keyword of metadata is not true";
		waitForElementNotPresentNotDisplay(ELEMENT_DESCRIPTION);
		assert waitForAndGetElementNotDisplay(ELEMENT_ROBOT).getAttribute("content").contentEquals("NOINDEX, NOFOLLOW"):"robots of metadata is not true";
		info("Metadata of acme homepage is true");
	}
	
	/*case10: Check metadata through View Page Source when user input value for both Description & Keywords, robots = INDEX & FOLLOW
	 * 
	 */
	@Test
	public void test10_CheckMetadataWhenInputDescription_KeywordRobotsIsIndexFollow(){
		String DATA_DESCRIPTION = "ECMS_WCM_SEO_Metadata_description_10";
		String DATA_KEYWORD = "ECMS_WCM_SEO_Metadata_keyword_10";
		
		//create new SEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, DATA_KEYWORD, "INDEX", "FOLLOW", true, "Always", "1");
		clearCache();
		
		//check metadata
		assert waitForAndGetElementNotDisplay(ELEMENT_DESCRIPTION).getAttribute("content").contentEquals(DATA_DESCRIPTION):"description of metadata is not true";
		assert waitForAndGetElementNotDisplay(ELEMENT_KEYWORD).getAttribute("content").contentEquals(DATA_KEYWORD):"Keyword of metadata is not true";
		assert waitForAndGetElementNotDisplay(ELEMENT_ROBOT).getAttribute("content").contentEquals("INDEX, FOLLOW"):"robots of metadata is not true";
		info("Metadata of acme homepage is true");
	}
	/*case11: Check metadata through View Page Source when user input value for both Description & Keywords, robots = NOINDEX & FOLLOW
	 * 
	 */
	@Test
	public void test11_CheckMetadataWhenInputDescription_KeywordRobotsIsNoIndexFollow(){
		String DATA_DESCRIPTION = "ECMS_WCM_SEO_Metadata_description_11";
		String DATA_KEYWORD = "ECMS_WCM_SEO_Metadata_keyword_11";
		
		//create new SEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, DATA_KEYWORD, "NOINDEX", "FOLLOW", true, "Always", "1");
		clearCache();
		
		//check metadata
		assert waitForAndGetElementNotDisplay(ELEMENT_DESCRIPTION).getAttribute("content").contentEquals(DATA_DESCRIPTION):"description of metadata is not true";
		assert waitForAndGetElementNotDisplay(ELEMENT_KEYWORD).getAttribute("content").contentEquals(DATA_KEYWORD):"Keyword of metadata is not true";
		assert waitForAndGetElementNotDisplay(ELEMENT_ROBOT).getAttribute("content").contentEquals("NOINDEX, FOLLOW"):"robots of metadata is not true";
		info("Metadata of acme homepage is true");
	}
	
	/*case12: Check metadata through View Page Source when user input value for both Description & Keywords, robots = INDEX & NOFOLLOW
	 * 
	 */
	@Test
	public void test12_CheckMetadataWhenInputDescription_KeywordRobotsIsIndexNoFollow(){
		String DATA_DESCRIPTION = "ECMS_WCM_SEO_Metadata_description_12";
		String DATA_KEYWORD = "ECMS_WCM_SEO_Metadata_keyword_12";
		
		//create new SEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, DATA_KEYWORD, "INDEX", "NOFOLLOW", true, "Always", "1");
		clearCache();
		
		//check metadata
		assert waitForAndGetElementNotDisplay(ELEMENT_DESCRIPTION).getAttribute("content").contentEquals(DATA_DESCRIPTION):"description of metadata is not true";
		assert waitForAndGetElementNotDisplay(ELEMENT_KEYWORD).getAttribute("content").contentEquals(DATA_KEYWORD):"Keyword of metadata is not true";
		assert waitForAndGetElementNotDisplay(ELEMENT_ROBOT).getAttribute("content").contentEquals("INDEX, NOFOLLOW"):"robots of metadata is not true";
		info("Metadata of acme homepage is true");
	}
	
	/*case12: Check metadata through View Page Source when user input value for both Description & Keywords, robots = NOINDEX & NOFOLLOW
	 * 
	 */
	@Test
	public void test13_CheckMetadataWhenInputDescription_KeywordRobotsIsNoIndexNoFollow(){
		String DATA_DESCRIPTION = "ECMS_WCM_SEO_Metadata_description_13";
		String DATA_KEYWORD = "ECMS_WCM_SEO_Metadata_keyword_13";
		
		//create new SEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, DATA_KEYWORD, "NOINDEX", "NOFOLLOW", true, "Always", "1");
		clearCache();
		
		//check metadata
		assert waitForAndGetElementNotDisplay(ELEMENT_DESCRIPTION).getAttribute("content").contentEquals(DATA_DESCRIPTION):"description of metadata is not true";
		assert waitForAndGetElementNotDisplay(ELEMENT_KEYWORD).getAttribute("content").contentEquals(DATA_KEYWORD):"Keyword of metadata is not true";
		assert waitForAndGetElementNotDisplay(ELEMENT_ROBOT).getAttribute("content").contentEquals("NOINDEX, NOFOLLOW"):"robots of metadata is not true";
		info("Metadata of acme homepage is true");
	}
}
