package me.davidgreene.minerstatus.service;

import java.util.Date;

import me.davidgreene.minerstatus.MinerStatusApp;
import me.davidgreene.minerstatus.beans.Result;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MinerServiceImpl implements MinerService {
	
	private final String tag = "TX";
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
		values.put("errors", 4); //This will be reset to 0 if it is successful		
		getDBw().insert("miners", null, values);
	}

	
	public void addJsonData(String miner, String jsonData) {
		ContentValues values = new ContentValues();
		values.put("miner", miner);
		values.put("json", jsonData);
		values.put("date_long", System.currentTimeMillis());		
		getDBw().insert("miner_data", null, values);
	}

	//public final String GET_LATEST_MINER_DATA = "SELECT json, date_long FROM miner_data WHERE date_long=(SELECT MAX(date_long) FROM miner_data WHERE miner=?) AND miner=?";
	//Select(max) columns need to be indexed, using an alternate method
	public final String GET_LATEST_MINER_DATA = "SELECT json, date_long FROM miner_data WHERE miner=? ORDER BY date_long DESC";
	public final String CLEAR_DAY_OLD_DATA = "DELETE FROM miner_data WHERE miner=? and date_long < ?";
	public Result readJsonData(String miner) {
		Cursor cursor=null;
		try{
			Long oneDayAgo = System.currentTimeMillis() - 86400000L;
			cursor = getDBw().rawQuery(CLEAR_DAY_OLD_DATA, new String[]{miner, oneDayAgo.toString()});
			
			cursor = getDBw().rawQuery(GET_LATEST_MINER_DATA, new String[]{miner});
			
			//Only select the first row since we're going for the max(date_long)
			if (cursor.moveToNext()){
				Result result = new Result();
				result.setData(cursor.getString(0));
				result.setDate(new Date(cursor.getLong(1)));
				return result;
			}
		} catch (Exception e){
			Log.d(tag, e.getMessage()); 
		} finally{
			if (cursor != null && !cursor.isClosed()){
				cursor.close();
			}
		}
		return null;
	}
	
	private final String SELECT_POOLS = "SELECT distinct pool FROM miners order by pool asc";
	
	public Cursor getPools() {
		return getDBw().rawQuery(SELECT_POOLS, null);
	}


	private final String SELECT_MINERS = "SELECT miner, errors FROM miners WHERE pool=?";
	
	public Cursor getMiners(String pool) {
		return getDBw().rawQuery(SELECT_MINERS, new String[]{pool});
	}

}
