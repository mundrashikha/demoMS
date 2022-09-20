package com.example.demoMS;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//import org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demoMS.domain.Student;
import com.example.demoMS.repo.DemoMSRepository;
import com.example.demoMS.service.DemoMSService;

@SpringBootTest
public class TestDemoMS {

	@Mock
	private DemoMSRepository repo;

	@InjectMocks
	private DemoMSService service = new DemoMSService();

	@Test
	void testSaveStudent() {

		Student s = new Student();
		s.setRollnumber(1);
		s.setName("Arun");
		s.setAddress("test,Madhya Pradesh");
		s.setStandard("12th");
		//Mockito.when(repo.save(Mockito.any())).thenReturn(s);
		service.saveStudent(s);
		
		Student s1 = new Student();
		s1.setRollnumber(2);
		s1.setName("Shikha");
		s1.setAddress("Dhanori,Pune");
		s1.setStandard("10th");
		
		Mockito.verify(repo, Mockito.times(1)).save(Mockito.any());
		// assertEquals("Arun", s1.get().getName());

	}
	
	@Test
	void testGetStudent() throws Exception {
		//when
		
		Student s = new Student();
		s.setRollnumber(5);
		s.setName("Arun");
		s.setAddress("test,Madhya Pradesh");
		s.setStandard("12th");
		//Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(Optional.of(s));
		Mockito.when(repo.findById(Mockito.anyInt())).thenThrow(new Exception());
		
		//actual
		Student student = service.getStudent(Mockito.anyInt());
		
		//then
		Mockito.verify(repo, Mockito.times(1)).findById(Mockito.any());
		assertEquals(s.getName(), student.getName());
		// assertEquals("Arun", s1.get().getName());
	}
	
	@Test
	void testGetStudentWhenExceptionOccures() throws Exception {
		//when
		
		Student s = new Student();
		s.setRollnumber(56);
		s.setName("Arun");
		s.setAddress("test,Madhya Pradesh");
		s.setStandard("12th");
		Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(Optional.of(s));
		
		//then
		Exception thrown = assertThrows(
				Exception.class,
		           () -> service.getStudent(Mockito.anyInt()),
		           "exception"
		    );
		assertTrue(thrown.getMessage().contains("exception"));
	}
}
