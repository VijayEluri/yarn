/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Time;

import java.util.GregorianCalendar;

/**
 * Utility class containing methods for date/times in the
 * <a href="http://en.wikipedia.org/wiki/Gregorian_calendar">Gregorian calendar</a>.
 * @author Rune Dahl Iversen
 */
public final class Gregorian {
    private Gregorian()
    { /* Intentional */ }

    /**
     * Returns the difference between the two specified Gregorian date/times
     * that stem from the differences in Hours, Minutes, Seconds and
     * Milli-seconds measured in days.
     * @param from From date/time.
     * @param to   To date/time.
     * @return     The difference measured in days stemming from Hours, Minutes,
     *             Seconds and Milli-seconds.
     */
    public static double belowDateDifference(
            final GregorianCalendar from,
            final GregorianCalendar to) {
        double difference = (to.get(GregorianCalendar.MILLISECOND) - from.get(GregorianCalendar.MILLISECOND)) / 1000.0;
        difference += (to.get(GregorianCalendar.SECOND) - from.get(GregorianCalendar.SECOND));
        difference /= 60.0;
        difference += (to.get(GregorianCalendar.MINUTE) - from.get(GregorianCalendar.MINUTE));
        difference /= 60.0;
        difference += (to.get(GregorianCalendar.HOUR_OF_DAY) - from.get(GregorianCalendar.HOUR_OF_DAY));
        difference /= 24.0;
        return difference;
    }

    /**
     * Returns the correction in terms of days to add when computing the date/time in the
     * <a href="http://en.wikipedia.org/wiki/Gregorian_calendar">Gregorian calendar</a>
     * from the same point in time from the
     * <a href="http://en.wikipedia.org/wiki/Julian_calendar">Julian calendar</a>.
     * @param year Year. Must be 1582 or later.
     * @return     Days to add to the date/time to get the date/time in the
     *             Gregorian calendar from the same point in time in the
     *             Julian calendar.
     */
    public static int correctionInDaysFromJulianToGregorian(final int year) {
        if (year < 1582)
            throw new IllegalArgumentException("Before the year 1582 " +
                    "the Gregorian calendar was not implemented.");
        if (4100 <= year)
            throw new IllegalArgumentException("Around the year 4100 " +
                    "the Gregorian calendar will need a correction of 1 day.");
        int centuryDigits = year / 100;
        int days = 10;
        if (1600 < year)
            days += centuryDigits - 16 - ((centuryDigits - 16) / 4);
        return days;
    }

    /**
     * Returns the date-part of the specified gregorian date/time.
     * @param dateTime Gregorian date/time.
     * @return         Gregorian date.
     */
    public static GregorianCalendar date(final GregorianCalendar dateTime) {
        int year = dateTime.get(GregorianCalendar.YEAR);
        int month = dateTime.get(GregorianCalendar.MONTH);
        int dayOfMonth = dateTime.get(GregorianCalendar.DAY_OF_MONTH);
        return new GregorianCalendar(year, month, dayOfMonth);
    }

    /**
     * Computes whether the specified year is a leap-year in the Gregorian calendar.
     * @param year Year.
     * @return     True if year is a leap-year, otherwise false.
     */
    public static boolean isLeapYear(final int year) {
        boolean isLeapYear = (year % 4 == 0);
        if (isLeapYear)
        {
            isLeapYear = (year % 100 != 0);
            if (!isLeapYear)
                isLeapYear = (year % 400 == 0);
        }
        return isLeapYear;
    }

    /**
     * Computes whether the specified date/time falls within a leap-year
     * in the Gregorian calendar.
     * @param date date/time.
     * @return     True if the date is within a leap-year, otherwise false.
     */
    public static boolean isLeapYear(final GregorianCalendar date) {
        return isLeapYear(date.get(GregorianCalendar.YEAR));
    }

    /**
     * Returns the actual length of a month in a year from the Gregorian calendar in days.
     * @param year  Year.
     * @param month Month.
     * @return Length of the month in days.
     */
    public static double lengthOfMonth(final int year, final int month) {
        double length = 0.0;
        switch (month)
        {
            case 0:
                length = 31.0;
                break;
            case 1:
                length = 28.0 + (isLeapYear(year) ? 1.0 : 0.0);
                break;
            case 2:
                length = 31.0;
                break;
            case 3:
                length = 30.0;
                break;
            case 4:
                length = 31.0;
                break;
            case 5:
                length = 30.0;
                break;
            case 6:
                length = 31.0;
                break;
            case 7:
                length = 31.0;
                break;
            case 8:
                length = 30.0;
                break;
            case 9:
                length = 31.0;
                break;
            case 10:
                length = 30.0;
                break;
            case 11:
                length = 31.0;
                break;
            default:
                throw new IllegalArgumentException("The month must be an " +
                        "integer in the range 0 to 11 (both included).");
        }
        return length;
    }

    /**
     * Returns the actual length of a year in the Gregorian calendar in days.
     * @param year Year.
     * @return Length of the year in days.
     */
    public static double lengthOfYear(final int year) {
        return 365.0 + (isLeapYear(year) ? 1.0 : 0.0);
    }

    /**
     * Returns the latest of the 2 specified Gregorian date/times.
     * @param date1 date/time.
     * @param date2 date/time.
     * @return      The latest of the 2 specified Gregorian date/times.
     */
    public static GregorianCalendar max(
            final GregorianCalendar date1, final GregorianCalendar date2) {
        if (0 < date2.compareTo(date1))
            return (GregorianCalendar)date2.clone();
        else
            return (GregorianCalendar)date1.clone();
    }

    /**
     * Returns the earliest of the 2 specified Gregorian date/times.
     * @param date1 date/time.
     * @param date2 date/time.
     * @return      The earliest of the 2 specified Gregorian date/times.
     */
    public static GregorianCalendar min(
            final GregorianCalendar date1, final GregorianCalendar date2) {
        if (0 < date2.compareTo(date1))
            return (GregorianCalendar)date1.clone();
        else
            return (GregorianCalendar)date2.clone();
    }

    /**
     * Returns the default string representation of a date/time in the
     * Gregorian calendar.
     * @param dateTime date/Time.
     * @return         String value representing the date/time.
     */
    public static String toString(final GregorianCalendar dateTime) {
        StringBuilder d = new StringBuilder();
        d.append(Integer.toString(dateTime.get(GregorianCalendar.YEAR)) + "-");
        d.append(Integer.toString(dateTime.get(GregorianCalendar.MONTH) + 1) + "-");
        d.append(Integer.toString(dateTime.get(GregorianCalendar.DAY_OF_MONTH)) + " ");
        d.append(Integer.toString(dateTime.get(GregorianCalendar.HOUR_OF_DAY)) + ":");
        d.append(Integer.toString(dateTime.get(GregorianCalendar.MINUTE)) + ":");
        d.append(Integer.toString(dateTime.get(GregorianCalendar.SECOND)) + ".");
        d.append(Integer.toString(dateTime.get(GregorianCalendar.MILLISECOND)));
        return d.toString();
    }
}
