package nure.ua.noalco.emailUtil;

import nure.ua.noalco.entity.AlcoTesting;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {
    public static void sendMessage(AlcoTesting test) throws MessagingException {

        System.out.println("Preparing for sending an email...");
        String to = test.getEmployee().getProfile().getEmail();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Properties mailProps = new Properties();
        try {
            mailProps.load(EmailUtil.class.getClassLoader().getResourceAsStream("mail.properties"));
        } catch (IOException e) {
            //TODO: emailNotFoundExeption
            System.out.println("Credentials are not found");
            return;
        }
        String email = mailProps.getProperty("mail.app.email");
        String appPassword = mailProps.getProperty("mail.app.password");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, appPassword);
            }
        });

        Message message = prepareMessage(test, to, email, session);
        Transport.send(message);
        System.out.println("Email sent successfully");
    }

    private static Message prepareMessage(AlcoTesting test, String to, String email, Session session) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Exceeding the limit of alcohol intoxication");
            message.setText(getEmailBody(test));

            return message;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getEmailBody(AlcoTesting test) {
        Date date = test.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        return "An AlcoTesting failure has occurred for employee " + test.getEmployee().getName() +
                " with ID " + test.getEmployee().getId() +
                " on " + strDate;
    }
}
