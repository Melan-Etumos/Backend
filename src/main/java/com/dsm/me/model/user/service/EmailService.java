package com.dsm.me.model.user.service;

import com.dsm.me.global.mail.MailContent;
import com.dsm.me.global.mail.MailHandler;
import com.dsm.me.global.mail.MailReceiver;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final MailHandler mailHandler;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendEmailCode(String email) throws MessagingException {
        mailHandler.setSenderAndReceiver(MailReceiver.builder().email(email).build());
        mailHandler.setMailMessage(createMailContent());
        mailHandler.sendMail();
    }

    public MailContent createMailContent(){
        final String title = "Melan Etumos 회원가입 인증 메일입니다.";

        Context context = new Context();
        context.setVariable("code",createEmailCode());
        return MailContent.builder().content(templateEngine.process("email_code", context)).title(title).build();
    }

    private int createEmailCode(){
        final int len = 6;

        StringBuilder id= new StringBuilder();

    }


}
