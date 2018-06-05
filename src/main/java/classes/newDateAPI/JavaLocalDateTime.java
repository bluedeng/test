// Copyright (C) 2017 Meituan
// All rights reserved
package classes.newDateAPI;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

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
        temporalAdjuster();
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

    private static void temporalAdjuster() {
        LocalDate date = LocalDate.now();
        println("DATE: " + date);
        println("NEXT OR THIS SUNDAY: " + date.with(nextOrSame(DayOfWeek.SUNDAY)));
        println("LAST DAY OF MONTH: " + date.with(lastDayOfMonth()));
        println("FIRST DAY OF NEXT MONTH: " + date.with(firstDayOfNextMonth()));
        println("NEXT WORKING DAY: " + date.with(LocalDateTimeUtils.nextWorkDay));
        enter();
    }
}
