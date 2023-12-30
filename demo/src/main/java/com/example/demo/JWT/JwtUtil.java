package com.example.demo.JWT;

import org.springframework.stereotype.Service;


@Service
public class JwtUtil {

    private String secret= "btechdays";

    public <T> T extractClamis(String token, Function<Claimsm, T>claimsResolver){

    }
}
