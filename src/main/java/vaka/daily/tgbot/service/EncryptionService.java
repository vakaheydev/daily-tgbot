package vaka.daily.tgbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
    @Value("${encryption.secret}")
    private String secretKey = "";

    public String encrypt(String data) {
        byte[] keyBytes = secretKey.getBytes();
        byte[] dataBytes = data.getBytes();

        byte[] encryptedData = new byte[dataBytes.length];
        for (int i = 0; i < dataBytes.length; i++) {
            encryptedData[i] = (byte) (dataBytes[i] ^ keyBytes[i % keyBytes.length]);
        }

        return new String(encryptedData);
    }

    // Расшифровка (XOR)
    public String decrypt(String encryptedData) {
        byte[] keyBytes = secretKey.getBytes();
        byte[] encryptedBytes = encryptedData.getBytes();

        byte[] decryptedData = new byte[encryptedBytes.length];
        for (int i = 0; i < encryptedBytes.length; i++) {
            decryptedData[i] = (byte) (encryptedBytes[i] ^ keyBytes[i % keyBytes.length]);
        }

        return new String(decryptedData);
    }
}
