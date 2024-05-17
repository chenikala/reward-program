package com.retailer.reward.program.service;

import com.retailer.reward.program.entity.mysql.Member;

import java.util.Optional;

public interface MemberService {
    public Optional<Member> getMemberDetails(int memberId);
    public Member insertMember(Member member);
    public Member updateMember(Member member);
    public String deleteMember(int memberId);
}
