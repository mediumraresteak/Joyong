package com.example.cultureview;

import helper.HttpManager;
import helper.Util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Model.Object;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SoloPlay extends Activity {

	Bitmap bmap;
	List<Object> loadObjects = new ArrayList<Object>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solo);
		
		
		Button play = (Button)findViewById(R.id.settings);
		
		Log.e("started", "asdfasdf");
		new AsyncTask<String, String, String>() {
			@Override
			protected void onPreExecute() {
				Log.e("pre", "execue");
			};
			protected String doInBackground(String... params) {
				loadObjects.addAll(loadingObjects(new HttpManager()   
				.GET(Util.SEND_GPS	+ "?gps_x=200")));          
				Log.e("objs"," : "+loadObjects.size());
				// 이제 loadObjects에서 커스텀 리스트로 뿌리리리리리기
				// 그리고 커스텀 리스트뷰의 아이템을 누르면 그 id 값가지고 아래의 소스를 돌려주면 되요. object는 문화재 말하는거고요
				
				//bmap = getBitmapFromURL("applepi.kr/images/[리스트뷰에서 누른 Object객체의 id 값]");  
				//ImageView img = (ImageView)findViewById(R.id.image);
				//img.setImageBitmap(bmap);   
				return null;
			}
			protected void onPostExecute(String result) {
			
			};
		}.execute();
		
		
		play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SoloPlay.this, SoloGameActivity.class);
                startActivity(i);
            }
        });
		
	}

	
	public List<Object> loadingObjects(String json){
		Log.e("json string : ",json);
		List<Object> loadedObjects = new ArrayList<Object>();
		try {
			
			JSONArray array = new JSONArray(json);
			Log.e("array ", "asd"+array.length());
			for (int i = 0; i < array.length(); i++) {
				Log.e("jsoning", "...");
				JSONObject jsonObj = array.getJSONObject(i);
				loadedObjects.add(new Object(jsonObj.getInt("ob_id"),
						jsonObj.getString("ob_name"),jsonObj.getString("ob_desc")));
				
			}
		} catch (JSONException e) {
		}
		return loadedObjects;
	}
}
