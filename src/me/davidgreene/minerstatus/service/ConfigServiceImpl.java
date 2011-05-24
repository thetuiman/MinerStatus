package me.davidgreene.minerstatus.service;

import me.davidgreene.minerstatus.MinerStatusApp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ConfigServiceImpl implements ConfigService {

	private MinerStatusApp app;
	
	public ConfigServiceImpl(Context context){
		this.app = ((MinerStatusApp)context);
	}
	
	private final String SELECT_CONFIG_VALUE = "select value from config where key=?";
	
	@Override
	public String getConfigValue(String key) {
		Cursor cursor = null;
		try{
			cursor = getDBr().rawQuery(SELECT_CONFIG_VALUE, new String[]{key});
			if (cursor.moveToNext()){
				return cursor.getString(0);
			} else {
				return null;
			}
		} catch (Exception e){
			return null;
		} finally{
			if (cursor != null){
				cursor.close();
			}
		}		
	}
	
	@Override
	public void setConfigValue(String key, String value) {
		deleteConfigValue(key);
		ContentValues values = new ContentValues();
		values.put("key", key);
		values.put("value", value);	
		getDBw().insert("config", null, values);
	}
	
	@Override
	public void deleteConfigValue(String key) {
		getDBw().delete("config", "key=?", new String[]{key});
	}
	
    private SQLiteDatabase getDBw(){
        return app.getDbHelper().getWritableDatabase();
    }	
    private SQLiteDatabase getDBr(){
        return app.getDbHelper().getReadableDatabase();
    }

}
