package com.Atlas.framework;


import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import boutons.ButtonCreator;
import boutons.NextActivityListener;

import composants.Animate;
import composants.AnimatedGnar;
import composants.MyLayoutParams;


public class MemoryActivity extends Activity {

	private boolean modeAide = true;
	private MediaPlayer mpEpic;
	private MediaPlayer mp;
	private MediaPlayer victoire;
	private ViewGroup parent;
	private boolean sound=true;
	private Button again;

		
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
		
		setContentView(R.layout.activity_memory);
		
		parent = (ViewGroup) findViewById(R.id.parent);
		parent.setBackground(this.getResources().getDrawable(R.drawable.jungle_h));
		parent.setClipChildren(false);
		
		mp = MediaPlayer.create(MemoryActivity.this,R.raw.music);
		mpEpic = MediaPlayer.create(MemoryActivity.this,R.raw.duel);
		victoire = MediaPlayer.create(this,R.raw.mountains);
		mp.seekTo(10000);
		mp.start();
		
		// un bouton pour stopper le son
		final Button audio = (Button) findViewById(R.id.audio);
		audio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(sound){
					audio.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.sound_e));
					if(mp.isPlaying()) mp.pause();
					if(mpEpic.isPlaying())mpEpic.pause();
					if(victoire.isPlaying())victoire.pause();
				}else {
					audio.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.sound));
					mp.start();
					
				}
				sound=!sound;
				
			}
		});
		
		
		
		final Compteur c = new Compteur();
		
		//	les differentes cases du plateau
		final RelativeLayout case1 = (RelativeLayout) findViewById(R.id.case1);
		final RelativeLayout case2 = (RelativeLayout) findViewById(R.id.case2);
		final RelativeLayout case3 = (RelativeLayout) findViewById(R.id.case3);
		final RelativeLayout case4 = (RelativeLayout) findViewById(R.id.case4);
		final RelativeLayout case5 = (RelativeLayout) findViewById(R.id.case5);
		final RelativeLayout case6 = (RelativeLayout) findViewById(R.id.case6);
		final RelativeLayout[] plateau = {case1,case2,case3,case4,case5,case6};
		
		//	apparition des buissons
		Button buisson1 = (Button) findViewById(R.id.buisson1);
		Animate.fade_in(buisson1,1000);
		Button buisson2 = (Button) findViewById(R.id.buisson2);
		Animate.fade_in(buisson2,1000);
		Button buisson3 = (Button) findViewById(R.id.buisson3);
		Animate.fade_in(buisson3,1000);
		Button buisson4 = (Button) findViewById(R.id.buisson4);
		Animate.fade_in(buisson4,1000);
		Button buisson5 = (Button) findViewById(R.id.buisson5);
		Animate.fade_in(buisson5,1000);
		Button buisson6 = (Button) findViewById(R.id.buisson6);
		Animate.fade_in(buisson6,1000);
			
		//	les mechant et les gentils gnar
		RelativeLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
				
		
		final ImageView mechant1 = new ImageView(this);
		mechant1.setLayoutParams(params);
		mechant1.setBackground(this.getResources().getDrawable(R.drawable.mechant));
		mechant1.setVisibility(View.INVISIBLE);
		final ImageView mechant2 = new ImageView(this);
		mechant2.setLayoutParams(params);
		mechant2.setBackground(this.getResources().getDrawable(R.drawable.mechant));
		mechant2.setVisibility(View.INVISIBLE);
		final ImageView mechant3 = new ImageView(this);
		mechant3.setLayoutParams(params);
		mechant3.setBackground(this.getResources().getDrawable(R.drawable.mechant));
		mechant3.setVisibility(View.INVISIBLE);
		final ImageView mechant4 = new ImageView(this);
		mechant4.setLayoutParams(params);
		mechant4.setBackground(this.getResources().getDrawable(R.drawable.mechant));
		mechant4.setVisibility(View.INVISIBLE);
		final ImageView gnarPetit = new ImageView(this);
		gnarPetit.setLayoutParams(params);
		gnarPetit.setBackground(this.getResources().getDrawable(R.drawable.fruit));
		gnarPetit.setVisibility(View.INVISIBLE);
		final ImageView gnar = new ImageView(this);
		gnar.setLayoutParams(params);
		gnar.setBackground(this.getResources().getDrawable(R.drawable.fruit));
		gnar.setVisibility(View.INVISIBLE);

		
		//	on attribue les mechants/gnar au differents buissons
		final ArrayList<Integer> emplacement = emplacement();
		plateau[emplacement.get(0)].addView(mechant1,1);
		plateau[emplacement.get(1)].addView(mechant2,1);
		plateau[emplacement.get(2)].addView(mechant3,1);
		plateau[emplacement.get(3)].addView(mechant4,1);
		plateau[emplacement.get(4)].addView(gnarPetit,1);
		plateau[emplacement.get(5)].addView(gnar,1);
		
		
				
		//	les evenements au click
		
		buisson1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				action(plateau,1,c,emplacement);
					
			}});
		
		buisson2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				action(plateau,2,c,emplacement);
				
			}});
		
		buisson3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				action(plateau,3,c,emplacement);
					
			}});
		
		buisson4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				action(plateau,4,c,emplacement);
				
			}});
		buisson5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				action(plateau,5,c,emplacement);
					
			}});
		
		buisson6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				action(plateau,6,c,emplacement);
			}});
				
		// aide du jeu
		
		RelativeLayout aide = (RelativeLayout) findViewById(R.id.aide);
		final ImageButton tete = createAide(aide);
		tete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(modeAide){/// on est denas le mode aide
					tete.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.tete3));
					Animate.scale(plateau[emplacement.get(5)], 1, (float) 1.5, 2000, 20, true);
					modeAide = false;
				} else {
					tete.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.tete));
					plateau[emplacement.get(5)].clearAnimation();	
					modeAide = true;
					}
				
			}});
		
	}
	
	/**
	 * methode qui definit la regle du jeu a appliquer a chaque case du plateau
	 * @param plateau le plateau de jeu : un ensemble de case (RelativeLayout[])
	 * @param index la case a qui l'on definit l'action
	 * @param c le compteur de bonnes reponses trouvees
	 * @param emplacement un tableau ou l'on a les indices de toutes les cases melangees
	 *  pour creer un plateau aleatoire
	 */
	public void action(RelativeLayout[] plateau, int index, Compteur c,ArrayList<Integer> emplacement) {
		index--;
		RelativeLayout rl = plateau[index];
		Animate.fade_in(rl.getChildAt(1), 1000);
		
		if(emplacement.get(5)==index){ // c'est un gentil
			c.setB1(true);
			mpEpic.seekTo(0);
			mpEpic.start();
			mp.pause();
			if(c.getB1()&&c.getB2()) { // c'est gagne !!
				
				victoire();
			}
			
		} else 
			if(emplacement.get(4)==index){ // c'est un gentil
				c.setB2(true);
				mpEpic.seekTo(0);
				mpEpic.start();
				mp.pause();
				if(c.getB1()&&c.getB2()) { // c'est gagne !!
					
					victoire();
				}
				
	   
		}else { // c'est un mechant
			c.reinitialize();
			for(int i=0;i<6;i++){
				View v = plateau[i].getChildAt(1);
				if(v.getVisibility()==View.VISIBLE)
				Animate.fade_out(v, 1000, false);
				if(mpEpic.isPlaying()){
					mpEpic.pause();
					mp.start();
				}
			}
			
		}
	}
	
	/**
	 * methode qui defint l'action a faire quand toutes les bonnes reponses ont ete trouvees
	 */
	public void victoire(){
		parent.removeView(findViewById(R.id.aide));
		mp.stop();
		mpEpic.stop();
		victoire.start();
		int width = this.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
		
		RelativeLayout fin = new RelativeLayout(this);
		parent.addView(fin);
		RelativeLayout.LayoutParams params = new LayoutParams(width/3,LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		//params.addRule(RelativeLayout.CENTER_VERTICAL);
		fin.setLayoutParams(params);
				
			
		again = ButtonCreator.createRoundedButton(this, R.color.vert1);
		fin.addView(again);
		RelativeLayout.LayoutParams again_params = new LayoutParams(LayoutParams.MATCH_PARENT,width/10);
		again_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		again_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		again.setLayoutParams(again_params);
		again.setText("Encore une fois !!");
		Typeface externalFont = Typeface.createFromAsset(this.getAssets(),
				"fonts/intsh.ttf");
		again.setTypeface(externalFont);
		again.setTextSize(40);
		again.setId(12);
		
				
		RelativeLayout gnar = new RelativeLayout(this);
		fin.addView(gnar);
		RelativeLayout.LayoutParams gnar_params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		gnar_params.addRule(RelativeLayout.ABOVE,12);
		gnar_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		gnar.setLayoutParams(gnar_params);
		AnimatedGnar.addAnimatedGnar(this, gnar);
		
		
		Animate.pop_in(fin, 1000);
		
		Button pressed = ButtonCreator.createRoundedButton(this,R.color.vert2);
		pressed.setText(again.getText());
		again.setOnClickListener(new NextActivityListener(again,pressed.getBackground(),MemoryActivity.this,MenuActivity.class));
		
	}
	
	/**
	 * permet de creer un bouton d'aide et de definir son action sur le jeu
	 * @param rl le layout qui contient ce bouton
	 * @return
	 */
	public ImageButton createAide(RelativeLayout rl){
		final Resources r = this.getResources();
		rl.setClipChildren(false);
		
		// Creation des parties de gnar
		ImageButton tete = ButtonCreator.create(this).setBack(r.getDrawable(R.drawable.tete)).buildImage();
		ImageButton oreille_gauche = ButtonCreator.create(this).setBack(r.getDrawable(R.drawable.oreille)).buildImage();
		ImageButton oreille_droite = ButtonCreator.create(this).setBack(r.getDrawable(R.drawable.oreille)).mirror().buildImage();
		
		// Tailles des drawable
		Bitmap bit_tete = ((BitmapDrawable) r.getDrawable(R.drawable.tete)).getBitmap();
		int W_tete = bit_tete.getWidth();
				
		//Placements des parties
		tete.setId(178);
		MyLayoutParams params_tete = new MyLayoutParams().centerHorizontal();
		MyLayoutParams params_oreille_gauche = new MyLayoutParams();
		MyLayoutParams params_oreille_droite = new MyLayoutParams().marginLeft(W_tete);
		
		// Animation
		int T = 1000;
		Animate.rotate(oreille_gauche, T,-2, 3,1f,1f, true);
		Animate.rotate(oreille_droite, T,3,-2,0f,1f, true);
		
		rl.addView(oreille_gauche,params_oreille_gauche);
		rl.addView(oreille_droite,params_oreille_droite);
		rl.addView(tete,params_tete);
		return tete;
				
	}
	
	/**
	 * methode qui permet de melanger les indices d'un 
	 * tableau ou d'une liste pour generer un plateau aleatoire par la suite
	 * @return une liste d'indice dans un ordre aleatoire (indices entre 1 et 6)
	 */
	public ArrayList<Integer> emplacement(){
		ArrayList<Integer> tab = new ArrayList<Integer>();
		for(int i=0;i<6;i++)
			tab.add(i);
		Collections.shuffle(tab);
		
		return tab;
	}
	
	@Override
	public void onStop(){
		super.onStop();
		mp.stop();
		mp.release();
		mpEpic.stop();
		mpEpic.release();
		victoire.stop();
		victoire.release();
			
	}
		
	@Override
	public void onResume(){
		super.onResume();
		again = ButtonCreator.createRoundedButton(this,R.color.vert1);
		
	}
	
	
}
