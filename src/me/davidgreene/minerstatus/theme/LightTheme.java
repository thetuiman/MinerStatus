package me.davidgreene.minerstatus.theme;

import android.graphics.Color;

public class LightTheme implements Theme {

	@Override
	public int getTextColor() {
		return Color.BLACK;
	}

	@Override
	public int getBackgroundColor() {
		return Color.WHITE;
	}

	@Override
	public int getHeaderTextColor() {
		return Color.BLUE;
	}

}
