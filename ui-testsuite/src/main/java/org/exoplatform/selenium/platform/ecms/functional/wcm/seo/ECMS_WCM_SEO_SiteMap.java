package org.exoplatform.selenium.platform.ecms.functional.wcm.seo;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.inputDataSeo;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * @author: Lientm
 * @date: 23/10/2012
 */

public class ECMS_WCM_SEO_SiteMap extends EcmsBase {

	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";
	
	public static final By ELEMENT_SITEMAP_FILE = By.linkText("sitemaps");
	public static final By ELEMENT_CONTENT_SITEMAP = By.id("content");
	public static final String DATA_CONTENT_ACME = "<url><loc>/portal/acme</loc><changefreq>${frequency}</changefreq><priority>1.0</priority></url>";
	
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
	
	//function to go to SEO of acme page
	public static void goToAcmeSEO(){
		info("Go to SEO of acme home page");
		goToSiteExplorer();
		goToNode(By.linkText("acme"));
		goToNode(By.linkText("SEO"));
	}
	/*Case01: Check default of Sitemap
	 * 
	 */
	@Test
	public void test01_CheckSitemapDefault(){
		String DATA_DESCRIPTION = "ECMS_WCM_CEO_SiteMap_description_01";
		String DATA_KEYWORD = "ECMS_WCM_CEO_SiteMap_keyword_01";

		//create new CEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, DATA_KEYWORD, "INDEX", "FOLLOW", false, "Always", "1");
		clearCache();
		
		//go to site explorer
		goToAcmeSEO();
		
		//check file sitemap.xml
		goToNode(ELEMENT_SITEMAP_FILE);
		if (waitForAndGetElement(ELEMENT_EDIT_LINK) != null) {
			click(ELEMENT_EDIT_LINK);
		}
		waitForElementPresent(ELEMENT_CONTENT_SITEMAP);
		assert isTextNotPresent("<loc>"):"sitemap file is wrong";
		assert isTextNotPresent("<changefreq>"):"sitemap file is wrong";
		assert isTextNotPresent("<priority>"):"sitemap file is wrong";
		info("Content of file sitemap.xml is true");
	}
	
	/*case02: Check Sitemap with Frequency: Always
	 * 
	 */
	@Test
	public void test02_CheckSitemapFreAlways(){
		String DATA_DESCRIPTION = "ECMS_WCM_CEO_SiteMap_description_02";
		String DATA_KEYWORD = "ECMS_WCM_CEO_SiteMap_keyword_02";
		

		//create new CEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, DATA_KEYWORD, "INDEX", "FOLLOW", true, "Always", "1");
		clearCache();
		
		//go to site explorer
		goToAcmeSEO();
		
		//check file sitemap.xml
		goToNode(ELEMENT_SITEMAP_FILE);
		if (waitForAndGetElement(ELEMENT_EDIT_LINK) != null) {
			click(ELEMENT_EDIT_LINK);
		}
		waitForElementPresent(ELEMENT_CONTENT_SITEMAP);
		assert getText(ELEMENT_CONTENT_SITEMAP).contains(DATA_CONTENT_ACME.replace("${frequency}", "Always")):"Content of sitemap file is wrong";
		info("Content of file sitemap.xml when frequency = always is true");
	}
	
	/*case03: Check Sitemap with Frequency: Hourly
	 * 
	 */
	@Test
	public void test03_CheckSitemapFreHourly(){
		String DATA_DESCRIPTION = "ECMS_WCM_CEO_SiteMap_description_03";
		String DATA_KEYWORD = "ECMS_WCM_CEO_SiteMap_keyword_03";
		

		//create new CEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, DATA_KEYWORD, "INDEX", "FOLLOW", true, "Hourly", "1");
		clearCache();
		
		//go to site explorer
		goToAcmeSEO();
		
		//check file sitemap.xml
		goToNode(ELEMENT_SITEMAP_FILE);
		if (waitForAndGetElement(ELEMENT_EDIT_LINK) != null) {
			click(ELEMENT_EDIT_LINK);
		}
		waitForElementPresent(ELEMENT_CONTENT_SITEMAP);
		assert getText(ELEMENT_CONTENT_SITEMAP).contains(DATA_CONTENT_ACME.replace("${frequency}", "Hourly")):"Content of sitemap file is wrong";
		info("Content of file sitemap.xml when frequency = Hourly is true");
	}
	
	/*case04: Check Sitemap with Frequency: Daily
	 * 
	 */
	@Test
	public void test04_CheckSitemapFreDaily(){
		String DATA_DESCRIPTION = "ECMS_WCM_CEO_SiteMap_description_04";
		String DATA_KEYWORD = "ECMS_WCM_CEO_SiteMap_keyword_04";
		

		//create new CEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, DATA_KEYWORD, "INDEX", "FOLLOW", true, "Daily", "1");
		clearCache();
		
		//go to site explorer
		goToAcmeSEO();
		
		//check file sitemap.xml
		goToNode(ELEMENT_SITEMAP_FILE);
		if (waitForAndGetElement(ELEMENT_EDIT_LINK) != null) {
			click(ELEMENT_EDIT_LINK);
		}
		waitForElementPresent(ELEMENT_CONTENT_SITEMAP);
		assert getText(ELEMENT_CONTENT_SITEMAP).contains(DATA_CONTENT_ACME.replace("${frequency}", "Daily")):"Content of sitemap file is wrong";
		info("Content of file sitemap.xml when frequency = Daily is true");
	}
	
	/*case05: Check Sitemap with Frequency: Weekly
	 * 
	 */
	@Test
	public void test05_CheckSitemapFreWeekly(){
		String DATA_DESCRIPTION = "ECMS_WCM_CEO_SiteMap_description_05";
		String DATA_KEYWORD = "ECMS_WCM_CEO_SiteMap_keyword_05";
		

		//create new CEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, DATA_KEYWORD, "INDEX", "FOLLOW", true, "Weekly", "1");
		clearCache();
		
		//go to site explorer
		goToAcmeSEO();
		
		//check file sitemap.xml
		goToNode(ELEMENT_SITEMAP_FILE);
		if (waitForAndGetElement(ELEMENT_EDIT_LINK) != null) {
			click(ELEMENT_EDIT_LINK);
		}
		waitForElementPresent(ELEMENT_CONTENT_SITEMAP);
		assert getText(ELEMENT_CONTENT_SITEMAP).contains(DATA_CONTENT_ACME.replace("${frequency}", "Weekly")):"Content of sitemap file is wrong";
		info("Content of file sitemap.xml when frequency = Weekly is true");
	}
	
	/*case06: Check Sitemap with Frequency: Monthly
	 * 
	 */
	@Test
	public void test06_CheckSitemapFreMonthly(){
		String DATA_DESCRIPTION = "ECMS_WCM_CEO_SiteMap_description_06";
		String DATA_KEYWORD = "ECMS_WCM_CEO_SiteMap_keyword_06";
		

		//create new CEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, DATA_KEYWORD, "INDEX", "FOLLOW", true, "Monthly", "1");
		clearCache();
		
		//go to site explorer
		goToAcmeSEO();
		
		//check file sitemap.xml
		goToNode(ELEMENT_SITEMAP_FILE);
		if (waitForAndGetElement(ELEMENT_EDIT_LINK) != null) {
			click(ELEMENT_EDIT_LINK);
		}
		waitForElementPresent(ELEMENT_CONTENT_SITEMAP);
		assert getText(ELEMENT_CONTENT_SITEMAP).contains(DATA_CONTENT_ACME.replace("${frequency}", "Monthly")):"Content of sitemap file is wrong";
		info("Content of file sitemap.xml when frequency = Monthly is true");
	}
	
	/*case07: Check Sitemap with Frequency: Yearly
	 * 
	 */
	@Test
	public void test07_CheckSitemapFreYearly(){
		String DATA_DESCRIPTION = "ECMS_WCM_CEO_SiteMap_description_07";
		String DATA_KEYWORD = "ECMS_WCM_CEO_SiteMap_keyword_07";
		

		//create new CEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, DATA_KEYWORD, "INDEX", "FOLLOW", true, "Yearly", "1");
		clearCache();
		
		//go to site explorer
		goToAcmeSEO();
		
		//check file sitemap.xml
		goToNode(ELEMENT_SITEMAP_FILE);
		if (waitForAndGetElement(ELEMENT_EDIT_LINK) != null) {
			click(ELEMENT_EDIT_LINK);
		}
		waitForElementPresent(ELEMENT_CONTENT_SITEMAP);
		assert getText(ELEMENT_CONTENT_SITEMAP).contains(DATA_CONTENT_ACME.replace("${frequency}", "Yearly")):"Content of sitemap file is wrong";
		info("Content of file sitemap.xml when frequency = Yearly is true");
	}
	
	/*case08: Check Sitemap with Frequency: Never
	 * 
	 */
	@Test
	public void test08_CheckSitemapFreNever(){
		String DATA_DESCRIPTION = "ECMS_WCM_CEO_SiteMap_description_08";
		String DATA_KEYWORD = "ECMS_WCM_CEO_SiteMap_keyword_08";
		

		//create new CEO for acme home page
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, DATA_KEYWORD, "INDEX", "FOLLOW", true, "Never", "1");
		clearCache();
		
		//go to site explorer
		goToAcmeSEO();
		
		//check file sitemap.xml
		goToNode(ELEMENT_SITEMAP_FILE);
		if (waitForAndGetElement(ELEMENT_EDIT_LINK) != null) {
			click(ELEMENT_EDIT_LINK);
		}
		waitForElementPresent(ELEMENT_CONTENT_SITEMAP);
		assert getText(ELEMENT_CONTENT_SITEMAP).contains(DATA_CONTENT_ACME.replace("${frequency}", "Never")):"Content of sitemap file is wrong";
		info("Content of file sitemap.xml when frequency = Never is true");
	}
	
	/*case09: Alert message when user input invalid data 
	 * input priority number > 1 and alphabet
	 */
	@Test
	public void test09_CheckAlertWhenInputInvalidData(){
		String DATA_DESCRIPTION = "ECMS_WCM_CEO_SiteMap_description_09";
		String DATA_KEYWORD = "ECMS_WCM_CEO_SiteMap_keyword_09";
		

		//create new CEO for acme home page with priority is > 1
		goToSeoManagement();
		inputDataSeo(DATA_DESCRIPTION, DATA_KEYWORD, "INDEX", "FOLLOW", true, "Always", "2");
		checkAlertWarning("The field Priority is invalid float number. Valid values range from 0.0 to 1.0.");
		
		//create new CEO for acme home page with priority is alphabet
		inputDataSeo(DATA_DESCRIPTION, DATA_KEYWORD, "INDEX", "FOLLOW", true, "Always", "abc");
		checkAlertInfo("The field Priority is invalid float number. Valid values range from 0.0 to 1.0.");
		
	}
}
