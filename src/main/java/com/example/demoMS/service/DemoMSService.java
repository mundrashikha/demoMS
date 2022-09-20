package com.example.demoMS.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoMS.domain.Student;
import com.example.demoMS.repo.DemoMSRepository;

@Service
public class DemoMSService {
	
	@Autowired
	DemoMSRepository repo;
	
	public void saveStudent(Student student) {
		repo.save(student);
		
	}
	public Student getStudent(int rollnumber) throws Exception {
		Optional<Student> student = repo.findById(rollnumber);
		if(student.isPresent()) {
			if(student.get().getRollnumber()>50)
			{
				throw new Exception("exception");
			}
			return student.get();
		}
		return null;
	}
	
	public List<Student> getAllStudents() {
		return repo.findAll();
		
	}
	
	public List<Student> java8practice() {
		
		Student s = new Student();
		s.setRollnumber(1);
		s.setName("Arun");
		s.setAddress("test,Madhya Pradesh");
		s.setStandard("12th");
		saveStudent(s);
		
		Student s1 = new Student();
		s1.setRollnumber(2);
		s1.setName("Shikha");
		s1.setAddress("Pune,Maharashtra");
		s1.setStandard("10th");
		saveStudent(s1);
		
		Student s2 = new Student();
		s2.setRollnumber(3);
		s2.setName("Nirav");
		s2.setAddress("Ahmedabad,Gujarat");
		s2.setStandard("9th");
		saveStudent(s2);
		
		Student s3 = new Student();
		s3.setRollnumber(4);
		s3.setName("Shraddha");
		s3.setAddress("Mumbai,Maharashtra");
		s3.setStandard("10th");
		saveStudent(s3);
		
		Student s4 = new Student();
		s4.setRollnumber(5);
		s4.setName("Keyur");
		s4.setAddress("Ahmedabad,Gujarat");
		s4.setStandard("11th");
		saveStudent(s4);
		
		//find students from Gujarat
		List<Student> studentList=getAllStudents();
		List<Student> filteredlist=null;
		if(studentList!=null) {
			filteredlist=studentList.stream().filter(student->student.getAddress().contains("Gujarat")).collect(Collectors.toList());
			filteredlist.forEach(student->System.out.println(student));
		}
		return filteredlist;
	}
}
