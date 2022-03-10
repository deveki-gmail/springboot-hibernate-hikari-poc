package com.boraji.tutorial.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMP")
public class Employee {
	
	@Column(name  = "NAME")
	private String empName;
	
	@Id
	@Column(name  = "EMP_ID")
	private String empId;

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	
	
}
