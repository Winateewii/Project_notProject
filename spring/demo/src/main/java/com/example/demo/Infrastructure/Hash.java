package com.example.demo.Infrastructure;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Hash {
    public static String Encrypt(String str,byte[] salt,int round, int keyLength) {
        String result = "";
        try {
            KeySpec spec = new PBEKeySpec(str.toCharArray(), salt,round ,keyLength );
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ConstValue.AlgHash);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            result = Base64.getEncoder().withoutPadding().encodeToString(hash);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String saltEncrypt(byte[] salt){
        return Base64.getEncoder().withoutPadding().encodeToString(salt);
    }
    public static void generateToken(){

    }
}
