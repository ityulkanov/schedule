import java.text.SimpleDateFormat;

public class ScheduleRequest {
    private long timeSubmitted;
    private String userId;
    private long meetingStartTime;
    private long timeRequested;
    private int timeNeeded;

    public ScheduleRequest() {
    }

    public void scheduleRequestCreator(String requestMessage) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm");

        String[] data = requestMessage.split(" ");

        this.timeSubmitted = simpleTimeFormat.parse(data[0]).getTime() + simpleDateFormat.parse(data[0]).getTime();
        this.userId = data[2];
        this.meetingStartTime = simpleTimeFormat.parse(data[3]).getTime() + simpleDateFormat.parse(data[4]).getTime();
        this.timeNeeded = Integer.parseInt(data[5]);
    }

    @Override
    public String toString() {
        return "ScheduleRequest{" +
                "timeSubmitted=" + timeSubmitted +
                ", userId='" + userId + '\'' +
                ", timeRequested=" + timeRequested +
                ", timeNeeded=" + timeNeeded +
                '}';
    }
}
