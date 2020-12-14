/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moveplus;

import sun.net.www.http.HttpClient;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author enlem
 */
public class Slack {
    
    static final String pt1 = "https://hooks.slack.com/services";
    static final String pt2 = "/T01GZHCP4U8/B01GQHAEX5K";
    static final String pt3 = "/1bNdcD3nlUI3vDDUR525NdZ0";
    
//    private static HttpClient client = HttpClient.newHttpClient();
    private static final String url = pt1 + pt2 + pt3;
    
    public void sendMessage(JSONObject message) throws Exception {

        URL obj = new URL(this.url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(message.toString());

        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        System.out.println("Sending 'POST' request to URL: " + this.url);
        System.out.println("POST parameters: " + message.toString());
        System.out.println("Response Code: " + responseCode);

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;

        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        
        reader.close();
        System.out.println("Success.");
    }
}
