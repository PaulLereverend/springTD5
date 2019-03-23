package s4.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.entities.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
}
