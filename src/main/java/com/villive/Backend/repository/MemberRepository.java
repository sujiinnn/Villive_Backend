package com.villive.Backend.repository;

import com.villive.Backend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 회원 ID로 조회
    Optional<Member> findByMemberId(String memberId);


}
