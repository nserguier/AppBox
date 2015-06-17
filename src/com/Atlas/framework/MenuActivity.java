package com.Atlas.framework;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import boutons.Bouton;

import composants.Animer;

import custom.FabriqueMenu;
import custom.Menu;
import custom.TypeMenu;


public class MenuActivity extends Activity {

	private Button jouer;
	private Button decor;
	private MediaPlayer mp;
	private boolean sound = true;
	private TypeMenu menuDepart = TypeMenu.OceanHorizontal;
	Menu m;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* Passage en plein ecran */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		View decorView = getWindow().getDecorView();
		decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_FULLSCREEN
				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

		setContentView(R.layout.activity_menu_style);

		// recuperation du type de menu
		Intent i = getIntent();
		TypeMenu s =  (TypeMenu) i.getSerializableExtra("extra");
		if(s!=null) menuDepart = s;
		
		try {
			m = FabriqueMenu.create(menuDepart,this);
		} catch (IllegalArgumentException | InstantiationException
				| IllegalAccessException e) {
			
			e.printStackTrace();
		}
		final ViewGroup parent = (ViewGroup) findViewById(R.id.parent);
		m.createMenu(parent,menuDepart);
		m.rassembler(1, 2);
		m.rassembler(3, 4);
		m.addTitre("MEMORY GNAR !!");
		jouer = m.addButton("Jouer", 1);
		decor = m.addButton("Autre decor !", 3);
		m.addButton("Aide de GNAR ?", 5);
		m.addButton("tes scores !!!", 6);

		mp = MediaPlayer.create(MenuActivity.this, R.raw.music);
		if(menuDepart.equals(TypeMenu.OceanHorizontal)) mp = MediaPlayer.create(MenuActivity.this, R.raw.habla_con_hella);
		mp.setLooping(true);
		mp.start();

		// un bouton pour stopper le son
		final Button audio = (Button) findViewById(R.id.audio);
		audio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (sound) {
					audio.setBackground(getApplicationContext().getResources()
							.getDrawable(R.drawable.sound_e));
					if (mp.isPlaying())
						mp.pause();

				} else {
					audio.setBackground(getApplicationContext().getResources()
							.getDrawable(R.drawable.sound));
					mp.start();
				}
				sound = !sound;

			}
		});

		// passage a l'autre activite
		jouer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				TypeMenu menu = TypeMenu.JungleHorizontal;
				if(menuDepart.equals(TypeMenu.OceanHorizontal)) menu = TypeMenu.OceanHorizontal;
				mp.stop();
				Animer.changeActivityAnimation(parent, MemoryActivity.class,menu);
			}
		});
	
		

		decor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
								
				TypeMenu menu = TypeMenu.JungleHorizontal;
				if(menuDepart.equals(TypeMenu.JungleHorizontal)) menu = TypeMenu.OceanHorizontal;	
				Animer.changeActivityAnimation(parent, MenuActivity.class,menu);

			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		jouer = Bouton.createRoundedButton(this, R.color.vert1);
		mp.start();

	}

	@Override
	public void onStop() {
		super.onPause();
		mp.stop();
		mp.release();
	}

}
