package com.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "marks")
public class Marks implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Lesson lesson;

	@Column(name = "mark")
	private String mark;

	public Marks() {
	}

	public Marks(Long id, Lesson lesson, String mark) {
		this.id = id;
		this.lesson = lesson;
		this.mark = mark;
	}
	
	public Marks( Lesson lesson, String mark) {
		this.lesson = lesson;
		this.mark = mark;
	}

	public Marks(Long id) {
		this.id = id;
	}

	public Marks(String mark) {
		this.mark = mark;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return String.format("Mark[id=%d, mark='%s']", id, mark);

	}

}