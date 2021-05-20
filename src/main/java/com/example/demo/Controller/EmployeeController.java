package com.example.demo.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Employee;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepo repo;

	@RequestMapping("/")
	public String Home()
	{
		return "home";
	}
	
	@RequestMapping("/addEmployee")
	public String addEmployee(Employee Employee)
	{
		repo.save(Employee);
		return "home.jsp";
	}
	
	//Request Param
	
	@RequestMapping("/getEmployee")
	public String addEmployee(@RequestParam int aid)
	{
		System.out.println(repo.findByAname("Sachin"));
		System.out.println("*******************************");
		System.out.println(repo.findByAidGreaterThan(aid));
		System.out.println("*******************************");
		System.out.println(repo.findByTechSorted(null));
		ModelAndView mv = new ModelAndView("showEmployee.jsp");
		Employee al = repo.findById(aid).orElse(new Employee());
		mv.addObject(al);
		return repo.findByAidGreaterThan(aid).toString();
	}
	
	// Path Types

	@GetMapping("/Employee/{aid}")
	@ResponseBody
	public String getSpecific(@PathVariable("aid") int aid)
	{
		return repo.findById(aid).toString();
	}
	
	// Content Negotiation
	@PostMapping(path="/Employee",consumes= {"application/json"})
	public Employee add_Employee(@RequestBody Employee Employee)
	{
		repo.save(Employee);
		return Employee;
	}
	
	@DeleteMapping("Employee/{aid}")
	public String deleteEmployee(@PathVariable int aid)
	{
		Employee a = repo.getOne(aid);
		repo.delete(a);
		return "deleted";
	}
	
	@PutMapping("/Employee")
	public Employee updateEmployee(@RequestBody Employee Employee)
	{
		repo.save(Employee);
		return Employee;
	}
	
	
	
	@GetMapping("/Employees")
	@ResponseBody
	public String getAll()
	{
		return repo.findAll().toString();
	}
}