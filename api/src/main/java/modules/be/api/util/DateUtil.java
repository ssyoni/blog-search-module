package modules.be.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy/MM/dd");
//    public static ZonedDateTime toZonedDateTime(String datetimeString) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        return ZonedDateTime.of(LocalDateTime.parse(datetimeString, formatter));
//    }
}
