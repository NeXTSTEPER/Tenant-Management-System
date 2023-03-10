import java.time.LocalDate;
import java.util.ArrayList;

import controller.ApartmentBuildingHelper;
import controller.ApartmentDetailsHelper;
import controller.TenantHelper;
import model.ApartmentBuilding;
import model.ApartmentDetails;
import model.Tenant;

/**
 * @author andrewmccoy - agmccoy
 * CIS175 - Fall 2021
 * Mar 4, 2023
 */
public class ApartmentDetailsTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApartmentDetailsHelper adh = new ApartmentDetailsHelper();
		TenantHelper th = new TenantHelper();
		ApartmentBuildingHelper abh = new ApartmentBuildingHelper();
		
		
		
		
		  ApartmentBuilding buildingOne = new ApartmentBuilding("Apartment One", 15,
		  30); Tenant tenantOne = new Tenant("McCoys", 3, false, LocalDate.now(),
		  1500); Tenant tenantTwo = new Tenant("Smiths", 4, true, LocalDate.now(),
		  1200); Tenant tenantThree = new Tenant("Coxs", 2, false, LocalDate.now(),
		  1300); var listOfTenants = new ArrayList<Tenant>();
		  listOfTenants.add(tenantOne); listOfTenants.add(tenantTwo);
		  listOfTenants.add(tenantThree);
		  
		  var ApartmentDetails = new ApartmentDetails("Apartment One Details",
		  buildingOne, listOfTenants);
		  
		  adh.insertNewApartmentDetails(ApartmentDetails);
		  
		 
		
		
		
		  var found = adh.getApartments(); 
		  for(ApartmentDetails a: found) {
			  
			  System.out.println(a.getListOfTenants().toString()); 			  
		  }		  			  		 		 	
	}
}
