package com.Atlas.framework;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import boutons.Bouton;

import composants.Police;

public class FontActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_font);

		Button bouton1 = (Button) findViewById(R.id.bouton_police1);
		Bouton.setButtonStyle(this, bouton1, R.color.vert1,
				"Définir cette police", R.color.blanc);
		Typeface font1 = Typeface.createFromAsset(getAssets(),
				"fonts/comic.otf");
		bouton1.setTypeface(font1);
		bouton1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Police.setDefaultFont(getApplicationContext(),
						"fonts/comic.otf");
				Intent intent = getIntent();
				finish();
				startActivity(intent);
			}
		});

		Button bouton2 = (Button) findViewById(R.id.bouton_police2);
		Bouton.setButtonStyle(this, bouton2, R.color.rose,
				"Définir cette police", R.color.blanc);
		Typeface font2 = Typeface.createFromAsset(getAssets(),
				"fonts/onthemove.ttf");
		bouton2.setTypeface(font2);
		bouton2.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Police.setDefaultFont(getApplicationContext(),
						"fonts/onthemove.ttf");
				Intent intent = getIntent();
				finish();
				startActivity(intent);
			}
		});
	}
}
