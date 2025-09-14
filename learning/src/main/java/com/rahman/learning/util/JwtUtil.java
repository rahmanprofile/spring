//package com.rahman.learning.util;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import java.security.Key;
//import java.util.Date;
//
//public class JwtUtil {
//
//    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    private static final long EXPIRATION_TIME = 1000 * 60 * 30;
//
//    public static String generateToken(String email) {
//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(key)
//                .compact();
//    }
//}
