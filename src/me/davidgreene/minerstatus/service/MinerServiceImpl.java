package me.davidgreene.minerstatus.service;

import java.util.Date;

import me.davidgreene.minerstatus.MinerStatusApp;
import me.davidgreene.minerstatus.beans.Result;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MinerServiceImpl implements MinerService {
	
	private MinerStatusApp app;
	
    public MinerServiceImpl(Context context){
		this.app = ((MinerStatusApp)context);
	}
	
	public void updateErrorCount(String miner, int errorNum){
		ContentValues args = new ContentValues();
		args.put("errors", errorNum);
		getDBw().update("miners", args, "miner=?", new String[]{miner});
	}
	
	public void deleteMiner(String miner){
		getDBw().delete("miners", "miner=?", new String[]{miner});
	}
	
	private SQLiteDatabase getDBw(){
    	return app.getDbHelper().getWritableDatabase();
    }
    
    private final String SELECT_MINER = "SELECT miner FROM miners WHERE miner=? AND pool=?";
    
	@Override
	public Boolean minerExists(String miner, String pool) {
		Cursor cursor=null;
		try{
			cursor = getDBw().rawQuery(SELECT_MINER, new String[]{miner, pool});
			if (cursor.moveToNext()){
				return Boolean.TRUE;
			}
		} finally{ 
			if (cursor != null && !cursor.isClosed()){
				cursor.close();
			}
		}
		return Boolean.FALSE;
	}
    
	@Override
	public void insertMiner(String miner, String pool){
		ContentValues values = new ContentValues();
		values.put("miner", miner);
		values.put("pool", pool);
		values.put("errors", 14); //This will be reset to 0 if it is successful		
		getDBw().insert("miners", null, values);
	}

	
	public final String GET_LATEST_MINER_DATA = "SELECT json, date_long FROM miner_data WHERE date_long=(SELECT MAX(date_long) FROM miner_data WHERE miner=?) AND miner=?";
	
	@Override
	public void addJsonData(String miner, String jsonData) {
		ContentValues values = new ContentValues();
		values.put("miner", miner);
		values.put("json_data", jsonData);
		values.put("date_long", System.currentTimeMillis());		
		getDBw().insert("miner_data", null, values);
	}

	@Override
	public Result readJsonData(String miner) {
		Cursor cursor=null;
		try{
			cursor = getDBw().rawQuery(GET_LATEST_MINER_DATA, new String[]{miner, miner});
			if (cursor.moveToNext()){
				Result result = new Result();
				result.setData(cursor.getString(0));
				result.setDate(new Date(cursor.getLong(1)));
				return result;
			}
		} finally{ 
			if (cursor != null && !cursor.isClosed()){
				cursor.close();
			}
		}
		return null;
	}
	
	private final String SELECT_POOLS = "SELECT distinct pool FROM miners order by pool asc";
	
	@Override
	public Cursor getPools() {
		return getDBw().rawQuery(SELECT_POOLS, null);
	}


	private final String SELECT_MINERS = "SELECT miner, errors FROM miners WHERE pool=?";
	
	@Override
	public Cursor getMiners(String pool) {
		return getDBw().rawQuery(SELECT_MINERS, new String[]{pool});
	}

}
