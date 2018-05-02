package ro.RecognitionApp.Server.FingerPrint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ro.RecognitionApp.Server.Person.Person;

@Service
public class FingerPrintService {

	@Autowired
	FingerPrintRepository fingerPrintRepository;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public FingerPrint saveFingerPrint(FingerPrint newFingerPrint) {
		return fingerPrintRepository.save(newFingerPrint);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateFingerPrint(FingerPrint newFingerPrint) {
		fingerPrintRepository.updateFingerPrint(newFingerPrint.getId(), newFingerPrint.getPersonID(), newFingerPrint.getFingerNumber(), newFingerPrint.getfPrint());
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<FingerPrint> getFingerPrintByPersonID(long personID) {
		return fingerPrintRepository.findByPersonID(personID);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int getLastValueAsID() {
		return fingerPrintRepository.getLastValueAsID();
	}
	
}
