package ru.geekbrains.team1.geek_crm.Util;

import com.google.gson.annotations.Until;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class TimeUtils {

    public static LocalDateTime localDateConvert(String dateTime) {
        if (!dateTime.equals("null")) {
            dateTime = dateTime.replace("T", " ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime date = LocalDateTime.parse(dateTime, formatter);
            System.out.println(date);
            return date;
        } else return null;
    }
}
