package horloge;


import com.Atlas.framework.R;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * objet horloge qui cree une horloge
 * dans un relative layout vide et la met à l'heure voulue
 * @author Victor
 *
 */
public class Clock {

	public static RelativeLayout create(RelativeLayout r, Context ctx, int h,
			int m, int s) {

		ImageView horloge = new ImageView(ctx);
		horloge.setBackground(ctx.getResources().getDrawable(
				R.drawable.clock_dial));

		ImageView heures = new ImageView(ctx);
		heures.setBackground(ctx.getResources().getDrawable(
				R.drawable.clock_hour));

		ImageView minutes = new ImageView(ctx);
		minutes.setBackground(ctx.getResources().getDrawable(
				R.drawable.clock_minute));

		ImageView secondes = new ImageView(ctx);
		secondes.setBackground(ctx.getResources().getDrawable(
				R.drawable.clock_seconds));

		ViewGroup parent = (ViewGroup) r.getParent();

		RelativeLayout.LayoutParams horloge_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		horloge_params.addRule(RelativeLayout.CENTER_VERTICAL);
		horloge_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		horloge.setLayoutParams(horloge_params);
		//horloge.setElevation(1f);
		r.addView(horloge);
		parent.removeView(horloge);

		RelativeLayout.LayoutParams heures_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		heures_params.addRule(RelativeLayout.CENTER_VERTICAL);
		heures_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		heures.setLayoutParams(heures_params);
		//heures.setElevation(2f);
		r.addView(heures);
		parent.removeView(heures);

		RelativeLayout.LayoutParams minutes_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		minutes_params.addRule(RelativeLayout.CENTER_VERTICAL);
		minutes_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		minutes.setLayoutParams(minutes_params);
		//minutes.setElevation(2f);
		r.addView(minutes);
		parent.removeView(minutes);

		RelativeLayout.LayoutParams secondes_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		secondes_params.addRule(RelativeLayout.CENTER_VERTICAL);
		secondes_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		secondes.setLayoutParams(secondes_params);
		//secondes.setElevation(2f);
		r.addView(secondes);
		parent.removeView(secondes);

		h = h % 12; // heure en format pm

		heures.setRotation((float) (30 * h + 0.5 * m));
		minutes.setRotation((float) (6 * m + 0.1 * s));
		secondes.setRotation(6 * s);

		return r;
	}
	/**
	 * permet de remettre un relative layout contenant
	 *  l'horloge a son etat de depart
	 */
	public static void erase(RelativeLayout r) {
		r.removeAllViews();
	}
}
