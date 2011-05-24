package me.davidgreene.minerstatus;

import static me.davidgreene.minerstatus.util.MinerStatusConstants.*;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class AddMinerActivity extends AbstractMinerStatusActivity {
	
	//private static final String tag = "TX";
	
	private String poolToAdd;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addminer1);
        int bgColor = themeService.getTheme().getBackgroundColor();
        int color = themeService.getTheme().getTextColor();
        ScrollView scrollView = (ScrollView) findViewById(R.id.addMinerScrollView);
        scrollView.setBackgroundColor(bgColor);
    	TextView minerNameLabel = (TextView) findViewById(R.id.minerNameLabel);
    	minerNameLabel.setTextColor(color);
    	TextView minerDirections = (TextView) findViewById(R.id.minerDirections);
    	minerDirections.setTextColor(color);
        
    	final RadioButton radio_bitcoinpool = (RadioButton) findViewById(R.id.radio_bitcoinpool);
    	final RadioButton radio_slush = (RadioButton) findViewById(R.id.radio_slush);
    	final RadioButton radio_deepbit = (RadioButton) findViewById(R.id.radio_deepbit);
    	final RadioButton radio_btcmine = (RadioButton) findViewById(R.id.radio_btcmine);
    	final RadioButton radio_btcguild = (RadioButton) findViewById(R.id.radio_btcguild);

    	radio_btcmine.setOnClickListener(radio_listener);
    	radio_btcmine.setTextColor(color);
    	
    	radio_deepbit.setOnClickListener(radio_listener);
    	radio_deepbit.setTextColor(color);
    	
        radio_bitcoinpool.setOnClickListener(radio_listener);
        radio_bitcoinpool.setTextColor(color);
        
        radio_slush.setOnClickListener(radio_listener);
        radio_slush.setTextColor(color);

        radio_btcguild.setOnClickListener(radio_listener);
        radio_btcguild.setTextColor(color);
    }
	
	private OnClickListener radio_listener = new OnClickListener() {
	    public void onClick(View v) {
	        // Perform action on clicks
	        RadioButton rb = (RadioButton) v;
	    	final TextView minerNameLabel = (TextView) findViewById(R.id.minerNameLabel);
	    	final EditText minerName = (EditText) findViewById(R.id.minerName);	
	    	final Button addMinerButton = (Button) findViewById(R.id.addMinerButton);	
	    	final TextView minerDirections = (TextView) findViewById(R.id.minerDirections);	
	        if(rb.getText().equals("Bitcoin Pool")){
	        	poolToAdd = "bitcoinpool";
	        	minerNameLabel.setText("Miner Name");
	        } else if (rb.getText().equals("Slush's Pool")){
	        	poolToAdd = "slush";
	        	minerNameLabel.setText("API Key");
	        } else if (rb.getText().equals("Deepbit.net")){
	        	poolToAdd = "deepbit";
	        	minerNameLabel.setText("API Key");
	        } else if (rb.getText().equals("BtcMine")){
	        	poolToAdd = "btcmine";
	        	minerNameLabel.setText("API Key");
	        } else if (rb.getText().equals("Btcguild")){
	        	poolToAdd = "btcgulid";
	        	minerNameLabel.setText("API Key");
	        }
	        minerNameLabel.setVisibility(TextView.VISIBLE);
	        minerName.setVisibility(EditText.VISIBLE);	 
	        addMinerButton.setVisibility(Button.VISIBLE);
	        minerDirections.setVisibility(TextView.VISIBLE);	
	        minerDirections.setText(POOL_DIRECTIONS.get(poolToAdd));
	    }
	};
	
	public void addMiner(View view){
		final EditText minerName = (EditText) findViewById(R.id.minerName);	
		if (insertMiner(minerName.getText().toString(), poolToAdd)){
			Toast.makeText(getApplicationContext(), minerName.getText().toString()+"/"+poolToAdd+" added",
				Toast.LENGTH_LONG).show();
			AddMinerActivity.this.finish();
		} else {
			Toast.makeText(getApplicationContext(), minerName.getText().toString()+"/"+poolToAdd+" is invalid",
					Toast.LENGTH_LONG).show();			
		}
	}
    
	private Boolean validateMiner(String miner, String pool){
		if (miner == null || miner.length() == 0){
			return Boolean.FALSE;
		}
		if (pool == null || pool.length() == 0){
			return Boolean.FALSE;
		}
		if(pool.equals("slush")){
			for(Character c : miner.toCharArray()){
				if (!Character.isLetterOrDigit(c) && !c.equals('-')){
					return Boolean.FALSE;
				}
			}
			return Boolean.TRUE;
		} else if(pool.equals("deepbit")){
			for(Character c : miner.toCharArray()){
				if (!Character.isLetterOrDigit(c) && !c.equals('_')){
					return Boolean.FALSE;
				}
			}
			return Boolean.TRUE;
		} else if(pool.equals("btcmine") || pool.equals("btguild") || pool.equals("bitcoinpool")){
			for(Character c : miner.toCharArray()){
				if (!Character.isLetterOrDigit(c)){
					return Boolean.FALSE;
				}
			}
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	private Boolean insertMiner(String miner, String pool){
		if (validateMiner(miner, pool) && !minerService.minerExists(miner, pool)){
			minerService.insertMiner(miner, pool);
			return Boolean.TRUE;
		} else{
			Toast.makeText(getApplicationContext(), "Invalid Miner name: "+miner,
					Toast.LENGTH_LONG).show();
		}
		return Boolean.FALSE;
	}
    
}