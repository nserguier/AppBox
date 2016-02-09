package composants;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.Atlas.framework.R;

/**
 * Composant comprenant toutes les fonctionnalites isolees mais utiles !
 */
public class Utile {

	/* COULEURS */

	/**
	 * Assombrit une couleur
	 * 
	 * @param color
	 *            la couleur a assombrir
	 * @return la couleur assombrie
	 */
	public static int darkenColor(int color) {
		float[] hsv = new float[3];
		Color.colorToHSV(color, hsv);
		hsv[2] *= 0.85f;
		color = Color.HSVToColor(hsv);
		return color;
	}

	/**
	 * Eclaircit une couleur
	 * 
	 * @param color
	 *            la couleur a eclaircir
	 * @return la couleur eclaircie
	 */
	public static int lightenColor(int color) {
		float[] hsv = new float[3];
		Color.colorToHSV(color, hsv);
		hsv[2] *= 1.2f;
		hsv[1] *= 0.85f;
		color = Color.HSVToColor(hsv);
		return color;
	}

	/* ECRAN */

	/**
	 * Donne la taille de l'ecran en px, attention cela ne dépend pas des "mesures" de l'ecran
	 * mais de sa densite de pixel, donc cela peut être plus grand pour un smartphone qu'une tablette ...
	 * 
	 * @param a
	 *            L'activite concerne
	 * @return [0] : la largeur en px [1] : la hauteur en px
	 */
	public static int[] getScreenSize(Activity a) {
		Display display = a.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int[] rep = { size.x, size.y };
		return rep;
	}
	
	/**
	 * donne la dimension de l'ecran en pouces 
	 * @param a
	 * @return
	 */
	public static double getScreeSizeInch(Activity a){
		DisplayMetrics dm = new DisplayMetrics();
		a.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width=dm.widthPixels;
		int height=dm.heightPixels;
		int dens=dm.densityDpi;
		double wi=(double)width/(double)dens;
		double hi=(double)height/(double)dens;
		double x = Math.pow(wi,2);
		double y = Math.pow(hi,2);
		double screenInches = Math.sqrt(x+y);
		return screenInches;
		
	}
	
	/**
	 * Donne la taille de l'ecran en px/inch, attention cela ne dépend pas des "mesures" de l'ecran
	 * mais de sa densite de pixel, donc cela peut être plus grand pour un smartphone qu'une tablette ...
	 * 
	 * @param a
	 *            L'activite concerne
	 * @return [0] : la largeur en px [1] : la hauteur en px
	 */
	public static int[] getScreenSizePI(Activity a) {
		final DisplayMetrics metrics = new DisplayMetrics();
        a.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int[] rep = { (int) metrics.xdpi, (int) metrics.ydpi };
		return rep;
	}

	/**
	 * Passe en plein ecran l'activite A inserer dans onCreate avant le
	 * setContentView.
	 * 
	 * @param a
	 *            L'activite
	 */
	public static void fullScreen(Activity a) {
		a.requestWindowFeature(Window.FEATURE_NO_TITLE);
		View decorView = a.getWindow().getDecorView();
		decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		if (Build.VERSION.SDK_INT >= 19) {
			/*
			 * if (Build.VERSION.SDK_INT >= 19) {
			 * decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
			 * | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
			 * View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
			 * View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
			 * View.SYSTEM_UI_FLAG_FULLSCREEN |
			 * View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY); }
			 */
		}
	}


	public static void fullScreenResume(final Activity a) {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			public void run() {
				// execute after 500ms
				hideNavBar(a);
			}
		}, 500);
	}

	public static void hideNavBar(Activity a) {
		View v = a.getWindow().getDecorView();
		v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		/*if (Build.VERSION.SDK_INT >= 19) {
			v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}*/
	}

	/* GLOW BOUTON */

	/**
	 * Met en surbrillance anime un bouton rond
	 * 
	 * @param bouton
	 * @param ctx
	 * @param relativeID
	 *            L'id que l'on veut donner au relative layout qui va englober
	 *            bouton + glow
	 * @return
	 */
	public static ImageView makeGlow(Button bouton, Context ctx, int relativeID) {
		ViewGroup parent = (ViewGroup) bouton.getParent();

		RelativeLayout.LayoutParams params = (LayoutParams) bouton
				.getLayoutParams();
		RelativeLayout rl = new RelativeLayout(ctx);
		rl.setId(relativeID);
		rl.setLayoutParams(params);
		parent.addView(rl);

		parent.setClipChildren(false);
		if (parent.getParent() != null) {
			ViewGroup pp = (ViewGroup) parent.getParent();
			pp.setClipChildren(false);
		}

		RelativeLayout.LayoutParams bouton_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		bouton_params.addRule(RelativeLayout.CENTER_VERTICAL);
		bouton_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		bouton.setLayoutParams(bouton_params);
		parent.removeView(bouton);
		rl.addView(bouton);

		ImageView glow = new ImageView(ctx);
		glow.setBackgroundDrawable(ctx.getResources().getDrawable(
				R.drawable.glow_circle));
		glow.setLayoutParams(bouton_params);
		glow.setAlpha(0.7f);

		rl.addView(glow);
		glow.startAnimation(AnimationUtils
				.loadAnimation(ctx, R.anim.glow_scale));

		return glow;
	}

	/**
	 * Stoppe la surbrillance anime d'un bouton rond
	 * 
	 * @param bouton
	 */
	public static void stopGlow(Button bouton) {
		ViewGroup rl = (ViewGroup) bouton.getParent();
		RelativeLayout.LayoutParams params = (LayoutParams) rl
				.getLayoutParams();
		ViewGroup parent = (ViewGroup) rl.getParent();

		rl.removeAllViews();
		parent.removeView(rl);
		bouton.setLayoutParams(params);
		parent.addView(bouton);
	}

	/* POLICES DE TEXTE */

	/**
	 * Change la police d'ecriture d'une vue affichant du texte par une police
	 * issu d'un fichier place dans assets/fonts
	 * 
	 * @param a
	 *            l'activite
	 * @param v
	 *            la vue qui affiche le texte (Bouton, TextView...)
	 * @param font
	 *            le nom de la resource (ex: "comic.ttf")
	 */
	public static void setFont(Activity a, View v, String font) {
		Typeface externalFont = Typeface.createFromAsset(a.getAssets(),
				"fonts/" + font);
		if (v instanceof TextView) {
			((TextView) v).setTypeface(externalFont);

		} else if (v instanceof Button) {
			((Button) v).setTypeface(externalFont);
		}
	}

	/* TAILLE DES VUES */

	/**
	 * Redimensionne une vue positionnée dans un RelativeLayout
	 * 
	 * @param view
	 *            La vue
	 * @param h
	 *            La hauteur souhaitee en px (0 pour wrap_content)
	 * @param w
	 *            La largeur souhaitee en px (0 pour wrap_content)
	 */
	public static void setSize(View view, int h, int w) {
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view
				.getLayoutParams();
		if (h == 0) {
			params.height = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
		} else {
			params.height = h;
		}
		if (w == 0) {
			params.width = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
		} else {
			params.width = w;
		}
		view.setLayoutParams(params);
	}
	
	/**
	 * Redimensionne une vue positionnée dans un LinearLayout
	 * 
	 * @param view
	 *            La vue
	 * @param h
	 *            La hauteur souhaitee en px (0 pour wrap_content)
	 * @param w
	 *            La largeur souhaitee en px (0 pour wrap_content)
	 */
	public static void setSizeLinear(View view, int h, int w) {
		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view
				.getLayoutParams();
		if (h == 0) {
			params.height = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
		} else {
			params.height = h;
		}
		if (w == 0) {
			params.width = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
		} else {
			params.width = w;
		}
		view.setLayoutParams(params);
	}

	/* OMBRES */

	public static void addShadow(View view) {
		ViewGroup parent = (ViewGroup) view.getParent();
		int index = parent.indexOfChild(view);
		MyLayoutParams params = new MyLayoutParams(view); // on donnera a
															// l'ombre les memes
															// params que la vue
		params.alignTop(view).alignBottom(view).alignEnd(view).alignStart(view);
		ImageView shadow = new ImageView(view.getContext());
		shadow.setBackgroundDrawable(view.getContext().getResources()
				.getDrawable(R.drawable.shadow_large));
		parent.addView(shadow, index - 1, params); // place l'ombre juste en
													// dessous de la vue
		parent.invalidate();

	}
}
