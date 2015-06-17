package com.Atlas.framework;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import boutons.Bouton;
import boutons.NextActivityListener;
import boutons.Bouton;
import boutons.HomeActivityListener;

import composants.Animer;
import composants.AnimatedGnar;
import composants.MyLayoutParams;

import custom.TypeMenu;

public class MemoryActivity extends Activity {

	private boolean modeAide = true;
	private MediaPlayer mp;
	private RelativeLayout parent;
	private boolean sound = true;
	private Button again;
	private int pairesOK = 0; // le nb de paires trouvees
	private int nbVictoire = 0;
	private TypeMenu menu;
	private int nbCoups = 0;

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

		// apparition logo puis memory
		// Ecran logo

		final ImageView logo = (ImageView) findViewById(R.id.logo_image);
		logo.setVisibility(View.VISIBLE);
		AlphaAnimation alpha1 = new AlphaAnimation(0, 1);
		alpha1.setDuration(100);
		alpha1.setFillAfter(true);
		alpha1.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				AlphaAnimation alpha2 = new AlphaAnimation(1, 0);
				alpha2.setDuration(200);
				alpha2.setFillAfter(true);
				alpha2.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
					}

					@Override
					public void onAnimationEnd(Animation animation) {
						/* Apparition de l'activité */
						int H = getApplicationContext().getResources()
								.getDisplayMetrics().heightPixels;
						Animer.translateDecelerate(parent, 0, H, 0, 0,
								2000);

					}
				});
				logo.startAnimation(alpha2);

			}
		});
		logo.startAnimation(alpha1);

		// recuperation du type de menu
		Intent intent = getIntent();
		menu = (TypeMenu) intent.getSerializableExtra("memory");

		Intent intentVictoires = getIntent();
		if (intentVictoires.getSerializableExtra("victoire") != null)
			nbVictoire = nbVictoire
					+ (int) intentVictoires.getIntExtra("victoire", 0);

		parent = (RelativeLayout) findViewById(R.id.parent);
		parent.setBackground(this.getResources().getDrawable(
				menu.getBackground()));

		parent.setClipChildren(false);

		mp = MediaPlayer.create(MemoryActivity.this, R.raw.habla_con_hella);
		mp.seekTo(10000);
		mp.setLooping(true);
		mp.start();

		if (menu.equals(TypeMenu.JungleHorizontal)) {
			mp = MediaPlayer.create(MemoryActivity.this, R.raw.music);

		}

		// un bouton pour revenir au menu principal
		final Button home = (Button) findViewById(R.id.home);
		HomeActivityListener listenner = new HomeActivityListener(
				getApplicationContext(), home, MemoryActivity.this,
				MenuActivity.class);
		home.setOnClickListener(listenner);

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

		// creation des elements du plateau
		final ArrayList<Element> tab = createPlateau(parent);

		// evenements au click
		for (int i = 0; i < tab.size(); i++) {
			final Element e = tab.get(i);
			tab.get(i).getZone().setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					action(tab, e);
				}
			});
		}

		// aide du jeu

		RelativeLayout aide = (RelativeLayout) findViewById(R.id.aide);
		final ImageButton tete = createAide(aide);
		tete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (modeAide) {// / on est denas le mode aide
					tete.setBackground(getApplicationContext().getResources()
							.getDrawable(R.drawable.tete3));
					// Animer.scale(plateau[numDesordre.get(5)], 1, (float)
					// 1.5, 2000, 20, true);
					modeAide = false;
				} else {
					tete.setBackground(getApplicationContext().getResources()
							.getDrawable(R.drawable.tete));
					// plateau[numDesordre.get(5)].clearAnimation();
					modeAide = true;
				}

			}
		});

	}

	/**
	 * cree un tableau d'elements que l'on positionne sur le plateau la place de
	 * l'element i du tableau est aleatoire sur le terrain
	 * 
	 * @param parent
	 * @return
	 */
	public ArrayList<Element> createPlateau(ViewGroup parent) {
		
		int cache = 0;
		switch(menu){
		case OceanHorizontal: cache = R.drawable.une_bulle;
		case JungleHorizontal: cache = R.drawable.buisson;
		}
		
		final ArrayList<Integer> numDesordre = numDesordre();
		final ArrayList<Integer> zoneDesordre = numDesordre();

		int nbCaches = 6 + 2 * nbVictoire;
		ArrayList<Element> tab = new ArrayList<Element>();
		int height = this.getApplicationContext().getResources()
				.getDisplayMetrics().heightPixels;
		int width = this.getApplicationContext().getResources()
				.getDisplayMetrics().widthPixels;
		for (int i = 0; i < nbCaches; i++) {

			Element e = new Element(i / 2,cache, this);
			RelativeLayout zone = e.getZone();
			parent.addView(zone);
			zone.setX(width / nbCaches * numDesordre.get(i));
			zone.setY(height / nbCaches * zoneDesordre.get(i));
			Animer.fade_in(zone, 1000);
			tab.add(e);

		}
		return tab;
	}

	/**
	 * methode qui definit la regle du jeu a appliquer a chaque case du plateau
	 * 
	 * @param plateau
	 *            le plateau de jeu : un ensemble de case (RelativeLayout[])
	 * @param index
	 *            la case a qui l'on definit l'action
	 * @param c
	 *            le compteur de bonnes reponses trouvees
	 * @param numDesordre
	 *            un tableau ou l'on a les indices de toutes les cases melangees
	 *            pour creer un plateau aleatoire
	 */
	public void action(ArrayList<Element> e, Element element) {

		TextView item = element.getItem();
		Animer.fade_in(item, 1000);
		element.setVisible(true);
		for (int i = 0; i < e.size(); i++) {
			Element ei = e.get(i);
			if (!ei.equals(element) && ei.isVisible())
				if (ei.getPair() == element.getPair()) {
					pairesOK++;
					e.remove(ei);
					ei.setVisible(false);
					element.setVisible(false);
					e.remove(e);
					mange(element, ei);
					nbCoups++;
				} else {

					Animer.fade_out(item, 1000, false);
					ei.setVisible(false);
					element.setVisible(false);
					Animer.fade_out(ei.getItem(), 1000, false);
					nbCoups++;
				}
		}
		if (pairesOK == 3 + nbVictoire)
			victoire();
	}

	public void mange(Element e1, Element e2) {

		Animer.fade_out(e1.getItem(), 1000, true);
		Animer.fade_out(e2.getItem(), 1000, true);
		Animer.fade_out(e1.getZone(), 1000, true);
		Animer.fade_out(e2.getZone(), 1000, true);

	}

	/**
	 * methode qui defint l'action a faire quand toutes les bonnes reponses ont
	 * ete trouvees
	 */
	public void victoire() {

		// on affiche le nb de coups qu'il a fallu à l'enfent pour reussir le
		// niveau
		TextView coup = new TextView(this);
		coup.setText("Tu as reussi en " + nbCoups
				+ " coups !\n    FELICITATION !");
		coup.setTextColor(this.getResources().getColor(R.color.orange6));
		coup.setTextSize(50f);
		Typeface externalFont = Typeface.createFromAsset(this.getAssets(),
				"fonts/intsh.ttf");
		coup.setTypeface(externalFont);
		coup.setTextAlignment(Gravity.CENTER);
		RelativeLayout.LayoutParams coup_params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		coup_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		coup_params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		coup_params.setMargins(0, 50, 0, 0);
		coup.setLayoutParams(coup_params);
		parent.addView(coup);

		parent.removeView(findViewById(R.id.aide));
		int width = this.getApplicationContext().getResources()
				.getDisplayMetrics().widthPixels;

		RelativeLayout fin = new RelativeLayout(this);
		parent.addView(fin);
		RelativeLayout.LayoutParams params = new LayoutParams(width / 3,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		// params.addRule(RelativeLayout.CENTER_VERTICAL);
		fin.setLayoutParams(params);

		Button again = Bouton.createRoundedButton(this, R.color.vert2);
		RelativeLayout.LayoutParams again_params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		again_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		again_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		again.setLayoutParams(again_params);
		again.setText("Niveau suivant !!");
		again.setTypeface(externalFont);
		again.setTextSize(40);
		again.setId(12);
		fin.addView(again);

		RelativeLayout gnar = new RelativeLayout(this);
		gnar.setClipChildren(false);
		fin.addView(gnar);
		RelativeLayout.LayoutParams gnar_params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		gnar_params.addRule(RelativeLayout.ABOVE, 12);
		gnar_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		gnar.setLayoutParams(gnar_params);
		AnimatedGnar.addGnar(this, gnar);

		Animer.pop_in(fin, 1000);

		Button pressed = Bouton.createRoundedButton(this, R.color.vert2);
		pressed.setText(again.getText());
		again.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Animer.changeActivityAnimation(parent, MemoryActivity.class,
						menu);
			}
		});
	}

	/**
	 * permet de creer un bouton d'aide et de definir son action sur le jeu
	 * 
	 * @param rl
	 *            le layout qui contient ce bouton
	 * @return
	 */
	public ImageButton createAide(RelativeLayout rl) {
		final Resources r = this.getResources();
		rl.setClipChildren(false);

		// Creation des parties de gnar
		ImageButton tete = Bouton.create(this)
				.setBack(r.getDrawable(R.drawable.tete)).buildImage();
		ImageButton oreille_gauche = Bouton.create(this)
				.setBack(r.getDrawable(R.drawable.oreille)).buildImage();
		ImageButton oreille_droite = Bouton.create(this)
				.setBack(r.getDrawable(R.drawable.oreille)).mirror()
				.buildImage();
		
		// Tailles des drawable
		Bitmap bit_tete = ((BitmapDrawable) r.getDrawable(R.drawable.tete))
				.getBitmap();
		int W_tete = bit_tete.getWidth();

		// Placements des parties
		tete.setId(178);
		MyLayoutParams params_tete = new MyLayoutParams().centerHorizontal();
		MyLayoutParams params_oreille_gauche = new MyLayoutParams();
		MyLayoutParams params_oreille_droite = new MyLayoutParams()
				.marginLeft(W_tete);

		// Animation
		int T = 1000;
		Animer.rotate(oreille_gauche, T, -2, 3, 1f, 1f, true);
		Animer.rotate(oreille_droite, T, 3, -2, 0f, 1f, true);

		rl.addView(oreille_gauche, params_oreille_gauche);
		rl.addView(oreille_droite, params_oreille_droite);
		rl.addView(tete, params_tete);
		return tete;

	}

	/**
	 * methode qui permet de melanger les indices d'un tableau ou d'une liste
	 * pour generer un plateau aleatoire par la suite
	 * 
	 * @return une liste d'indice dans un ordre aleatoire (indices entre 1 et 6)
	 */
	public ArrayList<Integer> numDesordre() {
		ArrayList<Integer> tab = new ArrayList<Integer>();
		for (int i = 0; i < 6 + 2 * nbVictoire; i++)
			tab.add(i);
		Collections.shuffle(tab);

		return tab;
	}

	@Override
	public void onStop() {
		super.onStop();
		mp.stop();
		mp.release();

	}

	@Override
	public void onResume() {
		super.onResume();
		again = Bouton.createRoundedButton(this,R.color.vert1);
	}

}
