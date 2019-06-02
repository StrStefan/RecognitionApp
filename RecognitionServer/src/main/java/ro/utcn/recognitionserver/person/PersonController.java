package ro.utcn.recognitionserver.person;


import java.io.IOException;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.utcn.recognitionserver.fingerprint.FingerPrint;
import ro.utcn.recognitionserver.fingerprint.FingerPrintController;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
    
	@Autowired
    private PersonService personService;
	
	@Autowired
	private FingerPrintController fingerPrintController;

    @RequestMapping(value="/savePerson", method = RequestMethod.POST)
    public Person savePerson(@RequestBody Person newPerson){
        
    	Person savedPerson =personService.savePerson(newPerson);
    	System.out.println("Person saved!");   
    	for(int i=0; i<newPerson.getfPrint().size(); i++) {
    		
    		FingerPrint newFingerPrint= new FingerPrint();
    		newFingerPrint.setId(i);
    		newFingerPrint.setPersonID(savedPerson.getId());
    		newFingerPrint.setfPrint(newPerson.getfPrint().get(i));
    		newFingerPrint.setFingerNumber(i+1);
    		
    		fingerPrintController.saveFingerPrint(newFingerPrint);
    	}
    	personService.savePerson(newPerson);
    
        return personService.savePerson(newPerson);
    }
    
    @RequestMapping(value="/getPersonByName", method = RequestMethod.GET)
    public List<Person> getPersonByName(@RequestParam String nameparam) {	
    	return personService.getPersonByName(nameparam);
    }
    
    @RequestMapping(value="/getPersonByID", method = RequestMethod.GET)
    public Person getPersonByID(@RequestParam Long id) {	
    	return personService.getPersonByID(id);
    }
    
    @RequestMapping(value="/updatePerson", method = RequestMethod.POST)
    public void updatePerson(@RequestBody Person updatedPerson) {
    	
    	List<FingerPrint> fList=fingerPrintController.getFingerPrintByPersonID(updatedPerson.getId());
    	for(int i=0; i<updatedPerson.getfPrint().size(); i++) {
    		
    		FingerPrint newFingerPrint= new FingerPrint();
    		newFingerPrint.setId(fList.get(i).getId());
    		newFingerPrint.setPersonID(updatedPerson.getId());
    		newFingerPrint.setfPrint(updatedPerson.getfPrint().get(i));
    		newFingerPrint.setFingerNumber(i+1);
    		
    		fingerPrintController.updateFingerPrint(newFingerPrint);
    	}
        personService.updatePerson(updatedPerson);
    }
    
    @RequestMapping(value="/matchedPerson", method = RequestMethod.POST)
    public Person getByComparingFP(@RequestBody String fingerPrint) throws IOException {	
        byte[] fingerPrintToMatch = DatatypeConverter.parseBase64Binary(fingerPrint);
        System.out.println("Amprenta digitala:        "+fingerPrint.toString());
    	return personService.getByComparingFP(fingerPrintToMatch);
    }
}
