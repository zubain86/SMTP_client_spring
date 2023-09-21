package com.example.sendgridemail.service;

import com.example.sendgridemail.request.EmailRequest;
import com.sendgrid.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EmailService {
    Response emailSendService(EmailRequest emailRequest, MultipartFile[] files) throws IOException;
}
