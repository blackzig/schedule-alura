package br.com.micheladrianomedeiros.agenda.webservice;

import android.util.Log;

import java.io.IOException;

import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by zigui on 07/12/2017.
 */

public class WebClient {

    public String post(String json){
        try {
            Log.i("json>>>", json);
            URL url = new URL("https://www.caelum.com.br/mobile");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Content-type","application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);

            PrintStream printStream = new PrintStream(httpURLConnection.getOutputStream());
            printStream.print(json);

            httpURLConnection.connect();

            Scanner scanner = new Scanner(httpURLConnection.getInputStream());
            String answer = scanner.next();
            Log.i("answer>>>>", answer);
            return answer;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
