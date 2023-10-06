package com.fittracker.fittracker.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private static final long MILLISECONDS_PER_MINUTE = 60_000;
    //TODO: move to config and inject via constructor
    private static final String SECRET = "gHgQ0R2AbGjCwoy63dQKMCWyEhKzNwff1OHQteTTOh1EUgJJd09gLIscovYWLQf6eyr7IccpwpbvdXem";
    //TODO: move to config and inject via constructor
    private static final long TOKEN_EXPIRATION_PERIOD_MINUTES = 60;

    private final long tokenExpirationPeriodMinutes;
    private final SecretKey secretKey;

    public JwtUtils() {
        this.secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());
        this.tokenExpirationPeriodMinutes = TOKEN_EXPIRATION_PERIOD_MINUTES;
    }

    public String generateToken(Authentication authentication) {
        Date dateNow = new Date();
        Date expirationDate = new Date(dateNow.getTime() + tokenExpirationPeriodMinutes * MILLISECONDS_PER_MINUTE);

        return Jwts.builder()
                .subject(authentication.getName())
                .issuedAt(dateNow)
                .expiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}