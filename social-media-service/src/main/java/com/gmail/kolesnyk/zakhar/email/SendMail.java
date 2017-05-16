package com.gmail.kolesnyk.zakhar.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * The {@code SendMail} utility class for sending emails
 *
 * @author Kolesnyk Zakhar
 * @since JDK1.8
 */
public class SendMail {

    /**
     * method allowed to send email letter
     *
     * @param from     back email address of sender
     * @param password password for email box of sender
     * @param to       email address for receiving
     * @param sub      title of email letter
     * @param msg      text of email message
     */
    public static void send(String from, String password, String to, String sub, String msg) {
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
//            message.setText(msg);
            message.setContent(msg, "text/html; charset=utf-8");
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
  

