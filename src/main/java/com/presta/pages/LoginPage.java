package com.presta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.commons.Configuration;
import com.selenium.commons.ReadExcel;

public class LoginPage {

	public WebDriver driver = Configuration.browser();
	public ReadExcel read;

	@FindBy(id = "email")
	private WebElement emailtextBox;

	@FindBy(id = "passwd")
	private WebElement passwdTextBox;

	@FindBy(id = "SubmitLogin")
	private WebElement LoginBtn;
	
	@FindBy(xpath = "//div[@class='error']//li")
	private WebElement errorMesage;
	
	

	public LoginPage() {
		PageFactory.initElements(driver, this);
		read = new ReadExcel();
	}

	public void validateLoginPage() {
		Assert.assertEquals(driver.getTitle(), read.readData("Login_Title"));
	}

	public void loginToAPP(String userName, String password) {
		emailtextBox.clear();
		emailtextBox.sendKeys(userName);
		passwdTextBox.clear();
		passwdTextBox.sendKeys(password);
		LoginBtn.click();
		
	}

	
	public void validateErrorMessage(){
	
	Assert.assertEquals(errorMesage.getText(), read.readData("Login_Error"));
	
}


}