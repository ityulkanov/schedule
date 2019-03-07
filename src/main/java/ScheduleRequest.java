import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ScheduleRequest implements Comparable <ScheduleRequest>{
    private Long timeSubmitted;
    private String userId;
    private Long meetingStartTime;
    private Long meetingEndTime;
    private boolean successful;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public Long getTimeSubmitted() {
        return timeSubmitted;
    }

    public void setTimeSubmitted(Long timeSubmitted) {
        this.timeSubmitted = timeSubmitted;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getMeetingStartTime() {
        return meetingStartTime;
    }

    public void setMeetingStartTime(Long meetingStartTime) {
        this.meetingStartTime = meetingStartTime;
    }

    public Long getMeetingEndTime() {
        return meetingEndTime;
    }

    public void setMeetingEndTime(Long meetingEndTime) {
        this.meetingEndTime = meetingEndTime;
    }

    public ScheduleRequest() {
    }

    public static List<ScheduleRequest> scheduleListCreator(Scanner inputData){
        List<ScheduleRequest> scheduleRequests = new ArrayList<ScheduleRequest>();
        //Receive input and store it into our array of objects
        while(inputData.hasNext()) {
            String requestMessage = inputData.nextLine();
            ScheduleRequest scheduleRequest = new ScheduleRequest();
            scheduleRequest.scheduleRequestCreator(requestMessage);
            scheduleRequests.add(scheduleRequest);
        }
        Collections.sort(scheduleRequests);
        return scheduleRequests;
    }

    private void scheduleRequestCreator(String requestMessage){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm");


        String[] data = requestMessage.split(" ");

        try {
            setTimeSubmitted(simpleDateFormat.parse(data[0]).getTime() + simpleTimeFormat.parse(data[1]).getTime());
            setUserId(data[2]);
            setMeetingStartTime(simpleDateFormat.parse(data[3]).getTime() + simpleTimeFormat.parse(data[4]).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setMeetingEndTime(Long.valueOf(data[5]) * 6000000 + getMeetingStartTime());
    }

    @Override
    public String toString() {
        return "ScheduleRequest{" +
                "timeSubmitted=" + timeSubmitted +
                ", userId='" + userId + '\'' +
                ", meetingStartTime=" + meetingStartTime +
                ", meetingEndTime=" + meetingEndTime +
                '}';
    }



    public int compareTo(ScheduleRequest object) {
        if (this.getTimeSubmitted() < object.getTimeSubmitted()) {
            return -1;

        } else if (this.getTimeSubmitted() > object.getTimeSubmitted()) {
            return 1;
        } else {
            return 0;
        }
    }

}
