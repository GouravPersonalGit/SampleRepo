import java.util.stream.StreamSupport;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

public class getOTP {
	
	static String ACCOUNT_SID="AC07a033b5320272b4769f1bbf65b94787";
	static String AUTH_TOKEN="59bd52e772091f932bfbbaf3476480f0";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// get the OTP using Twilio APIs:
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		String smsBody = getMessage();
		System.out.println(smsBody);
		}

		public static String getMessage() {
			return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
					.filter(m -> m.getTo().equals("+15872074342")).map(Message::getBody).findFirst()
					.orElseThrow(IllegalStateException::new);
		}

		private static Stream<Message> getMessages() {
			ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
			return StreamSupport.stream(messages.spliterator(), false);
		}

	}

