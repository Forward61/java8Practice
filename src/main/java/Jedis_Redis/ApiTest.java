package Jedis_Redis;


import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by freedom on 2019/3/27.
 */
public class ApiTest {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "https://www.apiopen.top/weatherApi?city=%E6%88%90%E9%83%BD";

    private static final String POST_URL = "https://www.apiopen.top/weatherApi?city=%E6%88%90%E9%83%BD";

    private static final String POST_PARAMS = "userName=Pankaj";

    public static void main(String[] args) throws IOException {
        Map<String,String> hashmap =new HashMap<String,String>();
        hashmap.put("1","11");
        hashmap.put("2","22");
        for (Map.Entry<String,String> entry:hashmap.entrySet()){
            String appFieldDefId = entry.getKey();
            String value = entry.getValue();
            System.out.println(appFieldDefId + " value " + value);
        }

//        sendGET();
//        System.out.println("GET DONE");
//        sendPOST();
//        System.out.println("POST DONE");
    }


    @Test
    private static void sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }

    private static void sendPOST() throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }

}
