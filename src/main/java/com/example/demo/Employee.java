package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee {

	@Id
	private int aid;
	private String aname; // Should be of same name both variables as of Home.jsp
	
	
	@Override
	public String toString() {
		return "Employee [aid=" + aid + ", aname=" + aname + "]";
	}


	public Employee(int aid, String aname) {
		super();
		this.aid = aid;
		this.aname = aname;
	}	
}