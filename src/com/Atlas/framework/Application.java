package com.Atlas.framework;

import android.widget.TextView;
import composants.Police;

public final class Application extends android.app.Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Police.setDefaultFont(getApplicationContext(), "onthemove.ttf");
	}
}