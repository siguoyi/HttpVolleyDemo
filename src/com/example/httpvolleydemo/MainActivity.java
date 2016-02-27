package com.example.httpvolleydemo;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StringRequest getRequest = new StringRequest("http://www.baidu.com", new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.d(TAG, response);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, error.getMessage(), error);
			}
		});
		
		StringRequest postRequest = new StringRequest(Method.POST, "http://www.baidu.com", new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				
			}}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("params1", "value1");
				map.put("params2", "value2");
				return map;
			}
		};
		
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		requestQueue.add(getRequest);
		requestQueue.add(postRequest);
	}
}
