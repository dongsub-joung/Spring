package com.dongsub.todo.service;


import com.dongsub.todo.entitiy.Member;
import com.dongsub.todo.repository.MemberRepository;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import com.dongsub.todo.util.hash;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Optional<Member> findMember(String _id){
        return memberRepository.findById(_id);
    }

    public boolean progressLogin(String _id, String _pw){
        var optionalMember= memberRepository.findByMemberIdString(_id);
        Member member= optionalMember.orElseThrow(() -> new NoSuchElementException("Value is absent")); // Throws NoSuchElementException

        String sha256Hash = hash.generateSHA256(_pw);

        if (member.getMember_id().equals(_id) && member.getHashed_pw().equals(sha256Hash)){
            return true;
        }

        return false;
    }
}
