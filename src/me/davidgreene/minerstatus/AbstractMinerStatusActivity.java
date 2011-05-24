package me.davidgreene.minerstatus;

import me.davidgreene.minerstatus.service.ConfigService;
import me.davidgreene.minerstatus.service.MinerService;
import me.davidgreene.minerstatus.service.ThemeService;
import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;

public abstract class AbstractMinerStatusActivity extends Activity {

	protected MinerService minerService; 
	protected ThemeService themeService;
	protected ConfigService configService;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        minerService = ((MinerStatusApp)getApplication().getApplicationContext()).getMinerService();
        configService = ((MinerStatusApp)getApplication().getApplicationContext()).getConfigService();
        themeService = ((MinerStatusApp)getApplication().getApplicationContext()).getThemeService();
	}
	
	protected int getDip(float dipValue){
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 
                (float) dipValue, getResources().getDisplayMetrics());
	}
}
