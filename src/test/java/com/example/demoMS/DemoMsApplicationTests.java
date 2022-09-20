package com.example.demoMS;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demoMS.repo.DemoMSRepository;
import com.example.demoMS.service.DemoMSService;

@SpringBootTest
class DemoMsApplicationTests {
	
	@Autowired
	DemoMSService service;
	
	@Autowired
	DemoMSRepository repo;
	
	@Test
	void testJava8() {
		assertNotNull(service.java8practice());
		assertEquals(2, service.java8practice().size());
		//Mockito.verify(repo, Mockito.times(1)).findAll();
	}

	
}
