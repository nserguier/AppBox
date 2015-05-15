package com.Atlas.framework;

import custom.MenuStyle;
import custom.MenuVertical;

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
		
		MenuVertical m = new MenuVertical(this);
		m.createMenu(parent);
		m.rassembler(1,2);
		m.rassembler(3,4);
		m.addTitre("MEMORY GNAR !!");
		m.addButton("Jouer", 1,R.color.vert1);
		m.addButton("Tête de GNAR !", 3,R.color.vert2);
		m.addButton("Aide de GNAR ?", 5,R.color.vert1);
		m.addButton("GNARGOUILLA !!!", 6,R.color.vert1);
		
	}
}
