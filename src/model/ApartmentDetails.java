package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author andrewmccoy - agmccoy
 * CIS175 - Spring 2023
 * Mar 4, 2023
 */
@Entity
@Table(name="apartmentDetails")
public class ApartmentDetails {
	

	//properties
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;	
	@Column(name="APARTMENT_LIST_NAME")
	private String apartmentListName;
	@JoinColumn(name="BUILDING")
	@ManyToOne (cascade = CascadeType.PERSIST)
	private ApartmentBuilding building;
	@JoinColumn(name="LIST_OF_TENANTS")
	@OneToMany (cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Tenant> listOfTenants;
	
	
	//constructors
	public ApartmentDetails() {
		super();		
	}
	public ApartmentDetails(int _id, String _apartmentListName, ApartmentBuilding _building, List<Tenant> _listOfTenants) {
		this.id = _id;
		this.apartmentListName = _apartmentListName;
		this.building = _building;
		this.listOfTenants = _listOfTenants; 		
	}
	public ApartmentDetails(String _apartmentListName, ApartmentBuilding _building, List<Tenant> _listOfTenants) {		
		this.apartmentListName = _apartmentListName;
		this.building = _building;
		this.listOfTenants = _listOfTenants; 		
	}
	public ApartmentDetails(String _apartmentListName, ApartmentBuilding _building) {		
		this.apartmentListName = _apartmentListName;
		this.building = _building;	 	
	}//not sure if we'll need this last one, but we got it in case. 
	
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getApartmentListName() {
		return apartmentListName;
	}
	public void setApartmentListName(String apartmentListName) {
		this.apartmentListName = apartmentListName;
	}
	public ApartmentBuilding getBuilding() {
		return building;
	}
	public void setBuilding(ApartmentBuilding building) {
		this.building = building;
	}
	public List<Tenant> getListOfTenants() {
		return listOfTenants;
	}
	public void setListOfTenants(List<Tenant> listOfTenants) {
		this.listOfTenants = listOfTenants;
	}
	
	
	
	//helper methods 
	@Override
	public String toString() {
		return "ApartmentDetails [id=" + id + ", apartmentListName=" + apartmentListName + ", building=" + building
				+ ", listOfTenants=" + listOfTenants + "]";
	}
		
	
}
