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

    @Async // 비동기기
   public void sendEmailCode(String email) throws MessagingException {
        final String title = "Melan Etumos 회원가입 인증 메일입니다.";
        Context context = new Context();
        context.setVariable("code",createEmailCode());
        String html = templateEngine.process("email_code", context);
        mailHandler.setSenderAndReceiver(MailReceiver.builder().email(email).build());
        mailHandler.setMailMessage(MailContent.builder().content(html).title(title).build());
        mailHandler.sendMail();
    }


    }


}
