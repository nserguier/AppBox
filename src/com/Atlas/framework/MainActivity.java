package com.Atlas.framework;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import boutons.NextActivityListener;
import fonts.FontsOverride;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		
		/* Mise des textes sur les boutons */
		Button b1 = (Button) findViewById(R.id.bouton1);
		Button b2 = (Button) findViewById(R.id.bouton2);
		Button b3 = (Button) findViewById(R.id.bouton3);
		Button b4 = (Button) findViewById(R.id.bouton4);
		Button b5 = (Button) findViewById(R.id.bouton5);
		Button b6 = (Button) findViewById(R.id.bouton6);
		Button b7 = (Button) findViewById(R.id.bouton7);
		Button b8 = (Button) findViewById(R.id.bouton8);
		Button b9 = (Button) findViewById(R.id.bouton9);
		Button b10 = (Button) findViewById(R.id.bouton10);

		b1.setText("Drag and Drop");
		b2.setText("Text to Speech");
		b3.setText("Boutons");
		b4.setText("Menus");
		b5.setText("Plein écran");
		b6.setText("Glow");
		b7.setText("Horloge");
		b8.setText("Bulles");
		b9.setText("Animations");
		b10.setText("Polices");

		/* Cree les lien des boutons */
		

		b1.setOnClickListener(new NextActivityListener(this,b1,MainActivity.this,DragAndDropActivity.class));
		b2.setOnClickListener(new NextActivityListener(this,b2,MainActivity.this,TTSActivity.class)); 
		b3.setOnClickListener(new NextActivityListener(this,b3,MainActivity.this,BoutonsActivity.class));
		b4.setOnClickListener(new NextActivityListener(this,b4,MainActivity.this,CustomMenuActivity.class));
		b5.setOnClickListener(new NextActivityListener(this,b5,MainActivity.this,FullScreenActivity.class));
		b6.setOnClickListener(new NextActivityListener(this,b6,MainActivity.this,GlowActivity.class));
		b7.setOnClickListener(new NextActivityListener(this,b7,MainActivity.this,ClockActivity.class));
		b8.setOnClickListener(new NextActivityListener(this,b8,MainActivity.this,BulleActivity.class));
		b9.setOnClickListener(new NextActivityListener(this,b9,MainActivity.this,AnimationActivity.class));
		b10.setOnClickListener(new NextActivityListener(this,b10,MainActivity.this,FontActivity.class));
		
	}

	@Override
	/* L'activite revient sur le devant de la scene */
	public void onResume() {
		super.onResume();
		Button b1 = (Button) findViewById(R.id.bouton1);
		Button b2 = (Button) findViewById(R.id.bouton2);
		Button b3 = (Button) findViewById(R.id.bouton3);
		Button b4 = (Button) findViewById(R.id.bouton4);
		Button b5 = (Button) findViewById(R.id.bouton5);
		Button b6 = (Button) findViewById(R.id.bouton6);
		Button b7 = (Button) findViewById(R.id.bouton7);
		Button b8 = (Button) findViewById(R.id.bouton8);
		Button b9 = (Button) findViewById(R.id.bouton9);
		Button b10 = (Button) findViewById(R.id.bouton10);
		Drawable d = getResources().getDrawable(R.drawable.bouton_bleu);
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

	}
}
