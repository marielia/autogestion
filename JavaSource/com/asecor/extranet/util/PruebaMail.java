/**
 * 
 */
package com.asecor.extranet.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author USUARIO
 *
 */
public class PruebaMail {

	/**
	 * 
	 */
	public PruebaMail() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {


	        final String username = "sepelio@asecor.com.ar";
	        final String password = "Mariela1946";

	        Properties prop = new Properties();
	       prop.put("mail.smtp.host", "mail.asecor.com.ar");
	       prop.put("mail.smtp.port", "25");
	       prop.put("mail.smtp.auth", "false");
	       prop.put("mail.smtp.starttls.enable", "true"); //TLSsmtp.office365.com
	        prop.put("mail.smtp.ssl.trust", "*");
	        /*
	        prop.put("mail.smtp.host", "smtp.office365.com");
	        prop.put("mail.smtp.starttls.enable", "true");
	        prop.put("mail.smtp.auth", "true"); 
	        prop.put("mail.smtp.port", "587"); */
	        prop.put("mail.debug", "true");
	        
	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });
	        
	        session.setDebug(true);
	       try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("sepelio@asecor.com.ar"));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse("marielita.martinez@gmail.com")
	            );
	            message.setSubject("Testing Gmail TLS");
	            message.setText("Dear Mail Crawler,"
	                    + "\n\n Please do not spam my email!");

	            Transport.send(message);

	            System.out.println("Done");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
}
