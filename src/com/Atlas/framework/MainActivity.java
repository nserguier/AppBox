package com.Atlas.framework;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import boutons.NextActivityListener;
import dragAndDrop.MyDragAndDrop;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/* Bouton pour aller a l'activite suivante */
		final Button boutonNext = (Button) findViewById(R.id.next);
		Drawable pressed = getResources().getDrawable(R.drawable.bouton1e);
		boutonNext.setOnClickListener(new NextActivityListener(boutonNext,
				pressed, MainActivity.this, SecondActivity.class));

		/* Drag And Drop */
		MyDragAndDrop dnd = new MyDragAndDrop(this, MainActivity.this);

		/* Objets qu'on veut deplacer */
		dnd.addDrag(R.id.gnar1);
		dnd.addDrag(R.id.gnar2);
		dnd.addDrag(R.id.gnar3);
		dnd.addDrag(R.id.gnar4);

		/* Zone ou on veut pouvoir les deposer */
		dnd.addDrop(R.id.topleft, R.drawable.shape, R.drawable.shape_drop);
		dnd.addDrop(R.id.topright, R.drawable.shape, R.drawable.shape_drop);
		dnd.addDrop(R.id.bottomleft, R.drawable.shape, R.drawable.shape_drop);
		dnd.addDrop(R.id.bottomright, R.drawable.shape, R.drawable.shape_drop);

	}

	@Override
	/* L'activite revient sur le devant de la scene */
	public void onResume() {
		super.onResume();
		final Button boutonNext = (Button) findViewById(R.id.next);
		Drawable d = getResources().getDrawable(R.drawable.bouton1);
		boutonNext.setBackground(d);

	}

}
