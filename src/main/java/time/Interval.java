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

    public String getStartWeek() {
        if(startDate == null) {
            return null;
        }
        return ""+startDate.get(Calendar.WEEK_OF_YEAR);
    }

    public String getStartYear() {
        if(startDate == null) {
            return null;
        }
        return ""+startDate.get(Calendar.YEAR);
    }

    public Calendar getEndDate() {
        if( endDate != null){
            return endDate;
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



}
