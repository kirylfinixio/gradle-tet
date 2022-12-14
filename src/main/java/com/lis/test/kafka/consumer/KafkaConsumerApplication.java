package com.lis.test.kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lis.test.kafka.consumer.serializer.SecureStringDeserializer;

import javax.crypto.Cipher;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.NoSuchPaddingException;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import com.lis.test.kafka.consumer.serializer.SecureStringDeserializer;;

@SpringBootApplication
public class KafkaConsumerApplication {
	public static void main(String[] args) {
		// byte[] test = Base64.getDecoder().decode("QGQGy8u2XEZFzt4j5+6n+jZYwG7uk2EyuAE2Xe5gV89fAhi6Etzs8uMAY2+UOpgpUCgy+KXPKuPTuOuK6g9dBKtgoHMDbe0eI6Vow18g7zOWOuVYxok27BMlKWmrap6weXZZPg8La/HGh+BStL9HJ9uzJc9kDFt3CMumC0bf1aBDDajAAzk3v62m5KvZoZw+Mqv0GDkcl5CNq5XtKRMuEjtX4iie+kNJkqbCFORgAPMvN54uciiUccgbXxJrWAtgT5jq8f4g845xTXexlhqUzdvWTRhU/PU7CqtckDnlX5nH8el8IhjIIh10BuHB9qSJrjDs/88Tz3tJOVcZcKwt/iMx9Qvgb2htdgz1jLR5PVhwSGl/xeWGp8g5vfKQHLDR0GIjUo036W2uiJvmeD+sBPydVzPvSdxs7ktuWqYlmI7x9MU/nv0zdLkEnBcKJ0nZYJV9OQ9y9F9c02+D0JblB9YEaNETv2irA7hFJ14HWaMi8u7Atu+pfIc1KQkGc6PvkHwrNeZq7Q5ZUbQT5zsGd7T/3ly8JFLdbaHVk/IWm/SFXArxlIxUNj6Vi7/0K7l2T8QbX+XAojPStwvgs4EPgqkXTxSZU1CaDsuS73rvXWcHHrzlVUN+UGbrBUOOYqyIMfcUDVCqydeUBmmnUXDUM2WKNqQIqR2vIJwVoWtGI3yDj60tKFtY0+DPH+4ydoBBzO7f1R+cGhALUc+tOzfK2GrNzWjEZMOFU//JBqMHp2ZdiShkoqGoMT7s3SkUKBNYXnUU4c6iL1/cqIfo9SMoP5tdxXgmrNWbEozmo8GPU9ZEO1pG8E6YQjZ7P3KyzBsSX5wSil3TXVN3QXHOrRpUChSPmVaK7Wb8IUPMqs6t399eLMZQfC3j3b12ia9oo00tG37eCA1UEiIBuQLys0ZG53iBmoczOuHoAyuLEfk5uo9j6wpM3WX0eIjtUVEXiGV7AQdQnT+cSqC37SMBANf677zcU9sSPILMtKxSpKJzo3DIvYM79weVBFLaQ3EObWl3Vb1lsG6COR/VhDBB8BRnAbkAXaJVrCVOhG01efUxgMZIGO/8d696u4hFuee3Jiy1DrSKPm5ZNe9A6w0QNPW4wu0fcYD/ZZdtsCj1j+ZAADIXuYPWkMxqlEHTjOujs3+ID38jfCnb3hUY5TPIq9ObJQ6vgeAfgm6Z3fOy");
		// SecureStringDeserializer deser = new SecureStringDeserializer();
		// byte[] res = deser.decrypt(test);
		// deser.close();
		// String enS = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(res)).toString();
		// System.out.println(enS);
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}
}
