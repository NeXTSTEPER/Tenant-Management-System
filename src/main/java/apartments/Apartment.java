/**
 * @author - Alex Cox
 * CIS175 2023
 * 
 */

package apartments;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="apartments")
public class Apartment implements Serializable {
    
  
    private static final long serialVersionUID = 1L;

  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Column for the apartment's address
    private String address;

    // Column for the rent cost of the apartment
    private double rent;

    // Column for the number of rooms in the apartment
    private int numberOfRooms;
    
    // Column to check if the apartment is selected or not
    private boolean isSelected;

    // Default constructor
    public Apartment() {
    }

    // Parameterized constructor to create a new Apartment object
    public Apartment(String address, double rent, int numberOfRooms) {
        this.address = address;
        this.rent = rent;
        this.numberOfRooms = numberOfRooms;
    }

    // Getters and setters for the fields
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRent() {
        return rent;
    }

    // Returns the rent value formatted as a string with two decimal places
    public String getFormattedRent() {
        return String.format("%.2f", this.rent);
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
    
    public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
    
    // toString method to print a readable string of the Apartment object
    @Override
    public String toString() {
        return "Apartment at " + this.address + " with rent " + this.rent + " and number of rooms " + this.numberOfRooms;
    }
}
