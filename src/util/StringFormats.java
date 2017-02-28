package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringFormats {
    public static String getTime(long millis) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(new Date(millis));
    }
}
