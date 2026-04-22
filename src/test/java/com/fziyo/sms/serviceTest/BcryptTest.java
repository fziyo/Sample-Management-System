package com.fziyo.sms.serviceTest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptTest {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        boolean match = encoder.matches(
            "1234",
            "$2a$12$8ePeG4ds4d0G7Idvdv4MfOmLxy.YjGiWYRpWgYDROVhVTWqL8XM6a"
        );
        
        System.out.println(match);
    }
}
