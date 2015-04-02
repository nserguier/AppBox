package com.Atlas.framework;

import bulles.BulleCreator;
import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BulleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bulle);
		
		Button bouton = (Button) findViewById(R.id.bouton_bulle);
		final TextView bulle1 = BulleCreator.createBubble(bouton, "Je suis une bulle à droite","right",true, this);
		final TextView bulle2 = BulleCreator.createBubble(bouton, "Je suis une bulle à gauche","left",true, this);
		final TextView bulle3 = BulleCreator.createBubble(bouton, "Je suis une bulle en haut","above",true, this);
		final TextView bulle4 = BulleCreator.createBubble(bouton, "Je suis une bulle en bas","below",true, this);
		
		Button suppr = (Button) findViewById(R.id.bouton_supprimer);
		suppr.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				BulleCreator.destroyBubble(bulle1, BulleActivity.this);
				BulleCreator.destroyBubble(bulle2, BulleActivity.this);
				BulleCreator.destroyBubble(bulle3, BulleActivity.this);
				BulleCreator.destroyBubble(bulle4, BulleActivity.this);
			}
		});
	}
}
