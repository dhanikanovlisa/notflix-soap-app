package utils;

import handler.ConfigHandler;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {
	private static EmailUtil instance;

	public static EmailUtil getInstance() {
		if (instance == null) {
			instance = new EmailUtil();
		}
		return instance;
	}

	// private String[] getAdminEmails(){
		
	// }

	public void sendEmail(){
		try {
			ConfigHandler ch = new ConfigHandler("config.email");
			String to = ch.get("receiver.email");
			String sub = "New Subscription Request";
			String body = "We have received a new subscription request. Please check the admin panel for more details.\n\n" +
					"Regards,\n" +
					"Team Notflixx";
			Properties props = System.getProperties();
			props.put("mail.smtp.host", ch.get("mail.smtp.host"));
			props.put("mail.smtp.port", ch.get("mail.smtp.port"));
			props.put("mail.smtp.ssl.enable",ch.get("mail.smtp.ssl.enable"));
			props.put("mail.smtp.auth", ch.get("mail.smtp.auth"));

			Authenticator auth = authenticator(ch);
			Session session = Session.getInstance(props, auth);

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(ch.get("mail.smtp.username")));
			msg.setRecipients(Message.RecipientType.TO, to);
			msg.setSubject(sub, "UTF-8");
			msg.setText(body, "UTF-8");

			System.out.println("Message is ready");
    	  	Transport.send(msg);
			System.out.println("EMail Sent Successfully!!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Authenticator authenticator(ConfigHandler ph) {
		String username = ph.get("mail.smtp.username");
		String password = ph.get("mail.smtp.password");
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};
		return auth;
	}

	public static void main(String[] args) throws AddressException {
		EmailUtil.getInstance().sendEmail();
	}
}