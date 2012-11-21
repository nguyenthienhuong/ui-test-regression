package org.exoplatform.selenium.platform.ecms;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.TestLogger.*;

public class SiteExplorer extends EcmsBase {
	//Show Drives link
	public static final By ELEMENT_SHOW_DRIVES = By.xpath("//a[@title='Show Drives']");

	//Driver Sites Management in Sites Explorer
	public static final By ELEMENT_ACME_DRIVER = By.linkText("acme-category");
	public static final By ELEMENT_SITES_MANAGEMENT_DRIVE = By.xpath("//a[@class='DriveLabel' and @title = 'Sites Management']");
	public static final By ELEMENT_DMS_ADMIN_DRIVE = By.linkText("DMS Administration");

	// Preference
	public static final By ELEMENT_PREFERENCE_LINK =By.xpath("//a[@class='SetupPreferencesButton']");

	// Edit Tag Form
	public static By ELEMENT_TAG_COULD = By.xpath("//div[@class='ItemIcon DefaultIcon TagExplorerIcon']");
	public static By ELEMENT_EDIT_PUBLIC_TAG = By.xpath("//div[@title='Edit Public Tags']");
	public static By ELEMENT_EDIT_PRIVATE_TAG = By.xpath("//div[@title='Edit Private Tags']");
	public static String REMOVE_TAG = "//div[text()='${TagsName}']/../../td/div/img[@title='Remove Tag']";
	public static By ELEMENT_CLOSE_EDIT_PUBLIC_TAG_FORM = By.xpath("//a[@title='Close Window']");
	public static String MESSAGE_WARNING_AFTER_DELETE_TAG = "Are you sure to delete this tag?";
	//public static By ELEMENT_TAG_CLOUD = By.xpath("//div[@title='Tag Cloud']");
	//public static By ELEMENT_EDIT_PUBLIC_TAGS = By.xpath("//div[@title='Edit Public Tags']");
	public static By ELEMENT_EDIT_TAGS_FORM = By.xpath("//span[contains(text(),'Edit Tag')]");
	public static final By ELEMENT_MANAGE_TAGS = By.linkText("Manage Tags");
	public static final By ELEMENT_TAG_PERMISSION = By.xpath("//div[contains(text(),'Tag Permission Manager')]");

	//Tag Manager Form
	public static By ELEMENT_ADD_TAGS_BUTTON = By.xpath("//a[contains(text(),'Add Tags')]");
	public static By ELEMENT_CLOSE_TAG_FORM = By.xpath("//a[contains(text(),'Close')]");
	public static By ELEMENT_TAG_NAME = By.id("names");
	public static By ELEMENT_TAG_SCOPE = By.id("tagScopes");

	//SideBar
	public static By ELEMENT_TAG_CLOUD = By.xpath("//div[3]/div[4]/div");
	public static By ELEMENT_FILE_EXPLORER = By.xpath("//div[@id='UISideBar']/div/div/div[3]/div/div");
	public static By ELEMENT_SAVED_SEARCH_ICON = By.xpath("//div[@title='Saved Searches']");

	//Advanced search form
	public static By ELEMENT_ADVANCED_SEARCH_ICON = By.xpath("//div[@title='Advanced Search']");
	public static By ELEMENT_ADVANCED_SEARCH_TAB = By.xpath("//div[contains(text(),'Advanced Search')]");

	//Simple Search
	public static final By ELEMENT_SIMPLESEARCH_TEXTBOX = By.id("simpleSearch");
	public static final By ELEMENT_SIMPLESEARCH_SUBMIT = By.id("SimpleSearch");

	//publication form
	public static By ELEMENT_PUBLIC_STATUS = By.xpath("//a[contains(text(), 'Published')]");
	public static By EMENET_CURRENT_STATUS = By.xpath("//a[@class='CurrentStatus']");
	public static By ELEMENT_CURRENT_PUBLIC_STATUS = By.xpath("//a[@class='CurrentStatus' and contains(text(), 'Published')]");
	
	//Choose a drive
	public static void chooseDrive(By locator)
	{
		waitForAndGetElement(ELEMENT_SHOW_DRIVES).click();
		waitForAndGetElement(locator).click();	
	}

	//Enable preferences option
	public static void checkPreferenceOption(String optionId){
		goToNode(ELEMENT_PREFERENCE_LINK);
		click(By.linkText("Advanced"));
		pause(500);
		WebElement check = waitForAndGetElement(By.id(optionId));
		if (check.isSelected()!= true){
			check.click();
		}
		click(By.linkText("Save"));
	}

	//Simple search
	public static boolean simpleSearch(String keyword){
		boolean delete = true;
		//		waitForAndGetElement(ELEMENT_SIMPLESEARCH_TEXTBOX).clear();
		type(ELEMENT_SIMPLESEARCH_TEXTBOX, keyword, true);
		click(ELEMENT_SIMPLESEARCH_SUBMIT);
		if (isElementPresent(By.xpath("//div[contains(text(),'"+keyword+"')]")))
		{
			return delete;}
		else {
			delete = false;
			return delete;}
	}

	//Simple search not return result
	public static boolean notSimpleSearch(String keyword) {
		return !simpleSearch(keyword);
	}

	public static void addTagForNode(String name, boolean isPublic) {
		// Go to collaboration tab
		info("Go to Collaboration tab");
		goToCollaboration();

		// Click Tags
		waitForElementPresent(ELEMENT_TAG);
		click(ELEMENT_TAG);

		// Input information
		type(ELEMENT_TAG_NAME, name, true);

		// Save
		if (isPublic){
			selectOption(ELEMENT_TAG_SCOPE, "Public");
		} else 
			selectOption(ELEMENT_TAG_SCOPE, "Private");
		click(ELEMENT_ADD_TAGS_BUTTON);

		//Verify new tag
		waitForElementPresent(By.linkText(name));

		//Close tag form
		click(ELEMENT_CLOSE_TAG_FORM);

		//Verify add new tag
		click(ELEMENT_TAG_CLOUD);

		//			waitForTextPresent("Private Tags");
		waitForTextPresent(name);
	}

	public static void deleteTag(String name, boolean isPublic){
		// Delete tags
		By ELEMENT_REMOVE_TAG = By.xpath(REMOVE_TAG.replace("${TagsName}", name));
		if (isPublic){
			waitForElementPresent(ELEMENT_EDIT_PUBLIC_TAG);
			click(ELEMENT_EDIT_PUBLIC_TAG);
		}
		else {
			waitForElementPresent(ELEMENT_EDIT_PRIVATE_TAG);
			click(ELEMENT_EDIT_PRIVATE_TAG);
		}
		waitForElementPresent(ELEMENT_REMOVE_TAG);
		click(ELEMENT_REMOVE_TAG);
		waitForConfirmation(MESSAGE_WARNING_AFTER_DELETE_TAG);
		click(ELEMENT_CLOSE_EDIT_PUBLIC_TAG_FORM);
		waitForTextNotPresent(name);
	}
	
	/**
	 * Remove permission 
	 * @param groupPath Group string separated by slash
	 * @param membership Membership 
	 */
	public static void removeTagPermission(String groupPath,String membership) {
		String str = membership + ":/" + groupPath;
		By locator = By.xpath("//div[@class='Text' and contains(text(),'" + str + "')]/ancestor::tr//img[@class='DeleteIcon']");
		By permission = By.xpath("//div[@class='Text' and contains(text(),'" + str + "')]");
		click(locator);
		waitForConfirmation("Are you sure to delete this permission?");
		waitForElementNotPresent(permission);
	}

	public static void editPublicTag(){
		goToSiteExplorer();     
		waitForElementPresent(ELEMENT_TAG_CLOUD);
		click(ELEMENT_TAG_CLOUD);
		waitForElementPresent(ELEMENT_EDIT_PUBLIC_TAG);
		click(ELEMENT_EDIT_PUBLIC_TAG);
		waitForElementPresent(ELEMENT_EDIT_TAGS_FORM);
	}

	//Go to advanced search in content explorer
	public static void goToAdvancedSearch(){
		click(ELEMENT_SAVED_SEARCH_ICON);
		click(ELEMENT_ADVANCED_SEARCH_ICON);
		click(ELEMENT_ADVANCED_SEARCH_TAB);
	}
	
	//Go to Tag Permission Screen
	public static void gotoTagPermission(){
		goToContentAdministration();
		click(ELEMENT_MANAGE_TAGS);
		click(ELEMENT_TAG_PERMISSION);
	}
	
	//function public a document
	public static void publicDocument(){
		info("Public this document");
		waitForElementPresent(ELEMENT_PUBLICATION);
		click(ELEMENT_PUBLICATION);
		WebElement current = waitForAndGetElement(EMENET_CURRENT_STATUS);
		if (current.getText().contains("Published") == false){
			click(ELEMENT_PUBLIC_STATUS);
		}
		waitForElementPresent(ELEMENT_CURRENT_PUBLIC_STATUS);
		save();
		info("Public document is successful");
	}
}
