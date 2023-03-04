package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author andrewmccoy - agmccoy
 * CIS175 - Fall 2021
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
	
	
	//constructors
	public ApartmentBuilding() {
		super();
	}
	
	public ApartmentBuilding(String _name, int _numberOfApartments) {
		this.name = _name;
		this.numberOfApartments = _numberOfApartments;
		//should always use this constructor (probably)
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

		
	//helper methods	
	@Override
	public String toString() {
		return "ApartmentBuilding [id=" + id + ", name=" + name + ", numberOfApartments=" + numberOfApartments + "]";
	}
	
}
