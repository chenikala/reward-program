package com.retailer.reward.program.rest;

import com.retailer.reward.program.entity.mysql.Member;
import com.retailer.reward.program.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberQueryController {

    private final MemberService service;

    @QueryMapping(name = "memberDetails")
    public Member getMemberDetails(@Argument int memberId){
        Member result = null;
        Optional<Member> member = service.getMemberDetails(memberId);
        if(member.isPresent()){
            result =  member.get();
        }
        return result;
    }
}
