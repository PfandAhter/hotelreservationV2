package com.hotelreservation.rest.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmailWithAttachment(String email) {

        MimeMessage mimeMessage =
                javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper;

        //TODO if you want to send mail with attachment

        /*List<File> attachments = new ArrayList<>();

        File test = new File();

        attachments.add("/email_attachment.png");

        attachments.forEach(file -> {
            try {
                FileSystemResource resource = new FileSystemResource(file);
                message.addAttachment(file.getName(), resource);
            } catch (MessagingException e) {
                throw new RuntimeException("Problem attaching file to email", e);
            }
        });*/

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);

            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText("text.", false);
            mimeMessageHelper.setSubject("Subject");


            javaMailSender.send(mimeMessage);
            log.info("Mail sended to : " + email);
        } catch (MessagingException e) {
            log.error("Error while sending mail to : " + email);
        }
    }
}
