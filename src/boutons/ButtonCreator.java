package boutons;

import com.Atlas.framework.R;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class ButtonCreator {

	/**
	 * Cree un bouton bleu classique avec un texte donne
	 * @param c Le contexte de l'activite
	 * @param nomBouton Le texte a mettre sur le bouton
	 * @return Le bouton cree
	 */
	public static Button createBlueButton(Context c,String nomBouton){
		Button b = new Button(c);
		b.setBackground(c.getResources().getDrawable(R.drawable.bouton_bleu));
		b.setLayoutParams(new LinearLayout.LayoutParams(400, 150));
		b.setText(nomBouton);
		b.setTextColor(c.getResources().getColor(R.color.jaune1));
		b.setPadding(0, 0, 0, 10);
		return b;
	}
	
	/**
	 * Change l'apparence d'un bouton pour lui donner clele du bouton bleu classique
	 * @param c Le contexte de l'activite
	 * @param b Le bouton a modifier
	 * @param nomBouton Le texte a mettre sur le bouton
	 */
	public static void setBlueButton(Context c,Button b, String nomBouton){
		b.setBackground(c.getResources().getDrawable(R.drawable.bouton_bleu_w));
		RelativeLayout.LayoutParams params =  (LayoutParams) b.getLayoutParams();
		params.height = 200;
		params.width = 500;
		b.setLayoutParams(params);
		b.setText(nomBouton);
		b.setTextSize(30f);
		b.setTextColor(c.getResources().getColor(R.color.jaune1));
		b.setPadding(0, 0, 0, 15);
	}
	
}
