package com.Atlas.framework;


import java.util.ArrayList;
import java.util.Collections;

import android.animation.AnimatorSet;
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
import android.widget.TextView;
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
	private int pairesOK = 0; // le nb de paires trouvees
	private int x = 0;
	private int nbVictoire = 0;

		
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
		
		// creation des elements du plateau
		final Element[] tab= createPlateau(parent);
	
		//evenements au click
		for(int i=0;i<tab.length;i++){
			final Element e = tab[i];
			tab[i].getZone().setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					action(tab,e);
				}
			});
		}
		
				
		// aide du jeu
		
		RelativeLayout aide = (RelativeLayout) findViewById(R.id.aide);
		final ImageButton tete = createAide(aide);
		tete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(modeAide){/// on est denas le mode aide
					tete.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.tete3));
					//Animate.scale(plateau[numDesordre.get(5)], 1, (float) 1.5, 2000, 20, true);
					modeAide = false;
				} else {
					tete.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.tete));
					//plateau[numDesordre.get(5)].clearAnimation();	
					modeAide = true;
					}
				
			}});
		
	}
	
	/**
	 *  cree un tableau d'elements que l'on positionne sur le plateau
	 *  la place de l'element i du tableau est aleatoire sur le terrain
	 * @param parent
	 * @return
	 */
	public Element [] createPlateau(ViewGroup parent){
		final ArrayList<Integer> numDesordre = numDesordre();
		
		int nbCaches = 6+2*nbVictoire;
		Element [] tab = new Element[nbCaches];
		int height = this.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
		int width = this.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
		for(int i=0;i<nbCaches;i++){
			int place = numDesordre.get(i);
			Element e = new Element(i%2, false, place, this);
			RelativeLayout zone = e.getZone();
			parent.addView(zone);
			zone.setX(width/nbCaches*place);
			zone.setY(height/nbCaches*place);
			Animate.fade_in(zone,1000);
			tab[i]=e;
			
		}
		return tab;
	}
	
	
	
	/**
	 * methode qui definit la regle du jeu a appliquer a chaque case du plateau
	 * @param plateau le plateau de jeu : un ensemble de case (RelativeLayout[])
	 * @param index la case a qui l'on definit l'action
	 * @param c le compteur de bonnes reponses trouvees
	 * @param numDesordre un tableau ou l'on a les indices de toutes les cases melangees
	 *  pour creer un plateau aleatoire
	 */
	public void action(Element[] e,Element element) {
		
		TextView item = element.getItem();
		Animate.fade_in(item, 1000);
		
			for(int i=0;i<e.length;i++) {
				if(!e[i].equals(element) && e[i].getItem().getVisibility()==View.VISIBLE)
					if(e[i].getPair() == element.getPair()) {
						e[i].setTrouve(true);
						element.setTrouve(true);
						pairesOK++;
						//tab[i].setClickable(false);
						//tab[element.getPlace()].setClickable(false);
						mange(element,e[i]);
						
					}
					else {
						
						Animate.fade_out(item, 1000, false);
						Animate.fade_out(e[i].getItem(), 1000, false);
					}
			}
		if(pairesOK==3) victoire();
			
	}
	
		
	public void mange(Element e1,Element e2) {
		
		Animate.fade_out(e1.getImg(), 1000, false);
		Animate.fade_out(e2.getImg(), 1000, false);
		
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

		
		Button again = ButtonCreator.createRoundedButton(this,R.color.vert2);
		RelativeLayout.LayoutParams again_params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

		again_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		again_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		again.setLayoutParams(again_params);
		again.setText("Encore une fois !!");
		Typeface externalFont = Typeface.createFromAsset(this.getAssets(),
				"fonts/intsh.ttf");
		again.setTypeface(externalFont);
		again.setTextSize(40);
		again.setId(12);
		fin.addView(again);
				
		RelativeLayout gnar = new RelativeLayout(this);
		gnar.setClipChildren(false);
		fin.addView(gnar);
		RelativeLayout.LayoutParams gnar_params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		gnar_params.addRule(RelativeLayout.ABOVE,12);
		gnar_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		gnar.setLayoutParams(gnar_params);
		AnimatedGnar.addAnimatedGnar(this, gnar);
		
		
		Animate.pop_in(fin, 1000);
		
		Button pressed = ButtonCreator.createRoundedButton(this,R.color.vert2);
		pressed.setText(again.getText());
		again.setOnClickListener(new NextActivityListener(
				again,pressed.getBackground(),MemoryActivity.this,MenuActivity.class));
		
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
		ImageButton oreille_gauche = ButtonCreator.create(this)
				.setBack(r.getDrawable(R.drawable.oreille)).buildImage();
		ImageButton oreille_droite = ButtonCreator.create(this)
				.setBack(r.getDrawable(R.drawable.oreille)).mirror().buildImage();
		
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
	public ArrayList<Integer> numDesordre(){
		ArrayList<Integer> tab = new ArrayList<Integer>();
		for(int i=0;i<6+2*nbVictoire;i++)
			tab.add(i);
		Collections.shuffle(tab);
		
		return tab;
	}
	
	@Override
	public void onStop(){
		super.onStop();
		mp.stop();
		mp.release();
		/*mpEpic.stop();
		mpEpic.release();
		victoire.stop();
		victoire.release();*/
			
	}
		
	@Override
	public void onResume(){
		super.onResume();
		again = ButtonCreator.createRoundedButton(this,R.color.vert1);
		
	}
	
	
}
