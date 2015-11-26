package boutons;

import com.Atlas.framework.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import composants.Couleur;

/**
 * classe qui permet de creer rapidement un bouton de la couleur de son choix
 * 
 * @author Victor,Nicklos
 */
public class Bouton {

	private final Button bouton;

	private final ImageButton image_bouton;

	public Bouton(final Activity a) {
		bouton = new Button(a);
		image_bouton = new ImageButton(a);
	}

	/**
	 * Cree une image de bouton carre a partir d'une couleur
	 * 
	 * @param color
	 *            La couleur servant de base au bouton
	 * @return L'image du bouton
	 */
	public static LayerDrawable createButtonDrawable(final int color) {
		final GradientDrawable fond = new GradientDrawable();
		fond.setShape(GradientDrawable.RECTANGLE);
		fond.setCornerRadius(15);
		fond.setColor(Couleur.darken(color));

		final GradientDrawable devant = new GradientDrawable();
		devant.setShape(GradientDrawable.RECTANGLE);
		devant.setCornerRadius(15);
		final int[] colors = { Couleur.lighten(color), color };
		if (Build.VERSION.SDK_INT >= 16) {
			devant.mutate();
			devant.setColors(colors);
			devant.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
		}

		final GradientDrawable[] layers = { fond, devant };
		final LayerDrawable res = new LayerDrawable(layers);
		res.setLayerInset(0, 0, 0, 0, 0);
		res.setLayerInset(1, 2, 2, 2, 15);
		return res;
	}

	/**
	 * Cree une image de bouton carre presse a partir d'une couleur
	 * 
	 * @param color
	 *            La couleur seravnt de base au bouton
	 * @return L'image du bouton
	 */
	public static LayerDrawable createButtonPressedDrawable(final Context c,
			int color) {
		color = c.getResources().getColor(color);
		final GradientDrawable fond = new GradientDrawable();
		fond.setShape(GradientDrawable.RECTANGLE);
		fond.setCornerRadius(15);
		fond.setColor(Couleur.darken(color));

		final GradientDrawable devant = new GradientDrawable();
		devant.setShape(GradientDrawable.RECTANGLE);
		devant.setCornerRadius(15);
		final int[] colors = { color, Couleur.lighten(color), color };
		if (Build.VERSION.SDK_INT >= 16) {
			devant.mutate();
			devant.setColors(colors);
			devant.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
		}

		final GradientDrawable[] layers = { fond, devant };
		final LayerDrawable res = new LayerDrawable(layers);
		res.setLayerInset(0, 0, 0, 0, 0);
		res.setLayerInset(1, 2, 2, 2, 6);
		return res;
	}

	/**
	 * Cree une image de bouton arrondi a partir d'une couleur
	 * 
	 * @param color
	 *            La couleur servant de base au bouton
	 * @param f
	 *            la largeur de l'image
	 * @return L'image du bouton
	 */
	public static LayerDrawable roundedDrawable(final Activity a, int color,
			final float f) {
		color = a.getResources().getColor(color);
		final Display display = a.getWindowManager().getDefaultDisplay();
		final Point size = new Point();
		display.getSize(size);
		final int H = size.y;
		final int W = size.x;
		final int margin = (H + W) / 200;

		final GradientDrawable fond = new GradientDrawable();
		fond.setShape(GradientDrawable.RECTANGLE);
		fond.setCornerRadius(1000);
		final int fonce = Couleur.darken(color);
		final int[] colors0 = { fonce, color };
		if (Build.VERSION.SDK_INT >= 16) {
			fond.mutate();
			fond.setColors(colors0);
			fond.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
		}
		fond.setStroke(margin / 3, Couleur.darken(fonce));
		final int width = (int) (f * (W / 3));
		fond.setSize(width, H / 8);

		final GradientDrawable devant = new GradientDrawable();
		devant.setShape(GradientDrawable.RECTANGLE);
		devant.setCornerRadius(1000);
		final int clair = Couleur.lighten(color);
		final int[] colors = { color, clair };
		if (Build.VERSION.SDK_INT >= 16) {
			devant.mutate();
			devant.setColors(colors);
			devant.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
		}
		devant.setStroke(margin / 4, Couleur.lighten(Couleur.lighten(clair)));

		final GradientDrawable[] layers = { fond, devant };
		final LayerDrawable res = new LayerDrawable(layers);
		res.setLayerInset(0, 0, 0, 0, 0);
		res.setLayerInset(1, margin, margin, margin, margin);
		return res;
	}

	/**
	 * Cree une image de bouton arrondi presse a partir d'une couleur
	 * 
	 * @param color
	 *            La couleur servant de base au bouton
	 * @param f
	 *            la largeur de l'image
	 * @return L'image du bouton
	 */
	public static LayerDrawable pressedRoundedDrawable(final Activity a,
			int color, final float f) {
		color = a.getResources().getColor(color);
		final Display display = a.getWindowManager().getDefaultDisplay();
		final Point size = new Point();
		display.getSize(size);
		final int H = size.y;
		final int W = size.x;
		final int margin = (H + W) / 200;

		final GradientDrawable fond = new GradientDrawable();
		fond.setShape(GradientDrawable.RECTANGLE);
		fond.setCornerRadius(1000);
		final int clair = Couleur.lighten(color);
		final int[] colors0 = { color, clair };
		if (Build.VERSION.SDK_INT >= 16) {
			fond.mutate();
			fond.setColors(colors0);
			fond.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
		}
		fond.setStroke(margin / 3, Couleur.darken(color));
		final int width = (int) (f * (W / 3));
		fond.setSize(width, H / 8);

		final GradientDrawable devant = new GradientDrawable();
		devant.setShape(GradientDrawable.RECTANGLE);
		devant.setCornerRadius(1000);
		final int clair2 = Couleur.lighten(clair);
		final int[] colors = { clair, clair2 };
		if (Build.VERSION.SDK_INT >= 16) {
			devant.mutate();
			devant.setColors(colors);
			devant.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
		}
		devant.setStroke(margin / 4, Couleur.lighten(Couleur.lighten(clair2)));

		final GradientDrawable[] layers = { fond, devant };
		final LayerDrawable res = new LayerDrawable(layers);
		res.setLayerInset(0, 0, 0, 0, 0);
		res.setLayerInset(1, margin, margin, margin, margin);
		return res;
	}

	/**
	 * Cree un bouton en relief carre a partir d'une couleur
	 * 
	 * @param c
	 *            Le contexte
	 * @param color
	 *            L'identifiant de la couleur ex(R.color.bleu)
	 * @return Le bouton
	 */
	public static Button createButton(final Context c, int color) {
		color = c.getResources().getColor(color);
		final LayerDrawable layerDrawable = createButtonDrawable(color);
		final Button bouton = new Button(c);
		bouton.setHeight(80);
		bouton.setWidth(200);
		setBackground(bouton, layerDrawable);
		return bouton;
	}

	/**
	 * Cree un bouton en relief arrondi a partir d'une couleur
	 * 
	 * @param c
	 *            Le contexte
	 * @param color
	 *            L'identifiant de la couleur ex(R.color.bleu)
	 * @return Le bouton
	 */
	public static Button createRoundedButton(final Activity a, final int color) {
		final LayerDrawable layerDrawable = roundedDrawable(a, color, 1);
		final Button bouton = new Button(a);
		bouton.setHeight(100);
		bouton.setWidth(500);
		setBackground(bouton, layerDrawable);
		return bouton;
	}

	/**
	 * Change l'apparence d'un bouton selon des couleurs et un un texte
	 * 
	 * @param c
	 *            Le contexte de l'activite
	 * @param b
	 *            Le bouton a modifier
	 * @param color
	 *            L'id de la couleur qui sert de base au bouton
	 *            (ex:R.color.bleu)
	 * @param textColor
	 *            L'id de la couleur du texte du bouton (ex:R.color.bleu)
	 * @param nomBouton
	 *            Le texte a mettre sur le bouton
	 */
	public static void setButtonStyle(final Context c, final Button b,
			int color, final String nomBouton, int textColor) {

		color = c.getResources().getColor(color);
		textColor = c.getResources().getColor(textColor);
		final LayerDrawable layerDrawable = createButtonDrawable(color);
		final Typeface comic = Typeface.createFromAsset(c.getAssets(),
				"fonts/comic.otf");
		b.setTypeface(comic);
		b.setAllCaps(false);
		b.setText(nomBouton);
		b.setTextSize(30f);
		b.setTextColor(textColor);
		b.setPadding(0, 0, 0, 15);
		setBackground(b, layerDrawable);
	}

	/**
	 * Met en srubrillance un bouton rond
	 * 
	 * @param bouton
	 * @param ctx
	 * @param relativeID
	 *            L'id que l'on veut donner au relative layout qui va englober
	 *            bouton + glow
	 * @return
	 */
	public static ImageView makeGlow(final Button bouton, final Context ctx,
			final int relativeID) {
		final ViewGroup parent = (ViewGroup) bouton.getParent();
		final RelativeLayout.LayoutParams params = (LayoutParams) bouton
				.getLayoutParams();
		final RelativeLayout rl = new RelativeLayout(ctx);
		rl.setId(relativeID);
		rl.setLayoutParams(params);
		parent.addView(rl);

		parent.setClipChildren(false);
		if (parent.getParent() != null) {
			final ViewGroup pp = (ViewGroup) parent.getParent();
			pp.setClipChildren(false);
		}

		final RelativeLayout.LayoutParams bouton_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		bouton_params.addRule(RelativeLayout.CENTER_VERTICAL);
		bouton_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		bouton.setLayoutParams(bouton_params);
		parent.removeView(bouton);
		rl.addView(bouton);
		if (Build.VERSION.SDK_INT >= 21) {
			final float elevation = bouton.getElevation();
			rl.setElevation(elevation);
		}

		final ImageView glow = new ImageView(ctx);
		if (Build.VERSION.SDK_INT >= 16) {
			glow.setBackground(ctx.getResources().getDrawable(
					R.drawable.glow_circle));
		} else {
			glow.setBackgroundDrawable(ctx.getResources().getDrawable(
					R.drawable.glow_circle));
		}
		glow.setLayoutParams(bouton_params);
		glow.setAlpha(0.7f);

		rl.addView(glow);
		glow.startAnimation(AnimationUtils
				.loadAnimation(ctx, R.anim.glow_scale));

		return glow;
	}

	/**
	 * Enleve le glow d'un bouton
	 * 
	 * @param bouton
	 *            Le bouton en srubrillance
	 */
	public static void stopGlow(final Button bouton) {
		final ViewGroup rl = (ViewGroup) bouton.getParent();
		final RelativeLayout.LayoutParams params = (LayoutParams) rl
				.getLayoutParams();
		final ViewGroup parent = (ViewGroup) rl.getParent();

		rl.removeAllViews();
		parent.removeView(rl);
		bouton.setLayoutParams(params);
		parent.addView(bouton);

	}

	// les prochaines methodes permettent de changer les param�tres des boutons
	// avec une ecriture fluent: on peut chainer les methodes pour plus de
	// lisibilite et de concision.

	/**
	 * Methode pour l'ecriture fluent
	 * 
	 * @param context
	 * @return
	 */
	public static Bouton create(final Activity a) {
		return new Bouton(a);
	}

	/**
	 * Change le background drawable du bouton
	 * 
	 * @param d
	 * @return
	 */
	public Bouton setBack(final Drawable d) {
		setBackground(bouton, d);
		setBackground(image_bouton, d);
		return this;
	}

	/**
	 * Change le background drawable du bouton en miroir
	 * 
	 * @param d
	 * @return
	 */
	public Bouton mirror() {
		bouton.setScaleX(-1f);
		image_bouton.setScaleX(-1f);
		return this;
	}

	/**
	 * Change le texte du bouton
	 * 
	 * @param d
	 * @return
	 */
	public Bouton setText(final String s) {
		bouton.setText(s);
		return this;
	}

	/**
	 * Change le taille du texte du bouton
	 * 
	 * @param d
	 * @return
	 */
	public Bouton setTextSize(final float s) {
		bouton.setTextSize(s);
		return this;
	}

	/**
	 * Change la couleur du texte du bouton
	 * 
	 * @param d
	 * @return
	 */
	public Bouton setTextColor(final int color) {
		final int true_color = bouton.getContext().getResources()
				.getColor(color);
		bouton.setTextColor(true_color);
		return this;
	}

	/**
	 * Retourne le bouton cree
	 * 
	 * @return le bouton
	 */
	public Button build() {
		return bouton;
	}

	/**
	 * Retourne le image bouton cree
	 * 
	 * @return le bouton
	 */
	public ImageButton buildImage() {
		return image_bouton;
	}

	/**
	 * sets the background of a view depending on the API
	 * 
	 * @param v
	 * @param d
	 */
	@SuppressWarnings("deprecation")
	private static void setBackground(final View v, final Drawable d) {
		if (Build.VERSION.SDK_INT >= 16) {
			v.setBackground(d);
		} else {
			v.setBackgroundDrawable(d);
		}
	}

}
