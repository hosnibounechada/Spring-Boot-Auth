package com.hb.auth.common.template;

import org.springframework.mail.SimpleMailMessage;

public class MailTemplate {
    public static SimpleMailMessage accountConfirmationMail(String to, String code){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Spring-Boot-Auth Account Confirmation Email");
        String body = "Confirmation code to activate your account of Spring-Boot-Auth: " +
                code +
                " will expire after 3 hours";
        message.setText(body);
        return message;
    }





}
