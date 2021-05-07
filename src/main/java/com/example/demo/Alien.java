package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Alien {

	@Id
	private int aid;
	private String aname; // Should be of same name both variables as of Home.jsp
	
	
	@Override
	public String toString() {
		return "Alien [aid=" + aid + ", aname=" + aname + "]";
	}
	
	
	
}