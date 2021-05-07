package com.example.demo.Controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import com.example.demo.Alien;

public interface AlienRepo extends JpaRepository<Alien,Integer>{
	
	List<Alien> findByAname(String aname);
	
	List<Alien> findByAidGreaterThan(int aid);
	
	@Query("from Alien where aid>101 order by aname")
	List<Alien> findByTechSorted(String tech);
}