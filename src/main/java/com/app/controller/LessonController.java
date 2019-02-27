package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.message.Response;
import com.app.model.Lesson;
import com.app.repository.LessonRepository;

@RestController
public class LessonController {

	@Autowired
	LessonRepository lessonRepository;

	@RequestMapping(value = "/lessons", method = RequestMethod.POST)
	public Response addLesson(@RequestBody Lesson lesson) {
		lessonRepository.save(lesson);
		return new Response("Done", lesson);
	}

	@RequestMapping("/lessons")
	public Iterable<Lesson> getAllLessons() {
		return lessonRepository.findAll();
	}

	@RequestMapping("/lessons/{id}")
	public Lesson getLesson(@PathVariable Long id) {
		return lessonRepository.findOne(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/lessons/{id}")
	public Response updateLesson(@RequestBody Lesson lesson, @PathVariable Long id) {
		lessonRepository.save(lesson);
		return new Response("Updated!", lesson);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/lessons/{id}")
	public Response deleteLesson(@PathVariable Long id) {
		lessonRepository.delete(id);
		return new Response("Deleted!", id);
	}

}