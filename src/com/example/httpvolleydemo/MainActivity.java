package com.example.httpvolleydemo;

import java.util.Map;

import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private static final String TAG = "MainActivity";
	
	private Button bt_get;
	private Button bt_post;
	private Button bt_json;
	private TextView tv_get;
	private ImageView imageview;
	private TextView tv_json;
	private RequestQueue requestQueue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bt_get = (Button) findViewById(R.id.bt_get);
		bt_post = (Button) findViewById(R.id.bt_image);
		bt_json = (Button) findViewById(R.id.bt_json);
		bt_get.setOnClickListener(this);
		bt_post.setOnClickListener(this);
		bt_json.setOnClickListener(this);
		
		tv_get = (TextView) findViewById(R.id.tv_get);
		imageview = (ImageView) findViewById(R.id.imageview);
		tv_json = (TextView) findViewById(R.id.tv_json);
		requestQueue = Volley.newRequestQueue(this);
	}
	@Override
	public void onClick(View v) {
		Log.d(TAG, "onClick");
		switch (v.getId()) {
		case R.id.bt_get:
//			Toast.makeText(MainActivity.this, "GET", Toast.LENGTH_SHORT).show();
			StringRequest getRequest = new StringRequest("http://www.baidu.com", new Response.Listener<String>() {

				@Override
				public void onResponse(String response) {
					Log.d(TAG, response);
					tv_get.setText(response);
				}
			}, new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					Log.e(TAG, error.getMessage(), error);
					tv_get.setText(error.getMessage());
				}
			});
			
			requestQueue.add(getRequest);
			
			break;
		case R.id.bt_image:
//			@SuppressWarnings("deprecation")
//			ImageRequest imageRequest = new ImageRequest("http://buptant.cn/images/logo.jpg", new Listener<Bitmap>() {
//
//				@Override
//				public void onResponse(Bitmap response) {
//					imageview.setImageBitmap(response);
//					
//				}
//			}, 0, 0, Config.RGB_565, new ErrorListener() {
//
//				@Override
//				public void onErrorResponse(VolleyError error) {
//					imageview.setImageResource(R.drawable.no_media);
//				}
//			});
//			requestQueue.add(imageRequest);
			
			ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageCache() {
				
				@Override
				public void putBitmap(String url, Bitmap bitmap) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public Bitmap getBitmap(String url) {
					// TODO Auto-generated method stub
					return null;
				}
			});
			
			ImageListener listener = ImageLoader.getImageListener(imageview, R.drawable.no_media, R.drawable.ic_launcher);
			imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener,300,300);
			break;
		case R.id.bt_json:
			JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://m.weather.com.cn/data/101010100.html", null, 
					new Response.Listener<JSONObject>() {

				@Override
				public void onResponse(JSONObject response) {
					Log.d(TAG, response.toString());
					tv_json.setText(response.toString());
				}
			}, new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					Log.e(TAG, error.getMessage(), error);
					tv_json.setText(error.getMessage());
				}
			});
			
			requestQueue.add(jsonObjectRequest);
			break;
		}
		
	}
}
