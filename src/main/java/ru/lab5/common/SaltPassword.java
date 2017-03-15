package ru.lab5.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Ирина on 14.03.2017.
 */
public class SaltPassword {
    public static String encryptPass(String unecryptedPassword) {
        System.out.println("Unencrypted Password: " + unecryptedPassword);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(unecryptedPassword);
        System.out.println(hashedPassword);
        return hashedPassword;
    }
}
