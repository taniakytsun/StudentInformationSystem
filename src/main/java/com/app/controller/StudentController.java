package com.app.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.message.Response;
import com.app.model.Lesson;
import com.app.model.Marks;
import com.app.model.Student;
import com.app.repository.LessonRepository;
import com.app.repository.StudentRepository;

import api.MarkRequest;

@RestController
public class StudentController {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	LessonRepository lessonRepository;


	@RequestMapping("/students")
	public Iterable<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@RequestMapping("/students/{id}")
	public Student getStudentById(@PathVariable Long id) {
		return studentRepository.findOne(id);
	}

	@RequestMapping(value = "/students", method = RequestMethod.POST)
	public Response addStudent(@RequestBody Student student) {
		studentRepository.save(student);
		return new Response("Done!", student);
	}

	@RequestMapping("/students/findbyfaculty")
	public List<Student> findByFaculty(@RequestParam String faculty) {
		return studentRepository.findByFaculty(faculty);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/students/{id}")
	public Response updateStudent(@RequestBody Student student, @PathVariable Long id) {
		studentRepository.save(student);
		return new Response("Updated!", student);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/students/{id}")
	public Response deleteStudent(@PathVariable Long id) {
		studentRepository.delete(id);
		return new Response("Deleted!", id);
	}

	@RequestMapping(value = "/students/{id}/marks", method = RequestMethod.PUT)
	public Response addMark(@PathVariable Long id, @RequestBody List<MarkRequest> marksRequestList) {
		Student student = studentRepository.findOne(id);
		List<Marks> marks = new ArrayList<>();
		for(MarkRequest request:marksRequestList){
			Lesson lesson = lessonRepository.findOne(request.getLessonId());
			Marks mark = new Marks(request.getId(), lesson, request.getMark());
			marks.add(mark);
				student.getMarks().addAll(marks);
				studentRepository.save(student);		
		}
		return new Response("Done!", marks);
	}

	@RequestMapping(value = "/students/{id}/marks", method = RequestMethod.GET)
	public List<Marks> getMarks(@PathVariable Long id) {
		Student student = studentRepository.findOne(id);
		return student.getMarks();
	}
	
	@RequestMapping(value = "/students/{studentId}/marks", method = RequestMethod.DELETE)
	public  Response deleteMarksByStudent(@PathVariable Long studentId, @RequestBody List<Long> markIds) {
		Student student = studentRepository.findOne(studentId);
		List<Marks> marks = student.getMarks();
		for (int i=0; i<markIds.size();i++){
			 for (int j=0;j<marks.size();j++){
				 if (markIds.contains(marks.get(j).getId())) {
						marks.remove(marks.get(j));
					}
			 }
		}
		studentRepository.save(student);
		return 	new Response("Done!", marks);
	}
}