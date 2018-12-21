package ro.RecognitionApp.Server.Person;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends CrudRepository<Person, Long>{

	//Find person by name
	List<Person> findByName(/*@Param("name")*/ String nameparam);
	
	//Find person by name
	Person findByid(Long id);
	
	@Query("SELECT p FROM Person p")
	List<Person> findAllFP();
	
	@Modifying
	@Query("UPDATE Person p SET p.name = :name, p.description = :description WHERE p.id = :id")
	void updatePerson(@Param("id") Long id, @Param("name") String name, @Param("description") String description);

	@Query("SELECT max(p.id) FROM Person p")
	int getLastValueAsID();
}
