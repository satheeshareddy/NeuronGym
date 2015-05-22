package com.edlogiq.neurongym.neurongym;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import com.edlogiq.neurongym.R;

public class ServiceHandler {

    public final static int GET = 1;
    public final static int POST = 2;
    static InputStream is = null;
    static String response = null;

    public ServiceHandler() {

    }

    /**
     * Making service call
     *
     * @url - url to make request
     * @method - http request method
     */
    public String makeServiceCall(String url, int method) {

        return this.makeServiceCall(url, method, null);
    }

    /**
     * Making service call
     *
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     */
    public String makeServiceCall(String url, int method,
                                  ArrayList<NameValuePair> params) {
        try {
            // http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;
            String error = "Error";
            // Checking http request method type
            if (method == POST) {
                HttpPost httpPost = new HttpPost(url);
                // adding post params
                if (params != null) {
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                }

                httpResponse = httpClient.execute(httpPost);

            } else if (method == GET) {
                // appending params to url
                if (params != null) {
                    String paramString = URLEncodedUtils
                            .format(params, "utf-8");
                    url += "?" + paramString;
                }
                HttpGet httpGet = new HttpGet(url);

                httpResponse = httpClient.execute(httpGet);
                //   System.out.println("HTTP RESPONSE CODE***"+httpResponse.getStatusLine().getStatusCode());
                if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {

                    System.out.println("HTTP RESPONSE CODE***" + httpResponse.getStatusLine().getStatusCode());
                    //return error;
                } else {
                    System.out.println("HTTP RESPONSE CODE" + httpResponse.getStatusLine().getStatusCode());
                }
            }
            httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnknownHostException un) {
            System.out.println("HTTP Response Unknownhost exception**");
            un.printStackTrace();
            response = "error";
        } catch (UnsupportedEncodingException e) {

            System.out.println("HTTP Response UnsupportedEncodingException**");
            response = "error";
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            System.out.println("HTTP Response ClientProtocolException**");
            response = "error";
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("HTTP Response IOException**");
            response = "error";
            e.printStackTrace();
            //return error;
        }


        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            response = sb.toString();
            System.out.println("response is=" + response);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error: " + e.toString());
            System.out.println("HTTP Response Buffer Error**");
        }

        return response;

    }
}