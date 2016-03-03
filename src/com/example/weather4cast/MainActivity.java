package com.example.weather4cast;



import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity {
	
	ListView lv;
	ArrayList<student> list = new ArrayList<student>();
	Myadapter adapter;
	String result,country;
	boolean isConnected;
    String loct,ss;
    int flag;
    Button ser;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        this.lv = (ListView) this.findViewById(R.id.listView1);
        ser =(Button) this.findViewById(R.id.button1);
        
        ser.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),search.class));
				//startActivity(new Intent(getApplicationContext(), Registration.class));
				
			}
		});
       ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
      isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        
      if(isConnected = true) {
          country = "Manila,Philippines";
          flag = R.drawable.philippines;
          jsonpar();   
          
          country = "Palau";
          flag = R.drawable.palau;
          jsonpar();
          
          country = "Panama";
          flag = R.drawable.panama;
          jsonpar();
          
          country = "Papau new guinea";
          flag = R.drawable.papuanewguinea;
          jsonpar();
          
          country = "Paraguay";
          flag = R.drawable.paraguay;
          jsonpar();
          
          country = "Peru";
          flag = R.drawable.peru;
          jsonpar();
          
          country = "Poland";
          flag = R.drawable.poland;
          jsonpar();
          
          country = "Portugal";
          flag = R.drawable.portugal;
          jsonpar();
          
          country = "North Korea";
          flag = R.drawable.northkorea;
          jsonpar();
          
          
       }
      else{
          Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
      }
      
      
    }
    

	/*public void addCountry() {

        try {
        	//Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        	JSONObject json=new JSONObject(result);
			
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
                int tempe = (int) tem;
                ss = Double.toString(tempe);
                
                list.add(new student(R.drawable.philippines,country,ss , loct ));
                this.adapter = new Myadapter(list,this);
                this.lv.setAdapter(adapter);
            }
			


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        		
	}*/
	
	
	public void jsonpar(){
	
	try {
		URL url=new URL("http://api.openweathermap.org/data/2.5/find?q="+country+"&appid=44db6a862fba0b067b1930da0d769e98");
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
	                int tempe = (int) tem;
	                ss = Double.toString(tempe);
	                
	                list.add(new student(flag,country,ss , loct ));
	                this.adapter = new Myadapter(list,this);
	                this.lv.setAdapter(adapter);
			
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
	

	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
