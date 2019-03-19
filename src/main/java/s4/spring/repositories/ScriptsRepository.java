package s4.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.entities.Scripts;


public interface ScriptsRepository extends JpaRepository<Scripts, Integer> {
	
}
