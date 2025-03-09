package com.example.security.service;

import com.example.security.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceToken {

    private String secretKey = null;
    public String generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();
        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(user.getUserName())
                .issuer("Dharma")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ 60*10*1000))
                .and()
                .signWith(generateKey())
                .compact();


    }

    private SecretKey generateKey(){

        byte[] decode = Decoders.BASE64.decode(getSecretKey());

        return Keys.hmacShaKeyFor(decode);
    }

    public String getSecretKey(){
        return secretKey = "f57b222ee6bb20d2b6151875b6417249a038c943bc7d90e08ebb04a16d151665c2061bd4895b15424d5862a452d762c658bf4b5776315a611477ac2717825852";
    }

    public String extractUserName(String jwtToken) {

        return extractClaims(jwtToken, Claims::getSubject);

    }

    private <T> T extractClaims(String jwtToken, Function<Claims, T> claimsResolver) {

        Claims claims =  extractClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractClaims(String jwtToken) {

      return  Jwts
                .parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {

         final String userName = extractUserName(jwtToken);

         return (userName.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractionExpiration(jwtToken).before(new Date());
    }

    private Date extractionExpiration(String jwtToken) {
        return extractClaims(jwtToken, Claims::getExpiration);
    }
}
