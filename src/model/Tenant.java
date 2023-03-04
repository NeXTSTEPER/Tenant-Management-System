package model;

import java.time.LocalDate;

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
	
	
	//constructors
	public Tenant(){
		super();
	}
	public Tenant(String _familyName, int _numberOfOccupants, boolean _hasPets, LocalDate _residencyDate) {
		this.familyName = _familyName;
		this.numberOfOccupants = _numberOfOccupants;
		this.hasPets = _hasPets;
		this.residencyDate = _residencyDate;
		//we'll probably only need this one different constructor (I think)	
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
	
	
	
	//helper methods
	@Override
	public String toString() {
		return "Tenant [id=" + id + ", familyName=" + familyName + ", numberOfOccupants=" + numberOfOccupants
				+ ", hasPets=" + hasPets + ", residencyDate=" + residencyDate + "]";
	}
	
	
	

}
