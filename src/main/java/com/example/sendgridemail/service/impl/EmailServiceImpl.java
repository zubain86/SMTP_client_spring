package com.example.sendgridemail.service.impl;

import com.example.sendgridemail.request.EmailRequest;
import com.example.sendgridemail.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.io.InputStream;


@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    SendGrid sendGrid;

    @Override
    public Response emailSendService(EmailRequest emailRequest, MultipartFile[] files) {
        Mail mail = new Mail(new Email(), emailRequest.getSubject(), new Email(emailRequest.getTo()), new Content("text/plain", emailRequest.getBody()));
        for (MultipartFile file : files) {
            try {
                final InputStream inputStream = file.getInputStream();
                final Attachments attachments = new Attachments
                        .Builder(file.getOriginalFilename(), inputStream)
                        .withType("application/pdf")
                        .build();
                mail.addAttachments(attachments);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }}
            Request request = new Request();
            Response response = null;
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                response = this.sendGrid.api(request);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            return response;
        }
    }









