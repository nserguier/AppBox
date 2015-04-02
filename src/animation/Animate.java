package animation;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

public class Animate {

	/**
	 * Anime une vue en la faisant grossir/retrecir
	 * @param view La vue a animer
	 * @param from Le facteur d'echelle a prende au debut d'animation
	 * @param to Le facteur d'echelle a prendre en fin d'animation
	 * @param duration La duree d'un cycle d'animation en millisecondes
	 * @param loop "true" pour boucler l'animation a l'infini
	 * 				"false" pour effectuer une seule fois l'animation
	 */
	public static void scale(View view,float from, float to,int duration,boolean loop){
		ScaleAnimation scale = new ScaleAnimation(from, to, from, to, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		if(loop){
			scale.setRepeatCount(ScaleAnimation.INFINITE);
			scale.setRepeatMode(ScaleAnimation.REVERSE);
		}
		scale.setDuration(duration);
		view.startAnimation(scale);
	}
	
	/**
	 * Fait tourner une vue indefiniement autour de son centre
	 * @param duration La duree d'un cycle d'animation en millisecondes
	 * @param view
	 */
	public static void rotateInfinite(View view,int duration){
		RotateAnimation rotate = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotate.setDuration(duration);
		rotate.setRepeatCount(ScaleAnimation.INFINITE);
		rotate.setInterpolator(new LinearInterpolator());
		view.startAnimation(rotate);
	}
	
	/**
	 * Fait tourner une vue d'un certain angle autour de son centre
	 * @param duration La duree d'un cycle d'animation en millisecondes
	 * @param angle L'angle de rotation en de degre
	 * @param loop "true" pour boucler l'animation a l'infini
	 * 				"false" pour effectuer une seule fois l'animation
	 * @param view La vue a animer
	 */
	public static void rotate(View view,int duration,int angle,boolean loop){
		RotateAnimation rotate = new RotateAnimation(0,angle,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotate.setDuration(duration);
		if(loop){
			rotate.setRepeatCount(ScaleAnimation.INFINITE);
			rotate.setRepeatMode(ScaleAnimation.REVERSE);
		}
		rotate.setInterpolator(new AccelerateDecelerateInterpolator());
		view.startAnimation(rotate);
	}
	
	/**
	 * Fait apparaitre une vue invisible avec une animation d'echelle et un effet de rebond
	 * @param view La vue a animer
	 * @param duration La duree de l'animation en millisecondes
	 */
	public static void pop_in(View view,int duration){
		ScaleAnimation scale = new ScaleAnimation(0,1, 0, 1,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scale.setDuration(duration);
		scale.setInterpolator(new BounceInterpolator());
		view.startAnimation(scale);
		view.setVisibility(View.VISIBLE);
	}
	
	/**
	 * Fait disparaite une vue avec une animation d'echelle et un effet de rebond
	 * A la fin de l'animation, la vue est invisible mais existe toujours.
	 * @param view La vue a animer
	 * @param duration La duree de l'animation en millisecondes
	 */
	public static void pop_out(final View view,int duration){
		ScaleAnimation scale = new ScaleAnimation(1,0, 1, 0,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scale.setDuration(duration);
		scale.setInterpolator(new BounceInterpolator());
		scale.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(
					android.view.animation.Animation animation) {
			}

			@Override
			public void onAnimationEnd(
					android.view.animation.Animation animation) {
			}

			@Override
			public void onAnimationRepeat(
					android.view.animation.Animation animation) {
				view.setVisibility(View.INVISIBLE);
			}
		});
		view.startAnimation(scale);
	}
	
}
