package custom;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import boutons.ButtonCreator;
import boutons.HomeActivityListener;


import com.Atlas.framework.CustomMenuActivity;
import com.Atlas.framework.MainActivity;

public class MenuRapide {

	private static Button bouton;
	private static TextView titre;
	

	public static RelativeLayout create(int couleurBackground,
			String texteTitre, int couleurTitre, String textBouton,
			int couleurBouton, Activity a) {

		
		RelativeLayout menu = new RelativeLayout(a);
		RelativeLayout.LayoutParams menu_params = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		menu.setLayoutParams(menu_params);

		// creation du backroung

		int colorB = a.getResources().getColor(couleurBackground);
		menu.setBackgroundColor(colorB);

		// creation du bouton de couleur choisie

		int color = a.getResources().getColor(couleurBouton);
		bouton = ButtonCreator.createButton(a, color);
		bouton.setText(textBouton);
		bouton.setOnClickListener(new HomeActivityListener(a.getApplicationContext(), bouton, a,
				MainActivity.class));
		RelativeLayout.LayoutParams bouton_params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		bouton_params.addRule(RelativeLayout.CENTER_VERTICAL);
		bouton_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		bouton.setLayoutParams(bouton_params);
		bouton.setElevation(4);
		menu.addView(bouton);

		// creation du titre

		int colorTitre = a.getResources().getColor(couleurTitre);
		titre = new TextView(a);
		titre.setText(texteTitre);
		titre.setTextColor(colorTitre);
		titre.setTextSize(30);
		RelativeLayout.LayoutParams titre_params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		titre_params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		titre_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		titre.setLayoutParams(titre_params);
		titre.setElevation(4);
		menu.addView(titre);

		return menu;

	}

	public static Button getButton() {
		return bouton;
	}

	public static TextView getTitre() {
		return titre;
	}

}
