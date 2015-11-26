package composants;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import boutons.Bouton;

import com.Atlas.framework.R;

/**
 * Ajoute une mascotte anime
 */
public class AnimatedGnar {

	static ImageButton tete = null;
	static ImageButton tete_mini = null;

	/**
	 * Ajoute un Gnar anime au RelativeLayout(vide) donne en parametre
	 * 
	 * @param a
	 *            l'activite
	 * @param rl
	 *            le layout dans lequel placer Gnar
	 */
	public static void addGnar(final Activity a, final RelativeLayout rl) {
		final Resources r = a.getResources();
		rl.setClipChildren(false);

		// Creation des parties de gnar
		tete = Bouton.create(a).setBack(r.getDrawable(R.drawable.tete))
				.buildImage();
		final ImageButton oreille_gauche = Bouton.create(a)
				.setBack(r.getDrawable(R.drawable.oreille)).buildImage();
		final ImageButton oreille_droite = Bouton.create(a)
				.setBack(r.getDrawable(R.drawable.oreille)).mirror()
				.buildImage();
		final ImageButton corps = Bouton.create(a)
				.setBack(r.getDrawable(R.drawable.corps)).buildImage();
		final ImageButton bras_gauche = Bouton.create(a)
				.setBack(r.getDrawable(R.drawable.bras)).buildImage();
		final ImageButton bras_droit = Bouton.create(a)
				.setBack(r.getDrawable(R.drawable.bras)).mirror().buildImage();
		final ImageButton jambe_gauche = Bouton.create(a)
				.setBack(r.getDrawable(R.drawable.jambe)).buildImage();
		final ImageButton jambe_droit = Bouton.create(a)
				.setBack(r.getDrawable(R.drawable.jambe)).mirror().buildImage();

		// Tailles des drawable
		final Bitmap bit_tete = ((BitmapDrawable) r
				.getDrawable(R.drawable.tete)).getBitmap();
		final int W_tete = bit_tete.getWidth();
		final int H_tete = bit_tete.getHeight();

		// Placements des parties
		corps.setId(177);
		tete.setId(178);
		final MyLayoutParams params_tete = new MyLayoutParams()
				.centerHorizontal();
		final MyLayoutParams params_oreille_gauche = new MyLayoutParams();
		final MyLayoutParams params_oreille_droite = new MyLayoutParams()
				.marginLeft(W_tete);
		final MyLayoutParams params_corps = new MyLayoutParams()
				.centerHorizontal().below(tete).marginTop(-H_tete / 6);
		final MyLayoutParams params_bras_gauche = new MyLayoutParams()
				.marginTop(14 * H_tete / 15).toLeftOf(corps)
				.marginRight(-W_tete / 11);
		final MyLayoutParams params_bras_droit = new MyLayoutParams()
				.marginTop(14 * H_tete / 15).toRightOf(corps)
				.marginLeft(-W_tete / 11);
		final MyLayoutParams params_jambe_gauche = new MyLayoutParams()
				.marginTop((int) (H_tete * 1.47f)).toLeftOf(corps)
				.marginRight(-W_tete / 5);
		final MyLayoutParams params_jambe_droit = new MyLayoutParams()
				.marginTop((int) (H_tete * 1.47f)).toRightOf(corps)
				.marginLeft(-W_tete / 5);

		// Animation
		final int T = 1000;
		Animer.rotate(oreille_gauche, T, -2, 3, 1f, 1f, true);
		Animer.rotate(oreille_droite, T, 3, -2, 0f, 1f, true);
		Animer.rotate(bras_gauche, T, -3, 3, 1f, 0f, true);
		Animer.rotate(bras_droit, T, 3, -3, 0f, 0f, true);
		Animer.translate(corps, 0, -H_tete / 30, 0, H_tete / 100, T, true);

		// Clics
		final OnClickListener ocl = new View.OnClickListener() {
			int num_tete = 2;

			@Override
			public void onClick(final View v) {
				final int random = (int) Math.floor(Math.random() * 3);
				switch (random) {
				case 0:
					if (num_tete == 0) {
						setBackground(tete, r.getDrawable(R.drawable.tete3));
					} else {
						setBackground(tete, r.getDrawable(R.drawable.tete2));
					}
					num_tete = 0;
					break;
				case 1:
					if (num_tete == 1) {
						setBackground(tete, r.getDrawable(R.drawable.tete));
						blink();
					} else {
						setBackground(tete, r.getDrawable(R.drawable.tete3));
					}
					num_tete = 1;
					break;
				default:
					if (num_tete == 2) {
						setBackground(tete, r.getDrawable(R.drawable.tete2));
					} else {
						setBackground(tete, r.getDrawable(R.drawable.tete));
						blink();
					}
					num_tete = 2;
					break;

				}

			}
		};
		tete.setOnClickListener(ocl);
		oreille_droite.setOnClickListener(ocl);
		oreille_gauche.setOnClickListener(ocl);
		jambe_droit.setOnClickListener(ocl);
		jambe_gauche.setOnClickListener(ocl);
		corps.setOnClickListener(ocl);
		bras_droit.setOnClickListener(ocl);
		bras_gauche.setOnClickListener(ocl);

		// clignement d'yeux
		blink();

		// Ajout des elements au layout
		rl.addView(bras_gauche, params_bras_gauche);
		rl.addView(bras_droit, params_bras_droit);
		rl.addView(oreille_gauche, params_oreille_gauche);
		rl.addView(oreille_droite, params_oreille_droite);
		rl.addView(jambe_droit, params_jambe_droit);
		rl.addView(jambe_gauche, params_jambe_gauche);
		rl.addView(corps, params_corps);
		rl.addView(tete, params_tete);

	}

	/**
	 * sets the background of a view depending on the API
	 * 
	 * @param v
	 * @param d
	 */
	@SuppressWarnings("deprecation")
	private static void setBackground(final View v, final Drawable d) {
		if (Build.VERSION.SDK_INT >= 16) {
			v.setBackground(d);
		} else {
			v.setBackgroundDrawable(d);
		}
	}

	public static void blink() {
		tete.setBackgroundResource(R.drawable.blink);
		final AnimationDrawable frameAnimation = (AnimationDrawable) tete
				.getBackground();
		frameAnimation.start();
	}

	public static void blink_mini() {
		tete_mini.setBackgroundResource(R.drawable.blink_mini);
		final AnimationDrawable frameAnimation = (AnimationDrawable) tete_mini
				.getBackground();
		frameAnimation.start();
	}

	/**
	 * Ajoute un bebe Gnar anime au RelativeLayout(vide) donne en parametre
	 * 
	 * @param a
	 *            l'activite
	 * @param rl
	 *            le layout dans lequel placer Gnar
	 */
	public static void addMiniGnar(final Activity a, final RelativeLayout rl) {
		final Resources r = a.getResources();
		rl.setClipChildren(false);

		// Creation des parties de gnar
		final ImageButton tete = Bouton.create(a)
				.setBack(r.getDrawable(R.drawable.mini_tete)).buildImage();
		tete_mini = tete;
		final ImageButton oreille_gauche = Bouton.create(a)
				.setBack(r.getDrawable(R.drawable.mini_oreille)).buildImage();
		final ImageButton oreille_droite = Bouton.create(a)
				.setBack(r.getDrawable(R.drawable.mini_oreille)).mirror()
				.buildImage();
		final ImageButton corps = Bouton.create(a)
				.setBack(r.getDrawable(R.drawable.mini_corps)).buildImage();
		final ImageButton bras_gauche = Bouton.create(a)
				.setBack(r.getDrawable(R.drawable.mini_bras)).buildImage();
		final ImageButton bras_droit = Bouton.create(a)
				.setBack(r.getDrawable(R.drawable.mini_bras)).mirror()
				.buildImage();
		final ImageButton jambe_gauche = Bouton.create(a)
				.setBack(r.getDrawable(R.drawable.mini_jambe)).buildImage();
		final ImageButton jambe_droit = Bouton.create(a)
				.setBack(r.getDrawable(R.drawable.mini_jambe)).mirror()
				.buildImage();

		// Tailles des drawable
		final Bitmap bit_tete = ((BitmapDrawable) r
				.getDrawable(R.drawable.mini_tete)).getBitmap();
		final int W_tete = bit_tete.getWidth();
		final int H_tete = bit_tete.getHeight();

		// Placements des parties
		corps.setId(179);
		tete.setId(180);
		final MyLayoutParams params_tete = new MyLayoutParams()
				.centerHorizontal();
		final MyLayoutParams params_oreille_gauche = new MyLayoutParams()
				.marginTop(-H_tete / 10);
		final MyLayoutParams params_oreille_droite = new MyLayoutParams()
				.marginLeft(W_tete).marginTop(-H_tete / 10);
		final MyLayoutParams params_corps = new MyLayoutParams()
				.centerHorizontal().below(tete).marginTop(-H_tete / 6);
		final MyLayoutParams params_bras_gauche = new MyLayoutParams()
				.marginTop(14 * H_tete / 15).toLeftOf(corps)
				.marginRight(-W_tete / 15);
		final MyLayoutParams params_bras_droit = new MyLayoutParams()
				.marginTop(14 * H_tete / 15).toRightOf(corps)
				.marginLeft(-W_tete / 15);
		final MyLayoutParams params_jambe_gauche = new MyLayoutParams()
				.marginTop((int) (H_tete * 1.22f)).toLeftOf(corps)
				.marginRight(-W_tete / 7);
		final MyLayoutParams params_jambe_droit = new MyLayoutParams()
				.marginTop((int) (H_tete * 1.22f)).toRightOf(corps)
				.marginLeft(-W_tete / 7);

		// Animation
		final int T = 1000;
		Animer.rotate(oreille_gauche, T, -2, 3, 1f, 1f, true);
		Animer.rotate(oreille_droite, T, 3, -2, 0f, 1f, true);
		Animer.rotate(bras_gauche, T, -3, 3, 1f, 0f, true);
		Animer.rotate(bras_droit, T, 3, -3, 0f, 0f, true);
		Animer.translate(corps, 0, -H_tete / 30, 0, H_tete / 100, T, true);

		// Clics

		final OnClickListener ocl = new View.OnClickListener() {
			int num_tete = 2;

			@Override
			public void onClick(final View v) {
				final int random = (int) Math.floor(Math.random() * 3);
				switch (random) {
				case 0:
					if (num_tete == 0) {
						setBackground(tete,
								r.getDrawable(R.drawable.mini_tete_3));
					} else {
						setBackground(tete,
								r.getDrawable(R.drawable.mini_tete_2));
					}
					num_tete = 0;
					break;
				case 1:
					if (num_tete == 1) {
						setBackground(tete, r.getDrawable(R.drawable.mini_tete));
					} else {
						setBackground(tete,
								r.getDrawable(R.drawable.mini_tete_3));
					}
					num_tete = 1;
					break;
				default:
					if (num_tete == 2) {
						setBackground(tete,
								r.getDrawable(R.drawable.mini_tete_2));
					} else {
						setBackground(tete, r.getDrawable(R.drawable.mini_tete));
					}
					num_tete = 2;
					break;

				}

			}
		};

		tete.setOnClickListener(ocl);
		oreille_droite.setOnClickListener(ocl);
		oreille_gauche.setOnClickListener(ocl);
		jambe_droit.setOnClickListener(ocl);
		jambe_gauche.setOnClickListener(ocl);
		corps.setOnClickListener(ocl);
		bras_droit.setOnClickListener(ocl);
		bras_gauche.setOnClickListener(ocl);

		// clignement d'yeux
		blink_mini();

		// Ajout des elements au layout
		rl.addView(bras_gauche, params_bras_gauche);
		rl.addView(bras_droit, params_bras_droit);
		rl.addView(oreille_gauche, params_oreille_gauche);
		rl.addView(oreille_droite, params_oreille_droite);
		rl.addView(jambe_droit, params_jambe_droit);
		rl.addView(jambe_gauche, params_jambe_gauche);
		rl.addView(corps, params_corps);
		rl.addView(tete, params_tete);

	}

}
