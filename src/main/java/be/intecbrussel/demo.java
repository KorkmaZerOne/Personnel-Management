package be.intecbrussel;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class demo {

    public static <decimal> void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LocalDate localStartDate;
        LocalDate localEndDate;

        //System.out.println("Start Date (yyyy-MM-dd): ");
        String startDate = "2020-08-08";
        System.out.println(startDate);
        //scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        localStartDate = LocalDate.parse(startDate, formatter);
        System.out.println(localStartDate);

        //System.out.println("End Date (yyyy-MM-dd): ");
        String endDate = "2020-08-09";
        System.out.println(endDate);
                //scanner.next();
        localEndDate = LocalDate.parse(endDate, formatter);
        System.out.println(localEndDate);

       int result = (int) ((int) (ChronoUnit.DAYS.between(localStartDate, localEndDate))/30.00*22*8);
        //DecimalFormat daydreamer = new DecimalFormat("###,######");
        //System.out.println("difference between hours: " + daydreamer.format(result));
        System.out.println("HoursWorked: " + result);
    }
}
