package com.Atlas.framework;

import boutons.NextActivityListener;
import fonts.FontsOverride;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		/* Changement de la police par defaut */
		FontsOverride.setDefaultFont(this,"MONOSPACE", "fonts/onthemove.ttf");
		
		/* Mise des textes sur les boutons */
		Button b1 = (Button) findViewById(R.id.bouton1);
		Button b2 = (Button) findViewById(R.id.bouton2);
		Button b3 = (Button) findViewById(R.id.bouton3);
		Button b4 = (Button) findViewById(R.id.bouton4);
		Button b5 = (Button) findViewById(R.id.bouton5);
		
		b1.setText("Drag and Drop");
		b2.setText("Text to Speech");
		b3.setText("Boutons");
		b4.setText("Menus");
		b5.setText("Plein écran");
		
		/* Cree les lien des boutons */
		b1.setOnClickListener(new NextActivityListener(this,b1,MenuActivity.this,DragAndDropActivity.class));
		b2.setOnClickListener(new NextActivityListener(this,b2,MenuActivity.this,TTSActivity.class)); 
		b3.setOnClickListener(new NextActivityListener(this,b3,MenuActivity.this,BoutonsActivity.class));
		b4.setOnClickListener(new NextActivityListener(this,b4,MenuActivity.this,CustomMenuActivity.class));
		b5.setOnClickListener(new NextActivityListener(this,b5,MenuActivity.this,FullScreenActivity.class));
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
		Drawable d = getResources().getDrawable(R.drawable.bouton_bleu);
		b1.setBackground(d);
		b2.setBackground(d);
		b3.setBackground(d);
		b4.setBackground(d);
		b5.setBackground(d);

	}
}
