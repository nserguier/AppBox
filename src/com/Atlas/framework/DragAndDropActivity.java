package com.Atlas.framework;


import composants.FontsOverride;

import android.app.Activity;
import android.os.Bundle;
import dragAndDrop.MyDragAndDrop;

public class DragAndDropActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drag_n_drop);

		/* Changement de la police par defaut */
		FontsOverride.setDefaultFont(this,"MONOSPACE", "fonts/onthemove.ttf");

		/* Drag And Drop */
		MyDragAndDrop dnd = new MyDragAndDrop(this, DragAndDropActivity.this);

		/* Objets qu'on veut deplacer */
		dnd.addDrag(R.id.cercle1);
		dnd.addDrag(R.id.cercle2);
		dnd.addDrag(R.id.cercle3);
		dnd.addDrag(R.id.cercle4);

		/* Zone ou on veut pouvoir les deposer */
		dnd.addDrop(R.id.reserve, 0, 0);
		dnd.addDrop(R.id.topleft, R.drawable.shape, R.drawable.shape_drop);
		dnd.addDrop(R.id.topright, R.drawable.shape, R.drawable.shape_drop);
		dnd.addDrop(R.id.bottomleft, R.drawable.shape, R.drawable.shape_drop);
		dnd.addDrop(R.id.bottomright, R.drawable.shape, R.drawable.shape_drop);

	}


}