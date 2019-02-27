package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.message.Response;
import com.app.model.Lesson;
import com.app.model.Teacher;
import com.app.repository.LessonRepository;
import com.app.repository.TeacherRepository;

@RestController
public class TeacherController {

	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	LessonRepository lessonRepository;

	@RequestMapping("/teachers")
	public Iterable<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@RequestMapping("/teachers/{id}")
	public Teacher getTeacher(@PathVariable Long id) {
		return teacherRepository.findOne(id);
	}

	@RequestMapping(value = "/teachers", method = RequestMethod.POST)
	public Response addTeacher(@RequestBody Teacher teacher) {
		teacherRepository.save(teacher);
		return new Response("Done!",teacher);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/teachers/{id}")
	public Response updateTeacher(@RequestBody Teacher teacher, @PathVariable Long id) {
		teacherRepository.save(teacher);
		return new Response("Updated!",teacher);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/teachers/{id}")
	public Response deleteTeacher(@PathVariable Long id) {
		teacherRepository.delete(id);
		return new Response("Deleted!",id);
	}

	@RequestMapping("/findbychair")
	public List<Teacher> findByChair(String chair) {
		return teacherRepository.findByChair(chair);
	}

	@RequestMapping("/teachers/{id}/lessons")
	public List<Lesson> getLessonsByTeacher(@PathVariable Long id) {
		return teacherRepository.findOne(id).getLessons();
	}
	

	public boolean isExist(Lesson lesson, Long id){
		lesson = lessonRepository.findOne(id);
		if (lesson == null){
			return false;
		}else{
			return true;
		}
	}
	
	@RequestMapping(value = "/teachers/{teacherId}/lessons", method = RequestMethod.POST)
	public Response addLessonByTeacher(@PathVariable Long teacherId, @RequestBody List<Long> lessonIds) {
		Teacher teacher = teacherRepository.findOne(teacherId);
		for(int i=0; i<lessonIds.size();i++){	
		 Lesson lesson = lessonRepository.findOne(lessonIds.get(i));
			if (isExist(lesson, lessonIds.get(i))){
				teacher.getLessons().add(lesson);			
			}else {
				return 	new Response("Lesson Not Found!", lesson);
			}
		}
		List<Lesson> lessons = teacher.getLessons();
		teacherRepository.save(teacher);	
		return 	new Response("Done!", lessons);
	}
	
	@RequestMapping(value = "/teachers/{teacherId}/lessons", method = RequestMethod.PUT)
	public  Response deleteLessonByTeacher(@PathVariable Long teacherId, @RequestBody List<Long> lessonIds) {
		Teacher teacher = teacherRepository.findOne(teacherId);
		List<Lesson> lessons = teacher.getLessons();
		for (int i=0; i<lessonIds.size();i++){
			 Lesson lesson = lessonRepository.findOne(lessonIds.get(i));
			 for (int j=0;j<lessons.size();j++){
				 if(!lessons.contains(lesson)){ //to add
					 if (isExist(lesson, lessonIds.get(i))){
							teacher.getLessons().add(lesson);				
						}else {
							return 	new Response("Lesson Not Found!", lesson);
						 }
				 }
					if (!lessonIds.contains(lessons.get(i).getId())) {//to delete
						lessons.remove(lessons.get(i));
					}
			 }
		}
		teacherRepository.save(teacher);
		return 	new Response("Done!", lessons);
	}
}