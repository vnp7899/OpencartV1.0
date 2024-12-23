package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC01_AccountRegisterationTest extends BaseClass{
	
	
	
	@Test(groups= {"master","regression"})
	public void verify_account_registration()
	{
		try 
		{
		logger.info("****starting the TC01_AccountRegisterationTest**** ");
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount link");
		hp.clickRegister();
		logger.info("Clicked on Registration link");
		
		logger.info("Entering all the details");
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String password=randomeAlpaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmpassword(password);
		
		regpage.clickPolicy();
		regpage.clickContinuButton();
		logger.info("Clicked on continue button");
		
		logger.info("validating the expected message");
		String msg=regpage.getConfirmatiomsg();
		Assert.assertEquals(msg, "Your Account Has Been Created!");
		logger.info("Test passed..");
		}
		catch(Exception e)
		{
			logger.error("Test failed" + e.getMessage());
			Assert.fail("Test Failed"+ e.getMessage());
		}
		finally
		{
			logger.info("***Finished TC01_AccountRegisterationTest****");
		}
	}
	
	
}


