package com.dongsub.todo.entitiy;

import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memberId;

    @Column
    private String hashedPw;

    public Member(String id, String pw){
        this.memberId= id;
        this.hashedPw= pw;
    }
    public void setHashed_pw(String hashed_pw) {
        this.hashedPw =  hash.generateSHA256(hashed_pw);
    }
}

