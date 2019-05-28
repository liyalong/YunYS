package com.yunyisheng.app.yunys.utils;

import android.annotation.TargetApi;
import android.os.Build;

import java.io.UnsupportedEncodingException;


/**
 * Created by liyalong on 2018/11/12.
 */

public class AESPassword {
    /**
     * 密码加密
     * @param laws
     * @return
     */
    public static String passwordEncryption(String laws, String salt) {

        String ciphertext = null;
        try {
            String b64p = AESBelle.encode(laws);
            String saltANDb64p = salt + b64p;
            ciphertext = AESBelle.encode(saltANDb64p);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ciphertext;
    }
    /**
     * 密码解密
     * @param ciphertext
     * @return
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static String passwordDecrypt(String ciphertext, String salt){

        String laws = null;
        try {
            String saltANDb64p = AESBelle.decode(ciphertext);
            String b64p = saltANDb64p.replaceFirst(salt, "");

            laws = AESBelle.decode(b64p);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return laws;
    }





    public static void main(String[] args) throws UnsupportedEncodingException {
        //String uuid = UuidUtils.getUuid();
        //System.out.println("salt: " + uuid);

        String uuid = "4f2e7b009a3f4a949fb5752db799f041";
        String laws = "123456";

        String ciphertext = AESPassword.passwordEncryption(laws, uuid);
        System.out.println("ciphertext: " + ciphertext);

        laws = AESPassword.passwordDecrypt(ciphertext, uuid);
        System.out.println("laws: " + laws);
    }
}
