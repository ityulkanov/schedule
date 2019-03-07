import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetData {

    private static volatile GetData getData = new GetData();
    public static GetData getInstance() {
        if (getData == null){
            getData = new GetData();
        }
        return getData;
    }

    public DataInputStream fetchFromUrl(String path) {

        URL url = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection urlConn = null;
        try {
            urlConn = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        urlConn.setDoInput(true);
        urlConn.setUseCaches(false);

        try {
            DataInputStream dis = new DataInputStream(urlConn.getInputStream());
            return dis;
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataInputStream dis = null;
        return dis;
    }

}
