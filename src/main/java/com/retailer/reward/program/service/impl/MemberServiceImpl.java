package com.retailer.reward.program.service.impl;

import com.retailer.reward.program.entity.mysql.Member;
import com.retailer.reward.program.repository.mysql.MemberRepository;
import com.retailer.reward.program.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    @Override
    public Optional<Member> getMemberDetails(int memberId) {
        return repository.findById(memberId);
    }

    @Override
    public Member insertMember(Member member) {
        return repository.saveAndFlush(member);
    }

    @Override
    public Member updateMember(Member member) {
        return repository.saveAndFlush(member);
    }

    @Override
    public String deleteMember(int memberId) {
        repository.deleteById(memberId);
        return "Deleted successfully...!";
    }
}
