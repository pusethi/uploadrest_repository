package com.psethi.api.util;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;


/**
 * Util class for date related operations
 * 
 * @author Puneet Kaur Sethi
 * @date Aug 29, 2017
 * @version 1.0
 */
public class DateUtil {

    /**
     * Helper Method to get current datetime using JodaTime and convert it to
     * java.util.Date Obj
     * 
     * @return the current date
     */
    public static Date getCurrentDate() {
        DateTime dateTimeObj = new DateTime(DateTimeZone.UTC);
        Date dateObj = dateTimeObj.toDate();
        return dateObj;
    }
}
