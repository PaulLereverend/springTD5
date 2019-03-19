package s4.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.entities.History;

public interface HistoryRepository extends JpaRepository<History, Integer> {
	
}
