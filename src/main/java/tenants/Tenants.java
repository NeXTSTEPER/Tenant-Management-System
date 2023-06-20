/**
 * @author - Alex Cox
 * CIS175 2023
 * 
 */

package tenants;

// Importing necessary libraries for our Tenant class
import java.io.Serializable; // This allows our Tenant objects to be converted into a format that can be stored and retrieved easily.
import javax.persistence.Entity; // This marks our Tenant class as an entity which can be mapped to a database.
import javax.persistence.GeneratedValue; // This is for our ID field which is auto-incremented.
import javax.persistence.GenerationType; // This is needed to specify the type of auto-increment.
import javax.persistence.Id; // This marks our id field as the primary key of our table.
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table; // This is needed to specify the name of the table that this entity is mapped to.

import apartments.Apartment;

// Defining entity class Tenant which will represent a table named 'tenants' in the database.
@Entity
@Table(name="tenants")
public class Tenants implements Serializable {
    
    // A unique identifier for versions of the class to verify during deserialization
    private static final long serialVersionUID = 1L;

    // Persistent Fields:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generate id automatically with identity strategy (auto incremented field)
    private int id; // Unique identifier for each tenant
    
    @ManyToOne
    @JoinColumn(name="apartment_id")
    private Apartment apartment;
    
    private String name; // Name of the tenant
    
    private String phoneNumber; // Phone number of the tenant
    
    private int numberOfRoomsDesired; // Number of rooms desired by the tenant

    // Constructors:
    // Default constructor for Tenant class
    public Tenants() {
    }

    // Overloaded constructor to initialize a tenant with name, phone number, and number of rooms desired
    public Tenants(String name, String phoneNumber, int numberOfRoomsDesired) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.numberOfRoomsDesired = numberOfRoomsDesired;
    }

    // String Representation:
    // Overriding the toString method to represent our tenant
    @Override
    public String toString() {
        return this.name + ", " + this.phoneNumber + ", desires " + this.numberOfRoomsDesired + " rooms.";
    }

    // Getter methods
    // Method to get id of the tenant
    public int getId() {
        return id;
    }

    // Method to get the name of the tenant
    public String getName() {
        return name;
    }

    // Method to get the phone number of the tenant
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Method to get the number of rooms desired by the tenant
    public int getNumberOfRoomsDesired() {
        return numberOfRoomsDesired;
    }

    // Setter methods
    // Method to set the id of the tenant
    public void setId(int id) {
        this.id = id;
    }

    // Method to set the name of the tenant
    public void setName(String name) {
        this.name = name;
    }

    // Method to set the phone number of the tenant
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Method to set the number of rooms desired by the tenant
    public void setNumberOfRoomsDesired(int numberOfRoomsDesired) {
        this.numberOfRoomsDesired = numberOfRoomsDesired;
    }
    
    //getters and setters for 'apartment'
    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
