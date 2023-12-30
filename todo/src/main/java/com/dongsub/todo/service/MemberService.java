package com.dongsub.todo.service;


import com.dongsub.todo.entitiy.Member;
import com.dongsub.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.dongsub.todo.util.hash;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public boolean progressLogin(String _id, String _pw){

        Member member = memberRepository.findByMemberId(_id)
                .orElseThrow(() -> new NullPointerException("Member not found for ID: "));
        System.out.println(member);
        String sha256Hash = hash.generateSHA256(_pw.trim());

        _id= _id.trim();
        if (member.getMemberId().equals(_id) && member.getHashedPw().equals(sha256Hash)){
            return true;
        }

        return false;
    }

    public String hashing(String pw){
        return hash.generateSHA256(pw);
    }

    public boolean joining(String id, String hashedPw){
        var member= new Member(id, hashedPw);

        try{
            memberRepository.save(member);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
