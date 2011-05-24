package me.davidgreene.minerstatus;

import me.davidgreene.minerstatus.service.ConfigService;
import me.davidgreene.minerstatus.service.ConfigServiceImpl;
import me.davidgreene.minerstatus.service.MinerService;
import me.davidgreene.minerstatus.service.MinerServiceImpl;
import me.davidgreene.minerstatus.service.ThemeService;
import me.davidgreene.minerstatus.service.ThemeServiceImpl;
import me.davidgreene.minerstatus.util.DbOpenHelper;
import android.app.Application;

public class MinerStatusApp extends Application {
	
	private DbOpenHelper dbHelper;
	private MinerService minerService; 
	private ThemeService themeService;
	private ConfigService configService;
	
    @Override
    public void onCreate() {
        super.onCreate();
        setDbHelper(new DbOpenHelper(this));
        setThemeService(new ThemeServiceImpl(this));
        setMinerService(new MinerServiceImpl(this));
        setConfigService(new ConfigServiceImpl(this));
    }
    
    @Override
    public void onTerminate() {
        super.onTerminate();        
        if (dbHelper != null) {
        	if (dbHelper.getReadableDatabase() != null){
        		dbHelper.getReadableDatabase().close();
        	}
        	if(dbHelper.getWritableDatabase() != null){
        		dbHelper.getWritableDatabase().close();
        	}
        	dbHelper.close();
        }
    }

	public DbOpenHelper getDbHelper() {
		return dbHelper;
	}
	public void setDbHelper(DbOpenHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	public MinerService getMinerService() {
		return minerService;
	}
	public void setMinerService(MinerService minerService) {
		this.minerService = minerService;
	}
	public ThemeService getThemeService() {
		return themeService;
	}
	public void setThemeService(ThemeService themeService) {
		this.themeService = themeService;
	}

	public ConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}
}
