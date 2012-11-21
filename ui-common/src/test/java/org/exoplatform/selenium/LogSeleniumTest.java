package org.exoplatform.selenium;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.*;



public class LogSeleniumTest extends EcmsBase{
	
	public static final String ELEMENT_USER = "root";
	public static final String ELEMENT_PASS = "gtn";
	
	public static final String DATA_CONTENT_FOLDER_TITLE = "Test";
	public static final String DATA_ARTICLE_TITLE= "Testabc";
	public static final String DATA_DOCUMENT_FOLDER_TITLE = "Test";
	public static final String DATA_FILE_DOCUMENT_TITLE = "Test";
	public static final String DATA_PODCAST_DOCUMENT_TITLE = "Test";
	public static final String ELEMENT_NEW_CONTENT_LINK_XPATH ="//a[@title = 'New Content']" ; 
	public static final String DATA_SAMPLE_NODE_TITLE = "Test";
	public static final String DATA_SAMPLE_NODE_IMG = "/home/lientm/test.jpg";
	public static final String DATA_FILE_PLAN_TITLE = "Test";
	public static final String DATA_KOFAX_TITLE = "Test";
	public static final String ELEMENT_UPLOAD_LINK_XPATH = "//a[@title='Upload']";
	public static final String ELEMENT_UPLOAD_FILE_NAME_ID = "name";
	public static final String DATA_UPLOAD_FILE_NAME = "Testabc";
	public static final String ELEMENT_UPLOAD_IMG_FRAME_XPATH = "//iframe[contains(@id,'uploadFrame')]";
	public static final String ELEMENT_UPLOAD_IMG_ID = "file";
	public static final String DATA_UPLOAD_FILE_LINK = "/home/lientm/test.jpg";
	public static final String ELEMENT_UPLOAD_SAVE_BUTTON_LINKTEXT = "Save";
	public static final String ELEMENT_UPLOAD_CLOSE_BUTTON_LINKTEXT ="Close";
	
	public static final String ELEMENT_NEW_ARTICLE_POPUP_XPATH = "//div[@class='UIPopupWindow UIDragObject ExoMessageDecorator']";
	public static final String ELEMENT_NEW_ARTICLE_MESSAGE_TITLE_BLANK = " The field \"Title\" is required.      ";
	public static final String ELEMENT_NEW_ARTICLE_MESSAGE_NAME_BLANK = " The field \"Name\" is required.      ";
	public static final String ELEMENT_NEW_ARTICLE_MESSAGE_SUMMARY_BLANK = "Summary is empty";
	public static final String ELEMENT_NEW_ARTICLE_MESSAGE_CONTENT_BLANK = "Content is empty";
	public static final String ELEMENT_LOCK_OPTION_XPATH = "//a[contains(text(),'Lock')]";
	public static final String ELEMENT_UNLOCK_OPTION_XPATH = "//a[contains(text(),'Unlock')]";
	
	 @BeforeMethod
	  public void beforeMethods() throws Exception {
	   initSeleniumTest();
	    actions = new Actions(driver);
	    driver.get(baseUrl);
	  }

	  @AfterMethod
	  public void afterMethods() throws Exception {
	    driver.manage().deleteAllCookies();
		driver.quit();
	    actions = null;
//	    String verificationErrorString = verificationErrors.toString();
//	    if (!"".equals(verificationErrorString)) {
//	      Assert.fail(verificationErrorString);
//	    }
	  }
	/*case1: Add Article document in Content folder
	* login
	* create a new content folder
	* add new article to content folder just add
	* delete content folder
	* logout
	*/
	@Test
	public void addArticleToContentFolder_01(){
		debug("start test .......");
		warn("warnig message");
		info("testing message");
		//login
		loginEcms(ELEMENT_USER, ELEMENT_PASS);
		//create new content folder
		assert false;
		logoutEcms();
	}
}
