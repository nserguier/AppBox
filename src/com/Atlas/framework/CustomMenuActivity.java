package com.Atlas.framework;

import custom.MenuRapide;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Activite montrant l'utilisation du composant reutilisable menu
 * @author Nicklos
 *
 */
public class CustomMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_menu);
			
		ViewGroup parent = (ViewGroup) findViewById(R.id.parent);
		RelativeLayout menu = MenuRapide.create(R.color.vert1, "Menu rapide !",R.color.bleu1, "joli bouton", R.color.orange3, this);
		parent.addView(menu);
		
		
	}
}
