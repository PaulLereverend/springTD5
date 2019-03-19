package s4.spring.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Scripts {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Users user;
	
	@ManyToOne
	private Languages language;
	
	@OneToMany
	private List<History> history;
	
	@ManyToOne
	private Category category;
	
	private String title;
	private String description;
	private String content;
	private String creation;
	
	public Scripts() {
		history=new ArrayList<History>();
	}
	
}
