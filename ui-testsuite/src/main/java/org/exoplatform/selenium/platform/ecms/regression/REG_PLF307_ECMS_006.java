package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.NavigationToolbar.goToEditPageEditor;
import static org.exoplatform.selenium.platform.ecms.ActionBar.ELEMENT_PUBLICATION;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.createNewArticle;
import static org.exoplatform.selenium.platform.ecms.PageEditor.addContentListEmptyLayout;
import static org.exoplatform.selenium.platform.ecms.PageEditor.goToPageEditor_EmptyLayout;
import static org.exoplatform.selenium.platform.ecms.PageEditor.selectCLVPath;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.addView_withName;
import static org.exoplatform.selenium.platform.ecms.functional.wcm.viewer.listcontent.ECMS_WCM_Viewer_ListContent_EditIcon.*;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.functional.wcm.viewer.listcontent.ECMS_WCM_Viewer_ListContent_EditIcon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF307_ECMS_006 extends EcmsBase{
	public static final String DATA_USER = "root";
	public static final String DATA_PASS = "gtn";
	public static By ELEMENT_RELATION_TAB = By.linkText("Relation");
	public static By ELEMENT_GENERAL_DRIVER = By.xpath("//a[contains(text(),'General Drives')]");
	public static By ELEMENT_SITE_MANAGEMENT=By.xpath("//a[contains(text(),'Sites Management')]");
	//publication form
	public static By ELEMENT_PUBLIC_STATUS = By.xpath("//a[contains(text(), 'Published')]");
	public static By ELMENET_CURRENT_STATUS = By.xpath("//a[@class='CurrentStatus']");
	public static By ELEMENT_CURRENT_PUBLIC_STATUS = By.xpath("//a[@class='CurrentStatus' and contains(text(), 'Published')]");
	public static By ELEMENT_PUBLICATION_TAB_LINK= By.xpath("//a[contains(text(),'Publication')]");

	
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
	 * Goto Content Explorer
	 * Create new article
	 * Save as drafs
	 * Manage relation
	 * Add related link
	 * Publication
	 * Publish this article
	 * Create new Page
	 * Choose article as content
	 * Click on the link
	 * Expected: The related links are not clickable : code source displayed and if there is no related documents, label link is still displayed
	 */
	@Test
	public void test_displayContentPortlet(){
		String ELEMENT_NEW_VIEW="Relation";
		String ELEMENT_ARTICLE_TITLE="REG_PLF307_ECMS_006";
		String ELEMENT_ARTICLE_RELATION_TITLE="REG_PLF307_ECMS_006_RELATION";
		String ELEMENT_ATICLE_SUMMARY="contents";
		By ELEMENT_NEW_VIEW_TAB = By.xpath("//a[contains(text(),'"+ELEMENT_NEW_VIEW+"')]");
		By ELEMENT_NEW_ARTICLE = By.xpath("//a[contains(text(),'"+ELEMENT_ARTICLE_TITLE+"')]");
		By ELEMENT_RELATIONS_LINK = By.linkText("Relations");
		By ELEMENT_RELATION_TAB = By.xpath("//div[contains(text(),'Select Relation')]");
		By ELEMENT_COLLABORATION_DRIVER = By.xpath("//a[@class='DriveLabel' and @title = 'collaboration']");
		By ELEMENT_SELECT_RELATION=By.xpath("//div[@class='Select16x16Icon' and contains(@style,'margin-left:15px;')]");
		By ELEMENT_CLOSE_BUTTON = By.linkText("Close");
		Map<String, String> ELEMENT_PORTLET_ID = new HashMap<String, String>();
		ELEMENT_PORTLET_ID.put("Content/ContentListViewerPortlet","");
		String namePage="REG_PLF307_ECMS_006_PAGE";
		String pathContent = "General Drives/Sites Management/REG_PLF307_ECMS_006";

		//Go to Site Explorer
		goToSiteExplorer();
		//Choose ACME Driver
//		chooseDrive(ELEMENT_ACME_DRIVER);
		//Add new content
		goToAddNewContent();
		//Create an article
		createNewArticle(ELEMENT_ARTICLE_TITLE, ELEMENT_ARTICLE_TITLE, ELEMENT_ATICLE_SUMMARY, ELEMENT_ATICLE_SUMMARY);
		//Choose Collaboration driver
		chooseDrive(ELEMENT_COLLABORATION_DRIVER);
		//Add new content
		goToAddNewContent();
		//Create an article
		createNewArticle(ELEMENT_ARTICLE_RELATION_TITLE, ELEMENT_ARTICLE_RELATION_TITLE, ELEMENT_ATICLE_SUMMARY, ELEMENT_ATICLE_SUMMARY);
		//Add view to WCM view
		addView_withName(ELEMENT_NEW_VIEW,"WCM View", "Add Tab","manageRelations");
		info("Logout ECMS");
		logoutEcms();
		info("Login ECMS with "+DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
		//Go to Site Explorer
		goToSiteExplorer();
		//Choose the created article
		click(ELEMENT_NEW_ARTICLE);
		//Click to Relation tab view on action bar
		click(ELEMENT_NEW_VIEW_TAB);
		//Click to Relations link
		click(ELEMENT_RELATIONS_LINK);
		click(ELEMENT_RELATION_TAB);
		//Add relation for the created document
		click(ELEMENT_SELECT_RELATION);
		//Close add link for document
		click(ELEMENT_CLOSE_BUTTON);
		//Public document
		click(ELEMENT_PUBLICATION_TAB_LINK);
		publishDocument();
		//Go to ACME Home Page
		goToOverView();
		//Go to Add Page Editor with Editor
		goToPageEditor_EmptyLayout(namePage);
		// Create CLV page
		addContentListEmptyLayout();
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);
		//Edit CLV page
		goToEditPageEditor();
		selectCLVPath(pathContent,"article","content");
		click(ELEMENT_PAGE_EDIT_FINISH);
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_FINISH);
		captureScreen("REG_PLF307_ECMS_006");
	}
//	public static void publishDocument(){
//		info("Public this document");
//		click(ELEMENT_PUBLICATION_TAB_LINK);
//		waitForElementPresent(ELEMENT_PUBLICATION);
//		click(ELEMENT_PUBLICATION);
//		WebElement current = waitForAndGetElement(ELMENET_CURRENT_STATUS);
//		if (current.getText().contains("Published") == false){
//			click(ELEMENT_PUBLIC_STATUS);
//		}
//		waitForElementPresent(ELEMENT_CURRENT_PUBLIC_STATUS);
//		save();
//		info("Public document is successful");
//	}
}
