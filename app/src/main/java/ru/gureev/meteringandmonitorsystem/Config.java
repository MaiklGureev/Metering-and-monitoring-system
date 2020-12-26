package ru.gureev.meteringandmonitorsystem;

import java.util.Date;

public class Config {

    public static final String POSITION0 = "контролёр";
    public static final String POSITION1 = "диспетчер";

    public static final String DEVICE_STATUS_GOOD = "исправен";
    public static final String DEVICE_STATUS_BAD = "не работает";
    public static final String DEVICE_STATUS_BROKEN = "поврежден";
    public static final String PHOTO_1 = "photo_1";
    public static final String PHOTO_2 = "photo_2";
    public static final String PHOTO_3 = "photo_3";


    public static String getCurrentDateTime() {
        Date date = new Date();
        String dt = String.format("%td-%tm-%tY %tk:%tM", date, date, date, date, date);
        return dt;
    }

    public static String generateIdWithDate() {
        Date date = new Date();
        return String.valueOf(date.getTime());
    }

}
