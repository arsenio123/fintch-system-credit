package com.malagueta.fintch.report.cron;

import ch.qos.logback.classic.spi.ClassPackagingData;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Value;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class GmailEmailSender {

    private static final String USER_NAME= "mozadummy@gmail.com";
    private static final String PASSWORD="deht khep icxn eaqr";// "WCZ7:DqxHB,@,zy";//"d3senvoLv1#3nto";
    private static Session session;
    private static Properties props;

    @Value("${emailStyle}")
    private static String style;

    public GmailEmailSender(){

        // Set up the properties for the Gmail SMTP server
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

         session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        });
    }

    public  Message prepereMessage(String toEmail, String subject, String body) {

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(USER_NAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            // Set the email subject and body
            message.setSubject(subject);
            message.setText(body);
            //message.setContent(attach);

            // Send the email
            //Transport.send(message);

            System.out.println("Email sent successfully.");
            return message;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  Message addAttach(Message message, String strPaths){
        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();
        try {
        // Now set the actual message
        messageBodyPart.setText("This is message body");

        // Create a multipar message
        Multipart multipart = new MimeMultipart();


         String [] paths=strPaths.split(",");

            for (int i = 0; i < paths.length; i++) {
                // Set text message part
                multipart.addBodyPart(messageBodyPart);

                // Part two is attachment
                messageBodyPart = new MimeBodyPart();
                String filename = paths[i];
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename.substring(filename.lastIndexOf("\\")));
                multipart.addBodyPart(messageBodyPart);

                // Send the complete message parts
                message.setContent(multipart);
            }



        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static void main(String[] args) throws MessagingException {

        // Replace with the recipient's email address
        String toEmail = "arsenio.malagueta@mozabanco.co.mz,arsenio.malagueta@gmail.com";

        // Email details
        String subject = "Test Email";
        String body = "<h1>1</h1> <h2></h2>This is a test email sent from Java.";
        body=body+" "+style;
        // Create an instance of GmailEmailSender


        // Send the email
        GmailEmailSender gmailEmailSender=new GmailEmailSender();
        //Message message=gmailEmailSender.prepereMessage(toEmail, subject, body);
        //gmailEmailSender.addAttach(message,"D:\\pedido.pdf");
        //Message message=
                gmailEmailSender.sendEmailSMTP_HTML(toEmail,toEmail,subject,body);
        //Transport.send(message);

    }

   public void sendEmailSMTP_HTML(String toEmail, String ccEmail, String subject, String body)  {

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER_NAME));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toEmail));
            message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(ccEmail));

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);

            // second part (the image)
            messageBodyPart = new MimeBodyPart();

            //addAttachment(multipart, filesAttach);
            //addImage(multipart, images);
            // put everything together
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            //logger.debug("Email text/html Sent com sucesso.... ");
        } catch (MessagingException mex) {
            //logger.info("Email text/html Sent error...." + mex);
            //logger.error("Email text/html Sent error...." + mex.getMessage());
            mex.printStackTrace();
        }

    }

}
