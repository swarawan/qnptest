package id.swarawan.qnptest.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken() {
        Map<String, String> claims = new HashMap<>();
        claims.put("user", "default");

        LocalDateTime now = LocalDateTime.now().plusDays(1);

        return Jwts.builder()
                .header().type("JWT")
                .and()
                .issuedAt(new Date())
                .signWith(getKey())
                .expiration(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .claims(claims)
                .compact();
    }

    public boolean validateToken(String accessToken) {
        JwtParser parserBuilder = Jwts.parser().setSigningKey(getKey()).build();
        Jws<Claims> claims = parserBuilder.parseClaimsJws(accessToken);
        return claims.getPayload().get("user").equals("default");
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
