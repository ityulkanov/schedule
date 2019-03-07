import java.io.DataInputStream;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        //Parsing data and sorting by submission date
        DataInputStream dis = GetData.getInstance().fetchFromUrl(Settings.URL_PATH);
        StringBuilder inputData = ScheduleRequest.urlDataFetch(dis);
        List<ScheduleRequest> scheduleRequests = ScheduleRequest.scheduleListCreator(inputData.toString());
        //Book the conf room with currently available requests
        Schedule.getInstance().requestSubmitter(scheduleRequests);
        //Convert to json successful requests
        Schedule.getInstance().convertToJson(scheduleRequests);

    }
}
