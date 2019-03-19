package s4.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	
}
