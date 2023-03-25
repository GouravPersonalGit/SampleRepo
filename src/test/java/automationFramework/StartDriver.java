package automationFramework;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import static automationFramework.Config.*;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


import io.github.bonigarcia.wdm.WebDriverManager;

public class StartDriver {
	public static WebDriver driver;
	//just for email subject
	public static String emailCurrentTimeSub= Utils.getCurrrentTimeStamp();
	public static long executionStartTime;
	public static long executionEndTime;
	public static Logger log = Logger.getLogger(PageActions.class);
	/*
	 * Description: Initialize Driver
	 */
	public static void initializeWeb_Driver()
			throws IOException, ParseException, org.json.simple.parser.ParseException, InterruptedException {
		String browser = DataReader.getParameterString("browser", "environment");
		if ((browser.equalsIgnoreCase("chrome"))) {
			WebDriverManager.chromedriver().setup();
			
			Map<String, Object> preferences = new Hashtable<String, Object>();
			preferences.put("profile.default_content_settings.popups", 0);
			preferences.put("download.default_directory", fol_downloadFilePath);
			preferences.put("download.prompt_for_download", "false");

			// disable flash and the PDF viewer
			preferences.put("plugins.plugins_disabled", new String[]{
			    "Adobe Flash Player", "Chrome PDF Viewer"});
			
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("-incognito");
			options.addArguments("start-maximized");
			options.addArguments("--remote-allow-origins=*");
			options.setExperimentalOption("excludeSwitches",
				    Arrays.asList("disable-popup-blocking"));
			options.setExperimentalOption("prefs", preferences);
			//For headless mode
			//options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setAcceptInsecureCerts(true);
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			executionStartTime= System.currentTimeMillis();
		}

		else if ((browser.equalsIgnoreCase("firefox"))) {
			WebDriverManager.firefoxdriver().setup();
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setAcceptInsecureCerts(true);
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--start-maximized");
			driver = new FirefoxDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			executionStartTime= System.currentTimeMillis();
		}

	}


	public static void closeDriver() throws Exception {
		driver.quit();
		executionEndTime= System.currentTimeMillis();
		getExecutionTime();
//		uncomment when email trigger needed
//		try {
//			SendEmail.sendTestReport();
//			log.info("Execution report Email sent Successfully.");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			log.info("Email not sent. Something went Wrong!");
//		}
	}
	
	public static String getExecutionTime() throws Exception {
		long totalTime = StartDriver.executionEndTime - StartDriver.executionStartTime;
		long minutes = totalTime / 1000 / 60;
		long seconds = totalTime / 1000 % 60;
		String executionTime = minutes + " minutes and " + seconds + " seconds";
		System.out.println(executionTime);
		return executionTime;
	}
}