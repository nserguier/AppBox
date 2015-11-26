package composants;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Un texte anime lettre par lettre
 * 
 * @author Nicklos
 * 
 */
public class AnimatedText {

	/**
	 * Cree un texte avec une animation de vague chaque lettre a sa couleur
	 * 
	 * @param context
	 * @param layout
	 * @param myText
	 * @param colors
	 * @param textSize
	 */
	public static void add(Context context, LinearLayout layout, String myText,
			int[] colors, float textSize) {

		int[] size = Utile.getScreenSize((Activity) context);
		int offSet = 200;
		int j = 0;

		for (int i = 0; i < myText.length(); i++) {
			// Char creation
			String c = Character.toString(myText.charAt(i));
			TextView txt = new TextView(context);
			txt.setText(c + " ");
			txt.setTextColor(context.getResources().getColor(colors[j]));
			if (!c.contains(" ")) {
				j += 1;
			}
			txt.setTextSize(textSize);
			Utile.setFont((Activity) context, txt, "Action_Man.ttf");
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			txt.setPadding(0, 20 * size[1] / 1600, 0, 0);
			params.setMargins(0, 0, -60 * size[0] / 2560, 0); // espacement
																// entre les
																// lettres
			txt.setLayoutParams(params);

			// Animation du char
			final TranslateAnimation anim = new TranslateAnimation(0, 0, 0, -30);
			anim.setDuration(1000);
			anim.setRepeatMode(Animation.REVERSE);
			anim.setRepeatCount(Animation.INFINITE);
			anim.setStartOffset(offSet * i);
			anim.setAnimationListener(new Animation.AnimationListener() {

				int compteur = 0;

				@Override
				public void onAnimationStart(Animation animation) {
				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					anim.setStartOffset(0);
					if ((compteur % 2) != 0 && compteur != 0) {
						anim.setStartOffset(4000); // temps d'attente avant de
													// recommencer la vague
					}
					compteur += 1;

				}

				@Override
				public void onAnimationEnd(Animation animation) {
				}
			});

			layout.addView(txt);
			txt.startAnimation(anim);
		}
	}

	/**
	 * Cree un texte avec une animation de vague toutes les lettres ont la même
	 * couleur
	 * 
	 * @param context
	 * @param layout
	 * @param myText
	 * @param color
	 * @param textSize
	 */
	public static void add(Context context, LinearLayout layout, String myText,
			int color, float textSize) {

		int offSet = 200;
		Typeface myFont = Typeface.createFromAsset(context.getAssets(),
				"fonts/intsh.ttf");

		for (int i = 0; i < myText.length(); i++) {
			// Char creation
			String c = Character.toString(myText.charAt(i));
			TextView txt = new TextView(context);
			txt.setText(c + " ");
			txt.setTextColor(context.getResources().getColor(color));
			txt.setTextSize(textSize);
			txt.setTypeface(myFont);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			txt.setPadding(0, 50, 0, 0);
			params.setMargins(0, 0, -35, 0); // espacement entre les lettres
			txt.setLayoutParams(params);

			// Animation du char
			final TranslateAnimation anim = new TranslateAnimation(0, 0, 0, -30);
			anim.setDuration(1000);
			anim.setRepeatMode(Animation.REVERSE);
			anim.setRepeatCount(Animation.INFINITE);
			anim.setStartOffset(offSet * i);
			anim.setAnimationListener(new Animation.AnimationListener() {

				int compteur = 0;

				@Override
				public void onAnimationStart(Animation animation) {
				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					anim.setStartOffset(0);
					if ((compteur % 2) != 0 && compteur != 0) {
						anim.setStartOffset(4000); // temps d'attente avant de
													// recommencer la vague
					}
					compteur += 1;

				}

				@Override
				public void onAnimationEnd(Animation animation) {
				}
			});

			layout.addView(txt);
			txt.startAnimation(anim);
		}
	}

}
