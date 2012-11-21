package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.createNewArticle;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF307_ECMS_005 extends EcmsBase{
	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";
	@BeforeMethod
	public void beforeMethods() throws Exception {
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
	/*
	 * - Go to Content Explorer,
	 * - Choose a drive
	 * - Create some documents with titles or content contain *, ~, +, - characters
	 * - Fill in simple search box above action bar with words containing * or ~ character
	 * - Click Search or press Enter
	 * - Click Saved search then Advanced search- Fill in search by name tab title of document with + or – characters
	 * EXPECTED RESULT:
	 * - All document with title or content containing these characters are listed in search result
	 * - All document with title or content containing these characters are listed in search result and no exception
	 */
	@Test
	public void test_searchSpecialCharacters(){
		By ELEMENT_COLLABORATION_DRIVER = By.xpath("//a[@class='DriveLabel' and @title = 'collaboration']");
		String ELEMENT_ARTICLE_TITLE_1="La v* en Ro~e";
		String ELEMENT_ARTICLE_TITLE_2="PLF_307";
		String ELEMENT_ARTICLE_TITLE_3="TEST+WEBDRIVER&AUTOMATION";
		String ELEMENT_ATTICLE_NAME_1="article1";
		String ELEMENT_ATTICLE_NAME_2="article2";
		String ELEMENT_ATTICLE_NAME_3="article3";
		String ELEMENT_ATICLE_SUMMARY_1="L#^$@";
		String ELEMENT_ATICLE_SUMMARY_2="*()!@";
		String ELEMENT_ATICLE_SUMMARY_3="&*%$#@()_*&@#(@)@#)@#";
		//Go to Site Explorer
		goToSiteExplorer();
		//Choose Site Management drive
		chooseDrive(ELEMENT_COLLABORATION_DRIVER);
		//Create some documents
		goToAddNewContent();
		//Create an article 1
		createNewArticle(ELEMENT_ARTICLE_TITLE_1, ELEMENT_ATTICLE_NAME_1, ELEMENT_ATICLE_SUMMARY_1, ELEMENT_ATICLE_SUMMARY_1);
		//Create an article 2
		chooseDrive(ELEMENT_COLLABORATION_DRIVER);
		goToAddNewContent();
		//Create an article
		createNewArticle(ELEMENT_ARTICLE_TITLE_2, ELEMENT_ATTICLE_NAME_2, ELEMENT_ATICLE_SUMMARY_2, ELEMENT_ATICLE_SUMMARY_2);
		//Create an article 3
		chooseDrive(ELEMENT_COLLABORATION_DRIVER);
		goToAddNewContent();
		createNewArticle(ELEMENT_ARTICLE_TITLE_3, ELEMENT_ATTICLE_NAME_3, ELEMENT_ATICLE_SUMMARY_3, ELEMENT_ATICLE_SUMMARY_3);
		//Simple search
		simpleSearch(ELEMENT_ARTICLE_TITLE_1);
		//Capture screen
		captureScreen("REG_PLF307_ECMS_005_1");
		//Search for title 2
		simpleSearch(ELEMENT_ARTICLE_TITLE_2);
		captureScreen("REG_PLF307_ECMS_005_2");
		//Search for title 3
		simpleSearch(ELEMENT_ARTICLE_TITLE_3);
		captureScreen("REG_PLF307_ECMS_005_3");
		//Search for content 1
		simpleSearch(ELEMENT_ATICLE_SUMMARY_1);
		captureScreen("REG_PLF307_ECMS_005_4");
		//Search for content 2
		simpleSearch(ELEMENT_ATICLE_SUMMARY_2);
		captureScreen("REG_PLF307_ECMS_005_5");
		//Search for content 3
		simpleSearch(ELEMENT_ATICLE_SUMMARY_3);
		captureScreen("REG_PLF307_ECMS_005_6");
		//Advanced Search
		goToAdvancedSearch();
		//Capture screen
	}

}
