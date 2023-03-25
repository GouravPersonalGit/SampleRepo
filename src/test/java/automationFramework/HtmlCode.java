package automationFramework;

import stepDefinations.Hooks;

public class HtmlCode {
	
	public static String reportHtmlCode;
	
	public static String getHtmlCode() throws Exception
	{
		reportHtmlCode="<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<title>Page Title</title>\n" + "<style>\n" + "\n"
				+ "h1 {text-align: center;}\n" + "p {text-align: center;}\n" + "\n" + "#ReportStatus {\n"
				+ "  font-family: Arial, Helvetica, sans-serif;\n" + "  border-collapse: collapse;\n"
				+ "  width: 100%;\n" + "}\n" + "\n" + "#ReportStatus td, #ReportStatus th {\n"
				+ "  border: 1px solid #ddd;\n" + "  padding: 8px;\n" + "}\n" + "\n"
				+ "#ReportStatus tr:nth-child(even){background-color: #f2f2f2;}\n" + "\n"
				+ "#ReportStatus tr:hover {background-color: #ddd;}\n" + "\n" + "#ReportStatus th {\n"
				+ "  padding-top: 12px;\n" + "  padding-bottom: 12px;\n" + "  text-align: left;\n"
				+ "  background-color: #04AA6D;\n" + "  color: white;\n" + "}\n" + "</style>\n" + "</head>\n"
				+ "<body>\n" + "		<header class=\"site-header\" id=\"header\">\n"
				+ "		<h4 class=\"site-header__title\" data-lead-id=\"site-header-title\">Hello Team,<br>Greeting!<br>Hope you are working fine. please take a look at the execution report summary</h4> \n"
				+ "		<h1 class=\"site-header__title\" data-lead-id=\"site-header-title\">Test Execution Status Report</h1>\n"
				+ "	</header>\n" + "\n" + "	<div class=\"main-content\">\n"
				+ "		<p class=\"main-content__body\" data-lead-id=\"main-content-body\">Execution Report Summary Table</p>\n"
				+ "	</div>\n" + "\n" + "	<table id=\"ReportStatus\">\n" + "  <tr>\n"
				+ "    <th>Start Time</th>\n" + "    <th>End Time</th>\n" + "    <th>Total Scenario</th>\n"
				+ "    <th>Pass Scenario</th>\n" + "    <th>Fail Scenario</th>\n"
				+ "    <th>Total Execution Time</th>\n" + "  </tr>\n" + "  <tr>\n" + "    <td>"
				+ StartDriver.emailCurrentTimeSub + "</td>\n" + "    <td>" + SendEmail.executionEndTime + "</td>\n"
				+ "    <td>" + Hooks.scenarioCount + "</td>\n" + "    <td>" + Hooks.scenarioPassCount + "</td>\n"
				+ "    <td>" + Hooks.scenarioFailCount + "</td>\n" + "    <td>" + StartDriver.getExecutionTime() + "</td>\n"
				+ "  </tr>\n" + "</table>\n" + "\n" + "\n" + "	<footer class=\"site-footer\" id=\"footer\">\n"
				+ "			<h4 id=\"footerInfo\">For Detail information, please find attached the Execution Pdf report. Thanks!</h4>\n"
				+ "		<br>\n"
				+ "		<h4 id=\"ThanksMSG\" font color=\"#f1c232\">Thanks and Regards,<br>Shubham Dhiman<br>QA Automation Team</h4>\n"
				+ "		\n"
				+ "		<p class=\"site-footer__fineprint\" id=\"fineprint\">@2008-2022 Grazitti Interactive-QA Automation Team. All rights reserved.</p>\n"
				+ "	</footer>\n" + "\n" + "</body>\n" + "</html>";
		return reportHtmlCode;
	}

}
