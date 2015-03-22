package com.Atlas.framework;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
	}
}
