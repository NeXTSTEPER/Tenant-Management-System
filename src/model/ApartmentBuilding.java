package model;

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
@Table(name="apartmentBuilding")
public class ApartmentBuilding {
	
	//properties
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="NAME")
	private String name;
	@Column(name="NUMBER_OF_APARTMENTS")
	private int numberOfApartments;//or number of rooms
	@Column(name="NUMBER_OF_BATHS")
	private int numberOfBaths; //number of bathrooms in apartment
	
	
	//constructors
	public ApartmentBuilding() {
		super();
	}
	
	public ApartmentBuilding(String _name, int _numberOfApartments, int _numberOfBaths) {
		this.name = _name;
		this.numberOfApartments = _numberOfApartments;
		this.numberOfBaths = _numberOfBaths;
		//should always use this constructor (probably)
	}
	

	public ApartmentBuilding(String _name) {
		this.name = _name;
	}

	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfApartments() {
		return numberOfApartments;
	}
	public void setNumberOfApartments(int numberOfApartments) {
		this.numberOfApartments = numberOfApartments;
	}
	public int getNumberOfBaths() {
		return numberOfBaths;
	}

	public void setNumberOfBaths(int numberOfBaths) {
		this.numberOfBaths = numberOfBaths;
	}
		
	@Override
	public String toString() {
		return "ApartmentBuilding [id=" + id + ", name=" + name + ", numberOfApartments=" + numberOfApartments
				+ ", numberOfBaths=" + numberOfBaths + "]";
	}
	
}
