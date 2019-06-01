package ro.utcn.recognitionserver.person;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import BiometricsMain.Export;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Person savePerson(Person newPerson) {
		return personRepository.save(newPerson);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Person> getPersonByName(String nameparam) {
		return personRepository.findByName(nameparam.toString());
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Person getPersonByID(Long id) {
		return personRepository.findByid(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updatePerson(Person updatedPerson) {
		personRepository.updatePerson(updatedPerson.getId(),updatedPerson.getName(),updatedPerson.getDescription());
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Person getByComparingFP(byte[] fingerPrintToMatch) throws IOException {
		Export newTemplate=new Export(fingerPrintToMatch);
		long matchedID=Integer.parseInt(""+newTemplate.CompareType1N());
		return personRepository.findByid(matchedID);
	}
	
}
