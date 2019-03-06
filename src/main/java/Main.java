public class Main {


    public static void main(String[] args) throws Exception {

        String requestMessage = "2011-03-16 12:34:56 EMP002 2011-03-21 09:00 2";
        ScheduleRequest scheduleRequest = new ScheduleRequest();
        scheduleRequest.scheduleRequestCreator(requestMessage);
        System.out.println(scheduleRequest.toString());

    }
}
