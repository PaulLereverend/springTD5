package s4.spring.td2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import s4.spring.td2.entities.Organisation;

@Repository
public interface OrgaRepositoriy extends JpaRepository<Organisation, Integer>{

}
