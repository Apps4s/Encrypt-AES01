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
  private static final String AES = "AES";

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


    // En esta parte encriptamos:
    KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
    keyGenerator.init(128); // Tamaño del generado
    SecretKey secretKey = keyGenerator.generateKey(); // Instancia de llave secreta
    byte[] bytesSecretKey = secretKey.getEncoded(); // Codificar la llave en bytes
    SecretKeySpec secretKeySpec = new SecretKeySpec(bytesSecretKey, AES); // Construir una clave secreta indicandole que es de tipo AES
    Cipher cipher = Cipher.getInstance(AES); // Intanciamos un objeto de cifrado de tipo AES
    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec); // Inicializamos el sistema de cifrado en modo encriptacion
    byte[] encrytedMessage = cipher.doFinal(messageToEncrypt.getBytes()); // procedemos a encriptar el mensaje
    Log.d("TAG", "======================");
    Log.d("TAG", new String(encrytedMessage));
    Log.d("TAG", "======================");
    // Enviamos el mensaje a otro dipositivo

    // En esta parte Desencriptamos:
    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
    byte[] mensajeDesEncriptado = cipher.doFinal(encrytedMessage);
    Log.d("TAG", "* * * * * * * * * * * * * * * *");
    Log.d("TAG", new String(mensajeDesEncriptado));
    Log.d("TAG", "* * * * * * * * * * * * * * * *");
    return new String(mensajeDesEncriptado);
  }


}
