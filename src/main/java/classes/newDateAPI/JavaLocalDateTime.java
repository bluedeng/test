// Copyright (C) 2017 Meituan
// All rights reserved
package classes.newDateAPI;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Locale;

import static Utils.BasicUtils.enter;
import static Utils.BasicUtils.println;
import static java.time.temporal.TemporalAdjusters.*;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 2018/6/5.
 */
public class JavaLocalDateTime {

    public static void main(String... args) {
        localDateExample();
        localTimeExample();
        localDateTimeExample();
        temporalAdjusterExample();
        localDateTimeInteractWithDateExample();
        localDateParseExample();
    }

    private static void localDateExample() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        println("DATE: " + date);
        println("YEAR: " + date.getYear());
        println("MONTH: " + date.getMonth());
        println("DAY OF WEEK: " + date.getDayOfWeek());
        println("DAY OF YEAR: " + date.get(ChronoField.DAY_OF_YEAR));
        println("CUR MONTH LENTH: " + date.lengthOfMonth());
        println("CHANGE YEAR TO 2011: " + date.with(ChronoField.YEAR, 2011));
        println("CHANGE MONTH TO 5: " + date.withMonth(5));
        println("ORIGIN DATE NOT CHANGE: " + date);
        enter();

        date = LocalDate.now();
        println("DATE: " + date);
        enter();

        date = LocalDate.parse("2018-01-02");
        println("DATE: " + date);
        enter();
    }

    private static void localTimeExample() {
        LocalTime time = LocalTime.of(13, 45, 20);
        println("TIME: " + time);
        println("HOUR: " + time.getHour());
        println("MINUTE: " + time.getMinute());
        println("SECOND: " + time.getSecond());
        println("CHANGE MINUTE TO 10: " + time.withMinute(10));
        println("CHANGE SECOND TO 40: " + time.with(ChronoField.SECOND_OF_MINUTE, 40));
        println("ORIGIN TIME NOT CHANGE: " + time);
        enter();

        time = LocalTime.now();
        println("TIME: " + time);
        enter();

        time = LocalTime.parse("20:11:11");
        println("TIME: " + time);
        enter();
    }

    private static void localDateTimeExample() {
        LocalDateTime dateTime = LocalDateTime.of(2014, 3, 18, 13, 45, 20);
        println("DATETIME: " + dateTime);
        println("YEAR: " + dateTime.getYear());
        println("MONTH: " + dateTime.getMonth());
        println("DAY OF WEEK: " + dateTime.getDayOfWeek());
        println("DAY OF YEAR: " + dateTime.get(ChronoField.DAY_OF_YEAR));
        enter();

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        dateTime = LocalDateTime.now();
        println("NOW IS EQUAL? " + LocalDateTime.of(date, time).equals(dateTime));
        println("NOW IS: " + dateTime);
        println("NOW DATE IS EQUAL? " + date.equals(dateTime.toLocalDate()));
        println("NOW TIME IS EQUAL? " + time.equals(dateTime.toLocalTime()));
        println("DATE/TIME CONVERT CHECK? " + date.atTime(time).equals(time.atDate(date)));
        enter();
    }

    private static void temporalAdjusterExample() {
        LocalDate date = LocalDate.now();
        println("DATE: " + date);
        println("NEXT OR THIS SUNDAY: " + date.with(nextOrSame(DayOfWeek.SUNDAY)));
        println("LAST DAY OF MONTH: " + date.with(lastDayOfMonth()));
        println("FIRST DAY OF NEXT MONTH: " + date.with(firstDayOfNextMonth()));
        println("NEXT WORKING DAY: " + date.with(LocalDateTimeUtils.nextWorkDay));
        enter();
    }

    private static void localDateTimeInteractWithDateExample() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = new Date();
        println("LOCAL DATE TIME: " + localDateTime);
        println("DATE: " + date);
        LocalDateTime convertDateTime = LocalDateTimeUtils.convert2LocalDateTime(date);
        Date convertDate = LocalDateTimeUtils.convert2Date(localDateTime);
        println("DATE 2 LOCAL DATE TIME: " + convertDateTime);
        println("LOCAL DATE TIME 2 DATE: " + convertDate);
        //转换可能丢失精度，造成LocalDateTime.now()与new Date()不等
        println(localDateTime.equals(convertDateTime));
        println(date.equals(convertDate));
        enter();
    }

    private static void localDateParseExample() {
        LocalDate localDate = LocalDate.now();
        println("LOCAL DATE NOW: " + localDate);
        println("FORMAT 2 STRING: " + localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
        println("FORMAT 2 LOCAL DATE: " + LocalDate.parse("2018-06-06", DateTimeFormatter.ISO_LOCAL_DATE));

        DateTimeFormatter customFormatter1 = DateTimeFormatter.ofPattern("dd=MM=yyyy");
        println("CUSTOM FORMATTER: " + localDate.format(customFormatter1));

        DateTimeFormatter customFormatter2 = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral("~~")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral("--")
                .appendText(ChronoField.YEAR)
                .toFormatter(Locale.CHINA);
        println("CUSTOM FORMATTER: " + localDate.format(customFormatter2));
        enter();
    }
}
