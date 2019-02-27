package com.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.model.Lesson;
import com.app.model.Teacher;

public interface LessonRepository extends CrudRepository<Lesson, Long> {

	void save(Teacher teacher);
}