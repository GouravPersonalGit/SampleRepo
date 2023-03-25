package stepDefinations;

import automationFramework.StartDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.GetInTouchFormPage;

public class GetInTouchFormStepsDefination {
	
	GetInTouchFormPage gp= new GetInTouchFormPage(StartDriver.driver);
	
	@Given("User naviage to the Website homePage")
	public void user_naviage_to_the_website_home_page() throws Exception {
		gp.naviageToGetInTouchPage();
	}
	
}
