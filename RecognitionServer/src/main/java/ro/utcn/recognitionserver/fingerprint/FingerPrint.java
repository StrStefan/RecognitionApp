package ro.utcn.recognitionserver.fingerprint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fingerprint")
public class FingerPrint {
	
	@Id
	@Column(name = "id",unique = true, nullable = false)
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "personid")
	private long personID;

	@Column(name= "f_Print")
	private String fPrint;

	@Column(name= "finger_Number")
	private int fingerNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPersonID() {
		return personID;
	}

	public void setPersonID(long personID) {
		this.personID = personID;
	}

	public String getfPrint() {
		return fPrint;
	}

	public void setfPrint(String fPrint) {
		this.fPrint = fPrint;
	}

	public int getFingerNumber() {
		return fingerNumber;
	}

	public void setFingerNumber(int fingerNumber) {
		this.fingerNumber = fingerNumber;
	}
}
