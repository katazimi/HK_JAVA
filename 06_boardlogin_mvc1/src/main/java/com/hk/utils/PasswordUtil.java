package com.hk.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
    
    // Salt 생성
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    
    // 비밀번호 해시화 (Salt 포함)
    public static String hashPassword(String plainPassword, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashedPassword = md.digest(plainPassword.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }
    
    // 전체 해시 (Salt + Hash)
    public static String hashPasswordWithSalt(String plainPassword) {
        String salt = generateSalt();
        String hash = hashPassword(plainPassword, salt);
        return salt + ":" + hash; // Salt와 Hash를 콜론으로 구분
    }
    
    // 비밀번호 검증
    public static boolean checkPassword(String plainPassword, String storedPassword) {
        String[] parts = storedPassword.split(":");
        if (parts.length != 2) return false;
        
        String salt = parts[0];
        String hash = parts[1];
        
        return hash.equals(hashPassword(plainPassword, salt));
    }
}
