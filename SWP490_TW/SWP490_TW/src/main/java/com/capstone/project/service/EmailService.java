package com.capstone.project.service;

import com.capstone.project.request.UserRequest;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendMailForgetPass(UserRequest userRequest) throws MessagingException, UnsupportedEncodingException;
}
