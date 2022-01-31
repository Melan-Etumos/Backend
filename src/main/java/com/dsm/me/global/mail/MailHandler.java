package com.dsm.me.global.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailHandler {
    @Value("${email.send}")
    private String sendFrom;

//    private final JavaMailSender mailSender;
    private final MimeMessageHelper messageHelper;

    public MailHandler(JavaMailSender mailSender) throws MessagingException {
//        this.mailSender = mailSender;
        MimeMessage message = mailSender.createMimeMessage();
        this.messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    }

    // 이 메서드에서 보내는 사람과 받는 사람 지정
    public void setSenderAndReceiver(MailReceiver receiver) throws MessagingException {
        messageHelper.setFrom(sendFrom);
        messageHelper.setTo(receiver.email);
    }

    // 이 메서드에서 메세지 지정
    public void setMailMessage(MailContent content) throws MessagingException {
        messageHelper.setSubject(content.title);
        messageHelper.setText(content.content, true);
    }
}
