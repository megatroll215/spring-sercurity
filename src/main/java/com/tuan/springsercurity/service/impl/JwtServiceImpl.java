package com.tuan.springsercurity.service.impl;

import com.tuan.springsercurity.service.JWTService;
import com.tuan.springsercurity.util.constant.UserConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JWTService {

    @Value("${app.signin-key}")
    private String signInKey;
    @Override
    public String generateToken(UserDetails userDetails)
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put(UserConstant.ROLE, userDetails.getAuthorities());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private <T> T extractClaim(String token, Function<Claims,T> claimResolvers)
    {
        final Claims claims = extractAllClaim(token);
        return claimResolvers.apply(claims);
    }

    private Claims extractAllClaim(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
    }
    @Override
    public String extractUserName(String token)
    {
        return extractClaim(token,Claims::getSubject);
    }

    private Key getSigninKey() {
        byte[] key =  java.util.Base64.getDecoder().decode(signInKey.getBytes(StandardCharsets.UTF_8));
        return Keys.hmacShaKeyFor(key);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails)
    {
        final String username = extractUserName(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    @Override
    public String generateRefreshToken(Map<String,Object> extractClaims,UserDetails user) {
        return Jwts.builder().setClaims(extractClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+604800000))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
