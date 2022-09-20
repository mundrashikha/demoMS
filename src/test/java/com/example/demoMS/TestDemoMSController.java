package com.example.demoMS;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demoMS.controller.DemoMSController;
import com.example.demoMS.domain.Student;
import com.example.demoMS.service.DemoMSService;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.status.Status;

@WebMvcTest(DemoMSController.class)
public class TestDemoMSController {
	
	@MockBean
	private DemoMSService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectmapper;
	
	@Test
	void testGetStudent() throws Exception {
		Student s = new Student();
		s.setRollnumber(5);
		s.setName("Arun");
		s.setAddress("test,Madhya Pradesh");
		s.setStandard("12th");
		//when
		Mockito.when(service.getStudent(Mockito.anyInt())).thenReturn(s);
		//actual
		/*MultiValueMap<String, String> paramsMap=new LinkedMultiValueMap<>();
		paramsMap.add("rollnumber", "5");*/
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/getStudent").param("rollnumber", "5").contentType(APPLICATION_JSON);
				//params(paramsMap).contentType(APPLICATION_JSON);
		mockMvc.perform(requestBuilder)
		.andExpect(status().isOk())
		.andExpect(jsonPath("name", s.getName()).exists());
	}
	
	@Test
	void testSaveStudent() throws Exception{
		Student s = new Student();
		s.setRollnumber(6);
		s.setName("Keyur");
		s.setAddress("test,Gujarat");
		s.setStandard("11th");
		//Mockito.doNothing(Mockito.when(service.getAllStudents()));
		Mockito.doAnswer(i->{
			return null;
		}).when(service).saveStudent(Mockito.any(Student.class));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/student").contentType(APPLICATION_JSON)
																.content(objectmapper.writeValueAsString(s)))
				.andExpect(status().isCreated())
				.andDo(print());
		
	}

}
