package com.sigma.jesperturessonbooksearch;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class JsonParser extends Parser {
	
	JSONObject root = null;
	JSONArray arrayRoot = null;
	String json;

    @Override
    public void parse(InputStream data) {
     try {
            json = ServerConnection.readStream(data);
            root = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("", "Got error when trying to parse json from " + data);
            System.out.println("json =\n"+json);
        }
    }
    public JSONObject getRoot(){
      return root;
    }
}
