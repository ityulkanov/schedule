public class Schedule {
    private static volatile Schedule schedule = new Schedule();

    int[][] timeAvailable;

    private Schedule(){
        timeAvailable = new int[26][19];
    }
    public static Schedule getInstance() {
        if (schedule == null){ //if there is no instance available... create new one
            schedule = new Schedule();
        }

        return schedule;
    }
}
