package com.ubb.jobs.utils.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component(value = "MailSender")
public class MailSender {
    @Autowired
    private JavaMailSender sender;

    public void sendMail(String title, String message, String... address) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(address);
        mailMessage.setText(message);
        mailMessage.setSubject(title);
        sender.send(mailMessage);
    }
}
