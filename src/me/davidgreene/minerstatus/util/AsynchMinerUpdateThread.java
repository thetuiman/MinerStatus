package me.davidgreene.minerstatus.util;
import static me.davidgreene.minerstatus.util.MinerStatusConstants.MT_GOX_PUBLIC;
import static me.davidgreene.minerstatus.util.MinerStatusConstants.POOL_URLS;
import static me.davidgreene.minerstatus.util.MinerStatusConstants.SEKRET_MTGOX_KEY;
import me.davidgreene.minerstatus.service.ConfigService;
import me.davidgreene.minerstatus.service.MinerService;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.database.Cursor;
import android.util.Log;


public class AsynchMinerUpdateThread extends Thread {

	private final String tag = "TX_THREAD";
	private MinerService minerService;
	private ConfigService configService;
	
	public AsynchMinerUpdateThread(MinerService minerService, ConfigService configService){
		this.configService = configService;
		this.minerService = minerService;
	}

	@Override
	public void run() {
		super.run();
		
		String result="";
		Log.d(tag, "Status Update Start");
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, 3000);
		HttpConnectionParams.setSoTimeout(httpParameters, 5000);
		
		HttpClient httpClient = new DefaultHttpClient(httpParameters);
		HttpGet request;
		ResponseHandler<String> handler = new BasicResponseHandler();


		Boolean showMtGox = Boolean.valueOf(configService.getConfigValue("show.mtgox"));
		if (showMtGox){
			request = new HttpGet(MT_GOX_PUBLIC);
			try{
				result = httpClient.execute(request, handler);
				minerService.addJsonData(SEKRET_MTGOX_KEY, result);
			} catch (Exception e){  
				/*nothing*/ 
			}			
		}
			
		Cursor poolCursor = minerService.getPools();
		while(poolCursor.moveToNext()){
			String pool = poolCursor.getString(0);
			
	        Cursor cursor = minerService.getMiners(poolCursor.getString(0));
			while(cursor.moveToNext()) {
				
				String apiKey = cursor.getString(0);
				request = new HttpGet(POOL_URLS.get(pool).replace("%MINER%", apiKey));
	
				try{
					result = httpClient.execute(request, handler);
					if(result.contains("invalid") && result.contains("etcpasswd")){
						result = "";
					}
				} catch(ClientProtocolException e){
					//nothing
				} catch (Exception e){
					//nothing
				}
				minerService.addJsonData(apiKey, result);
			}
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}		
		}
		if (poolCursor != null && !poolCursor.isClosed()) {
			poolCursor.close();
		}	
		httpClient.getConnectionManager().shutdown();	
		configService.setConfigValue("last.updated", Long.toString(System.currentTimeMillis()));
	}
}
