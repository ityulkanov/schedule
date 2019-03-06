import java.util.Arrays;

public class Schedule {
    private static volatile Schedule schedule = new Schedule();

    int[][] timeAvailable;
    //Since we are going to use only one calendar, let's create singleton:
    private Schedule() {
        timeAvailable = new int[31][10];
        for (int[] row : timeAvailable) {
            Arrays.fill(row, 0);
        }
    }
    public static Schedule getInstance() {
        if (schedule == null){ //if there is no instance available... create new one
            schedule = new Schedule();
        }

        return schedule;
    }

    public boolean bookDate(String date, String time) {
        int dateOfBooking = Integer.parseInt(date);
        //current dates are off by 3 hours. But we are adjusting it to fit our schedule as well:
        int hourOfBooking = Integer.parseInt(time) - 6;
        if (hourOfBooking > 10 || hourOfBooking < 0) {
            return false;
        }
        if (timeAvailable[dateOfBooking][hourOfBooking] == 0) {

            timeAvailable[dateOfBooking][hourOfBooking] = 1;
            return true;
        } else {
            return false;
        }
    }
}
