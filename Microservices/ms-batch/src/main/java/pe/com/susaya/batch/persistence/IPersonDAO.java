package pe.com.susaya.batch.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.susaya.batch.model.Person;

@Repository
public interface IPersonDAO extends JpaRepository<Person, Long> {
}
