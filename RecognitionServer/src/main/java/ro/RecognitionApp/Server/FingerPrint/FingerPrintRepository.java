package ro.RecognitionApp.Server.FingerPrint;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ro.RecognitionApp.Server.Person.Person;

public interface FingerPrintRepository extends CrudRepository<FingerPrint, Long>{

	//Find fingerPrint by PersonID
	List<FingerPrint> findByPersonID(/*@Param("name")*/ long personID);
	
	@Modifying
	@Query("UPDATE FingerPrint f SET f.personID = :personID, f.fingerNumber = :fingerNumber, f.fPrint= :fPrint WHERE f.id = :id")
	void updateFingerPrint(@Param("id") Long id, @Param("personID") Long personID, @Param("fingerNumber") int fingerNumber, @Param("fPrint") String fPrint );

}
