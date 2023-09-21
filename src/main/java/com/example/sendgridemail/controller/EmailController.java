package com.example.sendgridemail.controller;


import com.example.sendgridemail.request.EmailRequest;
import com.example.sendgridemail.service.EmailService;
import com.sendgrid.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class EmailController {
    @Autowired
    EmailService emailService;
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestPart("test_json") EmailRequest emailReq, @RequestPart("test_file") MultipartFile[] files) throws IOException
    {
       Response response =  emailService.emailSendService(emailReq,files);
       if(response.getStatusCode()==200||response.getStatusCode()==202)
       {
           return new ResponseEntity<>("send successfully", HttpStatus.OK);
       }

        return new ResponseEntity<>("failed to send",HttpStatus.NOT_FOUND);
    }


}
