package com.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.model.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

	List<Teacher> findByChair(String chair);
}