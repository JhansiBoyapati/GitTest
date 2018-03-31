package com.presta.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.presta.pages.HomePage;
import com.presta.pages.LoginPage;
import com.selenium.commons.Configuration;

public class SmokeTest {

	public WebDriver driver = Configuration.browser();
	public HomePage home;
	public LoginPage login;

	public SmokeTest() {
		home = new HomePage();
		login = new LoginPage();

	}

	@BeforeSuite(alwaysRun = true)
	public void loginToAPP() {
		driver.get(Configuration.URL);
		driver.manage().window().maximize();
		home.tapOnLoginLink();
		login.validateLoginPage();
		login.loginToAPP(Configuration.username, Configuration.password);
		home.validateHomePage();

	}

	@AfterSuite(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void navigateToHome() {
		home.navigatetoHome();
	}

	@Test(testName = "login_Positive", description = "login_Positive", timeOut = 190000, enabled = true, groups = {
			"sanity", "1" })
	public void alogin_Positive() {
		Assert.assertTrue(true);

	}

	@Test(testName = "login_Negative", description = "login_Negative", timeOut = 190000, enabled = true, groups = {
			"sanity", "2" })
	public void login_Negative() {
		home.logout();
		login.loginToAPP("zdsa", "ads");
		login.validateErrorMessage();
		login.loginToAPP(Configuration.username, Configuration.password);
		home.validateHomePage();

	}

	@Test(testName = "searchProduct", description = "searchProduct", timeOut = 190000, enabled = true, groups = {
			"sanity", "3" })
	public void searchProduct() {
		home.searchProduct("ipod", "5 results have been found.", "iPod shuffle");

	}

}
