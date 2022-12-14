package com.lis.test.kafka.consumer.serializer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.io.*;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

@Slf4j
public class SecureStringDeserializer implements Deserializer<String> {

    private final StringDeserializer stringDeserializer;

    private String encryptionKeyStringBase64 = "hTts7oLmPAsb61iATg9ieA==";

    private Integer AES_KEY_SIZE = 256;

    public SecureStringDeserializer() {
        this.stringDeserializer = new StringDeserializer();
    }


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        encryptionKeyStringBase64 = (String) configs.get("DEFAULT_ENCRYPTION_MESSAGE_KEY");
        stringDeserializer.configure(configs, isKey);
    }

    @Override
    public String deserialize(String s, byte[] bytes) {
        return stringDeserializer.deserialize(s, modifyData(bytes));
    }

    private byte[] modifyData(byte[] payload) {
        return decrypt(payload);
    }

    public byte[] decrypt(final byte[] encryptedPayload) {
        try {
            // String encodedBase64PayloadString = Base64.getEncoder().encodeToString(encryptedPayload);
            // log.info("======================");
            // log.info("Base64 orign: " + encodedBase64PayloadString);
           
            // 1. Load the encrypred byte[] payload info a ByteBuffer
            ByteBuffer encryptedDataByteBuffer = ByteBuffer.wrap(encryptedPayload);
            encryptedDataByteBuffer = ByteBuffer.wrap(Base64.getDecoder().decode("QGQGy8u2XEZFzt4j5+6n+jZYwG7uk2EyuAE2Xe5gV89fAhi6Etzs8uMAY2+UOpgpUCgy+KXPKuPTuOuK6g9dBKtgoHMDbe0eI6Vow18g7zOWOuVYxok27BMlKWmrap6weXZZPg8La/HGh+BStL9HJ9uzJc9kDFt3CMumC0bf1aBDDajAAzk3v62m5KvZoZw+Mqv0GDkcl5CNq5XtKRMuEjtX4iie+kNJkqbCFORgAPMvN54uciiUccgbXxJrWAtgT5jq8f4g845xTXexlhqUzdvWTRhU/PU7CqtckDnlX5nH8el8IhjIIh10BuHB9qSJrjDs/88Tz3tJOVcZcKwt/iMx9Qvgb2htdgz1jLR5PVhwSGl/xeWGp8g5vfKQHLDR0GIjUo036W2uiJvmeD+sBPydVzPvSdxs7ktuWqYlmI7x9MU/nv0zdLkEnBcKJ0nZYJV9OQ9y9F9c02+D0JblB9YEaNETv2irA7hFJ14HWaMi8u7Atu+pfIc1KQkGc6PvkHwrNeZq7Q5ZUbQT5zsGd7T/3ly8JFLdbaHVk/IWm/SFXArxlIxUNj6Vi7/0K7l2T8QbX+XAojPStwvgs4EPgqkXTxSZU1CaDsuS73rvXWcHHrzlVUN+UGbrBUOOYqyIMfcUDVCqydeUBmmnUXDUM2WKNqQIqR2vIJwVoWtGI3yDj60tKFtY0+DPH+4ydoBBzO7f1R+cGhALUc+tOzfK2GrNzWjEZMOFU//JBqMHp2ZdiShkoqGoMT7s3SkUKBNYXnUU4c6iL1/cqIfo9SMoP5tdxXgmrNWbEozmo8GPU9ZEO1pG8E6YQjZ7P3KyzBsSX5wSil3TXVN3QXHOrRpUChSPmVaK7Wb8IUPMqs6t399eLMZQfC3j3b12ia9oo00tG37eCA1UEiIBuQLys0ZG53iBmoczOuHoAyuLEfk5uo9j6wpM3WX0eIjtUVEXiGV7AQdQnT+cSqC37SMBANf677zcU9sSPILMtKxSpKJzo3DIvYM79weVBFLaQ3EObWl3Vb1lsG6COR/VhDBB8BRnAbkAXaJVrCVOhG01efUxgMZIGO/8d696u4hFuee3Jiy1DrSKPm5ZNe9A6w0QNPW4wu0fcYD/ZZdtsCj1j+ZAADIXuYPWkMxqlEHTjOujs3+ID38jfCnb3hUY5TPIq9ObJQ6vgeAfgm6Z3fOy".getBytes()));
            
            //log.info("PAYLOAD: " + Base64.getEncoder().encode(encryptedDataByteBuffer).toString());

            byte[] iv = new byte[16];
            encryptedDataByteBuffer.get(iv);
            byte[] encryptedData = new byte[encryptedDataByteBuffer.remaining()];
            encryptedDataByteBuffer.get(encryptedData);

            // siv IN string: @d�˶\FE��#���
            // String siv = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(iv)).toString();
            // log.info("siv IN string: " + siv);
            // 2. Initialize the cipher with the algorithm, used
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            // 3. Load the base64 encoded encryption key in a decoded format
            byte[] encryptionKeyDecoded = Base64.getDecoder().decode(encryptionKeyStringBase64);
            // 4. Do the actual decryption
            SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKeyDecoded, "AES");
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);
            // log.info("SECRET KEY: " + new String(secretKeySpec.getEncoded()));
            // sgcm IN string: 
            // String sgcm = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(gcmParameterSpec.getIV())).toString();
            // log.info("sgcm IN string: " + sgcm);
            // log.info("len sgcm: " + sgcm.length());
            // sgcm IN string: 
            // String skey = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(secretKeySpec.getEncoded())).toString();
            // log.info("skey IN string: " + skey);
            // log.info("len skey: " + skey.length());

            // sgcm IN string: 
            // String encryptedS = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(encryptedData)).toString();
            // log.info("encryptedS IN string leng: " + encryptedS.length());
            // log.info("encryptedS IN string: " + encryptedS);

            cipher.init(2, secretKeySpec, gcmParameterSpec);

            // log.info("======================");
           
            return cipher.doFinal(encryptedData);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}
