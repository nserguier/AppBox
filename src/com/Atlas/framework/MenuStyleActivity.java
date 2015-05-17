package com.Atlas.framework;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import boutons.NextActivityListener;
import custom.MenuStyle;


public class MenuStyleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_style);
		
		final ViewGroup parent = (ViewGroup) findViewById(R.id.parent);
		MenuStyle m = new MenuStyle(this);
		m.createMenu(parent);
		m.rassembler(1,2);
		m.rassembler(3,4);
		m.addTitre("MEMORY GNAR !!");
		Button jouer = m.addButton("Jouer", 1,R.color.vert1);
		m.addButton("Tête de GNAR !", 3,R.color.vert2);
		m.addButton("Aide de GNAR ?", 5,R.color.vert1);
		Button music = m.addButton("GNARGOUILLA !!!", 6,R.color.vert1);
		
		final MediaPlayer mp = MediaPlayer.create(MenuStyleActivity.this,R.raw.music);
		mp.setLooping(true);
		music.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if (mp.isPlaying()) {
					 mp.pause();
                } else {
                    mp.start();
                }
						
			}
		});
					
		jouer.setOnClickListener(new NextActivityListener(this,jouer,MenuStyleActivity.this,MemoryActivity.class));
		
		
	}
	
}


