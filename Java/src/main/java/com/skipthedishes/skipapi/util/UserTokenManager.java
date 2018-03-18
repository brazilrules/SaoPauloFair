package com.skipthedishes.skipapi.util;

import com.skipthedishes.skipapi.entity.UserEntity;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class UserTokenManager {
    private static byte[] key;
    private static UserTokenManager instance;
    private Map<String, LocalDateTime> validTokens;
    
    private UserTokenManager() throws NoSuchAlgorithmException {
        validTokens = new HashMap<>();
        key = new byte[24];
        SecureRandom.getInstanceStrong().nextBytes(key);
        
        instance = this;
    }
    
    public static UserTokenManager getInstance() throws NoSuchAlgorithmException {
        if (instance == null) {
           instance = new UserTokenManager();
        }

        return instance;
    }
    
    public String generate(UserEntity user) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        byte[] seed = (user.getUserName() + user.getUserPassword() + now.toString()).getBytes();

        Cipher c = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "DESede"));
        
        String token = bytesToHexString(c.doFinal(seed));
        validTokens.put(token, now);
        return token;
    }
    
    public boolean validate(String token) {
        if (validTokens.containsKey(token)) {
            LocalDateTime then = validTokens.get(token);
            
            if(then.plusMinutes(15).isAfter(LocalDateTime.now())) return true;
            
            validTokens.remove(token);
        }
        return false;
    }
    
    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
