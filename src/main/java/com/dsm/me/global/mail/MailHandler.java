package com.dsm.me.global.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailHandler {
    @Value("${spring.mail.username}")
    private String sendFrom;

    private final JavaMailSender mailSender;
    private final MimeMessage message;
    private final MimeMessageHelper messageHelper;

    public MailHandler(JavaMailSender mailSender) throws MessagingException {
        this.mailSender = mailSender;
        this.message = mailSender.createMimeMessage();
        this.messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    }

    public void setSenderAndReceiver(MailReceiver receiver) throws MessagingException {
        messageHelper.setFrom(sendFrom);
        messageHelper.setTo(receiver.email);
    }

    public void setMailContent(MailContent content) throws MessagingException {
        messageHelper.setSubject(content.title);
        messageHelper.setText(content.content, true);
    }

    public void sendMail(){
        mailSender.send(message);
    }
}
