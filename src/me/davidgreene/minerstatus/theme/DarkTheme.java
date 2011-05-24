package me.davidgreene.minerstatus.theme;

import android.graphics.Color;

public class DarkTheme implements Theme {

	@Override
	public int getTextColor() {
		return Color.WHITE;
	}

	@Override
	public int getBackgroundColor() {
		return Color.BLACK;
	}

	@Override
	public int getHeaderTextColor() {
		return Color.GRAY;
	}

}
