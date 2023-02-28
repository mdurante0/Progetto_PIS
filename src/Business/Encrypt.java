package Business;

import java.security.MessageDigest;

public class Encrypt {
    public static String encrypt(String pwd)  {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (Exception e){
            e.printStackTrace();
        }

        md.update(pwd.getBytes());
        byte[] digest = md.digest();
        StringBuilder hexString = new StringBuilder();

        for (byte b : digest) {
            hexString.append(Integer.toHexString(0xFF & b));
        }
        return hexString.toString();
    }
}