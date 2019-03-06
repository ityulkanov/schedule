import java.text.SimpleDateFormat;

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

    public void scheduleRequestCreator(String requestMessage) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm");


        String[] data = requestMessage.split(" ");

        setTimeSubmitted(simpleDateFormat.parse(data[0]).getTime() + simpleTimeFormat.parse(data[1]).getTime());
        setUserId(data[2]);
        setMeetingStartTime(simpleDateFormat.parse(data[3]).getTime() + simpleTimeFormat.parse(data[4]).getTime());
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
