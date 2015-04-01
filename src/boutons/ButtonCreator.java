package boutons;

import com.Atlas.framework.R;

import divers.Couleur;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class ButtonCreator {

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
	 * Change l'apparence d'un bouton pour lui donner clele du bouton bleu
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
		//RelativeLayout.LayoutParams params = (LayoutParams) b.getLayoutParams();
		//params.height = 200;
		//params.width = 500;
		//b.setLayoutParams(params);
		b.setText(nomBouton);
		b.setTextSize(30f);
		b.setTextColor(c.getResources().getColor(R.color.jaune1));
		b.setPadding(0, 0, 0, 15);
	}

	
	/**
	 * Cree un bouton en relief classique a partir d'une couleur
	 * @param c Le contexte
	 * @param color La couleur
	 * @return Le bouton
	 */
	public static Button createButton(Context c,int color) {
		LayerDrawable layerDrawable = (LayerDrawable) c.getResources().getDrawable(R.drawable.custom_bouton);
		
		GradientDrawable fond = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.fond);
		fond.setColor(Couleur.darkenColor(color));
		
		GradientDrawable devant = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.devant);
		int[] colors = {Couleur.lightenColor(color),color};
		devant.setColors(colors);
		
		Button bouton = new Button(c);
		bouton.setHeight(80);
		bouton.setWidth(200);
		bouton.setBackground(layerDrawable);
		return bouton;
	}

}
