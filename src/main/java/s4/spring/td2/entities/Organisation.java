package s4.spring.td2.entities;

import javax.persistence.GenerationType;
import javax.persistence.*;

public class Organisation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String nom;
	private String domain;
	private String aliases;
}
