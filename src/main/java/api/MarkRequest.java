package api;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class MarkRequest {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long lessonId;
	private String mark;
	
	MarkRequest(){		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
}
