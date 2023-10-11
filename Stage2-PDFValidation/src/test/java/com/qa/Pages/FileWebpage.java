package com.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FileWebpage 
{
	WebDriver driver;
	
	public FileWebpage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@class='btn btn-outline btn-xl page-scroll']")
	WebElement BrowserFileButton;
	public void  clickBrowseFileButton()
	{
		BrowserFileButton.click();
	}
	
	@FindBy(xpath="//td[contains(text(),'Portable Document Format')]/following-sibling::td//a")
	WebElement DocumentsButton;
	public void clickDocumentsButton()
	{
		DocumentsButton.click();
	}
	
	
}
