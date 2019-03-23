package s4.spring.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class History {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String date;
	private String content;
	private String comment;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Scripts getScripts() {
		return scripts;
	}

	public void setScripts(Scripts scripts) {
		this.scripts = scripts;
	}

	@ManyToOne
	private Scripts scripts;
	
}
