import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
        //Parsing data and sorting by submission date
        Scanner inputData = new Scanner(new File(Settings.PATH));
        List<ScheduleRequest> scheduleRequests = ScheduleRequest.scheduleListCreator(inputData);
        //Book the conf room with currently available requests
        Schedule.getInstance().requestSubmitter(scheduleRequests);
        //Print successful requests
        Schedule.getInstance().printSchedule(scheduleRequests);

    }
}
