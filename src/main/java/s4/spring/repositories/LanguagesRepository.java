package s4.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.entities.History;
import s4.spring.entities.Languages;

public interface LanguagesRepository extends JpaRepository<Languages, Integer> {
	
}
