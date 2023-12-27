package com.malagueta.fintch.filter;

import com.malagueta.fintch.filter.io.GenericHttpResponse;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpSessionValidater {
    public static final String URL="http://localhost:8081/validate";
    private static final Logger log= LoggerFactory.getLogger(HttpSessionValidater.class);
    public static GenericHttpResponse sendPOST(String url, @NotNull String body, String token)  {



        try{
            URL obj = new URL(url);
            log.info("INPUT POST: [token: "+token+", body : "+body);
            log.error("INPUT POST: [token: "+token+", body : "+body);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization",token);
            con.setRequestProperty("Content-Type","application/json");
            GenericHttpResponse httpResponse = new GenericHttpResponse();

            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(body.getBytes());
            os.flush();
            os.close();
            // For POST only - END

            httpResponse.setHttpErrorCode(con.getResponseCode());
            log.error("POST Response Code :: " + con.getResponseCode());
            log.error("body: "+body);
            log.error("token: "+token);
            if (httpResponse.getHttpErrorCode() == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                httpResponse.setHttpBody(response.toString());
                in.close();

                // print result
                return httpResponse;
            } else {
                log.debug("validate reponse: "+httpResponse.getHttpBody());
                return httpResponse;
            }


        }catch (IOException e){
            GenericHttpResponse httpResponse=new GenericHttpResponse();
            httpResponse.setHttpBody("nao foi possivel validar a autenticacao, contacte o seu administrador de sistema");
            httpResponse.setHttpErrorCode(500);
            return httpResponse;
        }
    }

    public static void sendGET(String url, String token) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", token);
        // con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request did not work.");
        }

    }

    public static GenericHttpResponse sendPOST(String body, String token) {
        return sendPOST(URL,body,token);
    }
}
