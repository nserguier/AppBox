package composants;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Atlas.framework.R;

public class Bulle {

	/**
	 * Cree une bulle contenant du texte proche d'une vue et l'ajoute au layout
	 * 
	 * @param view
	 *            La vue sur laquelle la bulle va se placer
	 * @param texte
	 *            Le texte contenu dans la bulle
	 * @param lieu
	 *            La ou va se placer la bulle par rapport a la vue "above" au
	 *            dessus "below" en dessous "right" a droite "left" a gauche
	 * @param "true" pour que la bulle soit visible a sa creation "false" pour
	 *        que la bulle soit invisible a sa creation
	 * @param activity
	 *            L'activite en question
	 * @return La bulle
	 */
	public static TextView create(View view, String texte, String lieu,
			boolean isVisible, Activity activity) {

		Drawable bulle = null;
		TextView bulle_texte = new TextView(activity);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(Gravity.CENTER_HORIZONTAL);

		if (lieu.equals("above")) {
			bulle = activity.getResources().getDrawable(R.drawable.bulle_haut);
			params.addRule(RelativeLayout.ABOVE, view.getId());
			params.addRule(RelativeLayout.ALIGN_RIGHT, view.getId());
		} else if (lieu.equals("below")) {
			bulle = activity.getResources().getDrawable(R.drawable.bulle_bas);
			params.addRule(RelativeLayout.BELOW, view.getId());
			params.addRule(RelativeLayout.ALIGN_RIGHT, view.getId());
		} else if (lieu.equals("right")) {

			bulle = activity.getResources()
					.getDrawable(R.drawable.bulle_droite);
			params.addRule(RelativeLayout.ALIGN_TOP, view.getId());
			params.addRule(RelativeLayout.RIGHT_OF, view.getId());
		} else if (lieu.equals("left")) {
			bulle = activity.getResources()
					.getDrawable(R.drawable.bulle_gauche);
			params.addRule(RelativeLayout.ALIGN_TOP, view.getId());
			params.addRule(RelativeLayout.LEFT_OF, view.getId());
		}

		Typeface comic = Typeface.createFromAsset(activity.getAssets(),
				"fonts/comic.ttf");

		bulle_texte.setText(texte);
		bulle_texte.setTextSize(26);
		bulle_texte.setTextColor(Color.BLACK);
		bulle_texte.setTypeface(comic);
		bulle_texte.setBackgroundDrawable(bulle);
		bulle_texte.setGravity(Gravity.CENTER);
		// bulle_texte.setElevation(view.getElevation());

		ViewGroup parent = (ViewGroup) view.getParent();
		parent.addView(bulle_texte, params);

		if (isVisible) {
			Animer.pop_in(bulle_texte, 500);
		} else {
			bulle_texte.setVisibility(View.INVISIBLE);
		}
		bulle_texte.setAlpha(0.7f);
		return bulle_texte;
	}

	/**
	 * Supprime une bulle, avec une animation
	 * 
	 * @param bulle
	 *            la bulle existante
	 */
	public static void destroy(final TextView bulle, Activity activity) {
		Animer.pop_out(bulle, 500, true);

	}

}
