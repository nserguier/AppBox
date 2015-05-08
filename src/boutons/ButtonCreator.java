package boutons;

import com.Atlas.framework.R;
import composants.Couleur;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

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
	    devant.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
	    
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
	public static LayerDrawable createButtonRoundedDrawable(int color){	
		GradientDrawable fond = new GradientDrawable();
	    fond.setShape(GradientDrawable.RECTANGLE);
	    fond.setCornerRadius(1000);
	    int fonce = Couleur.darkenColor(color);
	    fond.setColor(fonce);
	    fond.setStroke(3, Couleur.darkenColor(fonce));
	    
		GradientDrawable devant = new GradientDrawable();
	    devant.setShape(GradientDrawable.RECTANGLE);
	    devant.setCornerRadius(1000);
	    int[] colors = { Couleur.darkenColor(color), color };
	    devant.setColors(colors);
	    devant.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
	    devant.setStroke(2, Couleur.lightenColor(color));
	    
	    GradientDrawable[] layers = {fond,devant};
	    LayerDrawable res = new LayerDrawable(layers);
	    res.setLayerInset(0, 0, 0, 0, 0);
	    res.setLayerInset(1,12, 12, 12, 12);
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
	 * Cree un bouton bleu classique avec un texte donne
	 * 
	 * @param c
	 *            Le contexte de l'activite
	 * @param nomBouton
	 *            Le texte a mettre sur le bouton
	 * @return Le bouton cree
	 */
	public static Button createBlueButton(Context c, String nomBouton) {
		Button b = new Button(c);
		b.setBackground(c.getResources().getDrawable(R.drawable.bouton_bleu));
		b.setLayoutParams(new LinearLayout.LayoutParams(400, 150));
		b.setText(nomBouton);
		b.setTextColor(c.getResources().getColor(R.color.jaune1));
		b.setPadding(0, 0, 0, 10);
		return b;
	}

	/**
	 * Change l'apparence d'un bouton pour lui donner celle du bouton bleu
	 * classique
	 * 
	 * @param c
	 *            Le contexte de l'activite
	 * @param b
	 *            Le bouton a modifier
	 * @param nomBouton
	 *            Le texte a mettre sur le bouton
	 */
	public static void setBlueButton(Context c, Button b, String nomBouton) {

		b.setBackground(c.getResources().getDrawable(R.drawable.bouton_bleu));
		// RelativeLayout.LayoutParams params = (LayoutParams)
		// b.getLayoutParams();
		// params.height = 200;
		// params.width = 500;
		// b.setLayoutParams(params);
		b.setText(nomBouton);
		b.setTextSize(30f);
		b.setTextColor(c.getResources().getColor(R.color.jaune1));
		b.setPadding(0, 0, 0, 15);
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
		LayerDrawable layerDrawable = createButtonRoundedDrawable(color);
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
