package time;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class Interval {

    protected Calendar startDate;
    protected Calendar endDate;
    protected Calendar startHour;
    protected Calendar endHour;


    public Calendar getEndDate() {
        if( endDate != null){
            return endDate;
        }

        return null;
    }

    public Calendar getStartDate() {
        if( startDate != null){
            return startDate;
        }
        return null;
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

    public void setStartDate(Calendar startDate) { this.startDate = startDate; }

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


}
