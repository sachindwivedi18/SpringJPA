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

import com.example.demo.Alien;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepo repo;

	@RequestMapping("/")
	public String Home()
	{
		return "home.jsp";
	}
	
	@RequestMapping("/addAlien")
	public String addAlien(Alien alien)
	{
		repo.save(alien);
		return "home.jsp";
	}
	
	@RequestMapping("/getAlien")
	public ModelAndView addAlien(@RequestParam int aid)
	{
		System.out.println(repo.findByAname("Sahin"));
		System.out.println("*******************************");
		System.out.println(repo.findByAidGreaterThan(102));
		System.out.println("*******************************");
		System.out.println(repo.findByTechSorted(null));
		ModelAndView mv = new ModelAndView("showalien.jsp");
		Alien al = repo.findById(aid).orElse(new Alien());
		mv.addObject(al);
		return mv;
	}
	
	@RequestMapping("/alien/{aid}")
	@ResponseBody
	public String getSpecific(@PathVariable("aid") int aid)
	{
		return repo.findById(aid).toString();
	}
	
	@GetMapping("/aliens")
	@ResponseBody
	public String getAll()
	{
		return repo.findAll().toString();
	}
	
	@PostMapping("/alien")
	public Alien add_Alien(@RequestBody Alien alien)
	{
		repo.save(alien);
		return alien;
	}
	
	@DeleteMapping("alien/{aid}")
	public String deleteAlien(@PathVariable int aid)
	{
		Alien a = repo.getOne(aid);
		repo.delete(a);
		return "deleted";
	}
	
	@PutMapping("/alien")
	public Alien updateAlien(@RequestBody Alien alien)
	{
		repo.save(alien);
		return alien;
	}
	
	
	
	
}