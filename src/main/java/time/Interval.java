package time;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class Interval {

    protected Calendar startDate;
    protected Calendar endDate;
    protected Calendar startHour;
    protected Calendar endHour;

    public Calendar getEndDate() {
        return endDate;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setEndDate(int year, int week) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week-1);

        if (getStartDate().after(calendar)) {
            calendar.set(Calendar.WEEK_OF_YEAR, week);
            setEndDate(calendar);
        } else {
            System.out.println("nej");
        }
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(int year, int week) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week+1);

        if (getStartDate().before(calendar)) {
            calendar.set(Calendar.WEEK_OF_YEAR, week);
            setStartDate(calendar);
        } else {
            System.out.println("nej");
        }
    }

    public Calendar getStartHour() { return startHour; }

    public void setStartHour(Calendar startHour) {
        this.startHour = startHour;
    }

    public Calendar getEndHour() {
        return endHour;
    }

    public void setEndHour(Calendar endHour) {
        this.endHour = endHour;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
}
