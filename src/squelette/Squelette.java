package squelette;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import boutons.ButtonCreator;

import com.Atlas.framework.R;

/**
 * permet rapidement la creation d'un menu a X elements, relativement
 * parametrables afin de simplifier la creation d'un menu (ici)
 * 
 * @author Victor
 * 
 */
public class Squelette {

	private int nbElements;
	private Context context;
	RelativeLayout[] squelette;

	public Squelette(int nbElements, Context context) {
		this.context = context;
		this.nbElements = nbElements;
		squelette = new RelativeLayout[nbElements+1];
	}

	/**
	 * cree un squelette de layout "vide" qui seront rempli par la suite.
	 * ces layouts sonr centres horizontalement et ce suivent de haut en bas
	 * ils sont stockes dans un tableau dont l'index indique la position sur l'ecran
	 * l'index 0 designe le layout parent qui occup tout l'ecran et englobe les autres
	 * @param height
	 * @param width
	 * @return
	 */
	public RelativeLayout[] createSquelette(int height, int width) {

		RelativeLayout parent = new RelativeLayout(context);
		RelativeLayout.LayoutParams parent_params = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		parent.setLayoutParams(parent_params);

		squelette[0] = parent;
		
		for (int i = 1; i <= nbElements; i++) {
			squelette[i] = new RelativeLayout(context);
			parent.addView(squelette[i]);

			RelativeLayout.LayoutParams i_params = new LayoutParams(width,height); 
			if (i == 1) {
				i_params.addRule(RelativeLayout.ALIGN_PARENT_TOP);

			} else {
				i_params.setMargins(0, (i-1)*(height+30), 0, 0);
			}
			i_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
			// i_params.addRule(RelativeLayout.BELOW,118*i);
			squelette[i].setLayoutParams(i_params);
			squelette[i].setBackground(context.getResources().getDrawable(
					R.color.fushia));
		}

		return squelette;

	}

	public RelativeLayout getRl(int place) {
		return squelette[place];
	}

	/**
	 * permet d'ajouter un bouton de la couleur choisie avec un texte
	 * le bouton est ajoute a la place voulue sur l'ecran (toujour centre horizontalement)
	 * @param text
	 * @param color
	 * @param place
	 */
	public void addBouton(String text, int color, int place) {

		if(place <= nbElements) {
		RelativeLayout r = squelette[place];
		Button bouton = ButtonCreator.createButton(context, color);
		RelativeLayout.LayoutParams params = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		bouton.setLayoutParams(params);
		bouton.setText(text);
		r.addView(bouton);
		}
		else Log.d("Attention","trop d'elements dans votre menu !");
	}

	/**
	 * permet d'ajouter un texte de la couleur choisie avec un texte
	 * le bouton est ajoute a la place voulue sur l'ecran (toujour centre horizontalement)
	 * @param text
	 * @param color
	 * @param place
	 */
	public void addText(String text, int color, int place) {

		if(place <= nbElements) {
		RelativeLayout r = squelette[place];
		TextView texte = new TextView(context);
		texte.setTextColor(context.getResources().getColor(color));
		texte.setText(text);
		texte.setTextSize(30);
		RelativeLayout.LayoutParams params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		texte.setLayoutParams(params);
		texte.setElevation(4);
		r.addView(texte);
		}
		else Log.d("Attention","trop d'elements dans votre menu !");
	}

	/**
	 * permet d'ajouter un background de la couleur choisie avec un drawable en fond (en bas a gauche)
	 * le background est ajoute au layout voulue sur l'ecran avec le num de ce layout
	 * @param text
	 * @param color
	 * @param place
	 */
	public void addBackground(int d, int color, int place) {

		if(place <= nbElements) {
		RelativeLayout r = squelette[place];
		if (d != 0) {
			RelativeLayout r2 = new RelativeLayout(context);
			r.addView(r2);
			RelativeLayout.LayoutParams params = new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.ALIGN_LEFT);
			params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			r2.setLayoutParams(params);
			r2.setBackground(context.getResources().getDrawable(d));
		}
		if(color!=0) r.setBackgroundColor(context.getResources().getColor(color));
		}
		else Log.d("Attention","le lieu ou vous voulez mettre le background n'existe pas !");
		

	}

}
