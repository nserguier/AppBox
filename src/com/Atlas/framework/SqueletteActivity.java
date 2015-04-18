package com.Atlas.framework;

import squelette.Squelette;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

/**
 * activite preremplie ou l'utilisateur rentre ces parametre aux endroits indiques
 * et qui permet de creer rapidement un menu relativement parametre
 * grace a des fonctions precodees
 * @author Victor
 *
 */
public class SqueletteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_squelette);
		
			
		// le nombre d'elements que vous souhaitez integrer au menu :
		int nbElements = 5; //TODO
		
		//creation du "squelette" de layout
		Squelette squelette = new Squelette(nbElements,this);
		
		// on parametre la taille de tous les layout du squelette
		squelette.createSquelette(200,700); //TODO
		
		//le parent de l'application
		ViewGroup parent = (ViewGroup) findViewById(R.id.parent);
		parent.addView(squelette.getRl(0));
		
		// c'est a vous de jouer, vous pouvez ajouter a votre menu des boutons, background 
		//ou textes avec pleins de parametres!!
		// voici qques exemples :
		// squelette.addBouton("votre texte ici", R.color.votrecouleur, le numero de l'endroit sur l'ecran);
		//squelette.addText("votre texte ici", R.color.votrecouleur, le numero de l'endroit sur l'ecran);
		// squelette.addBackground(R.drawable.votredrawable, R.color.votrecouleur,le numero du layout desire);
		// mettre 0 si vous ne voulez mpas de couleur ou de drawable au background
		
		//TODO
		squelette.addBouton("Votre titre", R.color.orange1, 1);
		squelette.addText("texte 1", R.color.orange2, 2);
		squelette.addText("texte 2", R.color.jaune1, 3);
		squelette.addBouton("bouton 1", R.color.bleu1, 4);
		squelette.addBouton("bouton 2", R.color.bleu2, 5);
		squelette.addBackground(0, R.color.jaune1,2);		
		squelette.addBackground(R.drawable.gnar, R.color.bleu1,0);
		// et voila, votre menu est fabrique !!
		
	}
}
