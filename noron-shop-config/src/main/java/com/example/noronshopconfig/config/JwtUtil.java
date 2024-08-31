package com.example.noronshopconfig.config;

import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshopconfig.exception.NotTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class JwtUtil {

    @Value("${jwt.privateKey}")
    private String privateKey;

    public String generateToken(String username){
        final Long EXPIRATION = Long.valueOf(1 * 24 * 24 * 60 * 1000);

        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSignKey())
                .compact();
    }

    private Key getSignKey(){
        byte[] key = Decoders.BASE64.decode(privateKey);
        return Keys.hmacShaKeyFor(key);
    }

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
    }

    public boolean verifyToken(String token) { //throws ExpiredJwtException, MalformedJwtException, UnsupportedJwtException, SignatureException, IllegalArgumentException
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), "Token expired.");
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Token is null, empty or only whitespace.");
        } catch (MalformedJwtException e){
            throw new MalformedJwtException("JWT is invalid.");
        } catch (UnsupportedJwtException e){
            throw new UnsupportedJwtException("JWT is not  support.");
        } catch (SignatureException e){
            throw new SignatureException("Signature validation failed.");
        } catch (JwtException e){
            throw new SignatureException("Jwt is invalid.");
        }
    }

    public String extractUsername(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
