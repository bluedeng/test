package classes.newDateAPI;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
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

    /**
     * 可能丢失精度
     * localDateTime为纳秒级
     * date为毫秒级
     * @param localDateTime
     * @return
     */
    public static Date convert2Date(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        } else {
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
            return Date.from(zonedDateTime.toInstant());
        }
    }

    /**
     * 可能丢失精度
     * localDateTime为纳秒级
     * date为毫秒级
     * @param date
     * @return
     */
    public static LocalDateTime convert2LocalDateTime(Date date) {
        if (date == null) {
            return null;
        } else {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return LocalDateTime.ofInstant(instant, zoneId);
        }
    }
}
