package com.example.weather4cast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class search extends Activity {
	
	String loct,ss;
	TextView country,temperature,location;
	Button search;
	EditText coun;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		
		this.country = (TextView) this.findViewById(R.id.country);
		this.temperature= (TextView) this.findViewById(R.id.temp);
		this.location = (TextView) this.findViewById(R.id.location);
		this.search = (Button) this.findViewById(R.id.search11);
		this.coun =(EditText) this.findViewById(R.id.editText1);
		
		search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				jsonpar();
				//startActivity(new Intent(getApplicationContext(), Registration.class));
				
			}
		});
		
	}
	
	public void jsonpar(){
		
		try {
			
			URL url=new URL("http://api.openweathermap.org/data/2.5/find?q="+coun.getText()+"&appid=44db6a862fba0b067b1930da0d769e98");
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			InputStream is=conn.getInputStream();
			int c=0;
			StringBuffer sb=new StringBuffer();
			
			while((c=is.read())!=-1){				
				sb.append((char)c);				
			}
			is.close();
			conn.disconnect();
			
			Log.d("json", sb.toString());
			
			JSONObject json=new JSONObject(sb.toString());
			
					
					JSONArray jarr=json.getJSONArray("list");
					
					for(int i=0;i<jarr.length();i++){
						
						JSONObject item=jarr.getJSONObject(i);
						JSONObject items=item.getJSONObject("main");
						JSONObject loc=item.getJSONObject("coord");
						String ww = items.getString("temp");
						String lon = loc.getString("lon");
						String lan = loc.getString("lat");
						loct = lan+", "+lon;
		                double tem = Double.parseDouble(ww);
		                tem = tem-273.15;
		                //int tempe = (int) tem;
		                //ss = Double.toString(tempe);
		                
		                String ss=String.format("%.2f", tem);
		                
		               country.setText(coun.getText());
		               temperature.setText(ss);
		               location.setText(loct);
				
				}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException f) {
			// TODO Auto-generated catch block
			f.printStackTrace();
		} catch (JSONException g) {
			// TODO Auto-generated catch block
			g.printStackTrace();
		}

		}
		
	
	

}
