package com.example.httpvolleydemo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private static final String TAG = "MainActivity";
	
	private Button bt_get;
	private Button bt_post;
	private Button bt_json;
	private TextView tv_get;
	private TextView tv_post;
	private TextView tv_json;
	private RequestQueue requestQueue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bt_get = (Button) findViewById(R.id.bt_get);
		bt_post = (Button) findViewById(R.id.bt_post);
		bt_json = (Button) findViewById(R.id.bt_json);
		bt_get.setOnClickListener(this);
		bt_post.setOnClickListener(this);
		bt_json.setOnClickListener(this);
		
		tv_get = (TextView) findViewById(R.id.tv_get);
		tv_post = (TextView) findViewById(R.id.tv_post);
		tv_json = (TextView) findViewById(R.id.tv_json);
		requestQueue = Volley.newRequestQueue(this);
	}
	@Override
	public void onClick(View v) {
		Log.d(TAG, "onClick");
		switch (v.getId()) {
		case R.id.bt_get:
			Toast.makeText(MainActivity.this, "GET", Toast.LENGTH_SHORT).show();
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
		case R.id.bt_post:
			StringRequest postRequest = new StringRequest(Method.POST, "http://www.baidu.com", new Response.Listener<String>() {

				@Override
				public void onResponse(String response) {
					tv_post.setText(response);
				}
			}, new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					tv_post.setText(error.getMessage());
				}}){
				@Override
				protected Map<String, String> getParams() throws AuthFailureError {
					Map<String, String> map = new HashMap<String, String>();
					map.put("params1", "value1");
					map.put("params2", "value2");
					return map;
				}
			};
			
			requestQueue.add(postRequest);
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
