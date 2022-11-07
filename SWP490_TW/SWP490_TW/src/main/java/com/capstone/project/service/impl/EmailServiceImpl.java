package com.capstone.project.service.impl;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.User;
import com.capstone.project.repository.UserRepository;
import com.capstone.project.service.EmailService;
import com.capstone.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private JavaMailSender javaMailSender;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Value("${spring.mail.username}")
    private String sendForm;


    @Override
    public void sendMailForgetPass(String username) throws MessagingException, UnsupportedEncodingException {
        String subject = "Forget password from Tree World system";
        String senderName = "Tree World";
        String password = generateString(10);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("User name not found"));
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        String mailContent = "Dear [[username]],<br><br>" + "Mật khẩu mới của bạn là :<br>"
                + "<h3>[[password]]</h3>" + "Cảm ơn,<br>" + "Tree World!";

        mailContent = mailContent.replace("[[username]]", user.getUsername());
        mailContent = mailContent.replace("[[username]]", password);

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sendForm, senderName);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(mailContent, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String generateString(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        String password = "";

        password += lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password += capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password += specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password += numbers.charAt(random.nextInt(numbers.length()));

        for (int i = 4; i < length; i++) {
            password += combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return password;
    }
}
