package HMS;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class DateValidator {
    private static final String FORMATTED = "dd/MM/yyyy";

    //constructor
    public DateValidator() {
    }

    /**
     *
     */
    public String validate(final String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATTED);
        Date date2 = null;
        try {
            date2 = dateFormat.parse(date);
        } catch (ParseException e) {
            throw e;
        }
        return dateFormat.format(date2);
    }

    public boolean isSecondDateGreater(String date1, String date2) throws ParseException {

        SimpleDateFormat dateformat = new SimpleDateFormat(FORMATTED);
        Date d1 = dateformat.parse(date1);
        Date d2 = dateformat.parse(date2);

        if (d1.compareTo(d2) > 0) {
            return false;
        } else if (date1.compareTo(date2) < 0) {
            return true;
        } else if (date1.compareTo(date2) == 0) {
            return true;
        } else {
            return false;
        }
    }
}