package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC02_LoginPageTest extends BaseClass {
	
	@Test(groups= {"sanity","master"})
	public void verify_LoginPage()
	{
		try {
		logger.info("*** TC02_LoginPageTest started***");
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicking on Login Link");
		hp.clickLogin();
		
		logger.info("Entering the login credentials");
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		logger.info("clicking on login button");
		lp.clickLogin();
		
		
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean targetpage=myacc.isMyAccountPageExists();
		
		Assert.assertEquals(targetpage, true,"Login failed");
		
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("TC02_LoginPageTest completed..");
	}
	

}
