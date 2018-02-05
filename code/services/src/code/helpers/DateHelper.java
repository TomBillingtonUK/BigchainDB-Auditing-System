package code.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
    This class contains methods which help the system deal with dates
 */
public class DateHelper
{
    /*
        Converts a ISO String to a Local Date Time object
     */
    public static LocalDateTime stringToDate(String date)
    {
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
    }

    /*
        Converts a Local Date Time to a ISO String
     */
    public static String dateToString(LocalDateTime date)
    {
        return date.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
