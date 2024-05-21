package useCase;

import java.io.IOException;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.Locators;
import reUseables.reuseable_Method;

public class CaseStudy_1 extends reuseable_Method {

	@BeforeSuite
	public void wrapOn() throws IOException {
		launchBrowser();
		openURL("https://www.saucedemo.com/");
	}
	
	@Parameters({"username","password"})
	@Test
	public void scenario(String username, String password) throws Exception {
		titleChk(Locators.title, "Swag Labs");
		type(Locators.username, username);
		type(Locators.password, password);
		click(Locators.login);
		QuitBrowser();
	}

}
