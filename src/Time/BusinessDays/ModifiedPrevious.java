/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Time.BusinessDays;

import Time.Holidays.Holiday;
import Time.Periods.Period;
import java.util.Calendar;

/**
 * Implementation of {@see BusinessDays business days} that use the
 * <a href="http://www.investopedia.com/terms/m/modifiedfollowing.asp">
 * modified previous</a> algorithm to adjust to business days.
 * @author Rune Dahl Iversen
 * @param <TypeOfCalendar> Type of calendar.
 */
public final class ModifiedPrevious<TypeOfCalendar>
        extends HolidaysBased<TypeOfCalendar> {
    /**
     * Creates an instance of the modified previous business days for the
     * specified holidays and period.
     * @param holidays Holidays.
     * @param period   Period to use when adjusting and shifting date/times.
     */
    public ModifiedPrevious(final Holiday<TypeOfCalendar> holidays,
            final Period<TypeOfCalendar> period) {
        super(holidays, period);
    }

    @Override
    public TypeOfCalendar adjust(final TypeOfCalendar dateTime) {
        Period<TypeOfCalendar> p = this.getPeriod();
        TypeOfCalendar adjusted = dateTime;
        Holiday<TypeOfCalendar> holidays = this.getHolidays();
        while (holidays.isHoliday(adjusted))
            adjusted = p.shift(adjusted, -1);
        Calendar d = (Calendar)dateTime;
        Calendar a = (Calendar)adjusted;
        if (d.get(Calendar.MONTH) != a.get(Calendar.MONTH)) {
            adjusted = dateTime;
            while (holidays.isHoliday(adjusted))
                adjusted = p.shift(adjusted, 1);
        }
        return adjusted;
    }

    @Override
    public TypeOfCalendar shift(final TypeOfCalendar dateTime,
            final int count) {
        Period<TypeOfCalendar> p = this.getPeriod();
        TypeOfCalendar shifted = dateTime;
        Holiday<TypeOfCalendar> holidays = this.getHolidays();
        for (int i = 0; i < Math.abs(count); )
        {
            shifted = p.shift(shifted, (int)Math.signum(count));
            if (!holidays.isHoliday(shifted))
                i++;
        }
        return shifted;
    }
}