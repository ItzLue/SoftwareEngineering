package time;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class Interval {

    protected Calendar startDate;
    protected Calendar endDate;

    /*
    Get/Set methods
     */

    // Christian
    public Calendar getStartDate() {
        if( startDate != null){
            return startDate;
        }
        return null;
    }

    // Christian
    public void setStartDate(int year, int week) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        this.startDate = calendar;
    }

    // Joachim
    public Calendar getEndDate() {
        if(endDate != null){
            return endDate;
        }
        return null;
    }

    // Joachim
    public void setEndDate(int year, int week) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        this.endDate = calendar;
    }

    // Christian
    public int getStartWeek() {
        if(startDate == null) {
            return -1;
        }
        return startDate.get(Calendar.WEEK_OF_YEAR);
    }

    // Joachim
    public int getEndWeek() {
        if(endDate == null) {
            return -1;
        }
        return endDate.get(Calendar.WEEK_OF_YEAR);
    }

    // Christian
    public int getStartYear() {
        if(startDate == null) {
            return -1;
        }
        return startDate.get(Calendar.YEAR);
    }

    // Joachim
    public int getEndYear() {
        if(endDate == null) {
            return -1;
        }
        return endDate.get(Calendar.YEAR);
    }
}
