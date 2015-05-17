package composants;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import boutons.ButtonCreator;

public class MenuDeroulant {

	private static RelativeLayout menuDeroulant;
	private static boolean isOpen = false;
	
	public static RelativeLayout create(final RelativeLayout parent,Context context,int colorBouton,int colorDeroulant,String text) {
		
		parent.setClipChildren(true);
		// on cree deux layout : un pour le bouton qui fait poper le menu et un pour le menu deroulant
		menuDeroulant = new RelativeLayout(context);
		menuDeroulant.setId(1);
		RelativeLayout bouton = new RelativeLayout(context);
		bouton.setId(2);
		RelativeLayout.LayoutParams params = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		bouton.setLayoutParams(params);
		
		
		RelativeLayout.LayoutParams menu_params = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		menu_params.setMargins(0, -10 , 0, 0);
		menu_params.addRule(RelativeLayout.BELOW,bouton.getId());
		menu_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		menuDeroulant.setLayoutParams(menu_params);
		menuDeroulant.setVisibility(View.INVISIBLE);
		menuDeroulant.setBackgroundColor(context.getResources().getColor(colorDeroulant));
		
				
		// on cree le bouton et son listenner
		
		Button b = ButtonCreator.createButton(context, colorBouton);
		b.setLayoutParams(bouton.getLayoutParams());
		b.setText(text);
				
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				isOpen = toggle(menuDeroulant,isOpen);
				
			}});
	
		// enfin on ajoute ces vues aux parents
		bouton.addView(b);
		parent.addView(menuDeroulant);
		parent.addView(bouton);
		
			
		return menuDeroulant;
	}
	
	public static void add(View v) {
		
		RelativeLayout.LayoutParams params = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_END);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.setMargins(0,10,0,0);
		v.setLayoutParams(params);
		menuDeroulant.addView(v);
	}
	
	/**
	 * Utilisee pour ouvrir ou fermer le menu.
	 * 
	 * @return true si le menu est désormais ouvert.
	 */
	public static boolean toggle(RelativeLayout menuDeroulant, boolean isOpen) {
		int duration = 600;
		// Animation de transition.
		TranslateAnimation animation = null;

		// On passe de ouvert à fermé (ou vice versa)
		isOpen = !isOpen;

		// Si le menu est déjà ouvert
		if (isOpen) {
			// Animation de translation du bas vers le haut
			animation = new TranslateAnimation(0.0f, 0.0f,
					-menuDeroulant.getHeight(), 0.0f);
			animation.setAnimationListener(openListener);
		} else {
			// Sinon, animation de translation du haut vers le bas
			animation = new TranslateAnimation(0.0f, 0.0f, 0.0f,
					-menuDeroulant.getHeight());
			animation.setAnimationListener(closeListener);
		}

		// On détermine la durée de l'animation
		animation.setDuration(duration);
		// On ajoute un effet d'accélération
		animation.setInterpolator(new AccelerateInterpolator());
		// Enfin, on lance l'animation
		menuDeroulant.startAnimation(animation);

		return isOpen;
	}
	
	
	/* Listener pour l'animation de fermeture du menu */
	static Animation.AnimationListener closeListener = new Animation.AnimationListener() {
		@Override
		public void onAnimationEnd(Animation animation) {
			// On dissimule le menu
			menuDeroulant.setVisibility(View.INVISIBLE);
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

		@Override
		public void onAnimationStart(Animation animation) {

		}
	};

	/* Listener pour l'animation d'ouverture du menu */
	static Animation.AnimationListener openListener = new Animation.AnimationListener() {
		@Override
		public void onAnimationEnd(Animation animation) {
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationStart(Animation animation) {
			// On affiche le menu
			menuDeroulant.setVisibility(View.VISIBLE);
		}
	};
	
	
}
