
package com.jersey.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class connectionMail {
	
	private String email;
	private String msg;
	//private String sub;
	
	public connectionMail(String email, String msg)
	{
		super();
		this.email = email;
		this.msg = msg;
		//this.sub = sub;
	}
	
	public connectionMail()
	{}
	
	public static boolean ConnectionMail(String email, String message)
	{
		System.out.println("Email has been sent to user "+ message);
		boolean isSent = false;
		isSent = new connectionMail(email, message).sendSimpleMail();
		return isSent;
	}
	
	private boolean sendSimpleMail() 
	{
		final String username="humairarizwan2000@gmail.com";
		final String password = "";
		
		boolean isSent = false;
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
 
		Session session = Session.getInstance(props, new javax.mail.Authenticator(){
			
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			//message.setSubject(sub);
			message.setText(msg);
			Transport.send(message);
			isSent=true;
			System.out.println("Done");
		} catch (MessagingException e) {
			isSent=false;
			throw new RuntimeException(e);
		}
		
		return isSent;
	}

}