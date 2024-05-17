package com.retailer.reward.program.repository.mysql;

import com.retailer.reward.program.entity.mysql.Result;
import com.retailer.reward.program.entity.mysql.ResultID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, ResultID>{

	List<Result> findByStudentId(int studentId);
}
