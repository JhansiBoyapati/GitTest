package com.presta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.commons.Configuration;
import com.selenium.commons.ReadExcel;

public class HomePage {

	public WebDriver driver = Configuration.browser();
	public ReadExcel read;

	public HomePage() {
		PageFactory.initElements(driver, this);
		read = new ReadExcel();
	}

	@FindBy(className = "login")
	private WebElement loginLink;

	@FindBy(className = "logout")
	private WebElement logoutLink;

	@FindBy(id = "search_query_top")
	private WebElement searchTextBox;

	@FindBy(name = "submit_search")
	private WebElement submitButton;

	@FindBy(xpath = "//h3[@class='nbresult']//span")
	private WebElement totalResult;

	@FindBy(className = "logo")
	private WebElement logo;

	public void tapOnLoginLink() {
		loginLink.click();
	}

	public void validateHomePage() {
		Assert.assertEquals(driver.getTitle(), read.readData("MyAccount_Title"));
	}

	public void navigatetoHome() {
		logo.click();
	}

	public void logout() {
		logoutLink.click();
		loginLink.click();
	}

	public void searchProduct(String productName, String results,
			String resultProduct) {
		searchTextBox.sendKeys(productName);
		submitButton.click();
		Assert.assertEquals(totalResult.getText(), results);
		Assert.assertTrue(
				driver.findElement(
						By.xpath("//h3//a[text()='" + resultProduct + "']"))
						.isDisplayed(), "product not displayed  ");

	}

}
