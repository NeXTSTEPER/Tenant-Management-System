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

    private String address;

    private double rent;

    private int numberOfRooms;

    public Apartment() {
    }

    public Apartment(String address, double rent, int numberOfRooms) {
        this.address = address;
        this.rent = rent;
        this.numberOfRooms = numberOfRooms;
    }

    @Override
    public String toString() {
        return "Apartment at " + this.address + " with rent " + this.rent + " and number of rooms " + this.numberOfRooms;
    }

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

    public void setRent(double rent) {
        this.rent = rent;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
}