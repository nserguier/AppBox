package com.Atlas.framework;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import boutons.NextActivityListener;
import boutons.TTSBouton;

public class TTSActivity extends Activity {

	EditText write = null;
	Button bouton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tts);

		bouton = (Button) findViewById(R.id.bouton_tts);
		write = (EditText) findViewById(R.id.champ);
		TTSBouton.parleEdit(bouton, write, this);

		/* Bouton home de retour au menu */
		Button home = (Button) findViewById(R.id.home);
		home.setOnClickListener(new NextActivityListener(home, null,
				TTSActivity.this, MainActivity.class));
	}

}
