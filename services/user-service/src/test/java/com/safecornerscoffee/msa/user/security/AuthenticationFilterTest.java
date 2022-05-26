package com.safecornerscoffee.msa.user.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;


public class AuthenticationFilterTest {

    @Test
    void generateSecretKey() {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secretString = Encoders.BASE64.encode(key.getEncoded());

        System.out.println(secretString);

        byte[] decodedBytes = Decoders.BASE64.decode(secretString);
        SecretKey decodedKey = Keys.hmacShaKeyFor(decodedBytes);
        assertThat(decodedKey.getEncoded()).isEqualTo(key.getEncoded());
    }
}