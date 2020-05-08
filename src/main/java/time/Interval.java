package time;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class Interval {

    protected Calendar startDate;
    protected Calendar endDate;


    public Calendar getStartDate() {
        if( startDate != null){
            return startDate;
        }
        return null;
    }

    public int getStartWeek() {
        if(startDate == null) {
            return -1;
        }
        return startDate.get(Calendar.WEEK_OF_YEAR);
    }

    public int getStartYear() {
        if(startDate == null) {
            return -1;
        }
        return startDate.get(Calendar.YEAR);
    }

    public Calendar getEndDate() {
        if(endDate != null){
            return endDate;
        }
        return null;
    }

    public int getEndWeek() {
        if(endDate == null) {
            return -1;
        }
        return endDate.get(Calendar.WEEK_OF_YEAR);
    }

    public int getEndYear() {
        if(endDate == null) {
            return -1;
        }
        return endDate.get(Calendar.YEAR);
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
}
