package com.Atlas.framework;

import boutons.HomeActivityListener;
import boutons.NextActivityListener;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		/* Bouton home de retour au menu */
		Button home = (Button) findViewById(R.id.home);
		home.setOnClickListener(new HomeActivityListener(this,home,SecondActivity.this,MainActivity.class));
		
		/* Bouton pour aller a l'activite suivante */
		final Button boutonNext = (Button) findViewById(R.id.next);
		Drawable pressed = getResources().getDrawable(R.drawable.bouton1e);
		boutonNext.setOnClickListener(new NextActivityListener(boutonNext,pressed, SecondActivity.this, ThirdActivity.class));
	}
	
	@Override
	/* L'activite revient sur le devant de la scene */
	public void onResume() {
		super.onResume();
		final Button boutonNext = (Button) findViewById(R.id.next);
		Drawable d = getResources().getDrawable(R.drawable.bouton1);
		boutonNext.setBackground(d);
		final Button home = (Button) findViewById(R.id.home);
		home.setBackground(getResources().getDrawable(R.drawable.home));
	}
}
