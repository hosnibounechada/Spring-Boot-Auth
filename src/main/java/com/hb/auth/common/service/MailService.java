package com.hb.auth.common.service;

import com.hb.auth.common.template.MailTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    public void sendMailSync(SimpleMailMessage simpleMailMessage) {

        mailSender.send(simpleMailMessage);
    }

    @Async
    public void sendMailAsync(SimpleMailMessage simpleMailMessage) {

        mailSender.send(simpleMailMessage);
    }
}
