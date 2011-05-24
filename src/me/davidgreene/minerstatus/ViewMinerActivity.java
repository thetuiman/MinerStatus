package me.davidgreene.minerstatus;

import me.davidgreene.minerstatus.beans.BitpoolStatus;
import me.davidgreene.minerstatus.beans.BtcMine;
import me.davidgreene.minerstatus.beans.BtcguildStatus;
import me.davidgreene.minerstatus.beans.BtcguildWorker;
import me.davidgreene.minerstatus.beans.DeepbitStatus;
import me.davidgreene.minerstatus.beans.SlushStatus;
import me.davidgreene.minerstatus.beans.Status;
import me.davidgreene.minerstatus.beans.WorkerStatus;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ViewMinerActivity extends AbstractMinerStatusActivity {

	//private static final String tag = "TX";
	
	private Status minerStatus;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getIntent().getExtras();
        minerStatus = (Status) bundle.getSerializable("status");
        setContentView(R.layout.viewminer);
        int bgColor = themeService.getTheme().getBackgroundColor();
        ScrollView scrollView = (ScrollView) findViewById(R.id.viewMinerScrollView);
        scrollView.setBackgroundColor(bgColor);
        
        
        populateDetailedView();
        
        Button deleteMinerButton = (Button) findViewById(R.id.deleteMinerButton);
        deleteMinerButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					AlertDialog.Builder alert = new AlertDialog.Builder(ViewMinerActivity.this);
					alert.setTitle(minerStatus.getApiKey());
					alert.setPositiveButton("Remove", new DialogInterface.OnClickListener() {	
						public void onClick(DialogInterface dialog, int whichButton) {
							Toast.makeText(getApplicationContext(), minerStatus.getApiKey()+" removed.",
									Toast.LENGTH_LONG).show();
							minerService.deleteMiner(minerStatus.getApiKey());
							ViewMinerActivity.this.finish();
						}
					});		
					alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							dialog.cancel();
						}
					});				
					alert.show();  
				}
			});
        
    }
	
	private void populateDetailedView(){
		TableLayout tl = (TableLayout) findViewById(R.id.detailedView);
		if (minerStatus instanceof BitpoolStatus){
			render((BitpoolStatus)minerStatus, tl);
		} else if (minerStatus instanceof DeepbitStatus){
			render((DeepbitStatus)minerStatus, tl);
		} else if (minerStatus instanceof SlushStatus){
			render((SlushStatus)minerStatus, tl);
		} else if (minerStatus instanceof BtcMine){
			render((BtcMine)minerStatus, tl);
		} else if (minerStatus instanceof BtcguildStatus){
			render((BtcguildStatus)minerStatus, tl);
		} else {
			tl.setVisibility(TableLayout.INVISIBLE);
		}
		
	}
	
	private TableRow renderRow(String left, String right){
		TableRow tr = new TableRow(this);
		TextView leftCol = new TextView(getApplicationContext());
		leftCol.setPadding(getDip(5F), getDip(5F), getDip(5F), getDip(5F));
		leftCol.setTextColor(themeService.getTheme().getHeaderTextColor());
		leftCol.setText(left);
		tr.addView(leftCol);
		TextView rightCol = new TextView(getApplicationContext());
		rightCol.setPadding(getDip(10F), getDip(5F), getDip(5F), getDip(5F));
		rightCol.setTextColor(themeService.getTheme().getTextColor());
		rightCol.setText(right);
		tr.addView(rightCol);
		return tr;
	}
	
	private void render(BitpoolStatus status, TableLayout tl){
		tl.addView(renderRow("Username", status.getUsername()));
		tl.addView(renderRow("Status", status.getUser().getStatus()));
		tl.addView(renderRow("Current Speed", status.getUser().getCurrSpeed()));
		tl.addView(renderRow("Curr. Pool Speed", status.getPool().getCurrentSpeed()));
		tl.addView(renderRow("Currrent Round", status.getPool().getCurrentRound()));
		tl.addView(renderRow("Join Date", status.getUser().getJoinDt()));
		tl.addView(renderRow("Last Seen", status.getUser().getLastSeen()));
		tl.addView(renderRow("Active", status.getUser().getActive()));
		tl.addView(renderRow("Estimated Earnings", status.getUser().getEstimated()));
		tl.addView(renderRow("Unconfirmed", status.getUser().getUnconfirmed()));
		tl.addView(renderRow("Historical", status.getUser().getHistorical()));
		tl.addView(renderRow("Unpaid", status.getUser().getUnpaid()));
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<status.getUser().getSolvedBlocks().length; i++){
			sb.append(status.getUser().getSolvedBlocks()[i]);
			if(i < status.getUser().getSolvedBlocks().length-1){
				sb.append(',');
			}
		}
		tl.addView(renderRow("Solved Blocks", sb.toString()));
		tl.addView(renderRow("Requested", status.getUser().getRequested().toString()));
		tl.addView(renderRow("Submitted", status.getUser().getSubmitted().toString()));
		tl.addView(renderRow("Efficiency", status.getUser().getEfficiency()));
		
	}
	private void render(DeepbitStatus status, TableLayout tl){
		//tl.addView(renderRow("Api Key", status.getApiKey()));
		tl.addView(renderRow("Hashrate", status.getHashrate().toString()));
		tl.addView(renderRow("Confirmed Reward", status.getConfirmed_reward().toString()));
		tl.addView(renderRow("Ipa", status.getIpa().toString()));
		tl.addView(renderRow("Worker(s):",""));
	    for( String key : status.getWorkers().keySet() ){
	    	WorkerStatus workerStatus = status.getWorkers().get(key);
	    	tl.addView(renderRow("",key));
	    	tl.addView(renderRow("Alive",workerStatus.getAlive().toString()));
	    	tl.addView(renderRow("Shares",workerStatus.getShares().toString()));
	    	tl.addView(renderRow("Stales",workerStatus.getStales().toString()));
	    	tl.addView(renderRow("",""));
	    }
	}
	
	private void render(BtcguildStatus status, TableLayout tl){
		//tl.addView(renderRow("Api Key", status.getApiKey()));
		tl.addView(renderRow("Confirmed Rewards", status.getUser().getConfirmed_rewards().toString()));
		tl.addView(renderRow("Unconfirmed Rewards", status.getUser().getUnconfirmed_rewards().toString()));
		tl.addView(renderRow("Estimated Rewards", status.getUser().getEstimated_rewards().toString()));
		tl.addView(renderRow("Payouts", status.getUser().getPayouts().toString()));
		tl.addView(renderRow("Worker(s):",""));
	    for( String key : status.getWorkers().keySet() ){
	    	BtcguildWorker worker = status.getWorkers().get(key);
	    	tl.addView(renderRow("",worker.getWorker_name()));
	    	tl.addView(renderRow("Hashrate",worker.getHash_rate().toString()));
	    	tl.addView(renderRow("Last Share",worker.getLast_share()));
	    	tl.addView(renderRow("Round Shares",worker.getRound_shares().toString()));
	    	tl.addView(renderRow("Round Stales",worker.getRound_stales().toString()));
	    	tl.addView(renderRow("Total Shares",worker.getTotal_shares().toString()));
	    	tl.addView(renderRow("Total Stales",worker.getTotal_stales().toString()));
	    	tl.addView(renderRow("Blocks Found",worker.getBlocks_found().toString()));
	    	tl.addView(renderRow("",""));
	    }
	}	
	
	private void render(SlushStatus status, TableLayout tl){
		tl.addView(renderRow("Username", status.getUsername()));
		//tl.addView(renderRow("Api Key", status.getApiKey()));
		tl.addView(renderRow("Send Threshold", status.getSend_threshold()));
		tl.addView(renderRow("Estimated", status.getEstimated_reward()));
		tl.addView(renderRow("Unconfirmed", status.getUnconfirmed_reward()));
		tl.addView(renderRow("Confirmed", status.getConfirmed_reward()));
		tl.addView(renderRow("Wallet", status.getWallet()));
	}
  
	private void render(BtcMine status, TableLayout tl){
		tl.addView(renderRow("Hashrate", status.getHashrate()));
		tl.addView(renderRow("Total Payout", status.getTotal_payout()));
		tl.addView(renderRow("Total Bounty", status.getTotal_bounty()));
		//tl.addView(renderRow("Api Key", status.getApiKey()));
		tl.addView(renderRow("Confirmed Bounty", status.getConfirmed_bounty()));
		tl.addView(renderRow("Estimated Bounty", status.getEstimated_bounty()));
		tl.addView(renderRow("Unconfirmed Bounty", status.getUnconfirmed_bounty()));
		tl.addView(renderRow("Round Shares", status.getRound_shares().toString()));
		tl.addView(renderRow("Solved Shares", status.getSolved_shares().toString()));
		tl.addView(renderRow("Solved Blocks", status.getSolved_blocks().toString()));
	}
}