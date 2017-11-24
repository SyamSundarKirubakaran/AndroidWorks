package com.bugscript.internetinteract.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by syamsundark on 24/11/17.
 */

public class NetworkUtils {

    final static String GITHUB_BASE_URL =
            "https://api.github.com/users/";

    final static String FOLLOWERS_KEYWORD = "/followers";

    public static URL cookingTheUrl(String gotFromMainActivity){
        URL url=null;
        try {
            url= new URL(GITHUB_BASE_URL+gotFromMainActivity+FOLLOWERS_KEYWORD);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
