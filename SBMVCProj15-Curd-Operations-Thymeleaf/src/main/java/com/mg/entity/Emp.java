package com.mg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
public class Emp {
	
	@Id
	@SequenceGenerator(name = "emp_seq_gen", sequenceName = "emp_seq1", allocationSize = 1)
	@GeneratedValue(generator = "emp_seq_gen", strategy = GenerationType.SEQUENCE)
	private Integer empno;
//	@NonNull
	@Column(length=30)
	private String ename;
//	@NonNull
	private String job;
//	@NonNull
	private Double sal;
//	@NonNull
	private Integer deptno;
}
