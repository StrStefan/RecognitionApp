package ro.RecognitionApp.Server.Person;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="person")
public class Person {
	
	@Id
	@Column(name = "id",unique = true, nullable = false)
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;

	@Transient
	@Column(name= "fPrint")
	private List<String> fPrint;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public List<String> getfPrint() {
		return fPrint;
	}

	public void setfPrint(List<String> fPrint) {
		this.fPrint = fPrint;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
