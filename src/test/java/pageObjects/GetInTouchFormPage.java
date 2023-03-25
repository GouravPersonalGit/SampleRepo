package pageObjects;

import static automationFramework.DataReader.*;
import static automationFramework.DynamicWebElements.*;
//import static automationFramework.PageActions.*;
import static automationFramework.StartDriver.driver;
import static automationFramework.Waits.*;
import static org.testng.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationFramework.Constant;

public class GetInTouchFormPage 
{
	public GetInTouchFormPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//h2[text()='Login']")
	WebElement pageHeadingText;
	
	@FindBy(xpath = "//*[@aria-label='Refresh']//div")
	WebElement emailRefreshButton;
	
	@FindBy(xpath = "//button[text()='Submit']")
	WebElement submitButton;
	
	public void naviageToGetInTouchPage() throws Exception
	{
		String url= getParameterString("BaseUrl", "environment");
		driver.get(url);
//		log.info("Browser opened and URL hit");
		waitTillPageLoad();
	}
	
	
}
