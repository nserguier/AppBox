package com.Atlas.framework;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import boutons.NextActivityListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		/* Mise des textes sur les boutons */
		final Button b1 = (Button) findViewById(R.id.bouton1);
		final Button b2 = (Button) findViewById(R.id.bouton2);
		final Button b3 = (Button) findViewById(R.id.bouton3);
		final Button b4 = (Button) findViewById(R.id.bouton4);
		final Button b5 = (Button) findViewById(R.id.bouton5);
		final Button b6 = (Button) findViewById(R.id.bouton6);
		final Button b7 = (Button) findViewById(R.id.bouton7);
		final Button b8 = (Button) findViewById(R.id.bouton8);
		final Button b9 = (Button) findViewById(R.id.bouton9);
		final Button b10 = (Button) findViewById(R.id.bouton10);
		final Button b11 = (Button) findViewById(R.id.bouton11);
		final Button b12 = (Button) findViewById(R.id.bouton12);

		b1.setText("Drag and Drop");
		b2.setText("Text to Speech");
		b3.setText("Boutons");
		b4.setText("Menus");
		b5.setText("Plein �cran");
		b6.setText("Glow");
		b7.setText("Horloge");
		b8.setText("Bulles");
		b9.setText("Animations");
		b10.setText("Polices");
		b11.setText("rien");
		b12.setText("Menu deroulant");

		/* Cree les lien des boutons */

		b1.setOnClickListener(new NextActivityListener(b1, null,
				MainActivity.this, DragAndDropActivity.class));
		b2.setOnClickListener(new NextActivityListener(b2, null,
				MainActivity.this, TTSActivity.class));
		b3.setOnClickListener(new NextActivityListener(b3, null,
				MainActivity.this, BoutonsActivity.class));
		b4.setOnClickListener(new NextActivityListener(b4, null,
				MainActivity.this, MenuActivity.class));
		b5.setOnClickListener(new NextActivityListener(b5, null,
				MainActivity.this, FullScreenActivity.class));
		b6.setOnClickListener(new NextActivityListener(b6, null,
				MainActivity.this, GlowActivity.class));
		b7.setOnClickListener(new NextActivityListener(b7, null,
				MainActivity.this, ClockActivity.class));
		b8.setOnClickListener(new NextActivityListener(b8, null,
				MainActivity.this, BulleActivity.class));
		b9.setOnClickListener(new NextActivityListener(b9, null,
				MainActivity.this, AnimationActivity.class));
		b10.setOnClickListener(new NextActivityListener(b10, null,
				MainActivity.this, FontActivity.class));
		b12.setOnClickListener(new NextActivityListener(b12, null,
				MainActivity.this, DeroulantActivity.class));

	}

	@SuppressWarnings("deprecation")
	@Override
	/* L'activite revient sur le devant de la scene */
	public void onResume() {
		super.onResume();
		final Button b1 = (Button) findViewById(R.id.bouton1);
		final Button b2 = (Button) findViewById(R.id.bouton2);
		final Button b3 = (Button) findViewById(R.id.bouton3);
		final Button b4 = (Button) findViewById(R.id.bouton4);
		final Button b5 = (Button) findViewById(R.id.bouton5);
		final Button b6 = (Button) findViewById(R.id.bouton6);
		final Button b7 = (Button) findViewById(R.id.bouton7);
		final Button b8 = (Button) findViewById(R.id.bouton8);
		final Button b9 = (Button) findViewById(R.id.bouton9);
		final Button b10 = (Button) findViewById(R.id.bouton10);
		final Button b11 = (Button) findViewById(R.id.bouton11);
		final Button b12 = (Button) findViewById(R.id.bouton12);
		final Drawable d = getResources().getDrawable(R.drawable.bouton_bleu);
		if (Build.VERSION.SDK_INT >= 16) {
			b1.setBackground(d);
			b2.setBackground(d);
			b3.setBackground(d);
			b4.setBackground(d);
			b5.setBackground(d);
			b6.setBackground(d);
			b7.setBackground(d);
			b8.setBackground(d);
			b9.setBackground(d);
			b10.setBackground(d);
			b11.setBackground(d);
			b12.setBackground(d);
		} else {
			b1.setBackgroundDrawable(d);
			b2.setBackgroundDrawable(d);
			b3.setBackgroundDrawable(d);
			b4.setBackgroundDrawable(d);
			b5.setBackgroundDrawable(d);
			b6.setBackgroundDrawable(d);
			b7.setBackgroundDrawable(d);
			b8.setBackgroundDrawable(d);
			b9.setBackgroundDrawable(d);
			b10.setBackgroundDrawable(d);
			b11.setBackgroundDrawable(d);
			b12.setBackgroundDrawable(d);
		}

	}
}
