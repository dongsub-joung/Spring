package com.dongsub.todo.repository;

import com.dongsub.todo.entitiy.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByMemberIdString(String member_id);
    Optional<Member> findByMemberPWString(String member_pw);

}
