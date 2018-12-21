package ro.RecognitionApp.Server.Person;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import BiometricsMain.Export;
import ro.RecognitionApp.Server.FingerPrint.FingerPrint;
import ro.RecognitionApp.Server.FingerPrint.FingerPrintController;
import ro.RecognitionApp.Server.FingerPrint.FingerPrintService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
    
	@Autowired
    private PersonService personService;
	
	@Autowired
	private FingerPrintController fingerPrintController;

    @RequestMapping(value="/SavePerson", method = RequestMethod.POST)
    public Person savePerson(@RequestBody Person newPerson){
        
    	newPerson.setId(personService.getLastValueAsID()+1);
    	personService.savePerson(newPerson);
    	System.out.println("Person saved!");   
    	for(int i=0; i<newPerson.getfPrint().size(); i++) {
    		
    		FingerPrint newFingerPrint= new FingerPrint();
    		newFingerPrint.setId(i);
    		newFingerPrint.setPersonID(newPerson.getId());
    		newFingerPrint.setfPrint(newPerson.getfPrint().get(i));
    		newFingerPrint.setFingerNumber(i+1);
    		
    		fingerPrintController.saveFingerPrint(newFingerPrint);
    	}
    
        return personService.savePerson(newPerson);
    }
    
    @RequestMapping(value="/GetPersonByName", method = RequestMethod.GET)
    public List<Person> getPersonByName(@RequestParam String nameparam) {	
    	return personService.getPersonByName(nameparam);
    }
    
    @RequestMapping(value="/GetPersonByID", method = RequestMethod.GET)
    public Person getPersonByID(@RequestParam Long id) {	
    	return personService.getPersonByID(id);
    }
    
    @RequestMapping(value="/UpdatePerson", method = RequestMethod.POST)
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
    
    @RequestMapping(value="/MatchedPerson", method = RequestMethod.POST)
    public Person getByComparingFP(@RequestBody String jsonObject) throws IOException {	
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode node = mapper.readTree(jsonObject);
        String fingerPrint=node.get("fPrint").asText();
    	byte[] fingerPrintToMatch = DatatypeConverter.parseBase64Binary(fingerPrint);
        System.out.println("Amprenta digitala:        "+fingerPrint.toString());
    	return personService.getByComparingFP(fingerPrintToMatch);
    }
    
    @RequestMapping(value="/FindPersonByFP", method = RequestMethod.POST)
    public Person getPersonByFP(@RequestBody String fingerPrint) {
    	byte[] fingerPrintNoPerson = DatatypeConverter.parseBase64Binary(fingerPrint);
    	
    	System.out.println("~~~~~~~~~~~");
    	System.out.println("Amprenta digitala:        "+fingerPrint.toString());
    	System.out.println("~~~~~~~~~~~");
    	
	    File dir=new File("E:\\FingerPrints");
	    File[] dirListing = dir.listFiles();
		Export newTemplate=new Export(fingerPrintNoPerson);
		
		long matchedID=newTemplate.execute();	
    	
    	return personService.getPersonByID(matchedID);
    }
}
