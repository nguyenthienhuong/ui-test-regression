package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.createnode;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;


public class ECMS_DMS_SE_PictureOnHeadLayoutContent extends EcmsBase {
	public static String USER = "john";
	public static String PASS = "gtn";
	public static final By ELEMENT_DRIVE_SITE_MANAGE= By.xpath("//a[@title='Sites Management']");
	public static final String DATA_ACME_WEB_CONTENT="acme/web contents";
	public static final String DATA_PIC_NAME_01="FNC_ECMS_FEX_CREATE_15_01";
	public static final String DATA_PIC_TITLE_01="FNC_ECMS_FEX_CREATE_15_01";
	public static final String DATA_PIC_FILE_01="TestData/FNC_ECMS_FEX_CREATE_15_01.png";
	public static final String DATA_PIC_NAME_02="";
	public static final String DATA_PIC_TITLE_02="";
	public static final String DATA_PIC_FILE_02="TestData/FNC_ECMS_FEX_CREATE_15_01.png";
	public static final By ELEMENT_PIC_LANGUGE_ID= By.id("content-lang");
	
	public void createNewPictureOnHeadLayoutWithLanguage(String name, String title, String file, String lang){
		click(ELEMENT_HEAD_LAYOUT_LINK);
		type(ELEMENT_HEAD_LAYOUT_NAME_TEXTBOX, name, false);
		type(ELEMENT_HEAD_LAYOUT_TITLE_TEXTBOX, title, false);
		if (file!=""){
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_HEAD_LAYOUT_UPLOAD_FRAME));
		type(ELEMENT_HEAD_LAYOUT_UPLOAD_FILE, getAbsoluteFilePath(file), false);
		switchToParentWindow();
		waitForElementPresent(ELEMENT_UPLOAD_FINISH_XPATH);
		selectOption(ELEMENT_PIC_LANGUGE_ID, lang);
		}
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}
	
	//add picture on head layout content
	@Test
	public void test01_AddPictureOnHeadLayoutContent() {
		By bPic= By.xpath("//a[@title='"+ DATA_PIC_TITLE_01+ " ']");

		info("Add picture on head layout content");
		chooseDrive(ELEMENT_DRIVE_SITE_MANAGE);
		//go to acme/web contents
		goToNodeByPath(DATA_ACME_WEB_CONTENT);
		goToAddNewContent();

		//create new picture on head layout
		createNewPictureOnHeadLayout(DATA_PIC_NAME_01, DATA_PIC_TITLE_01, DATA_PIC_FILE_01);
		waitForElementPresent(bPic);

		//delete picture on head layout content
		deleteDocument(bPic);
	}
	//add picture on head layout content with blank required fields
	@Test
	public void test02_AddPictureOnHeadLayoutContentWithBlankField() {
		
		info("Add picture on head layout content with blank required fields");
		chooseDrive(ELEMENT_DRIVE_SITE_MANAGE);
		//go to acme/web contents
		goToNodeByPath(DATA_ACME_WEB_CONTENT);
		goToAddNewContent();

		//create new picture on head layout
		createNewPictureOnHeadLayout(DATA_PIC_NAME_02, DATA_PIC_TITLE_02, DATA_PIC_FILE_02);
		waitForElementPresent("//span[contains(text(),'The field \"Name\" is required.')]");
		waitForElementPresent("//span[contains(text(),'The field \"Title\" is required.')]");		    
	}
	
	//add picture on head layout content in different language
	@Test
	public void test04_AddPictureOnHeadLayoutContentInDifferentLanguage() {
		By bPic= By.xpath("//a[@title='"+ DATA_PIC_TITLE_01+ " ']");

		info("Add picture on head layout content");
		chooseDrive(ELEMENT_DRIVE_SITE_MANAGE);
		//go to acme/web contents
		goToNodeByPath(DATA_ACME_WEB_CONTENT);
		goToAddNewContent();

		//create new picture on head layout
		createNewPictureOnHeadLayoutWithLanguage(DATA_PIC_NAME_01, DATA_PIC_TITLE_01, DATA_PIC_FILE_01, "fr");
		waitForElementPresent(bPic);

		//delete picture on head layout content
		deleteDocument(bPic);
	}

	@BeforeMethod
	public void beforeMethod() {
		initSeleniumTest();
		driver.get(baseUrl);

		actions = new Actions(driver);
		loginEcms(USER, PASS);
		goToSiteExplorer();
	}

	@AfterMethod
	public void afterMethod() {
		logoutEcms();
		driver.quit();
	}

}
