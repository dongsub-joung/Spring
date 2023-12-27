package com.dongsub.todo.entitiy;

import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.dongsub.todo.util.hash;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    private String id;

    @Column
    private String member_id;

    @Column
    private String hashed_pw;

    public void setHashed_pw(String hashed_pw) {
        this.hashed_pw =  hash.generateSHA256(hashed_pw);
    }
}

