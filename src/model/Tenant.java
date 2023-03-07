package model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author andrewmccoy - agmccoy
 * CIS175 - Spring 2023
 * Mar 4, 2023
 */
@Entity
@Table(name="tenant")
public class Tenant {

	//properties
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="FAMILY_NAME")
	private String familyName;
	@Column(name="NUMBER_OF_OCCUPANTS")
	private int numberOfOccupants;
	@Column(name="HAS_PETS")
	private boolean hasPets;
	@Column(name="RESIDENCY_DATE")
	private LocalDate residencyDate;
	@Column(name="RENT")
	private float rent;

	//constructors
	public Tenant(){
		super();
	}
	public Tenant(String _familyName, int _numberOfOccupants, boolean _hasPets, LocalDate _residencyDate, float _rent) {
		this.familyName = _familyName;
		this.numberOfOccupants = _numberOfOccupants;
		this.hasPets = _hasPets;
		this.residencyDate = _residencyDate;
		this.rent = _rent;
		//we'll probably only need this one different constructor (I think)	
	}
	public Tenant(String _familyName) {
		//used for exceptions in the TenantHelper class
		this.familyName = _familyName;
	}
	
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public int getNumberOfOccupants() {
		return numberOfOccupants;
	}
	public void setNumberOfOccupants(int numberOfOccupants) {
		this.numberOfOccupants = numberOfOccupants;
	}
	public boolean isHasPets() {
		return hasPets;
	}
	public void setHasPets(boolean hasPets) {
		this.hasPets = hasPets;
	}
	public LocalDate getResidencyDate() {
		return residencyDate;
	}
	public void setResidencyDate(LocalDate residencyDate) {
		this.residencyDate = residencyDate;
	}
	
	public float getRent() {
		return rent;
	}
	public void setRent(float rent) {
		this.rent = rent;
	}

	
	@Override
	public String toString() {
		return "Tenant [id=" + id + ", familyName=" + familyName + ", numberOfOccupants=" + numberOfOccupants
				+ ", hasPets=" + hasPets + ", residencyDate=" + residencyDate + ", rent=" + rent + "]";
	}
	
	//Method to convert string into Date for better formatting in output
	 public static Date dateConverter(String string){
	        // Converting the string to date
	        // in the specified format
		    DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	        LocalDate lDate = LocalDate.parse(string, format);
	        Date date = Date.from(lDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        // Returning the converted date
	        return date;
	    }

	 //converts Date to LocalDate to pass into Residency date
	 public static LocalDate dateToLocalDate(Date date) {
		 LocalDate ldate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		 return ldate;
	 }
	 
}
