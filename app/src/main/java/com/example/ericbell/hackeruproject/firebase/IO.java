package com.example.ericbell.hackeruproject.firebase;

import android.content.Intent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by eric.bell on 6/29/2017.
 */

public class IO {

    public static String getString (InputStream in ,String charest) throws IOException {
        StringBuilder builder = new StringBuilder();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in,charest));

        try {

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

        }
        finally {
            reader.close();
        }
        return builder.toString();
    }


    public  static String readWebSite (String url , String charest) throws IOException {
        URL addres =  new URL(url);
        URLConnection con = addres.openConnection();
        InputStream in = con.getInputStream();
        return getString(in,charest);
    }


}
