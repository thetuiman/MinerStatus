package me.davidgreene.minerstatus.theme;

public class ThemeFactory {
	
	private static final Theme DEFAULT_THEME = new DarkTheme();
	
	public static Theme getTheme(){
		return getTheme("");
	}
	public static Theme getTheme(String themeName){
		if(themeName == null){
			return DEFAULT_THEME;
		}
		if(themeName.equals("dark")){
			return new DarkTheme();
		}
		if(themeName.equals("light")){
			return new LightTheme();
		}
		return DEFAULT_THEME;
	}

}
