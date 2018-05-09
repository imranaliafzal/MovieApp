package com.iafzal.challenge.samsung.movieapp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/7/18.
 * Copyright © 2018 Imran Afzal. All rights reserved.
 */
public class DateUtil {
    private static DateUtil ourInstance = new DateUtil();
    public static DateUtil getInstance() {
        return ourInstance;
    }

    private DateUtil() {

    }


    public String formatShortDate(Date pDate){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = df.format(pDate);
        return formattedDate;
    }


    public String stringDateByAddingNumOfMonthsFrom(Date date, int numOfMonth){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.add(Calendar.MONTH, +numOfMonth);

        return formatShortDate(cal.getTime());
    }

    public Date dateByAddingNumOfMonthsFrom(Date date, int numOfMonth){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.add(Calendar.MONTH, +numOfMonth);

        return cal.getTime();
    }

    public String stringDateBySubtractingNumOfMonthsFrom(Date date, int numOfMonth){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -numOfMonth);

        return formatShortDate(cal.getTime());
    }

    public Date dateBySubtractingNumOfMonthsFrom(Date date, int numOfMonth){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -numOfMonth);
        return cal.getTime();
    }


}
