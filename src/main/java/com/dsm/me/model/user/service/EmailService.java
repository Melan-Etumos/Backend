package com.dsm.me.model.user.service;

import com.dsm.me.global.error.exceptions.EmailOverlapException;
import com.dsm.me.global.error.exceptions.SaveCodeNotFoundException;
import com.dsm.me.global.mail.MailContent;
import com.dsm.me.global.mail.MailHandler;
import com.dsm.me.global.mail.MailReceiver;
import com.dsm.me.model.user.model.UserRepository;
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
    private final UserRepository userRepository;

    @Async
    public void sendEmailCode(final String email) throws MessagingException {
        if (userRepository.existsById(email)){
            throw new EmailOverlapException();
        }

        final String code = createEmailCode();
        emailCodeSave(Code.builder().email(email).code(code).build());

        mailHandler.setSenderAndReceiver(MailReceiver.builder().email(email).build());
        mailHandler.setMailContent(createMailContent(code));
        mailHandler.sendMail();
    }

    public MailContent createMailContent(String code){
        final String title = "Melan Etumos 회원가입 인증 메일입니다.";

        Context context = new Context();
        context.setVariable("code",code);
        return MailContent.builder().content(templateEngine.process("email_code", context)).title(title).build();
    }

    private String createEmailCode(){
        Random random = new Random();
        return Integer.toString(random.nextInt(999999));
    }

    private void emailCodeSave(Code code){
        codeRepository.save(code);
    }
}
