package com.Atlas.framework;

import custom.MenuStyle;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MenuStyleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_style);
		
		ViewGroup parent = (ViewGroup) findViewById(R.id.parent);
		MenuStyle m = new MenuStyle(this);
		RelativeLayout[] menu = m.createMenu();
		parent.addView(menu[0]);
	}
}
