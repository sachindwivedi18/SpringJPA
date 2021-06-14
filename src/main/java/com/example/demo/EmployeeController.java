package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Employee;

import model.Customer;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepo employeeDB;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CustomerRepo repo;

	@RequestMapping("/homes")
	public String Home() {
		return "home";
	}

	@RequestMapping("/addEmployee")
	public String addEmployee(Employee Employee) {
		employeeDB.save(Employee);
		return "home.jsp";
	}
	
	
	// Couchebase //////////////////////////////////////////////////////
	
	
	
	@PostMapping("/addDocument")
	@ResponseStatus(HttpStatus.CREATED)
	public String saveCustomer(@RequestBody Customer customer)
	{
		repo.save(customer);
		return "saved";
	}
	
	@GetMapping("/getDocument")
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getAllCustomers()
	{
		return repo.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String delete(@PathVariable("id") int id)
	{
		repo.deleteById(id);
		return id+" is deleted";
	}
	
	
	/////////////////////////////////////////////////////////////////////

	// Request Param

	@RequestMapping("/getEmployee")
	public String addEmployee(@RequestParam int aid) {
		System.out.println(employeeDB.findByAname("sachin"));
		System.out.println("*******************************");
		System.out.println(employeeDB.findByAidGreaterThan(aid));
		System.out.println("*******************************");
		return employeeDB.findById(aid).toString();
	}

	// Path Param

	@GetMapping("/Employee/{aid}")
	@ResponseBody
	public String getSpecific(@PathVariable("aid") int aid) {
		return employeeDB.findById(aid).toString();
	}

	// Content Negotiation
	@PostMapping(path = "/Employee", consumes = { "application/json" })
	public Employee add_Employee(@RequestBody Employee Employee) {
		employeeDB.save(Employee);
		return Employee;
	}

	@DeleteMapping("deleteEmployee/{aid}")
	public String deleteEmployee(@PathVariable int aid) {
		Employee a = employeeDB.getOne(aid);
		employeeDB.delete(a);
		return "deleted";
	}

	@PutMapping("/Employee")
	public Employee updateEmployee(@RequestBody Employee Employee) {
		employeeDB.save(Employee);
		return Employee;
	}

// Get all employee

	@GetMapping("/Level_1")
	public List<Employee> Level_1() {
		Employee[] emp = restTemplate.getForObject("http://localhost:8080/Employees", Employee[].class);

		System.out.println("Done");

		return Arrays.asList(emp);
	}

	@GetMapping("/Employees")
//	@ResponseBody
	public List<Employee> getAll() {
		System.out.println("Following  is going to be deleted");
		System.out.println(employeeDB.findAll().get(0).toString());
		Employee emp = employeeDB.findAll().get(0);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		int emp_id = employeeDB.findAll().get(0).getAid();

		restTemplate.delete("http://localhost:8080/deleteEmployee/" + emp_id);

		System.out.println("Deleted");

		HttpEntity<Employee> request = new HttpEntity<Employee>(emp, headers);

		restTemplate.postForObject("http://localhost:8080/Employee", request, Employee.class);

		System.out.println("Recreated");

		return employeeDB.findAll();
	}
	
	@Autowired
	private KafkaTemplate<String,Object> kafkaTemp;
	
	private static final String topic="Kafka_example";
	
	@GetMapping("/publish/{name}")
	public String post(@PathVariable("name") final String name)
	{
//		kafkaTemp.send(topic,message);	
		kafkaTemp.send(topic,new Employee(1,name));	
		return "Published";
	}
}