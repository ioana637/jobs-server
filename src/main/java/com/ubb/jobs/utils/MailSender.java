package com.ubb.jobs.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSender {
    @Autowired
    JavaMailSender sender;

    public void sendMail(String title, String message, String... address) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(address);
        mailMessage.setText(message);
        mailMessage.setSubject(title);
        sender.send(mailMessage);
    }
}
