// Copyright (C) 2017 Meituan
// All rights reserved
package classes.newDateAPI;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 2018/6/5.
 */
public class LocalDateTimeUtils {

    public static TemporalAdjuster nextWorkDay = TemporalAdjusters.ofDateAdjuster(LocalDateTimeUtils::nextWorkingDay);

    private static LocalDate nextWorkingDay(LocalDate localDate) {
        Objects.requireNonNull(localDate);
        switch (localDate.getDayOfWeek()) {
        case FRIDAY:
            return localDate.plusDays(3);
        case SATURDAY:
            return localDate.plusDays(2);

        default:
            return localDate.plusDays(1);
        }
    }
}
