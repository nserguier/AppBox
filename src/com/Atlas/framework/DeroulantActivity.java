package com.Atlas.framework;

import boutons.ButtonCreator;
import boutons.HomeActivityListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import animation.MenuDeroulant;

public class DeroulantActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deroulant);
		
		ViewGroup parent = (ViewGroup) findViewById(R.id.parent);
		RelativeLayout fils = new RelativeLayout(this);
		RelativeLayout.LayoutParams params = new LayoutParams(600,600);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		fils.setLayoutParams(params);
		parent.addView(fils);
		
		RelativeLayout votreMenu = MenuDeroulant.create(fils, this, R.color.bleu1,R.color.bleu2, "derouler le menu");
		
	}
}
