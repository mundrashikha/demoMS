package com.example.demoMS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoMS.domain.Student;

@Repository
public interface DemoMSRepository extends JpaRepository<Student, Integer>{

}
