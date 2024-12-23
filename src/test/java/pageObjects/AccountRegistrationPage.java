package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	

@FindBy(xpath="//input[@id='input-firstname']")
WebElement txtFirstName;

@FindBy(xpath="//input[@id='input-lastname']")
WebElement txtLastName;

@FindBy(xpath="//input[@id='input-email']")
WebElement txtEmail;

@FindBy(xpath="//input[@id='input-telephone']")
WebElement txtTelephone;

@FindBy(xpath="//input[@id='input-password']")
WebElement txtPassword;

@FindBy(xpath="//input[@id='input-confirm']")
WebElement txtConfirmpassword;

@FindBy(xpath="//input[@name='agree']")
WebElement chkPolicy;

@FindBy(xpath="//input[@value='Continue']")
WebElement btnContinue;

@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgConfirmation;

	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String telephoneno)
	{
		txtTelephone.sendKeys(telephoneno);
	}
	
	public void setPassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	
	public void setConfirmpassword(String password)
	{
		txtConfirmpassword.sendKeys(password);
	}
	
	public void clickPolicy()
	{
		chkPolicy.click();
	}
	
	public void clickContinuButton()
	{
		btnContinue.click();
	}
	
	public String getConfirmatiomsg()
	{
		try 
		{
		 //String text= msgConfirmation.getText();
		  return (msgConfirmation.getText());
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
	}

}
