package com.hb.auth.common.service;

import com.hb.auth.common.template.MailTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    public void sendMail(SimpleMailMessage simpleMailMessage) {

        mailSender.send(simpleMailMessage);
    }
}
