import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import controller.ApartmentBuildingHelper;
import controller.ApartmentDetailsHelper;
import controller.TenantHelper;
import model.ApartmentBuilding;
import model.ApartmentDetails;
import model.Tenant;

/**
 * @author andrewmccoy - agmccoy
 * CIS175 - Spring 2023
 * Mar 4, 2023
 */

//Testing class 
public class ApartmentDetailsTester {

	public static void main(String[] args) {
		ApartmentDetailsHelper adh = new ApartmentDetailsHelper();
		TenantHelper th = new TenantHelper();
		ApartmentBuildingHelper abh = new ApartmentBuildingHelper(); 		 		 	

		ApartmentBuilding buildingOne = new ApartmentBuilding("Apartment Complex One", 15, 30);
		Tenant tenantOne = new Tenant("McCoy", 3, false, LocalDate.now(), 1500);
		Tenant tenantTwo = new Tenant("Smiths", 4, true, LocalDate.now(), 1200);
		Tenant tenantThree = new Tenant("Coxs", 2, false, LocalDate.now(), 1300);
		var listOfTenants = new ArrayList<Tenant>();
		listOfTenants.add(tenantOne);
		listOfTenants.add(tenantTwo);
		listOfTenants.add(tenantThree);
		
		//Date testing
		//Prompting for user input
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your moving date(MM-DD-YYYY):");
		String userChoice = in.nextLine();
		
		// Print & format the result for testing
		Date date = Tenant.dateConverter(userChoice); //new date to store converted user input
	
		//formatted output - gives weekday of move in
		System.out.println(new SimpleDateFormat("EEEEE MMMM d, yyyy").format(date)); 
	
		//Converting from Date to LocalDate
		LocalDate lDateResidency = Tenant.dateToLocalDate(date); 
		
		tenantThree.setResidencyDate(lDateResidency); //set residency date from date gathered from prompt
		var ApartmentDetails = new ApartmentDetails("Apartment Complex One Details", buildingOne, listOfTenants);
		adh.insertNewApartmentDetails(ApartmentDetails);
		
		
		System.out.println(tenantThree.getResidencyDate()); //Testing residency date
		
	in.close();

	}
}
