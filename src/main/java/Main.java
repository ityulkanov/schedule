import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {

        String filePath = "C:\\Users\\GOSHA\\IdeaProjects\\confSchedule\\src\\main\\java\\input.txt";
        Scanner inputData = new Scanner(new File(filePath));
        List<ScheduleRequest> scheduleRequests = new ArrayList<ScheduleRequest>();

        while(inputData.hasNext()) {
            String requestMessage = inputData.nextLine();
            ScheduleRequest scheduleRequest = new ScheduleRequest();
            scheduleRequest.scheduleRequestCreator(requestMessage);
            scheduleRequests.add(scheduleRequest);
        }
        Collections.sort(scheduleRequests);
        for (ScheduleRequest scheduleRequest : scheduleRequests) {
            Date meetingDate = new Date(scheduleRequest.getMeetingStartTime());

            DateFormat df = new SimpleDateFormat("dd");
            DateFormat tf = new SimpleDateFormat("HH");
            if (Schedule.getInstance().bookDate(df.format(meetingDate), tf.format(meetingDate))) {
                scheduleRequest.setSuccessful(true);
            }
        }

        for (ScheduleRequest scheduleRequest : scheduleRequests) {
            if (scheduleRequest.isSuccessful()) {
                Date meetingStartTime = new Date(scheduleRequest.getMeetingStartTime());
                DateFormat dateBooked = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                System.out.println(scheduleRequest.getUserId() + " has booked conf for " + dateBooked.format(meetingStartTime));
            }
        }
    }
}
