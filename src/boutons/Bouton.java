package boutons;


import com.Atlas.framework.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Display;
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
 *
 */
public class Bouton {

	private Button bouton;
	private ImageButton image_bouton;
	
	public Bouton(Activity a){
		bouton = new Button(a);
		image_bouton = new ImageButton(a);
	}
	
	/**
	 * Cree une image de bouton carre a partir d'une couleur
	 * @param color La couleur servant de base au bouton
	 * @return L'image du bouton
	 */
	public static LayerDrawable createButtonDrawable(int color){	
		GradientDrawable fond = new GradientDrawable();
	    fond.setShape(GradientDrawable.RECTANGLE);
	    fond.setCornerRadius(15);
	    fond.setColor(Couleur.darken(color));
	    
		GradientDrawable devant = new GradientDrawable();
	    devant.setShape(GradientDrawable.RECTANGLE);
	    devant.setCornerRadius(15);
	    int[] colors = { Couleur.lighten(color), color };
	    devant.setColors(colors);
	    devant.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
	    
	    GradientDrawable[] layers = {fond,devant};
	    LayerDrawable res = new LayerDrawable(layers);
	    res.setLayerInset(0, 0, 0, 0, 0);
	    res.setLayerInset(1, 2, 2, 2, 15);
	    return res;
	}
	
	/**
	 * Cree une image de bouton carre presse a partir d'une couleur
	 * @param color La couleur seravnt de base au bouton
	 * @return L'image du bouton
	 */
	public static LayerDrawable createButtonPressedDrawable(Context c,int color){
		color = c.getResources().getColor(color);
		GradientDrawable fond = new GradientDrawable();
	    fond.setShape(GradientDrawable.RECTANGLE);
	    fond.setCornerRadius(15);
	    fond.setColor(Couleur.darken(color));
	    
		GradientDrawable devant = new GradientDrawable();
	    devant.setShape(GradientDrawable.RECTANGLE);
	    devant.setCornerRadius(15);
	    int[] colors = { color,Couleur.lighten(color), color };
	    devant.setColors(colors);
	    devant.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
	    
	    GradientDrawable[] layers = {fond,devant};
	    LayerDrawable res = new LayerDrawable(layers);
	    res.setLayerInset(0, 0, 0, 0, 0);
	    res.setLayerInset(1, 2, 2, 2, 6);
	    return res;
	}
	
	/**
	 * Cree une image de bouton arrondi a partir d'une couleur
	 * @param color La couleur servant de base au bouton
	 * @param f la largeur de l'image
	 * @return L'image du bouton
	 */
	public static LayerDrawable roundedDrawable(Activity a,int color,float f){
		color = a.getResources().getColor(color);
		Display display = a.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int H = size.y;
		int W = size.x;
	    int margin = (H+W)/200;
		
		GradientDrawable fond = new GradientDrawable();
	    fond.setShape(GradientDrawable.RECTANGLE);
	    fond.setCornerRadius(1000);
	    int fonce = Couleur.darken(color);
	    int[] colors0 = { fonce, color };
	    fond.setColors(colors0);
	    fond.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
	    fond.setStroke(margin/3, Couleur.darken(fonce));
	    int width = (int) (f*(W/3));
	    fond.setSize(width,H/8);
	    
		GradientDrawable devant = new GradientDrawable();
	    devant.setShape(GradientDrawable.RECTANGLE);
	    devant.setCornerRadius(1000);
	    int clair = Couleur.lighten(color);
	    int[] colors = { color, clair };
	    devant.setColors(colors);
	    devant.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
	    devant.setStroke(margin/4, Couleur.lighten(Couleur.lighten(clair)));
	    
	    GradientDrawable[] layers = {fond,devant};
	    LayerDrawable res = new LayerDrawable(layers);
	    res.setLayerInset(0, 0, 0, 0, 0);
	    res.setLayerInset(1,margin,margin,margin,margin);
	    return res;
	}
	
	/**
	 * Cree une image de bouton arrondi presse a partir d'une couleur
	 * @param color La couleur servant de base au bouton
	 * @param f la largeur de l'image
	 * @return L'image du bouton
	 */
	public static LayerDrawable pressedRoundedDrawable(Activity a,int color,float f){
		color = a.getResources().getColor(color);
		Display display = a.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int H = size.y;
		int W = size.x;
	    int margin = (H+W)/200;
		
		GradientDrawable fond = new GradientDrawable();
	    fond.setShape(GradientDrawable.RECTANGLE);
	    fond.setCornerRadius(1000);
	    int clair = Couleur.lighten(color);
	    int[] colors0 = { color, clair };
	    fond.setColors(colors0);
	    fond.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
	    fond.setStroke(margin/3, Couleur.darken(color));
	    int width = (int) (f*(W/3));
	    fond.setSize(width,H/8);
	    
		GradientDrawable devant = new GradientDrawable();
	    devant.setShape(GradientDrawable.RECTANGLE);
	    devant.setCornerRadius(1000);
	    int clair2 = Couleur.lighten(clair);
	    int[] colors = { clair, clair2 };
	    devant.setColors(colors);
	    devant.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
	    devant.setStroke(margin/4, Couleur.lighten(Couleur.lighten(clair2)));
	    
	    GradientDrawable[] layers = {fond,devant};
	    LayerDrawable res = new LayerDrawable(layers);
	    res.setLayerInset(0, 0, 0, 0, 0);
	    res.setLayerInset(1,margin,margin,margin,margin);
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
	public static Button createButton(Context c, int color) {
		color = c.getResources().getColor(color);
		LayerDrawable layerDrawable = createButtonDrawable(color);
		Button bouton = new Button(c);
		bouton.setHeight(80);
		bouton.setWidth(200);
		bouton.setBackground(layerDrawable);
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
	public static Button createRoundedButton(Activity a,int color) {
		LayerDrawable layerDrawable = roundedDrawable(a,color,1);
		Button bouton = new Button(a);
		bouton.setHeight(100);
		bouton.setWidth(500);
		bouton.setBackground(layerDrawable);
		return bouton;
	}

	/**
	 * Change l'apparence d'un bouton selon des couleurs et un un texte
	 * 
	 * @param c
	 *            Le contexte de l'activite
	 * @param b
	 *            Le bouton a modifier
	 * 
	 * @param color
	 *            L'id de la couleur qui sert de base au bouton (ex:R.color.bleu)
	 * @param textColor
	 *            L'id de la couleur du texte du bouton (ex:R.color.bleu)
	 * @param nomBouton
	 *            Le texte a mettre sur le bouton
	 */
	public static void setButtonStyle(Context c, Button b, int color,
			String nomBouton, int textColor) {

		color = c.getResources().getColor(color);
		textColor = c.getResources().getColor(textColor);
		LayerDrawable layerDrawable = createButtonDrawable(color);
        Typeface comic = Typeface.createFromAsset(c.getAssets(),"fonts/comic.otf");
		b.setTypeface(comic);
		b.setAllCaps(false);
		b.setText(nomBouton);
		b.setTextSize(30f);
		b.setTextColor(textColor);
		b.setPadding(0, 0, 0, 15);
		b.setBackground(layerDrawable);
	}
	
	/** Met en srubrillance un bouton rond
	 * @param bouton
	 * @param ctx
	 * @param relativeID L'id que l'on veut donner au relative layout qui va englober bouton + glow
	 * @return
	 */
	public static ImageView makeGlow(Button bouton, Context ctx,int relativeID) {
		ViewGroup parent = (ViewGroup) bouton.getParent();

		float elevation = bouton.getElevation();

		RelativeLayout.LayoutParams params = (LayoutParams) bouton
				.getLayoutParams();
		RelativeLayout rl = new RelativeLayout(ctx);
		rl.setId(relativeID);
		rl.setLayoutParams(params);
		parent.addView(rl);

		parent.setClipChildren(false);
		if(parent.getParent()!=null){
			ViewGroup pp = (ViewGroup) parent.getParent();
			pp.setClipChildren(false);
		}

		RelativeLayout.LayoutParams bouton_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		bouton_params.addRule(RelativeLayout.CENTER_VERTICAL);
		bouton_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		bouton.setLayoutParams(bouton_params);
		parent.removeView(bouton);
		rl.addView(bouton);
		rl.setElevation(elevation);

		ImageView glow = new ImageView(ctx);
		glow.setBackground(ctx.getResources().getDrawable(
				R.drawable.glow_circle));
		glow.setLayoutParams(bouton_params);
		glow.setAlpha(0.7f);

		rl.addView(glow);
		glow.startAnimation(AnimationUtils
				.loadAnimation(ctx, R.anim.glow_scale));

		return glow;
	}
	
	/**
	 * Enleve le glow d'un bouton
	 * @param bouton Le bouton en srubrillance
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
	
	// les prochaines methodes permettent de changer les paramètres des boutons
	// avec une ecriture fluent: on peut chainer les methodes pour plus de
	// lisibilite et de concision.
	
	/**
	 *
	 * Methode pour l'ecriture fluent
	 * @param context
	 * @return 
	 */
	public static Bouton create(Activity a){
		return new Bouton(a) ;	}

	/**
	 * Change le background drawable du bouton
	 * @param d
	 * @return
	 */
	public Bouton setBack(Drawable d){
		bouton.setBackground(d);
		image_bouton.setBackground(d);
		return this;
	}
	
	/**
	 * Change le background drawable du bouton en miroir
	 * @param d
	 * @return
	 */
	public Bouton mirror(){
		bouton.setScaleX(-1f);
		image_bouton.setScaleX(-1f);
		return this;
	}
	
	/**
	 * Change le texte du bouton
	 * @param d
	 * @return
	 */
	public Bouton setText(String s){
		bouton.setText(s);
		return this;
	}
	
	/**
	 * Change le taille du texte du bouton
	 * @param d
	 * @return
	 */
	public Bouton setTextSize(float s){
		bouton.setTextSize(s);
		return this;
	}
	
	/**
	 * Change la couleur du texte du bouton
	 * @param d
	 * @return
	 */
	public Bouton setTextColor(int color){
		int true_color = bouton.getContext().getResources().getColor(color);
		bouton.setTextColor(true_color);
		return this;
	}
	
	/**
	 * Retourne le bouton cree
	 * @return le bouton
	 */
	public Button build(){
		return bouton;
	}
	
	/**
	 * Retourne le image bouton cree
	 * @return le bouton
	 */
	public ImageButton buildImage(){
		return image_bouton;
	}
	
	
}
