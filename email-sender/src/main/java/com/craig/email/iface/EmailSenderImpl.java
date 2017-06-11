package com.craig.email.iface;


import com.craig.email.EmailObj;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.springframework.ui.velocity.VelocityEngineFactory;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * Created by craig on 8/26/16.
 */
@Component
public class EmailSenderImpl implements EmailSender {

    private static Logger logger = Logger.getLogger(EmailSenderImpl.class);
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    public void sendMail(final EmailObj emailObj) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailObj.getTemplate(), emailObj.getBody());

                message.setTo(new InternetAddress(emailObj.getTo()));
                message.setFrom(new InternetAddress(emailObj.getFrom()));
                message.setSubject(emailObj.getSubject());
                message.setText(body, true);

            }
        };

        try {
            javaMailSender.send(preparator);
        } catch (MailException ex){
            logger.error(ex);
        }
    }
}
