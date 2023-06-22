/**
 * @author - Alex Cox
 * CIS175 2023
 * 
 * This class is used to calculate and format the move-in date for an apartment
 */

package apartments;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    // This method calculates the move-in date, which is two weeks from the current date,
    // and then formats the date into a string
    public static String getMoveInDate() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the move-in date by adding 2 weeks to the current date
        LocalDate moveInDate = currentDate.plusWeeks(2);

        // Format the move-in date and return the formatted date
        return formatDate(moveInDate);
    }

    // This method formats a LocalDate into a String using the pattern "MM/dd/yyyy"
    private static String formatDate(LocalDate date) {
        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        // Format the date and return the formatted date
        return date.format(formatter);
    }
}
