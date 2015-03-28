package com.Atlas.framework;

import boutons.ButtonCreator;
import boutons.HomeActivityListener;
import boutons.NextActivityListener;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class BoutonsActivity extends Activity {

	EditText edit = null;
	LinearLayout stock = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boutons);
		
		/* Recuperation elements */
		edit = (EditText) findViewById(R.id.editText);
		stock = (LinearLayout) findViewById(R.id.stock);
		
		/* Bouton home de retour au menu */
		Button home = (Button) findViewById(R.id.home);
		home.setOnClickListener(new HomeActivityListener(this,home,BoutonsActivity.this,MainActivity.class));
		
		/* Bouton de creation */
		Button creer =  (Button) findViewById(R.id.creer);
		creer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				edit = (EditText) findViewById(R.id.editText);
				String nomBouton = edit.getText().toString();
				Button b = ButtonCreator.createBlueButton(getApplicationContext(), nomBouton);
				stock.addView(b);
				
			}
		});
		
	}
	
	@Override
	/* L'activite revient sur le devant de la scene */
	public void onResume() {
		super.onResume();
		final Button home = (Button) findViewById(R.id.home);
		home.setBackground(getResources().getDrawable(R.drawable.home));
	}
}
