package me.davidgreene.minerstatus.service;

import me.davidgreene.minerstatus.MinerStatusApp;
import me.davidgreene.minerstatus.theme.Theme;
import me.davidgreene.minerstatus.theme.ThemeFactory;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ThemeServiceImpl implements ThemeService {
	
	private MinerStatusApp app;
	
	public ThemeServiceImpl(Context context){
		this.app = ((MinerStatusApp)context);
	}
	private final String SELECT_CONFIG_VALUE = "select value from config where key=?";
	
	public Theme getTheme() {
		Cursor cursor = null;
		try{
			cursor = getDBr().rawQuery(SELECT_CONFIG_VALUE, new String[]{"theme"});
			if (cursor.moveToNext()){
				return ThemeFactory.getTheme(cursor.getString(0));
			} else {
				return ThemeFactory.getTheme();
			}
		} catch (Exception e){
			return ThemeFactory.getTheme();
		} finally{
			if (cursor != null){
				cursor.close();
			}
		}
	}

    private SQLiteDatabase getDBr(){
        return app.getDbHelper().getReadableDatabase();
    }	
}
