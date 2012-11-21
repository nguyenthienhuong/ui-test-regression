package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF307_ECMS_001 extends EcmsBase {
	
	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";
	public static final By ELEMENT_ARTICLE_TITLE_TEXTBOX = By.id("title");
	public static final By ELEMENT_ARTICLE_LINK = By.xpath("//div[@title='Article']");
	public static final By ELEMENT_ARTICLE_NAME_TEXTBOX = By.id("name");
	public static final By ELEMENT_SUMMARY_SOURCE_BUTTON = By.id("cke_9_label");
	public static final By ELEMENT_CONTENT_SOURCE_BUTTON = By.id("cke_45_label");
	public static final By ELEMENT_ARTICLE_SUMMARY_SOURCE_FRAME = By.xpath("//td[@id='cke_contents_summary']/textarea");
	public static final By ELEMENT_ARTICLE_CONTENT_FRAME = By.xpath("//td[@id='cke_contents_content']/textarea");
	public static final By ELEMENT_VIEW_PROPERTIES_LINK =By.linkText("Properties");
	public static final By ELEMENT_SYSTEM_LINK =By.linkText("System");
	
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
	/* Case: View Properties" Component 
	 * Go to Site Explorer
	 * Select article template
	 * Create an article
	 * In summary field, click source 
	 * Paste the HTML content in file: https://jira.exoplatform.org/secure/attachment/39483/html.txt
	 * Save the content
	 * Click on "View Node Properties"
	 * Refresh the browser window
	 */
	@Test
	public void test_ViewPropertiesComponent(){
		String DATA_ARTICLE_TITLE = "REG_PLF307_ECMS_001";
		By ELEMENT_ARTICLE = By.xpath("//a[@title='" + DATA_ARTICLE_TITLE +" " + "']");
		String DATA_SUMMARY_FIELD = "<p>New article to test </p> <ul> <li> test list 1 </li> <li>test list 2</li> <ul>";
		//Go to Site Explorer
		goToSiteExplorer();
		//Add new Content
		goToAddNewContent();
		//Create an article
		createNewArticleUseSource(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_SUMMARY_FIELD, DATA_SUMMARY_FIELD);
		waitForElementPresent(ELEMENT_ARTICLE);
		
	}

	private void createNewArticleUseSource(String title,
			String name, String sum,
			String cont) {
		click(ELEMENT_ARTICLE_LINK);
		// Input information
		type(ELEMENT_ARTICLE_TITLE_TEXTBOX,title,false);
		type(ELEMENT_ARTICLE_NAME_TEXTBOX, name, true);
		//Click to the Source button on FCKEditor of Summary field
		click(ELEMENT_SUMMARY_SOURCE_BUTTON);
		type(ELEMENT_ARTICLE_SUMMARY_SOURCE_FRAME, sum, false);
		switchToParentWindow();
		//Click to the Source button on FCKEditor of Content field
		click(ELEMENT_CONTENT_SOURCE_BUTTON);
		type(ELEMENT_ARTICLE_CONTENT_FRAME, cont, false);
		switchToParentWindow();
		//save
		click(ELEMENT_SAVE_CLOSE_BUTTON);
		//View the properties
		//Click to system link
		click(ELEMENT_SYSTEM_LINK);
		click(ELEMENT_VIEW_PROPERTIES_LINK);
		waitForElementPresent(ELEMENT_VIEW_PROPERTIES_LINK);
		captureScreen("test_ViewPropertiesComponent_REG_PLF307_ECMS_001.png");
		
	}
}
