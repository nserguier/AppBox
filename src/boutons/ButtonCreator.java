package boutons;


import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.Button;
import android.widget.ImageButton;

import composants.Couleur;


public class ButtonCreator {

	Button bouton;
	ImageButton image_bouton;
	
	public ButtonCreator(Context c){
		bouton = new Button(c);
		image_bouton = new ImageButton(c);
	}
	
	/**
	 * Cree une image de bouton a partir d'une couleur
	 * @param color La couleur seravnt de base au bouton
	 * @return L'image du bouton
	 */
	public static LayerDrawable createButtonDrawable(int color){	
		GradientDrawable fond = new GradientDrawable();
	    fond.setShape(GradientDrawable.RECTANGLE);
	    fond.setCornerRadius(15);
	    fond.setColor(Couleur.darkenColor(color));
	    
		GradientDrawable devant = new GradientDrawable();
	    devant.setShape(GradientDrawable.RECTANGLE);
	    devant.setCornerRadius(15);
	    int[] colors = { Couleur.lightenColor(color), color };
	    devant.setColors(colors);
	    devant.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
	    
	    GradientDrawable[] layers = {fond,devant};
	    LayerDrawable res = new LayerDrawable(layers);
	    res.setLayerInset(0, 0, 0, 0, 0);
	    res.setLayerInset(1, 2, 2, 2, 15);
	    return res;
	}
	
	/**
	 * Cree une image de bouton arrondi a partir d'une couleur
	 * @param color La couleur seravnt de base au bouton
	 * @return L'image du bouton
	 */
	public static LayerDrawable roundedDrawable(int color,float f){	
		GradientDrawable fond = new GradientDrawable();
	    fond.setShape(GradientDrawable.RECTANGLE);
	    fond.setCornerRadius(1000);
	    int fonce = Couleur.darkenColor(color);
	    int[] colors0 = { fonce, color };
	    fond.setColors(colors0);
	    fond.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
	    fond.setStroke(3, Couleur.darkenColor(fonce));
	    int width = (int) (f*430);
	    fond.setSize(width,110);
	    
		GradientDrawable devant = new GradientDrawable();
	    devant.setShape(GradientDrawable.RECTANGLE);
	    devant.setCornerRadius(1000);
	    int clair = Couleur.lightenColor(color);
	    int[] colors = { color, clair };
	    devant.setColors(colors);
	    devant.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
	    devant.setStroke(2, Couleur.lightenColor(Couleur.lightenColor(clair)));
	    
	    GradientDrawable[] layers = {fond,devant};
	    LayerDrawable res = new LayerDrawable(layers);
	    res.setLayerInset(0, 0, 0, 0, 0);
	    res.setLayerInset(1,12,12,12,12);
	    return res;
	}
	
	/**
	 * Cree une image de bouton arrondi a partir d'une couleur
	 * @param color La couleur seravnt de base au bouton
	 * @return L'image du bouton
	 */
	public static LayerDrawable pressedRoundedDrawable(int color,float f){	
		GradientDrawable fond = new GradientDrawable();
	    fond.setShape(GradientDrawable.RECTANGLE);
	    fond.setCornerRadius(1000);
	    int clair = Couleur.lightenColor(color);
	    int[] colors0 = { color, clair };
	    fond.setColors(colors0);
	    fond.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
	    fond.setStroke(3, Couleur.darkenColor(color));
	    int width = (int) (f*430);
	    fond.setSize(width,110);
	    
		GradientDrawable devant = new GradientDrawable();
	    devant.setShape(GradientDrawable.RECTANGLE);
	    devant.setCornerRadius(1000);
	    int clair2 = Couleur.lightenColor(clair);
	    int[] colors = { clair, clair2 };
	    devant.setColors(colors);
	    devant.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
	    devant.setStroke(2, Couleur.lightenColor(Couleur.lightenColor(clair2)));
	    
	    GradientDrawable[] layers = {fond,devant};
	    LayerDrawable res = new LayerDrawable(layers);
	    res.setLayerInset(0, 0, 0, 0, 0);
	    res.setLayerInset(1,12,12,12,12);
	    return res;
	}
	
	/**
	 * Cree une image de bouton presse a partir d'une couleur
	 * @param color La couleur seravnt de base au bouton
	 * @return L'image du bouton
	 */
	public static LayerDrawable createButtonPressedDrawable(Context c,int color){
		color = c.getResources().getColor(color);
		GradientDrawable fond = new GradientDrawable();
	    fond.setShape(GradientDrawable.RECTANGLE);
	    fond.setCornerRadius(15);
	    fond.setColor(Couleur.darkenColor(color));
	    
		GradientDrawable devant = new GradientDrawable();
	    devant.setShape(GradientDrawable.RECTANGLE);
	    devant.setCornerRadius(15);
	    int[] colors = { color,Couleur.lightenColor(color), color };
	    devant.setColors(colors);
	    devant.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
	    
	    GradientDrawable[] layers = {fond,devant};
	    LayerDrawable res = new LayerDrawable(layers);
	    res.setLayerInset(0, 0, 0, 0, 0);
	    res.setLayerInset(1, 2, 2, 2, 6);
	    return res;
	}
	

	/**
	 * Cree un bouton en relief classique a partir d'une couleur
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
	public static Button createRoundedButton(Context c, int color) {
		color = c.getResources().getColor(color);
		LayerDrawable layerDrawable = roundedDrawable(color,1);
		Button bouton = new Button(c);
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
	
	/**
	 * Methode pour l'ecriture fluent
	 * @param context
	 * @return 
	 */
	public static ButtonCreator create(Context context){
		return new ButtonCreator(context) ;
	}

	/**
	 * Change le background drawable du bouton
	 * @param d
	 * @return
	 */
	public ButtonCreator setBack(Drawable d){
		bouton.setBackground(d);
		image_bouton.setBackground(d);
		return this;
	}
	
	/**
	 * Change le background drawable du bouton en miroir
	 * @param d
	 * @return
	 */
	public ButtonCreator mirror(){
		bouton.setScaleX(-1f);
		image_bouton.setScaleX(-1f);
		return this;
	}
	
	/**
	 * Change le texte du bouton
	 * @param d
	 * @return
	 */
	public ButtonCreator setText(String s){
		bouton.setText(s);
		return this;
	}
	
	/**
	 * Change le taille du texte du bouton
	 * @param d
	 * @return
	 */
	public ButtonCreator setTextSize(float s){
		bouton.setTextSize(s);
		return this;
	}
	
	/**
	 * Change la couleur du texte du bouton
	 * @param d
	 * @return
	 */
	public ButtonCreator setTextColor(int color){
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
