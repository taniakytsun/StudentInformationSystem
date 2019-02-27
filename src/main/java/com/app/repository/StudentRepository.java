package com.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
	List<Student> findByFaculty(String faculty);
}