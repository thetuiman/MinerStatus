package me.davidgreene.minerstatus;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class OptionsActivity extends AbstractMinerStatusActivity{
		
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        int bgColor = themeService.getTheme().getBackgroundColor();
        int color = themeService.getTheme().getTextColor();
        
        ScrollView scrollView = (ScrollView) findViewById(R.id.optionsScrollView);
        scrollView.setBackgroundColor(bgColor);
        
        TextView mtGoxToggleLabel = (TextView) findViewById(R.id.mtGoxButtonLabel);
        mtGoxToggleLabel.setTextColor(color);
        TextView themeSpinnerLabel = (TextView) findViewById(R.id.themeSpinnerLabel);
        themeSpinnerLabel.setTextColor(color);
        final ToggleButton mtGoxToggle = (ToggleButton) findViewById(R.id.mtGoxToggle);
        mtGoxToggle.setChecked(Boolean.valueOf(configService.getConfigValue("show.mtgox")));
        mtGoxToggle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		        if (mtGoxToggle.isChecked()) {
		        	configService.setConfigValue("show.mtgox", "true");
		        } else {
		        	configService.setConfigValue("show.mtgox", "false");
		        }
		        Toast.makeText(OptionsActivity.this, (mtGoxToggle.isChecked()) ? "Mt. Gox Visible" : "Mt. Gox Hidden", Toast.LENGTH_SHORT).show();
			}
		});
        
    	final RadioButton darkTheme = (RadioButton) findViewById(R.id.radio_dark);
    	final RadioButton lightTheme = (RadioButton) findViewById(R.id.radio_light);

    	String themeString = configService.getConfigValue("theme");
    	
    	if(themeString.equals("light")){
    		lightTheme.setChecked(Boolean.TRUE);
    	} else if (themeString.equals("dark")){
    		darkTheme.setChecked(Boolean.TRUE);
    	}
    	
    	darkTheme.setOnClickListener(radioListener);
    	darkTheme.setTextColor(color);
    	
    	lightTheme.setOnClickListener(radioListener);
    	lightTheme.setTextColor(color);
        
	}	
	
	private OnClickListener radioListener = new OnClickListener() {
	    public void onClick(View v) {
	        RadioButton rb = (RadioButton) v;
	        if(rb.getText().equals("Dark Theme")){
	        	configService.setConfigValue("theme", "dark");
	        } else if (rb.getText().equals("Light Theme")){
	        	configService.setConfigValue("theme", "light");
	        }
	    }
	};
	
}
