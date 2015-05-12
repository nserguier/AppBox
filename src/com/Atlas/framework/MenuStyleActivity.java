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
		m.rassembler(2,3);
		m.rassembler(4,5);
		m.addTitre("MEMORY GNAR !!");
		m.addButton("Jouer", 2);
		m.addButton("Têtes de GNAR !", 4);
		m.addButton("Aide de GNAR ?", 6);
		m.addButton("GNARGOUILLA !!!", 7);
		parent.addView(menu[0]);
	}
}
