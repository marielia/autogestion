package com.asecor.extranet.faces.seguridad; 
 
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.asecor.util.io.FileUtil; 

public class MailClient {

public void sendMail(String from, String to, String subject, String messageBody) throws MessagingException, AddressException { 
	// Setup mail server 
	String host = "smtp.gmail.com"; 
	String username = "asecorclienteol@gmail.com"; 
	String password = "Marambio2017"; 
	Properties props = new Properties(); 
	props.put("mail.smtps.auth", "true");
 
	//Get a mail session 
	Session session = Session.getDefaultInstance(props, null);
	
	//Define a new mail message 
	MimeMessage message = new MimeMessage(session); 
	message.setFrom(new InternetAddress(from)); 
	message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
	message.setSubject(subject);
	
	message.setText(messageBody);
	
	//Send the message 
	Transport t = session.getTransport("smtps"); 
	try { 
		t.connect(host, username, password); 
		t.sendMessage(message, message.getAllRecipients()); 
		} 
	finally { 
		t.close(); 
		}

}


public  void enviarConGMail( ) throws Exception {
	
	FileUtil fileUtil= new FileUtil();
		Properties property= fileUtil.getPropertiesFile();
		String mail_smtp_password= property.getProperty("mail_smtp_password");
		String mail_smtp_host= property.getProperty("mail_smtp_host");
		String mail_smtp_ssl_trust= property.getProperty("mail_smtp_ssl_trust");
		String mail_smtp_starttls_enable= property.getProperty("mail_smtp_starttls_enable");
		String mail_smtp_port = property.getProperty("mail_smtp_port");
		String mail_smtp_user = property.getProperty("mail_smtp_user");
		String mail_smtp_auth = property.getProperty("mail_smtp_auth");
		String mail_from = property.getProperty("mail_from");
		//System.out.println(" olUser.getEmail() "+ olUser.getEmail());
		String mail_to = "1140337703@sms.movistar.net.ar"   ;//olUser.getEmail();
       //String subject =  GetMensaje(property.getProperty("asuntoRegistracion"));
	   // String body =   GetMensaje(property.getProperty("cuerpoRegistracion")); 
	   
	Properties props = new Properties();

	// Nombre del host de correo, es smtp.gmail.com
	props.setProperty("mail.smtp.host", mail_smtp_host);  
	props.put("mail.smtp.ssl.trust", mail_smtp_ssl_trust );

	// TLS si está disponible
	props.setProperty("mail.smtp.starttls.enable", mail_smtp_starttls_enable);

	// Puerto de gmail para envio de correos
	props.setProperty("mail.smtp.port",mail_smtp_port);

	// Nombre del usuario
	//props.setProperty("mail.smtp.user", mail_smtp_user);

	// Si requiere o no usuario y password para conectarse.
	props.setProperty("mail.smtp.auth", mail_smtp_auth);

	//javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
	
	javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
		
        protected PasswordAuthentication getPasswordAuthentication() {

            return new PasswordAuthentication(mail_from, mail_smtp_password);

        }

    });

	// Para obtener un log de salida más extenso
	session.setDebug(true);
	
	MimeMessage message = new MimeMessage(session);

	// Quien envia el correo
	message.setFrom(new InternetAddress(mail_from,"asecorportal"));

	// A quien va dirigido
	message.addRecipient(Message.RecipientType.TO, new InternetAddress(  mail_to ));


	
//		MimeBodyPart mimeBodyPart = new MimeBodyPart();
//		mimeBodyPart.setContent(reemplazarEtiquetas(body,olUser), "text/html"); 
//		Multipart multipart = new MimeMultipart();
//	    // Agregar la parte del mensaje HTML al multiPart
//		multipart.addBodyPart(mimeBodyPart); 
//		message.setContent(multipart); 
	
		message.setSubject(	MimeUtility.encodeText("probando","UTF-8","B"));
	    message.setText("body probando" ); 
		
		
	
	Transport t = session.getTransport("smtp");

	// Aqui usuario y password de gmail
	//t.connect(mail_from , mail_smtp_password );
	t.connect();
	try {
		t.sendMessage(message,message.getAllRecipients());
	} finally {
		t.close();
	}
	
}


}
