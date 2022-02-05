package com.dsm.me.model.user.service;

import com.dsm.me.global.mail.MailContent;
import com.dsm.me.global.mail.MailHandler;
import com.dsm.me.global.mail.MailReceiver;
import com.dsm.me.model.user.model.redis.Code;
import com.dsm.me.model.user.model.redis.EmailCodeRepository;
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
    private final EmailCodeRepository codeRepository;

    @Async
    public void sendEmailCode(final String email) throws MessagingException {
        mailHandler.setSenderAndReceiver(MailReceiver.builder().email(email).build());
        mailHandler.setMailContent(createMailContent(email));
        mailHandler.sendMail();
    }

    public MailContent createMailContent(String email){
        final String title = "Melan Etumos 회원가입 인증 메일입니다.";

        Context context = new Context();
        context.setVariable("code",createEmailCodeAndSave(email));
        return MailContent.builder().content(templateEngine.process("email_code", context)).title(title).build();
    }

    private String createEmailCodeAndSave(String email){
        Random random = new Random();
        String code = Integer.toString(random.nextInt(999999));
        codeRepository.save(Code.builder().email(email).code(code).build());
        return code;
    }
}
