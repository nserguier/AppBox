package animation;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * objet qui permet de realiser l'apparition ou la disparition d'un menu en cliquant sur un bouton
 * le bouton change d'aspect s'il est appuye ou relache
 * @author pc
 *
 */
public class PopInPopOut extends Activity {

	private RelativeLayout menuDeroulant;
	private Button pop;
	private Drawable open;
	private Drawable close;
	boolean isOpen = false;

	// creation du menu deroulant

	public PopInPopOut(RelativeLayout menuDeroulant, Button pop, Drawable open,
			Drawable close) {
		this.close = close;
		this.menuDeroulant = menuDeroulant;
		this.pop = pop;
		this.open = open;
	}

	public void initialise() {
		// a l'origine, le menu est cache
		menuDeroulant.setVisibility(View.GONE);

		// On rajoute un Listener sur le clic du bouton...
		pop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View vue) {

				isOpen = toggle(menuDeroulant);

				// ...pour afficher ou cacher le menu
				if (isOpen) {
					// Si le Slider est ouvert...
					// ... on change le bouton d'aide en mode appuye
					pop.setBackground(open);
				} else {
					// Sinon on remet le bouton en mode "lache"
					pop.setBackground(close);
				}
			}
		});
	}

	/**
	 * Utilisée pour ouvrir ou fermer le menu.
	 * 
	 * @return true si le menu est désormais ouvert.
	 */
	public boolean toggle(RelativeLayout menuDeroulant) {
		int SPEED = 300;
		// Animation de transition.
		AnimationSet animationSet = new AnimationSet(true);
		TranslateAnimation animation = null;

		// On passe de ouvert à fermé (ou vice versa)
		isOpen = !isOpen;

		// Si le menu est déjà ouvert
		if (isOpen) {
			// Animation de translation du bas vers le haut
			animation = new TranslateAnimation(0.0f, 0.0f,
					-menuDeroulant.getHeight(), 0.0f);
			animation.setAnimationListener(openListener);
			animationSet.setAnimationListener(openListener);
		} else {
			// Sinon, animation de translation du haut vers le bas
			animation = new TranslateAnimation(0.0f, 0.0f, 0.0f,
					-menuDeroulant.getHeight());
			animation.setAnimationListener(closeListener);
			animationSet.setAnimationListener(closeListener);
		}

		// On détermine la durée de l'animation
		animation.setDuration(SPEED);
		// On ajoute un effet d'accélération
		animation.setInterpolator(new AccelerateInterpolator());
		// Enfin, on lance l'animation
		animationSet.addAnimation(animation);
		menuDeroulant.startAnimation(animationSet);

		return isOpen;
	}

	/* Listener pour l'animation de fermeture du menu */
	Animation.AnimationListener closeListener = new Animation.AnimationListener() {
		public void onAnimationEnd(Animation animation) {
			// On dissimule le menu
			menuDeroulant.setVisibility(View.GONE);
		}

		public void onAnimationRepeat(Animation animation) {

		}

		public void onAnimationStart(Animation animation) {

		}
	};

	/* Listener pour l'animation d'ouverture du menu */
	Animation.AnimationListener openListener = new Animation.AnimationListener() {
		public void onAnimationEnd(Animation animation) {
		}

		public void onAnimationRepeat(Animation animation) {
		}

		public void onAnimationStart(Animation animation) {
			// On affiche le menu
			menuDeroulant.setVisibility(View.VISIBLE);
		}
	};

}
