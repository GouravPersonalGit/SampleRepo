package automationFramework;

import static automationFramework.DataReader.getParameterString;
import static automationFramework.StartDriver.driver;

import java.io.File;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;


public class Utils {
	
	
	/**
	 * 
	 * Description Method to delete the directory
	 * @author aatish.slathia
	 * @param pathToDeleteFolder
	 */
	public static void deleteDir(String pathToDeleteFolder) {
		File extefolder = new File(pathToDeleteFolder);
		if ((extefolder.exists())) {
			deleteFolderDir(extefolder);
		}
	}

	/**
	 * 
	 * Description Method to delete folder directory
	 * @author aatish.slathia
	 * @param folderToDelete
	 */
	public static void deleteFolderDir(File folderToDelete) {
		File[] folderContents = folderToDelete.listFiles();
		if (folderContents != null) {
			for (File folderfile : folderContents) {
				if (!Files.isSymbolicLink(folderfile.toPath())) {
					deleteFolderDir(folderfile);
				}
			}
		}
		folderToDelete.delete();
	}
	
	/**
	 * 
	 * Description Method for fetching of get Current Date Time
	 * @author aatish.slathia
	 */
	public static Date getCurrentDateTime() {
		Calendar calendar = Calendar.getInstance();
		Date currentDateTime = calendar.getTime();
		return currentDateTime;
		
	}
	
	/**
	 * 
	 * Description Method for fetching of get Current Date Time
	 * @author aatish.slathia
	 * @param value
	 */
//	public static String getStringWithTimeStamp(String value) {
//		DateFormat dateFormat = new SimpleDateFormat("yy.MM.dd.HH.mm.ss");
//        String date = dateFormat.format(new Date());
//        String randomString=value+date;
//		return randomString;
//	
//	}
	
	public static String getCurrrentTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss");
        String date = dateFormat.format(new Date());
        String randomString=date;
		return randomString;
	}
	
	public static String getStringWithTimeStamp(String value) {
		DateFormat dateFormat = new SimpleDateFormat("_dMMMyy_HH.ms");
        String date = dateFormat.format(new Date());
        String randomString=value+date;
		return randomString;
	}
	
	public static String getRandomIntegerWithTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("dMMyyHm");
        String date = dateFormat.format(new Date());
        String randomStringWithTimeStamp=date;
		return randomStringWithTimeStamp;
	}
	
//	public static String getRandomInteger()
//	{
//		String randomGeneratedIntegerString=RandomStringUtils.randomNumeric(4);
//		//return Integer.parseInt(randomGeneratedIntegerString);
//		return randomGeneratedIntegerString;
//	}
	
	
	public static String getOpportunityNumber() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		return dateformat.format(new Date());
	}
	 
	public static String getCurrentDate1()
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("dd MMM yyyy");
		return dateformat.format(new Date());
	}
	
	public static String getCurrentMonthYear()
	{
		SimpleDateFormat dateformat = new SimpleDateFormat(" MMM yyyy");
		return dateformat.format(new Date());
	}
	
	public static String getFormattedPhoneNum()
	{
		String random12numInput=RandomStringUtils.randomNumeric(12);
		String phoneNum=random12numInput.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d+)", "+$1-$2-$3-$4");
		return phoneNum;
	}
	
	public static String getOnlyCurrentDate()
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("dd");
		SimpleDateFormat dateformat1 = new SimpleDateFormat("d");
		String date= dateformat.format(new Date());
		if(Integer.parseInt(date)<10)
		{
			return dateformat1.format(new Date());
		}
		else
		{
			return dateformat.format(new Date());
		}
	}
	
	public static void selectDateFromCalendar(String ExpDate_dmmmyyyy) {
		String ExpDate = ExpDate_dmmmyyyy.split(" ")[0];
		String Expmonth = ExpDate_dmmmyyyy.split(" ")[1];
		String ExpYear = ExpDate_dmmmyyyy.split(" ")[2];

		String monthyear = driver.findElement(By.xpath("//*[@class='datepicker-days']//th[@class='datepicker-switch']"))
				.getText();
		while (!(monthyear.split(" ")[0].contains(Expmonth) && monthyear.split(" ")[1].equalsIgnoreCase(ExpYear))) {
			driver.findElement(By.xpath("//*[@class='datepicker-days']//th[@class='datepicker-switch']")).click();
			if (driver.findElement(By.xpath("//*[@class='datepicker-months']//th[@class='datepicker-switch']"))
					.getText().equals(ExpYear)) {
				driver.findElement(By.xpath(
						"//*[@class='datepicker-months']//*[contains(@class,'month') and text()='" + Expmonth + "']"))
						.click();
			} else {
				driver.findElement(By.xpath("//*[@class='datepicker-months']//th[@class='datepicker-switch']")).click();
				try {
					driver.findElement(By.xpath(
							"//*[@class='datepicker-years']//*[contains(@class,'year') and text()='" + ExpYear + "']"))
							.click();
				} catch (Exception e) {
					String years = driver
							.findElement(By.xpath("//*[@class='datepicker-years']//th[@class='datepicker-switch']"))
							.getText();
					if (Integer.valueOf(ExpYear) > Integer.valueOf(years.split("-")[1])) {
						driver.findElement(By.xpath("//*[@class='datepicker-years']//*[@class='next']")).click();
					} else {
						driver.findElement(By.xpath("//*[@class='datepicker-years']//*[@class='prev']")).click();
					}
					driver.findElement(By.xpath(
							"//*[@class='datepicker-years']//*[contains(@class,'year') and text()='" + ExpYear + "']"))
							.click();
				}
				driver.findElement(By.xpath(
						"//*[@class='datepicker-months']//*[contains(@class,'month') and text()='" + Expmonth + "']"))
						.click();
			}
			monthyear = driver.findElement(By.xpath("//*[@class='datepicker-days']//th[@class='datepicker-switch']"))
					.getText();
		}
		driver.findElement(By.xpath("//*[@class='datepicker-days']//td[@class='day' and text()='" + ExpDate + "']"))
				.click();
	}
	
	public static boolean verifyFromAndToDateFilter(String fromDate,String compareDate,String ToDate) throws Exception
	{
		DateFormat format = new SimpleDateFormat("dd MMM yyyy");
		Date startDate = format.parse(fromDate);
		Date compDate = format.parse(compareDate);
		Date EndDate = format.parse(ToDate);

		if(compDate.after(startDate) && compDate.before(EndDate)) {
		   return true;
		}
		else
		{
			return false;
		}
	}

}