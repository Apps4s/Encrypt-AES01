package com.example.encryptionaes;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

  private String messageToEncrypt = "This is my personal message";
  private static final String AES = "AES"

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    try {
      encrypterAES(messageToEncrypt);
    } catch (Exception e) {

    }
  }


  public static String encrypterAES(String messageToEncrypt) throws Exception {

    KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
    keyGenerator.init(128);
    SecretKey secretKey = keyGenerator.generateKey();
    byte[] bytesSecretKey = secretKey.getEncoded();
    SecretKeySpec secretKeySpec = new SecretKeySpec(bytesSecretKey, AES);
    Cipher cipher = Cipher.getInstance(AES);
    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
    byte[] encrytedMessage = cipher.doFinal(messageToEncrypt.getBytes());
    Log.d("TAG", new String(encrytedMessage));
    return new String(encrytedMessage);
  }


}
