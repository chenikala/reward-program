package com.retailer.reward.program.entity.mysql;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subject")
public class Subject {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "subject_name")
	private String subjectName;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id", referencedColumnName = "id")
	private Member teacher;
	
	@Column(name = "experience")
	private int experience;
	
}
