package com.psethi.api.adapter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
 * Class does the conversion of String Date-Time to Date Object.
 * 
 * @author Puneet Kaur Sethi
 * @version 1.0
 * @date: 08/28/2017
 *
 *
 */
public final class XsdDateTimeUtils extends JsonSerializer<Date> {

    
    /**
     * Method is used to convert String Date to Date Object.
     * 
     * @param date
     * @return Date Object
     */
    public static Date parseDate(String date) {  
        Date dt = null;
        SimpleDateFormat formater = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        try {
            dt = formater.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * Method is used to convert Date to String.
     * 
     * @param date
     * @return String
     */
    public static String printDate(Date date) {
        SimpleDateFormat formater = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dt = formater.format(date);
        return dt;
    }

    /**
     * Method is used to serialize the Date
     * 
     * @param date
     * @param gen
     * @param provider
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        String formattedDate = XsdDateTimeUtils.printDate(date);
        gen.writeString(formattedDate);
    }
}