package com.Atlas.framework;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

import composants.Squelette;

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
		
		//	le nombre d'elements que vous souhaitez integrer au menu :
		int nbElements = 5; //TODO
		
		//	creation du "squelette" de layout
		Squelette squelette = new Squelette(nbElements,this);
		squelette.createSquelette();
		
		//	le parent de l'application
		ViewGroup parent = (ViewGroup) findViewById(R.id.parent);
		parent.addView(squelette.getRl(0));
		parent.getHeight();
		
		//	c'est a vous de jouer, vous pouvez ajouter a votre menu des boutons, background 
		//	ou textes avec pleins de parametres!!
		//	le numero du layout va de 1 au nb d'element voulu ou 0 pour le fond global
		// tous les layout sont centres et les uns en dessous des autres
		//	voici quelques exemples :
		//	squelette.addBouton("votre texte ici", R.color.votrecouleur, le numero de l'endroit sur l'ecran);
		//	squelette.addText("votre texte ici", R.color.votrecouleur, le numero de l'endroit sur l'ecran);
		//	squelette.addBackground(R.drawable.votredrawable, R.color.votrecouleur,le numero du layout desire);
		//	mettre 0 si vous ne voulez pas de couleur ou de drawable au background.
		//  squelette.addMenuDeroulant(R.color.couleurBouton, R.color.couleurMenu , "le titre de votre menu",le numero du layout desire);
		
		
		//TODO
		squelette.addText("Bienvenue dans le menu !", R.color.orange2, 2);
		squelette.addBouton("JOUER", R.color.vert1, 1);	
		squelette.addBouton("petit enfant", R.color.vert1, 3);
		squelette.addBouton("maison", R.color.vert1, 4);
		squelette.addBouton("ballon", R.color.vert1, 5);
		squelette.addBackground(R.drawable.gnar, R.color.bleu1,0);
		squelette.addBackground(R.drawable.shape, 0,5);
		squelette.addBackground(R.drawable.home, 0,4);
		squelette.addBackground(0, R.color.fushia,2);
		squelette.setWidth(1000,1);
		
		// et voila, votre menu est fabrique !!
		
	}
}
