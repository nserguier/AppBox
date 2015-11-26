package composants;

import java.io.Serializable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.Atlas.framework.R;

/**
 * Composant permettant d'animer des vues avec une collection d'animation
 * parametrables
 */
public class Animer {

	/**
	 * Anime une vue en la faisant grossir/retrecir
	 * 
	 * @param view
	 *            La vue a animer
	 * @param from
	 *            Le facteur d'echelle a prende au debut d'animation
	 * @param to
	 *            Le facteur d'echelle a prendre en fin d'animation
	 * @param duration
	 *            La duree d'un cycle d'animation en millisecondes
	 * @param offSet
	 *            La duree avant que l'animation se lance
	 * @param loop
	 *            "true" pour boucler l'animation a l'infini "false" pour
	 *            effectuer une seule fois l'animation
	 */
	public static ScaleAnimation scale(View view, float from, float to,
			int duration, int offSet, boolean loop) {
		ScaleAnimation scale = new ScaleAnimation(from, to, from, to,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		if (loop) {
			scale.setRepeatCount(Animation.INFINITE);
			scale.setRepeatMode(Animation.REVERSE);
		}
		scale.setDuration(duration);
		scale.setStartOffset(offSet);
		view.startAnimation(scale);
		return scale;
	}

	/**
	 * Fait tourner une vue indefiniement autour de son centre
	 * 
	 * @param duration
	 *            La duree d'un cycle d'animation en millisecondes
	 * @param view
	 */
	public static RotateAnimation rotateInfinite(View view, int duration) {
		RotateAnimation rotate = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotate.setDuration(duration);
		rotate.setRepeatCount(Animation.INFINITE);
		rotate.setInterpolator(new LinearInterpolator());
		view.startAnimation(rotate);
		return rotate;
	}

	/**
	 * Fait tourner une vue d'un certain angle autour de son centre
	 * 
	 * @param duration
	 *            La duree d'un cycle d'animation en millisecondes
	 * @param angle
	 *            L'angle de rotation en de degre
	 * @param loop
	 *            "true" pour boucler l'animation a l'infini "false" pour
	 *            effectuer une seule fois l'animation
	 * @param view
	 *            La vue a animer
	 */
	public static RotateAnimation rotate(View view, int duration, int angle,
			boolean loop) {
		RotateAnimation rotate = new RotateAnimation(0, angle,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotate.setDuration(duration);
		if (loop) {
			rotate.setRepeatCount(Animation.INFINITE);
			rotate.setRepeatMode(Animation.REVERSE);
		}
		rotate.setInterpolator(new AccelerateDecelerateInterpolator());
		view.startAnimation(rotate);
		return rotate;
	}

	/**
	 * Fait tourner une vue d'un angle à un autre autour d'un point
	 * 
	 * @param view
	 *            La vue a animer
	 * @param duration
	 *            duree d'un cycle d'animation en millisecondes
	 * @param from
	 *            l'angle à prendre au début de l'animation (souvent 0)
	 * @param to
	 *            l'angle à prendre en fin de l'animation
	 * @param pivotX
	 *            coordonnee x du point autour duquel tourner (x=0 bord gauche
	 *            de la vue, x=1 bord droit)
	 * @param pivotY
	 *            coordonnee y du point autour duquel tourner (y=0 haut de la
	 *            vue, y=1 bas)
	 * @param loop
	 *            "true" pour boucler l'animation a l'infini "false" pour
	 *            effectuer une seule fois l'animation
	 * @return
	 */
	public static RotateAnimation rotate(View view, int duration, int from,
			int to, float pivotX, float pivotY, boolean loop) {
		RotateAnimation rotate = new RotateAnimation(from, to,
				Animation.RELATIVE_TO_SELF, pivotX, Animation.RELATIVE_TO_SELF,
				pivotY);
		rotate.setDuration(duration);
		if (loop) {
			rotate.setRepeatCount(Animation.INFINITE);
			rotate.setRepeatMode(Animation.REVERSE);
		}
		rotate.setInterpolator(new AccelerateDecelerateInterpolator());
		view.startAnimation(rotate);
		return rotate;
	}

	/**
	 * Fait apparaitre une vue invisible avec une animation d'echelle et un
	 * effet de rebond
	 * 
	 * @param view
	 *            La vue a animer
	 * @param duration
	 *            La duree de l'animation en millisecondes
	 */
	public static ScaleAnimation pop_in(View view, int duration) {
		ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scale.setDuration(duration);
		scale.setInterpolator(new BounceInterpolator());
		view.startAnimation(scale);
		view.setVisibility(View.VISIBLE);
		return scale;
	}

	/**
	 * Fait disparaite une vue avec une animation d'echelle et un effet de
	 * rebond
	 * 
	 * @param view
	 *            La vue a animer
	 * @param duration
	 *            La duree de l'animation en millisecondes
	 * @param "true" supprime la vue a la fin de l'animation "false" rend la vue
	 *        invisible sans la supprimer a la fin de l'animation
	 */
	public static ScaleAnimation pop_out(final View view, int duration,
			final boolean delete) {
		ScaleAnimation scale = new ScaleAnimation(1, 0, 1, 0,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scale.setDuration(duration);
		scale.setInterpolator(new BounceInterpolator());
		scale.setAnimationListener(new AnimationListener() {
			
			public void onAnimationStart(
					android.view.animation.Animation animation) {
			}

			
			public void onAnimationEnd(
					android.view.animation.Animation animation) {
				if (delete) {
					ViewGroup parent = (ViewGroup) view.getParent();
					parent.removeView(view);
					view.destroyDrawingCache();
				} else {
					view.setVisibility(View.INVISIBLE);
				}
			}

			
			public void onAnimationRepeat(
					android.view.animation.Animation animation) {
			}
		});
		view.startAnimation(scale);
		return scale;
	}

	/**
	 * Fait apparaitre une vue invisible avec une animation de transparence
	 * progressive
	 * 
	 * @param view
	 *            La vue a animer
	 * @param duration
	 *            La duree de l'animation en millisecondes
	 */
	public static AlphaAnimation fade_in(View view, int duration) {
		AlphaAnimation alpha = new AlphaAnimation(0, 1);
		alpha.setDuration(duration);
		alpha.setInterpolator(new AccelerateInterpolator());
		view.startAnimation(alpha);
		view.setVisibility(View.VISIBLE);
		return alpha;
	}

	/**
	 * Fait disparaite une vue avec une animation de transparence A la fin de
	 * l'animation, la vue est invisible mais existe toujours.
	 * 
	 * @param view
	 *            La vue a animer
	 * @param duration
	 *            La duree de l'animation en millisecondes
	 * @param "true" supprime la vue a la fin de l'animation "false" rend la vue
	 *        invisible sans la supprimer a la fin de l'animation
	 */
	public static AlphaAnimation fade_out(final View view, int duration,
			final boolean delete) {
		AlphaAnimation alpha = new AlphaAnimation(1, 0);
		alpha.setDuration(duration);
		alpha.setInterpolator(new AccelerateDecelerateInterpolator());
		alpha.setAnimationListener(new AnimationListener() {
			
			public void onAnimationStart(
					android.view.animation.Animation animation) {
			}

			
			public void onAnimationEnd(
					android.view.animation.Animation animation) {
				if (delete) {
					ViewGroup parent = (ViewGroup) view.getParent();
					parent.removeView(view);
					view.destroyDrawingCache();
				} else {
					view.setVisibility(View.INVISIBLE);
				}
			}

			
			public void onAnimationRepeat(
					android.view.animation.Animation animation) {
			}
		});
		view.startAnimation(alpha);
		return alpha;
	}

	/**
	 * Translate une vue de sa position initiale à une autre position
	 * 
	 * @param view
	 * @param toX
	 * @param toY
	 * @param duration
	 */
	public static TranslateAnimation translate(View view, float toX, float toY,
			int duration) {
		TranslateAnimation trans = new TranslateAnimation(0, toX, 0, toY);
		trans.setDuration(duration);
		trans.setFillAfter(true);
		view.startAnimation(trans);
		return trans;

	}

	/**
	 * Translate une vue d'une position à une autre
	 * 
	 * @param view
	 * @param toX
	 * @param toY
	 * @param duration
	 */
	public static TranslateAnimation translate(View view, float fromX,
			float fromY, float toX, float toY, int duration) {
		TranslateAnimation trans = new TranslateAnimation(fromX, toX, fromY,
				toY);
		trans.setDuration(duration);
		trans.setFillAfter(true);
		view.startAnimation(trans);
		return trans;
	}

	/**
	 * Translate une vue d'une position à une autre et peut boucler cette
	 * animation
	 * 
	 * @param view
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @param duration
	 * @param loop
	 * @return
	 */
	public static TranslateAnimation translate(View view, float fromX,
			float fromY, float toX, float toY, int duration, boolean loop) {
		TranslateAnimation trans = new TranslateAnimation(fromX, toX, fromY,
				toY);
		trans.setDuration(duration);
		trans.setFillAfter(true);
		if (loop) {
			trans.setRepeatCount(Animation.INFINITE);
			trans.setRepeatMode(Animation.REVERSE);
		}
		view.startAnimation(trans);
		return trans;
	}

	/**
	 * Translate une vue d'un point à un autre en decelerrant sur la fin
	 * 
	 * @param view
	 * @param toX
	 * @param toY
	 * @param duration
	 */
	public static TranslateAnimation translateDecelerate(View view,
			float fromX, float fromY, float toX, float toY, int duration) {
		TranslateAnimation trans = new TranslateAnimation(fromX, toX, fromY,
				toY);
		trans.setInterpolator(new DecelerateInterpolator());
		trans.setDuration(duration);
		trans.setFillAfter(true);
		view.startAnimation(trans);
		return trans;
	}

	/**
	 * Translate une vue d'un point à un autre en decelerrant sur la fin Peut se
	 * lancer de maniere decale
	 * 
	 * @param view
	 * @param toX
	 * @param toY
	 * @param duration
	 * @param offSet
	 *            La duree avant que l'animation se lance
	 */
	public static TranslateAnimation translateDecelerate(View view,
			float fromX, float fromY, float toX, float toY, int duration,
			int offSet) {
		TranslateAnimation trans = new TranslateAnimation(fromX, toX, fromY,
				toY);
		trans.setInterpolator(new DecelerateInterpolator());
		trans.setDuration(duration);
		trans.setFillAfter(true);
		trans.setStartOffset(offSet);
		view.startAnimation(trans);
		return trans;
	}

	/**
	 * 
	 * @param parent
	 *            le parent de l'application
	 * @param targetActivity
	 *            l'application sur laquelle on veut aller et envoyer des
	 *            parametres (extra)
	 * @param extra
	 *            un parametre serialise qui sera repris par la nouvelle
	 *            application, mettre null pour l'ignorer
	 * @param name
	 *            l'id du parametre extra
	 * 
	 */
	public static void changeActivityAnimation(ViewGroup parent,
			final Class<?> targetActivity, final Serializable extra1,
			final String name1, final Serializable extra2, final String name2) {
		/* animation rideau sur l'ecran violet */
		final Context context = parent.getContext();
		int H = context.getApplicationContext().getResources()
				.getDisplayMetrics().heightPixels;

		TranslateAnimation trans = new TranslateAnimation(0, 0, 0, H * 1.1f);
		trans.setDuration(1300);
		trans.setFillAfter(true);
		trans.setInterpolator(new DecelerateInterpolator());
		trans.setAnimationListener(new Animation.AnimationListener() {

			
			public void onAnimationStart(Animation animation) {
			}

			
			public void onAnimationRepeat(Animation animation) {
			}

			
			public void onAnimationEnd(Animation animation) {
				/* Passage a l'autre activite */
				Intent intent = new Intent(context, targetActivity);
				if (extra1 != null)
					intent.putExtra(name1, extra1);
				if (extra2 != null)
					intent.putExtra(name2, extra2);
				((Activity) context).startActivity(intent);

				((Activity) context).overridePendingTransition(R.anim.fade_out,
						R.anim.fade_in);

			}
		});
		parent.startAnimation(trans);

	}

	/**
	 * animation qui fait apparaitre puis disparaitre le logo l'activite
	 * apparait en se translatant : soit deux slide se rejoignent aux centre,
	 * soit un l'activite se translate de bas en haut
	 * 
	 * @param logo_bouton
	 *            la view (logo) qui apparait et disparait avant l'activite
	 * @param slide_bottom
	 *            layout auquel on ajoute le logo, ne doit pas être nul
	 * @param slide_top
	 *            (nul si activite pas coupee en deux mais un seul bloc)
	 * @param H
	 *            la hauteur de l'ecran
	 */
	public static void activityApparitionAnimation(final View logo_bouton,
			final RelativeLayout slide_bottom, final RelativeLayout slide_top,
			final ImageView slide_top_shadow, final int H) {
		/* Apparition du logo bouton */

		Animer.fade_in(logo_bouton, 500);
		AlphaAnimation alpha = new AlphaAnimation(0, 1);
		alpha.setDuration(500);
		alpha.setAnimationListener(new AnimationListener() {

			
			public void onAnimationStart(Animation animation) {
			}

			
			public void onAnimationRepeat(Animation animation) {

			}

			
			public void onAnimationEnd(Animation animation) {
				/* disparition du logo */
				Animer.fade_out(logo_bouton, 1000, true);

				/* Arrivée du menu par le haut et le bas */
				if (slide_top != null) {
					slide_top.setVisibility(View.VISIBLE);
					Animer.translateDecelerate(slide_top, 0, -H / 3, 0, 0,
							1000, 200);
				}
				if (slide_top_shadow != null) {
					slide_top_shadow.setVisibility(View.VISIBLE);
					Animer.translateDecelerate(slide_top_shadow, 0, -H / 3, 0,
							0, 1000, 200);
				}
				slide_bottom.setVisibility(View.VISIBLE);
				Animer.translateDecelerate(slide_bottom, 0, H * 1.1f, 0, 0,
						1800, 600);

			}
		});
		logo_bouton.startAnimation(alpha);
	}
}
