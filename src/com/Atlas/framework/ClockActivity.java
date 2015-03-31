package com.Atlas.framework;

import horloge.Clock;

import java.util.Calendar;
import java.util.GregorianCalendar;

import boutons.HomeActivityListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ClockActivity extends Activity {

	ImageView heures = null;
	ImageView minutes = null;
	ImageView secondes = null;
	ImageView horloge = null;
	EditText write = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clock);

		
		RelativeLayout r = (RelativeLayout) findViewById(R.id.analogClock);
		write = (EditText)findViewById(R.id.champ);

		String s = recupereHeure(write);
		String[] time = s.split(","); 
		int heure = Integer.valueOf(time[0]);
		int minute = Integer.valueOf(time[1]);
		int seconde = Integer.valueOf(time[2]);
		
		
		r = Clock.create(r, this, heure, minute, seconde);

		/* Bouton home de retour au menu */
		Button home = (Button) findViewById(R.id.home);
		home.setOnClickListener(new HomeActivityListener(this, home,
				ClockActivity.this, MainActivity.class));

	}
	
	public String recupereHeure(EditText write) {
		String  s = write.getText().toString();
		return s;
	}
	

}
