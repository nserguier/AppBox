package com.Atlas.framework;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import composants.Bulle;

public class BulleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bulle);

		Button bouton = (Button) findViewById(R.id.bouton_bulle);
		final TextView bulle1 = Bulle.create(bouton,
				"Je suis une bulle à droite", "right", true, this);
		final TextView bulle2 = Bulle.create(bouton,
				"Je suis une bulle à gauche", "left", true, this);
		final TextView bulle3 = Bulle.create(bouton,
				"Je suis une bulle en haut", "above", true, this);
		final TextView bulle4 = Bulle.create(bouton,
				"Je suis une bulle en bas", "below", true, this);

		Button suppr = (Button) findViewById(R.id.bouton_supprimer);
		suppr.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Bulle.destroy(bulle1, BulleActivity.this);
				Bulle.destroy(bulle2, BulleActivity.this);
				Bulle.destroy(bulle3, BulleActivity.this);
				Bulle.destroy(bulle4, BulleActivity.this);
			}
		});
	}
}
