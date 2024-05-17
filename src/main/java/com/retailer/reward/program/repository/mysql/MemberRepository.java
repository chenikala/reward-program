package com.retailer.reward.program.repository.mysql;

import com.retailer.reward.program.entity.mysql.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{
	
	List<Member> findByType(String type);
	
	@Query(value = "SELECT m FROM Member m WHERE LOWER(m.firstName) like %:name%")
	List<Member> fetchMembersByName (@Param("name") String text);
}
