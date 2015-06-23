package com.Atlas.framework;


import java.util.Calendar;
import java.util.GregorianCalendar;

import boutons.NextActivityListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import composants.Horloge;

public class ClockActivity extends Activity {

	ImageView heures = null;
	ImageView minutes = null;
	ImageView secondes = null;
	ImageView horloge = null;
	EditText write = null;
	Button go = null;
	RelativeLayout r = null;

	@Override
	/**
	 * cree une horloge qui est de base a la bonne heure
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clock);

		r = (RelativeLayout) findViewById(R.id.analogClock);
		write = (EditText) findViewById(R.id.champ);
		go = (Button) findViewById(R.id.go);
		
		GregorianCalendar c =new GregorianCalendar();
		c.setTimeInMillis(System.currentTimeMillis());

		int heure = Calendar.HOUR;
		int minute = Calendar.MINUTE;
		int seconde = Calendar.SECOND;
		
		Horloge.create(r, this, heure, minute, seconde);

		go.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setHour();
			}
		});

		/* Bouton home de retour au menu */
		Button home = (Button) findViewById(R.id.home);
		home.setOnClickListener(new NextActivityListener(home,null,
				ClockActivity.this, MainActivity.class));

	}
	
	

	public void setHour() {
		String s = "";

		Horloge.erase(r);
		s = write.getText().toString();
		String[] time = s.split(",");
		int heure = Integer.valueOf(time[0]);
		int minute = Integer.valueOf(time[1]);
		int seconde = Integer.valueOf(time[2]);

		Horloge.create(r, this, heure, minute, seconde);
	}

}
