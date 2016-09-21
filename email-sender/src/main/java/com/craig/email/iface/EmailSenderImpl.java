package com.craig.email.iface;


import com.craig.email.EmailObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 * Created by craig on 8/26/16.
 */
@Component
public class EmailSenderImpl implements EmailSender {

    private static Logger logger = Logger.getLogger(EmailSenderImpl.class);
    @Autowired
    JavaMailSenderImpl javaMailSender;

    public void sendMail(final EmailObj emailObj) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailObj.getTo()));
                mimeMessage.setFrom(new InternetAddress(emailObj.getFrom()));
                mimeMessage.setSubject(emailObj.getSubject());
                mimeMessage.setText(emailObj.getBody());
            }
        };

        try {
            javaMailSender.send(preparator);
        } catch (MailException ex){
            logger.error(ex);
        }
    }
}
