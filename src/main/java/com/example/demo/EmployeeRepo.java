package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import com.example.demo.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Integer>{
	
	List<Employee> findByAname(String aname);
	
	List<Employee> findByAidGreaterThan(int aid);
	
	@Query("from Employee where aid>101 order by aname")
	List<Employee> findByTechSorted(String tech);
}