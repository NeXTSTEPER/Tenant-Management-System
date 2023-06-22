package apartments;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    public static String getMoveInDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate moveInDate = currentDate.plusWeeks(2);
        return formatDate(moveInDate);
    }

    private static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return date.format(formatter);
    }
}
