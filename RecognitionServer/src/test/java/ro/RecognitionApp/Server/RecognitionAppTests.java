package ro.RecognitionApp.Server;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import ro.utcn.recognitionserver.person.Person;
import ro.utcn.recognitionserver.person.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
public class RecognitionAppTests {

    @Autowired
    PersonRepository personRepository;
    
	@Test
	public void contextLoads() {
		System.out.println();
	//	System.out.println(" Id: "+ personRepository.findByName('Dan').get(1).getId() );
		System.out.println();
	}

}
