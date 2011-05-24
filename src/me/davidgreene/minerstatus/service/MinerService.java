package me.davidgreene.minerstatus.service;

import me.davidgreene.minerstatus.beans.Result;
import android.database.Cursor;


public interface MinerService {

	public void updateErrorCount(String miner, int errorNum);
	public void deleteMiner(String miner);
	public Boolean minerExists(String miner, String pool);
	public void insertMiner(String miner, String pool);
	
	public void addJsonData(String miner, String jsonData);
	public Result readJsonData(String miner);
	
	public Cursor getPools();
	public Cursor getMiners(String pool);
}
