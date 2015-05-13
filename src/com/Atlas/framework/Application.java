package com.Atlas.framework;

import composants.FontsOverride;

public final class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(getApplicationContext(), "onthemove.ttf");
    }
}