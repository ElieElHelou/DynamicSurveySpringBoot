package com.example.practice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.*;

@Service
public class EmailingService {

//    protected Message prepareMessage(String title, String message, String recipients, String attachment) throws MessagingException, IOException {
//        Message msg = null;
//        MailingConfig config = getMailingConfig();
//        if(config != null) {
//            Properties props = getMailingProperties();
//            if(props != null) {
//                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//                    //                   protected PasswordAuthentication getPasswordAuthentication() {###something goes here###}
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(config.getUsername(), config.getPassword());
//                    }
//                });
//                msg = new MimeMessage(session);
//                msg.setFrom(new InternetAddress(config.getFrom(), false));
//                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
//                msg.setSubject(title);
//                msg.setContent(message, config.getContentType());
//                msg.setSentDate(new Date());
//
//                MimeBodyPart messageBodyPart = new MimeBodyPart();
//                messageBodyPart.setContent(message, config.getContentType());
//
//                Multipart multipart = new MimeMultipart();
//                multipart.addBodyPart(messageBodyPart);
//
//                if(attachment != null && MailingUtils.stringNotEmpty(attachment)) {
//                    MimeBodyPart attachPart = new MimeBodyPart();
//                    attachPart.attachFile(attachment);
//                    multipart.addBodyPart(attachPart);
//                }
//                msg.setContent(multipart);
//            }
//        }
//        return msg;
//    }
//
//    protected boolean sendSimpleEmail(String title, String message, String recipients, String attachment) {
//        boolean isValid = false;
//        try {
//            MailingConfig config = getMailingConfig();
//            if(config != null) {
//                Message msg = prepareMessage(title, message, recipients, attachment);
//                Transport.send(msg);
//                isValid = true;
//            }
//        } catch (javax.mail.MessagingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return isValid;
//    }

    @Autowired
    private MailSender emailSender;

    public String sendEmail(Map<String, String> data, String title){
        String error = null;
        if(title != null && !title.trim().equals("")) {
            error = "Title can't be null!";
        } else if(data != null && data.size() > 0) {
            error = "No target clients for email transfer request!";
        }

        if(error == null) {
            Iterator<String> itr = data.keySet().iterator();

            while(itr.hasNext()){
                String currrentEmail = itr.next();
                String currentToken = data.get(currrentEmail);

                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("elieelhelou00@gmail.com");
                message.setTo(currrentEmail);
                message.setSubject(title + " survey link");
                message.setText("You have been invited to participate in the survey titled'" +
                        title + "'. Use the following link to fill in and submit you answers: '" +
                        "localhost:8080/surveyAPI/v1/usetoken/" + currentToken + "'."
                );
                emailSender.send(message);
            }

        }
        return error;
    }
}
