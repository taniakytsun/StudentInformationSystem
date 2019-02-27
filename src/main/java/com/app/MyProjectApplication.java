package com.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.app.model.Lesson;
import com.app.model.Student;
import com.app.model.Marks;
import com.app.model.Teacher;
import com.app.repository.LessonRepository;
import com.app.repository.StudentRepository;
import com.app.repository.TeacherRepository;


@SpringBootApplication
public class MyProjectApplication {

	@Autowired
	TeacherRepository teacherRepository;

	@Autowired
	LessonRepository lessonRepository;
	
	@Autowired
	StudentRepository studentRepository;

	@Bean
	public CommandLineRunner commandLineRunner(final ApplicationContext ctx, final TeacherRepository teacherRepository,
			LessonRepository lessonRepository) {
		return args -> {
			Lesson less1 = lessonRepository.save(new Lesson("OOP"));
			Lesson less2 = lessonRepository.save(new Lesson("Math"));
			Lesson less3 = lessonRepository.save(new Lesson("English"));
			Lesson less4 = lessonRepository.save(new Lesson("Web"));

			Teacher teacher1 = new Teacher("Tomka Yiuriy", "CS");
			List<Lesson> lessonsOfTeacher1 = new ArrayList<>();
			lessonsOfTeacher1.add(less1);
			lessonsOfTeacher1.add(less2);
			teacher1.setLessons(lessonsOfTeacher1);
			teacherRepository.save(teacher1);

			Teacher teacher2 = new Teacher("Talah Maria", "CS");
			List<Lesson> lessonsOfTeacher2 = new ArrayList<>();
			lessonsOfTeacher2.add(less4);
			lessonsOfTeacher2.add(less3);
			teacher2.setLessons(lessonsOfTeacher2);
			teacherRepository.save(teacher2);
			
			Student student1 = studentRepository.save(new Student("Mike", "FCIT"));
			Student student2 = studentRepository.save(new Student("Peter", "FCIT"));
			Student student3 = studentRepository.save(new Student("Alisa", "FKC"));
			
			Marks markA1 = new Marks(less1, "A");
			Marks markA2 = new Marks(less2, "A");
			Marks markA3 = new Marks(less3, "A");
			Marks markA4 = new Marks(less4, "A");
			
			Marks markB2 = new Marks(less1, "B");
			Marks markC3 = new Marks(less1, "C");
			Marks markD4 = new Marks(less1, "D");
			
			
			List<Marks> marksOfStudent1 = new ArrayList<>();
			marksOfStudent1.add(markA1);
			marksOfStudent1.add(markA2);
			marksOfStudent1.add(markB2);
			student1.setMarks(marksOfStudent1);
			studentRepository.save(student1);
			
			List<Marks> marksOfStudent2 = new ArrayList<>();
			marksOfStudent2.add(markA3);
			marksOfStudent2.add(markC3);
			student2.setMarks(marksOfStudent2);
			studentRepository.save(student2);
			
			List<Marks> marksOfStudent3 = new ArrayList<>();
			marksOfStudent3.add(markA4);
			marksOfStudent3.add(markD4);
			student3.setMarks(marksOfStudent3);
			studentRepository.save(student3);
		};
	};

	public static void main(String[] args) {
		SpringApplication.run(MyProjectApplication.class, args);

	}
}
