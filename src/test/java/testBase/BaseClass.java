package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j

public class BaseClass {

	static public WebDriver driver; //making this as static so that other  extent report classes can use this.
	//All < Trace < Debug < Info < Warn < Error < Fatal < off
	public Logger logger;
	public Properties p;
	
	
	@BeforeClass(groups= {"sanity","master","regression"})
	@Parameters({"os","browser"})
	public void  setUp(String os,String browser) throws IOException
	{
		//loading the properties file
		p=new Properties();
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		p.load(file);
		
		//loading the log4j file
		logger=LogManager.getLogger(this.getClass()); //Log4j
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching OS");
				return;
			}
			
			//browser
			switch(browser.toLowerCase())
			{
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "firefox":capabilities.setBrowserName("firefox");break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
			default:System.out.println("No matching browser");return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{

			//launching the browser based on the condition
			switch(browser.toLowerCase())
			{
			case "chrome": driver=new ChromeDriver();break;
			case "edge": driver=new EdgeDriver();break;
			default:System.out.println("No match found");return;
			}
			
		}
	
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appURL")); //getting the url from properties file
		driver.manage().window().maximize();
	}
	@AfterClass(groups= {"sanity","regression","master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomeString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomNumber()
	{
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomeAlpaNumeric()
	{
		String string=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(4);
		
		return(string+"@"+num);
	}
	
	public String captureScreen(String tname)
	{
		String timeStamp=new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
		
		TakesScreenshot takescreenshot=(TakesScreenshot)driver;
		File sourceFile=takescreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+ tname +"_"+timeStamp+".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
