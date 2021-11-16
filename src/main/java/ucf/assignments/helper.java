package ucf.assignments;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class helper {

    public static boolean check_task_length(String task)
    {
        if(task.length() > 256 || task.length() < 1)
            return false;
        else
            return true;

    }

    public static boolean check_date_format(String date)
    {
        System.out.println(date);
        String DATE_FORMAT = "yyyy/MM/dd";
        try {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        df.setLenient(false);
        df.parse(date);
        return true;
    } catch (ParseException e) {
        return false;
    }
    }

    public static LocalDate getLocalDate(String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate it = LocalDate.parse(date,formatter);
        return it;
    }



}
