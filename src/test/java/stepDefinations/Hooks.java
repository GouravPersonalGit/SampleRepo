package stepDefinations;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import automationFramework.StartDriver;
import automationFramework.Utils;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class Hooks extends StartDriver {

	@AfterStep
	public void AddScreenshot(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
			scenario.attach(fileContent, "image/png", "image");
		}
	}

	/**
	 * Description: Taking screenshot for pass and failed scenario- -creating folder
	 * for both as well pass and failed scenario
	 * 
	 * @author aatish.slathia
	 * @param scenario
	 * @throws IOException
	 * @throws EmailException
	 */
	public static int scenarioCount = 0;
	public static int scenarioPassCount = 0;
	public static int scenarioFailCount = 0;

	@After(order = 1)
	public static void saveScreenShotForFailedAndPassScenario(Scenario scenario) throws Exception {
		scenarioCount++;
		if (scenario.isFailed()) {
			scenarioFailCount++;
			try {
				((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				File screenshot_with_scenario_name = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot_with_scenario_name,
						new File("./Failed_Screenshots/" + Utils.getStringWithTimeStamp(scenario.getName()) + ".png"));
			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				System.err.println(somePlatformsDontSupportScreenshots.getMessage());
			}
		} else {
			scenarioPassCount++;
			try {
				((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				File screenshot_with_scenario_name = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot_with_scenario_name,
						new File("./Pass_Screenshots/" + Utils.getStringWithTimeStamp(scenario.getName()) + ".png"));
			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				System.err.println(somePlatformsDontSupportScreenshots.getMessage());

			}
		}
	}
}
