package me.davidgreene.minerstatus;

import static me.davidgreene.minerstatus.util.MinerStatusConstants.MAX_ERRORS;
import static me.davidgreene.minerstatus.util.MinerStatusConstants.POOL_LABELS;
import static me.davidgreene.minerstatus.util.MinerStatusConstants.SEKRET_MTGOX_KEY;
import me.davidgreene.minerstatus.beans.MtGox;
import me.davidgreene.minerstatus.beans.Result;
import me.davidgreene.minerstatus.beans.Status;
import me.davidgreene.minerstatus.util.AsynchMinerUpdateThread;
import me.davidgreene.minerstatus.util.StatusObjectFactory;

import org.joda.time.DateTime;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainMinerActivity extends AbstractMinerStatusActivity {
    
    private static final String tag = "TX";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        int bgColor = themeService.getTheme().getBackgroundColor();
        ScrollView scrollView = (ScrollView) findViewById(R.id.mainMinerScrollView);
        scrollView.setBackgroundColor(bgColor);
        getUserStatusUpdate();
    }	
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.layout.miner_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.add_miner:
                startActivityForResult(new Intent(MainMinerActivity.this, AddMinerActivity.class), 0);
	            break;
	        case R.id.fetch_status:     
	        	Thread asynchMinerUpdateThread = new AsynchMinerUpdateThread(minerService, configService);
	        	asynchMinerUpdateThread.start();
	            break;
	        case R.id.options:
                startActivityForResult(new Intent(MainMinerActivity.this, OptionsActivity.class), 0);
	            break;	            
	        case R.id.about: 
	        	Toast.makeText(this, "Created by David Greene", Toast.LENGTH_LONG).show();
	            break;
	    }
	    return true;
	}
	
	private TableRow createNewRow(String[] columns, Boolean headerTextColor){
		TableRow tr = new TableRow(getApplicationContext());
		tr.setLayoutParams(new LayoutParams(
		                  LayoutParams.FILL_PARENT,
		                  LayoutParams.WRAP_CONTENT));	
		for(String str :columns){
			TextView col = new TextView(getApplicationContext());
			col.setPadding(getDip(5F), getDip(5F), getDip(5F), getDip(5F));
			col.setTextColor((headerTextColor) ? themeService.getTheme().getHeaderTextColor() : themeService.getTheme().getTextColor());
			col.setText(str);
			tr.addView(col);
			
		}
		return tr;
	}
	
	private TableRow createNewRow(final Status status){
		TableRow tr = new TableRow(getApplicationContext());
		tr.setLayoutParams(new LayoutParams(
		                  LayoutParams.FILL_PARENT,
		                  LayoutParams.WRAP_CONTENT));	
		for(String str : new String[]{status.getUsername(), status.getDisplayCol1(), status.getDisplayCol2()}){
			TextView col = new TextView(getApplicationContext());
			col.setPadding(getDip(5F), getDip(5F), getDip(5F), getDip(5F));
			col.setTextColor(themeService.getTheme().getTextColor());
			col.setText(str);
			tr.addView(col);
			
		}
		tr.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
	        	Bundle bundle = new Bundle();
	        	bundle.putSerializable("status", status);
	        	Intent intent = new Intent(MainMinerActivity.this, ViewMinerActivity.class);
				intent.putExtras(bundle);
	        	startActivityForResult(intent, 0);
			}
		});
		return tr;
	}
	

	
	private void getUserStatusUpdate(){
		Log.d(tag, "Status Update Start");
		
		TableLayout mainTableLayout = (TableLayout) findViewById(R.id.statusLayout);
		mainTableLayout.removeAllViews();
		Boolean showMtGox = Boolean.valueOf(configService.getConfigValue("show.mtgox"));
		TableLayout mtGoxLayout = (TableLayout) findViewById(R.id.mtGoxLayout);
		if (showMtGox){
			try{
				mtGoxLayout.removeAllViews();
				Gson gson = new Gson();
				Result mtGoxResult = minerService.readJsonData(SEKRET_MTGOX_KEY);
				MtGox mtGox = gson.fromJson(mtGoxResult.getData(), MtGox.class);
				mtGoxLayout.addView(createNewRow(new String[] {"Mt. Gox:"}, Boolean.TRUE));
				mtGoxLayout.addView(createNewRow(new String[] {"Vol","Last", "High","Low","Buy","Sell"}, Boolean.FALSE));
				mtGoxLayout.addView(createNewRow(new String[] {mtGox.getTicker().getVol().toString(),
						mtGox.getTicker().getLast().toString(), 
						mtGox.getTicker().getHigh().toString(), 
						mtGox.getTicker().getLow().toString(),
						mtGox.getTicker().getBuy().toString(),
						mtGox.getTicker().getSell().toString()
						}, Boolean.FALSE));				
			} catch (Exception e){
				mtGoxLayout.setVisibility(TableLayout.INVISIBLE);
			}			
		} else {
			mtGoxLayout.setVisibility(TableLayout.INVISIBLE);
		}
		Cursor poolCursor = minerService.getPools();
		while(poolCursor.moveToNext()){
			String pool = poolCursor.getString(0);
			
	        Cursor cursor = minerService.getMiners(poolCursor.getString(0));
	        Boolean foundActiveRow = Boolean.FALSE;
			while(cursor.moveToNext()) {
				Integer errors = cursor.getInt(1);
				String apiKey = cursor.getString(0);
				Result minerResult = minerService.readJsonData(apiKey);
				Status status = null;
				try{
					if (minerResult == null || minerResult.getData().equals("")){
						throw new Exception("No JSON Data");
					}
					status = StatusObjectFactory.getStatusObject(minerResult.getData(), pool);
					status.setApiKey(apiKey);
				} catch (Exception e){
					minerService.updateErrorCount(apiKey, (errors+1));
					if (errors >= MAX_ERRORS){					
						minerService.deleteMiner(apiKey);
						Toast.makeText(getApplicationContext(), "Miner ("+apiKey+") does not exist for pool("+pool+") or there was no response from the server.",
								Toast.LENGTH_LONG).show();	
						Toast.makeText(getApplicationContext(), "Max error count hit.  Miner removed: "+apiKey,
								Toast.LENGTH_LONG).show();					
						continue;
					} else {
						Toast.makeText(getApplicationContext(), "Miner ("+apiKey+") does not exist for pool("+pool+") or there was no response from the server.",
								Toast.LENGTH_LONG).show();	
						continue;
					}
				}
				if (errors > 0){
					//reset errors after a successful fetch
					minerService.updateErrorCount(apiKey, 0);
				}
				if (!foundActiveRow){
					foundActiveRow = Boolean.TRUE;
			        mainTableLayout.addView(createNewRow(new String[] {POOL_LABELS.get(pool)+":"}, Boolean.TRUE));
			        mainTableLayout.addView(createNewRow(new String[] {status.getUsernameLabel(), status.getDisplayCol1Label(), status.getDisplayCol2Label()}, Boolean.FALSE));
				}
				
				mainTableLayout.addView(createNewRow(status));

			}
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}		
			mainTableLayout.addView(createNewRow(new String[] {""}, Boolean.FALSE));
		}
		if (poolCursor != null && !poolCursor.isClosed()) {
			poolCursor.close();
		}	
		this.setTitle("Miner Status - "+new DateTime(Long.getLong(configService.getConfigValue("last.updated"))).toString("yyyy/MM/dd @ HH:mm:ss"));
	}

}