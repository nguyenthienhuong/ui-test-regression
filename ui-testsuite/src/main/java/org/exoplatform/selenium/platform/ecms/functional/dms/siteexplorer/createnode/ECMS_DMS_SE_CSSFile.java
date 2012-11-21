package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.createnode;

//import java.util.concurrent.TimeUnit;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;

/*
 * @author: Lientm
 * @date: 9/2012
 */

public class ECMS_DMS_SE_CSSFile extends EcmsBase{

	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";

	public static final By ELEMENT_ACME_LINK = By.xpath("//a[@title='acme ']");
	public static final By ELEMENT_CSS_LINK = By.xpath("//a[@title='css ']");
	public static final By ELEMENT_CSS_NAME = By.id("name");
	public static final By ELEMENT_CSS_PRIOR = By.id("CSSpriority");
	public static final By ELEMENT_CSS_DATA	= By.id("contentHtml");
	public static final By ELEMENT_BODY = By.xpath("//body");
	public static final By ELEMENT_MYSITE_LINK = By.xpath("//a[contains(text(),'My Sites')]");
	public static final By ELEMENT_ACME_SITE_LINK = By.linkText("acme");
	public static final By ELEMENT_SHARED_LINK = By.xpath("//a[@title='shared ']");
	public static final By ELEMENT_INTRANET_SITE_LINK = By.linkText("intranet");

	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login to ECMS with user: "+DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() throws Exception {
		info("Logout to ECMS");
		logoutEcms();
		driver.manage().deleteAllCookies();
		driver.quit();
		actions = null;
	}

	/*case1: Create CSS file on sites explorer with Active is True and the priority is highest
	 * go to acme/css
	 * create new css file: active =true, set priority lowest
	 * delete css file
	 */
	@Test
	public void test01_CreateCssFileActive(){
		String DATA_CSS_NAME = "ECMS_DMS_SE_CSSFile_01";
		By ELEMENT_CSS = By.xpath("//a[@title='"+DATA_CSS_NAME+" "+"']");

		//go to acme/css
		goToSiteExplorer();
		info("Go to acme/css");
		goToNode(ELEMENT_ACME_LINK);
		goToNode(ELEMENT_CSS_LINK);
		//create new css file
		goToAddNewContent();

		debug("Create new css file with name: "+DATA_CSS_NAME);
		createNewCssFile(DATA_CSS_NAME, "10", "body {color:red;}");
		waitForElementPresent(ELEMENT_CSS);

		info("Create new css file successfully");
		//check CSS file must be affected to interface
		mouseOver((ELEMENT_MYSITE_LINK), true);
		click(ELEMENT_ACME_SITE_LINK);
		clearCache();
		WebElement body = waitForAndGetElement(ELEMENT_BODY);
		assert body.getCssValue("color").contains("rgba(255,0,0,1)"):"Setting up for page is not right";	  
		//delete css file
		goToSiteExplorer();
		goToNode(ELEMENT_ACME_LINK);
		goToNode(ELEMENT_CSS_LINK);
		goToNode(ELEMENT_CSS);
		deleteDocument(ELEMENT_CSS);
	}

	/*case2: Check CSS priority on Sites Explorer when have two CSS files which have the different priority level.
	 * go to acme/css
	 * create new 2 css file different priority level
	 * check the interface of acme site will be defined follow the file which has the lower value in Priority
	 * delete css file
	 */
	@Test
	public void test02_CheckSiteWhen2FileCssDifferentPriority(){
		String DATA_CSS_NAME = "ECMS_DMS_SE_CSSFile_02";
		String DATA_CSS_NAME_2 = "ECMS_DMS_SE_CSSFile_02_2";
		By ELEMENT_CSS = By.xpath("//a[@title='"+DATA_CSS_NAME+" "+"']");
		By ELEMENT_CSS_2 = By.xpath("//a[@title='"+DATA_CSS_NAME_2+" "+"']");

		//go to acme/css
		goToSiteExplorer();
		info("Go to acme/css");
		goToNode(ELEMENT_ACME_LINK);
		goToNode(ELEMENT_CSS_LINK);
		//create new css file with name = CssFiletest_02, priority = 20
		goToAddNewContent();
		debug("Create new css file with name: "+DATA_CSS_NAME+" and Priority: 20");
		createNewCssFile(DATA_CSS_NAME, "20", "body {color:blue;}");
		waitForElementPresent(ELEMENT_CSS);
		info("Create new css file 1 successfully");
		//create new css file with name = CssFiletest_02_2, priority = 21
		goToNode(ELEMENT_CSS_LINK);
		goToAddNewContent();
		debug("Create new css file with name: "+DATA_CSS_NAME_2+" and Priority: 21");
		createNewCssFile(DATA_CSS_NAME_2, "30", "body {color:red;}");
		waitForElementPresent(ELEMENT_CSS_2);

		info("Create new css file 2 successfully");
		//check the interface of acme site will be displayed following css file with the highest Priority
		mouseOver((ELEMENT_MYSITE_LINK), true);
		click(ELEMENT_ACME_SITE_LINK);
		clearCache();
		WebElement body = waitForAndGetElement(ELEMENT_BODY);
		assert body.getCssValue("color").contains("rgba(0,0,255,1)"):"Setting up for page is not right";
		//delete css file
		goToSiteExplorer();
		goToNode(ELEMENT_CSS);
		deleteDocument(ELEMENT_CSS);

		goToNode(ELEMENT_CSS_2);
		deleteDocument(ELEMENT_CSS_2);
	}

	/*case03: Check CSS priority on Sites Explorer when having two CSS files which have the different content.
	 * go to acme/css
	 * create new 2 css file different content
	 * check The interface of site will be defined by combining CSS1 and CSS2
	 * delete css file
	 * logout
	 */
	@Test
	public void test03_CheckSiteWhen2CssDifferentContent(){
		String DATA_CSS_NAME = "ECMS_DMS_SE_CSSFile_03";
		String DATA_CSS_NAME_2 = "ECMS_DMS_SE_CSSFile_03_2";
		By ELEMENT_CSS = By.xpath("//a[@title='"+DATA_CSS_NAME+" "+"']");
		By ELEMENT_CSS_2 = By.xpath("//a[@title='"+DATA_CSS_NAME_2+" "+"']");

		//go to acme/css
		goToSiteExplorer();
		info("Go to acme/css");
		goToNode(ELEMENT_ACME_LINK);
		goToNode(ELEMENT_CSS_LINK);
		//create new css file with name = CssFiletest_03, content = body {color:blue;}
		goToAddNewContent();
		debug("Create new css file with name: "+ELEMENT_CSS_NAME+" and content: body {color:blue;}");
		createNewCssFile(DATA_CSS_NAME, "20", "body {color:blue;}");
		waitForElementPresent(ELEMENT_CSS);
		info("Create new css file 1 successfully");
		//create new css file with name = CssFiletest_03_2, content = 21
		goToNode(ELEMENT_CSS_LINK);
		goToAddNewContent();
		debug("Create new css file with name: "+DATA_CSS_NAME_2+" and Priority: 21");
		createNewCssFile(DATA_CSS_NAME_2, "30", "background{color:gray;}");
		waitForElementPresent(ELEMENT_CSS_2);
		info("Create new css file 2 successfully");
		//check the interface of acme site will be defined follow the file which has the highest value in Priority
		mouseOver((ELEMENT_MYSITE_LINK), true);
		click(ELEMENT_ACME_SITE_LINK);
		clearCache();
		WebElement body = waitForAndGetElement(ELEMENT_BODY);
		assert body.getCssValue("color").contains("rgba(0,0,255,1)"):"Set up for page is not right";

		//delete css file
		goToSiteExplorer();
		goToNode(ELEMENT_CSS);
		deleteDocument(ELEMENT_CSS);

		goToNode(ELEMENT_CSS_2);
		deleteDocument(ELEMENT_CSS_2);
	}

	/*case04: Check the affection of CSS file in Share site
	 * goto shared
	 * create new CSS file
	 * check 
	 */
	@Test
	public void test04_CreateCssInShared(){
		String DATA_CSS_NAME = "ECMS_DMS_SE_CSSFile_04";
		By ELEMENT_CSS = By.xpath("//a[@title='"+DATA_CSS_NAME+" "+"']");

		//go to acme/css
		goToSiteExplorer();
		info("Go to acme/css");
		goToNode(ELEMENT_SHARED_LINK);
		goToNode(ELEMENT_CSS_LINK);
		//create new css file with name = CssFiletest_04, content = body {color:blue;}
		goToAddNewContent();
		debug("Create new css file with name: "+ELEMENT_CSS_NAME);
		createNewCssFile(DATA_CSS_NAME, "0", "body {color:blue;}");
		waitForElementPresent(ELEMENT_CSS);
		info("Create new css file successfully");
		//check CSS file must be affected to interface of intranet
		mouseOver((ELEMENT_MYSITE_LINK), true);
		click(ELEMENT_INTRANET_SITE_LINK);
		clearCache();
		WebElement body_intranet = waitForAndGetElement(ELEMENT_BODY);
		assert body_intranet.getCssValue("color").contains("rgba(0,0,255,1)"):"Set up for page intranet is not right";
		//check CSS file must be affected to interface of acme
		mouseOver((ELEMENT_MYSITE_LINK), true);
		click(ELEMENT_ACME_SITE_LINK);
		clearCache();
		WebElement body_acme = waitForAndGetElement(ELEMENT_BODY);
		assert body_acme.getCssValue("color").contains("rgba(0,0,255,1)"):"Set up for page acme is not right";

		//delete css file
		goToSiteExplorer();
		goToNode(ELEMENT_SHARED_LINK);
		goToNode(ELEMENT_CSS_LINK);
		goToNode(ELEMENT_CSS);
		deleteDocument(ELEMENT_CSS);
	}

	public void createNewCssFile(String name, String prior, String data){
		type(ELEMENT_CSS_NAME, name, false);
		type(ELEMENT_CSS_PRIOR, prior, false);
		type(ELEMENT_CSS_DATA, data, true);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}
}