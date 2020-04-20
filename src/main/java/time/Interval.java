package time;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class Interval {

    protected Calendar startDate;
    protected Calendar endDate;

    public Interval(Calendar startDate, Calendar endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setEndDate(int year, int week) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        setEndDate(calendar);
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(int year, int week) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        setStartDate(calendar);
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
}
