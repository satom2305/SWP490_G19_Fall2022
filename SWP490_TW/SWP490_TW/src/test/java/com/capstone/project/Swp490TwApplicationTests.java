package com.capstone.project;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class Swp490TwApplicationTests {
    private PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String password = "$2a$08$XSyVK9WSfp/UUJceLComaeHIj9TpOyAbvlhyX2ShPi6n1kRcnkFZa";
        String encoded = encoder.encode(password);
        System.out.println(encoded);
    }

}
