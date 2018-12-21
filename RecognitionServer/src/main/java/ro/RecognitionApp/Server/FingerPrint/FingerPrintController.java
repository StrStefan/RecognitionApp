package ro.RecognitionApp.Server.FingerPrint;

import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.RecognitionApp.Server.Person.Person;
import ro.RecognitionApp.Server.Person.PersonService;

@RestController
@RequestMapping(value = "/fingerPrint")
public class FingerPrintController {

	@Autowired
    private FingerPrintService fingerPrintService;
	
    @RequestMapping(value="/SaveFingerPrint", method = RequestMethod.POST)
    public FingerPrint saveFingerPrint(@RequestBody FingerPrint newFingerPrint){
		
    	byte[] binaryfPrint = DatatypeConverter.parseBase64Binary(newFingerPrint.getfPrint());
		String link=("E:\\FingerPrints\\"+newFingerPrint.getPersonID()+"_"+newFingerPrint.getFingerNumber()+".bmp");
		
		try {
			// convert byte array to BufferedImage
			InputStream in = new ByteArrayInputStream(binaryfPrint);
			BufferedImage bImageFromConvert = ImageIO.read(in);
			ImageIO.write(bImageFromConvert, "bmp", new File(link));
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}	
    	newFingerPrint.setfPrint(link);
  
    	return fingerPrintService.saveFingerPrint(newFingerPrint);
    	
    }
	
    @RequestMapping(value="/UpdateFingerPrint", method=RequestMethod.POST)
    public void updateFingerPrint(@RequestBody FingerPrint newFingerPrint) {
    	
    	byte[] binaryfPrint = DatatypeConverter.parseBase64Binary(newFingerPrint.getfPrint());
		String link=("E:\\FingerPrints\\"+newFingerPrint.getPersonID()+"_"+newFingerPrint.getFingerNumber()+".bmp");
		
		try {
			// convert byte array to BufferedImage
			InputStream in = new ByteArrayInputStream(binaryfPrint);
			BufferedImage bImageFromConvert = ImageIO.read(in);
			ImageIO.write(bImageFromConvert, "bmp", new File(link));
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}	
    	newFingerPrint.setfPrint(link);
  
    	fingerPrintService.updateFingerPrint(newFingerPrint);
    }
    @RequestMapping(value= "/getFingerPrint", method=RequestMethod.GET)
    public List<FingerPrint> getFingerPrintByPersonID(@RequestParam long l) {
    	 return fingerPrintService.getFingerPrintByPersonID(l);
    }
    
}
