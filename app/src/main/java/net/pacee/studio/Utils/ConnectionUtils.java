package net.pacee.studio.Utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by mupac_000 on 31-03-17.
 */


public class ConnectionUtils {
    public final static  String BASE_URL =  "http://code.pacee.net/study/web";
    public final static  String BASE_MATIERES = "matieres";
    public final static  String BASE_INTERROS = "interros";
    public final static  String BASE_INTERRO = "interro";


    final static  Uri BASE_URI =  Uri.parse(BASE_URL);


    public static URL getBaseUrl()
    {
        String u =  BASE_URL+"/"+BASE_MATIERES;
        URL url = null;
        try
        {
            url= new URL(u);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static URL buildUrl(String path)
    {
        Uri builtUri = BASE_URI.buildUpon().appendPath(path).build();
        URL url = null;
        try
        {
            url= new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;

    }

    public static URL getMatieres()
    {
        return buildUrl(BASE_MATIERES);
    }

    public static URL getInterros(int id)
    {
        Uri builtUri = BASE_URI
                .buildUpon()
                .appendPath(BASE_INTERROS)
                .appendPath(String.valueOf(id))
                .build();
        URL url = null;
        try
        {
            url= new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;

    }

    public static URL getInterro(int id)
    {
        Uri builtUri = BASE_URI
                .buildUpon()
                .appendPath(BASE_INTERRO)
                .appendPath(String.valueOf(id))
                .build();
        URL url = null;
        try
        {
            url= new URL(builtUri.toString());
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
