package com.retailer.reward.program.repository.mysql;

import com.retailer.reward.program.entity.mysql.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer>{

}
