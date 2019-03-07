import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Schedule {
    private static volatile Schedule schedule = new Schedule();
    int[][] scheduleHours;
    //Since we are going to need only one calendar let's create singleton:
    private Schedule() {
        scheduleHours = new int[Settings.DAYS_IN_MONTH][Settings.WORK_HOURS];
        for (int[] row : scheduleHours) {
            Arrays.fill(row, 0);
        }
    }
    public static Schedule getInstance() {
        if (schedule == null){
            schedule = new Schedule();
        }

        return schedule;
    }

    public void requestSubmitter(List<ScheduleRequest> requests) {
        for (ScheduleRequest scheduleRequest : requests) {
            Date meetingDate = new Date(scheduleRequest.getMeetingStartTime());
            DateFormat df = new SimpleDateFormat("dd");
            DateFormat tf = new SimpleDateFormat("HH");
            DateFormat ml = new SimpleDateFormat("hh");

            if (bookDate(df.format(meetingDate), tf.format(meetingDate), scheduleRequest.getMeetingDuration())) {
                scheduleRequest.setSuccessful(true);
            }
        }
    }

    public void printSchedule (List<ScheduleRequest> requests) {
        for (ScheduleRequest scheduleRequest : requests) {
            if (scheduleRequest.isSuccessful()) {
                Date meetingRealTime = new Date(scheduleRequest.getMeetingRealtime());
                DateFormat dateBooked = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                System.out.println(scheduleRequest.getUserId() + " has booked conf at " + dateBooked.format(meetingRealTime)
                        + " for " + scheduleRequest.getMeetingDuration() + " hours ");
            }
        }
    }

    private boolean bookDate(String date, String time, int duration) {
        /*current dates are off by 3 hours because of SimpleDateFormat
        implementation, also time available starts from 9, not from 0,
        so we adjust our time of booking accordingly*/
        int dateOfBooking = Integer.parseInt(date);
        int hourOfBooking = Integer.parseInt(time) - Settings.TIME_ADJUSTMENT;
        if (hourOfBooking < Settings.MIN_TIME_AVAILABLE || hourOfBooking > Settings.MAX_TIME_AVAILABLE
                || (hourOfBooking + duration) > Settings.MAX_TIME_AVAILABLE) {
            return false;
        }
        if (scheduleHours[dateOfBooking][hourOfBooking] == Settings.AVAILABLE) {
            //Since we are checking against booking our first, and it is available as well,
            // our logic should that into account:
            while(duration > 0) {
                duration--;
                if ((scheduleHours[dateOfBooking][hourOfBooking + duration]) == 1) {
                    return false;
                }
                scheduleHours[dateOfBooking][hourOfBooking + duration] = Settings.TAKEN;
            }
            return true;
        } else {
            return false;
        }
    }
}
