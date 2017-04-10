
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tomato on 05.04.17.
 */
public class Requester {

    private final String USER_AGENT = "Mozilla/5.0";
    String url;

    public Requester(String id){
        url = "https://graph.facebook.com/v2.8/" + id + "?fields=cover&access_token=" + FBevents.access;
    }

    public String reqest() throws Exception{

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("user-agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputline;
        StringBuffer response = new StringBuffer();

        while((inputline=in.readLine()) != null){
            response.append(inputline);
        }
        in.close();
        return response.toString();
    }

}
