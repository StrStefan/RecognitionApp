package ro.RecognitionApp.Server.Person;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import BiometricsMain.Export;
import BiometricsU.CFingerPrint;

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
	    
		File dir=new File("E:\\FingerPrints");
	    File[] dirListing = dir.listFiles();
	    long matchedID=newTemplate.CompareType1N(dir, dirListing);
		
	    return personRepository.findByid(matchedID);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int getLastValueAsID() {
		return personRepository.getLastValueAsID();
	}
	
}
