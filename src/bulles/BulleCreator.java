package bulles;

import com.Atlas.framework.R;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class BulleCreator {

	/**
	 * Cree une bulle contenant du texte proche d'une vue et l'ajoute au layout
	 * @param view La vue sur laquelle la bulle va se placer
	 * @param texte Le texte contenu dans la bulle
	 * @param lieu La ou va se placer la bulle par rapport a la vue 
	 * 		"above" au dessus
	 * 		"below"	en dessous
	 * 		"right" a droite
	 * 		"left" a gauche
	 * @param activity L'activite en question
	 * @return La bulle
	 */
	public static TextView createBubble(View view, String texte,String lieu, Activity activity){
		
		Drawable bulle = null;
		TextView bulle_texte = new TextView(activity);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.addRule(Gravity.CENTER_HORIZONTAL);
		
		
		switch (lieu) {
		case "above":
			bulle = activity.getResources().getDrawable(R.drawable.bulle_haut);
			params.addRule(RelativeLayout.ABOVE,view.getId());
			params.addRule(RelativeLayout.ALIGN_END,view.getId());
			break;

		case "below":
			bulle = activity.getResources().getDrawable(R.drawable.bulle_bas);
			params.addRule(RelativeLayout.BELOW,view.getId());
			params.addRule(RelativeLayout.ALIGN_END,view.getId());
			break;
			
		case "right":
			bulle = activity.getResources().getDrawable(R.drawable.bulle_droite);
			params.addRule(RelativeLayout.ALIGN_TOP,view.getId());
			params.addRule(RelativeLayout.END_OF,view.getId());
			break;
		
		case "left":
			bulle = activity.getResources().getDrawable(R.drawable.bulle_gauche);
			params.addRule(RelativeLayout.ALIGN_TOP,view.getId());
			params.addRule(RelativeLayout.LEFT_OF,view.getId());
			break;
			
		default:
			// afficher une erreur
			break;
		}
		
		Typeface comic = Typeface.createFromAsset(activity.getAssets(),"fonts/comic.otf");
		
		bulle_texte.setText(texte);
		bulle_texte.setTextSize(20);
		bulle_texte.setTextColor(Color.BLACK);
		bulle_texte.setTypeface(comic);
		bulle_texte.setBackground(bulle);
		
		ViewGroup parent = (ViewGroup) view.getParent();
		parent.addView(bulle_texte, params);
		
		bulle_texte.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.pop_in));
		
		return bulle_texte;
	}
	
	/**
	 * Supprime une bulle, avec une animation
	 * @param bulle la bulle existante
	 */
	public static void destroyBubble(final TextView bulle,Activity activity){
		Animation pop_out = AnimationUtils.loadAnimation(activity, R.anim.pop_out);
		pop_out.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				//retire la vue seulement quand l'animation est finie
				ViewGroup parent = (ViewGroup) bulle.getParent();
				parent.removeView(bulle);
			}
		});
		bulle.startAnimation(pop_out);
		
	}
	
}
