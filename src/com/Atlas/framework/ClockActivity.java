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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ClockActivity extends Activity {

	ImageView heures = null;
	ImageView minutes = null;
	ImageView secondes = null;
	ImageView horloge = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clock);

		
		RelativeLayout r = (RelativeLayout) findViewById(R.id.analogClock);

		r = Clock.create(r, this, 17, 34, 45);

		/* Bouton home de retour au menu */
		Button home = (Button) findViewById(R.id.home);
		home.setOnClickListener(new HomeActivityListener(this, home,
				ClockActivity.this, MainActivity.class));

	}

}
