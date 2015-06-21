package composants;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.Atlas.framework.R;

/**
 * cree une horloge analogique
 * @author Victor
 *
 */
public class Horloge {
	static Timer timer;
	static TimerTask timerTask;
	static Handler handler = new Handler();
	
	
	/**
	 * Cree une horloge anlagique dans un relative layout vide et la met a l'heure fixe voulue.
	 * @param r le relative layout vide
	 * @param ctx contexte de l'activite
	 * @param h l'heure, entier de 1 a 24
	 * @param m les minutes, entier de 0 a 59
	 * @param s les secondes, entier de 0 a 69
	 */
	public static void create(RelativeLayout r, Context ctx, int h,
			int m, int s) {

		ImageView horloge = new ImageView(ctx);
		horloge.setBackground(ctx.getResources().getDrawable(
				R.drawable.clock_dial_w));

		ImageView heures = new ImageView(ctx);
		heures.setBackground(ctx.getResources().getDrawable(
				R.drawable.clock_hour_w));

		ImageView minutes = new ImageView(ctx);
		minutes.setBackground(ctx.getResources().getDrawable(
				R.drawable.clock_minute_w));

		ViewGroup parent = (ViewGroup) r.getParent();

		RelativeLayout.LayoutParams horloge_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		horloge_params.addRule(RelativeLayout.CENTER_VERTICAL);
		horloge_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		horloge.setLayoutParams(horloge_params);
		if(Build.VERSION.SDK_INT >= 21)
			horloge.setElevation(1f);
		r.addView(horloge);

		RelativeLayout.LayoutParams heures_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		heures_params.addRule(RelativeLayout.CENTER_VERTICAL);
		heures_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		heures.setLayoutParams(heures_params);
		if(Build.VERSION.SDK_INT >= 21)
		heures.setElevation(2f);
		r.addView(heures);

		RelativeLayout.LayoutParams minutes_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		minutes_params.addRule(RelativeLayout.CENTER_VERTICAL);
		minutes_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		minutes.setLayoutParams(minutes_params);
		if(Build.VERSION.SDK_INT >= 21)
		minutes.setElevation(2f);
		minutes.setId(88);
		r.addView(minutes);

		h = h % 12; // heure en format pm

		heures.setRotation((float) (30 * h + 0.5 * m));
		minutes.setRotation((float) (6 * m + 0.1 * s));
	}
	/**
	 * permet de remettre un relative layout contenant
	 *  l'horloge a son etat de depart
	 */
	public static void erase(RelativeLayout r) {
		r.removeAllViews();
	}
	
	public static void incrementMin(Activity a){
		ImageView heures = (ImageView) a.findViewById(88);
		float degrees = heures.getRotation()+6f;
		int minutes = (int) Math.round(degrees/6);
		heures.setRotation(minutes*6);
	}
	
	private static void startTimer(Activity a) {
		timer = new Timer();
		initializeTimerTask(a);
		timer.schedule(timerTask, 60000, 60000); //
	}

	private static void stoptimertask(View v) {
		//stop the timer, if it's not already null
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

	private static void initializeTimerTask(final Activity a) {
		timerTask = new TimerTask() {
			public void run() {
				
				//use a handler to run a toast that shows the current timestamp
				handler.post(new Runnable() {
					public void run() {
						Horloge.incrementMin(a);
					}
				});
			}
		};
	}
	
	/**
	 * Cree une horloge anlagique dans un relative layout vide et la met a l'heure en temps reel
	 * @param r le relative layout vide
	 * @param ctx contexte de l'activite
	 */
	public static void create(RelativeLayout r, Context ctx) {
		Calendar now = Calendar.getInstance();
		int h = now.get(Calendar.HOUR_OF_DAY);
		int m = now.get(Calendar.MINUTE);
		int s = now.get(Calendar.SECOND);
		ImageView horloge = new ImageView(ctx);
		horloge.setBackground(ctx.getResources().getDrawable(
				R.drawable.clock_dial_w));

		ImageView heures = new ImageView(ctx);
		heures.setBackground(ctx.getResources().getDrawable(
				R.drawable.clock_hour_w));

		ImageView minutes = new ImageView(ctx);
		minutes.setBackground(ctx.getResources().getDrawable(
				R.drawable.clock_minute_w));

		RelativeLayout.LayoutParams horloge_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		horloge_params.addRule(RelativeLayout.CENTER_VERTICAL);
		horloge_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		horloge.setLayoutParams(horloge_params);
		horloge.setElevation(1f);
		r.addView(horloge);

		RelativeLayout.LayoutParams heures_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		heures_params.addRule(RelativeLayout.CENTER_VERTICAL);
		heures_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		heures.setLayoutParams(heures_params);
		heures.setElevation(2f);
		r.addView(heures);

		RelativeLayout.LayoutParams minutes_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		minutes_params.addRule(RelativeLayout.CENTER_VERTICAL);
		minutes_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		minutes.setLayoutParams(minutes_params);
		minutes.setElevation(2f);
		minutes.setId(88);
		r.addView(minutes);

		h = h % 12; // heure en format pm

		heures.setRotation((float) (30 * h + 0.5 * m));
		minutes.setRotation((float) (6 * m + 0.1 * s));
		
		startTimer((Activity) ctx.getApplicationContext());
	}
		
	public static String dateActuelle(){
		Calendar now = Calendar.getInstance();
		return "il est "+String.valueOf(now.get(Calendar.HOUR_OF_DAY))+
				" heure et "+String.valueOf(now.get(Calendar.MINUTE))+" minutes";
	}
}
