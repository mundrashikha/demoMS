package com.example.demoMS.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoMS.domain.Student;
import com.example.demoMS.service.DemoMSService;

@RestController
public class DemoMSController {
	Logger log= LoggerFactory.getLogger(DemoMSController.class);
	
	@Autowired
	DemoMSService service;
	
	@PostMapping("/student")
	public ResponseEntity<Integer> saveStudent(@RequestBody Student student) {
		try {
			service.saveStudent(student);
			return new ResponseEntity<Integer>(student.getRollnumber(), HttpStatus.CREATED);
		}
		catch(Exception e) {
			log.error("Exception occured in saveStudent :",e);
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/getStudent")
	public ResponseEntity<Student> getStudent(@RequestParam int rollnumber) throws Exception {
		try {
			Student s=service.getStudent(rollnumber);
			if(s!=null) {
				return new ResponseEntity<Student>(s,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
		}
		catch(Exception e) {
			log.error("Exception occured in saveStudent :",e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		List<Student> list=null;
		try {
			list= service.getAllStudents();
		}
		catch(Exception e){
			log.error("Exception occured in saveStudent :",e);
		}
		return list;
	}
}
