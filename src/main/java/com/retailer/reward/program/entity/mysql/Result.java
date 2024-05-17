package com.retailer.reward.program.entity.mysql;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "result")
@IdClass(ResultID.class)
public class Result {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "student_id", referencedColumnName = "id")
	private Member student;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "subject_id", referencedColumnName = "id")
	private Subject subject;
	
	@Column(name = "marks")
	private double marks;

}
