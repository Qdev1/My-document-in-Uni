/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Nezio
 */
public class SendEmail {

    public SendEmail() {
    }
    
    
    
    public static void SendMail(String recepient, String confCode) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccount = "supamegaultraultimafruitshop@gmail.com";
        String pass = "spyumkqhijdcpgdk";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount, pass);
            }
        });
        
        Message message= prepareMessage(session, myAccount, recepient, confCode) ;
        Transport.send(message);
        System.out.println("success");
    }
    
    private static Message prepareMessage(Session session, String myAccount, String recepient, String confirmCode){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Registration mail");
            message.setSentDate(new Date());
            message.setText(confirmCode);
            return message;
        } catch (Exception e) {
            System.out.println("Fuckthis");
        }
        return null;
    }
}
