package com.Atlas.framework;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment.InstantiationException;
import android.view.ViewGroup;
import composants.Ecran;

import custom.FabriqueMenu;
import custom.Menu;
import custom.TypeMenu;

public class MenuActivity extends Activity {

	private Menu m;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* Passage en plein ecran */
		Ecran.fullScreen(this);
		setContentView(R.layout.activity_menu_style);

		final ViewGroup parent = (ViewGroup) findViewById(R.id.parent);

		try {
			m = FabriqueMenu.create(TypeMenu.Options, this);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// on cree le menu
		m.createMenu(parent);
		// on rassemble les boutons

		m.addTitre("OPTIONS GNAR !!  ");
		// on cree les boutons, on pourra plus tard leur donner une tâche
		m.addButton("gnar", 1);
		m.addButton("horloge", 3);
		m.addButton("son", 5);
		m.addButton("bulle", 2);
		m.addButton("sommaire", 4);
		m.addButton("son", 6);

		// c'est fini, et voici le resultat !

	}
}
