package composants;


import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.Window;

public class Ecran {

	/**
	 * Donne la taille de l'ecran
	 * @param a L'activite concerne
	 * @return [0] : la largeur en px
	 * 			[1] : la hauteur en px
	 */
	public static int[] getSize(Activity a){
		Display display = a.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int[] rep = {size.x,size.y};
		return rep;
	}
	
	/**
	 * Passe en plein ecran l'activite
	 * A inserer dans onCreate avant le setContentView.
	 * @param a L'activite
	 */
	public static void fullScreen(Activity a){
		a.requestWindowFeature(Window.FEATURE_NO_TITLE);
		View decorView = a.getWindow().getDecorView();
		decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_FULLSCREEN
				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
	}
	
	public static void fullScreenResume(final Activity a) {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// execute after 500ms
				hideNavBar(a);
			}
		}, 500);
	}

	private static void hideNavBar(Activity a) {
		if (Build.VERSION.SDK_INT >= 19) {
			View v = a.getWindow().getDecorView();
			v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
	}
		
}
