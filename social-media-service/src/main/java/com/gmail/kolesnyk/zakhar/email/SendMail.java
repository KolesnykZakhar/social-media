package com.gmail.kolesnyk.zakhar.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {
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

//    public static void main(String[] args) throws IOException {
//        String msg = new String(Files.readAllBytes(Paths.get("social-media-service/src/main/resources/restore_password.html")), StandardCharsets.UTF_8)
//                .replace("?link?", "https://www.google.com.ua/").replace("?name?", "John");
//        SendMail.send("socialmediantk@gmail.com", "socialNetwork", "vend.88@mail.ru", "test", msg);
//    }
}  
  

