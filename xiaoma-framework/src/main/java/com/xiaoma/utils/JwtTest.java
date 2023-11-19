package com.xiaoma.utils;

import io.jsonwebtoken.Claims;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class JwtTest {
    public static void main(String[] args) {
        Long ttl = JwtUtil.JWT_TTL;
        String key  = JwtUtil.JWT_KEY;
        String token = JwtUtil.getUUID();
        String jwt = JwtUtil.createJWT("xiaoma");
        try {
            Claims par = JwtUtil.parseJWT(jwt);
            System.out.println(par);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] xiaoma = encoder.encode("xiaoma".getBytes(StandardCharsets.ISO_8859_1));
        System.out.println(Arrays.toString(xiaoma));
        byte[] decode = Base64.getDecoder().decode(xiaoma);

        System.out.println(Arrays.toString(decode));
        String s = new String(decode, StandardCharsets.ISO_8859_1);
        System.out.println(s);
//        String src = "xm";
//
//        // 编码
//        String encoded = Base64.getEncoder().encodeToString(src.getBytes());
//        Base64.getEncoder().encode(src.getBytes());
//        System.out.println("Encoded: " + encoded);
//
//        // 解码
//        byte[] decoded = Base64.getDecoder().decode(encoded);
//        String result = new String(decoded);
//        System.out.println("Decoded: " + result);

    }
}
