package com.retailer.reward.program.entity.mysql;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultID implements Serializable{

	private static final long serialVersionUID = 1L;

	private Member student;
	
	private Subject subject;
	
}
