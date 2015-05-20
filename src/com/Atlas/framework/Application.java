package com.Atlas.framework;

import composants.Fonts;

public final class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fonts.setDefaultFont(getApplicationContext(), "onthemove.ttf");
    }
}