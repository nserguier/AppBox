package com.Atlas.framework;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import boutons.ButtonCreator;
import boutons.NextActivityListener;
import custom.MenuJungleH;
import custom.MenuJungleV;
import custom.MenuOceanH;


public class MenuActivity extends Activity {

	private Button jouer;
	private MediaPlayer mp;
	private boolean sound=true;
	
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
		
		
				
		final ViewGroup parent = (ViewGroup) findViewById(R.id.parent);
		MenuOceanH m = new MenuOceanH(this);
		m.createMenu(parent);
		m.rassembler(1,2);
		m.rassembler(3,4);
		m.addTitre("AQUA GNAR !!");
		jouer = m.addButton("Jouer", 1,R.color.bleu1);
		m.addButton("Tête de GNAR !", 3,R.color.bleu2);
		m.addButton("Aide de GNAR ?", 5,R.color.bleu1);
		m.addButton("GNARGOUILLA !!!", 6,R.color.bleu1);
		
		mp = MediaPlayer.create(MenuActivity.this,R.raw.habla_con_hella);
		mp.setLooping(true);
		mp.start();
		
		// un bouton pour stopper le son
				final Button audio = (Button) findViewById(R.id.audio);
				audio.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						if(sound){
							audio.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.sound_e));
							if(mp.isPlaying()) mp.pause();
							
						}else {
							audio.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.sound));
							mp.start();
						}
						sound=!sound;
						
					}
				});
		
		Button pressed = ButtonCreator.createRoundedButton(this,R.color.vert3);
		pressed.setText(jouer.getText());
				
		//jouer.setOnClickListener(new NextActivityListener(jouer,pressed.getBackground(),MenuActivity.this,MemoryActivity.class));
		
		
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		jouer = ButtonCreator.createRoundedButton(this,R.color.vert1);
		
		
	}
	
	@Override
	public void onStop(){
		super.onPause();
		mp.stop();
		mp.release();
	}
	
}


