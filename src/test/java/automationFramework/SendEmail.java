package automationFramework;

import static automationFramework.DataReader.getParameterString;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;

public class SendEmail {

	public static String executionEndTime = Utils.getCurrrentTimeStamp();

	public static void sendTestReport() throws Exception {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(getParameterString("Email", "SendEmailCredentials"),
				getParameterString("Password", "SendEmailCredentials")));
		email.setSSLOnConnect(true);
		email.setFrom(getParameterString("Email", "SendEmailCredentials"));
		email.setSubject(getParameterString("EmailSubject", "SendEmailCredentials") + StartDriver.emailCurrentTimeSub);
		email.addTo(getParameterString("Emailto1", "SendEmailCredentials"));
		// email.addCc("");
		// email.addBcc("");
		// set the HTMl message
		email.setHtmlMsg(HtmlCode.getHtmlCode());

		// set the alternative message
		email.setTextMsg("HTML messages not supported at your machine");

		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(getParameterString("ReportPath", "SendEmailCredentials"));
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("\"Automation Execution Report-\"+StartDriver.executionStartTime");
		attachment.setName("Execution PDF Report");
		// add the attachment
		email.attach(attachment);

		// send the email
		email.send();
	}
}
