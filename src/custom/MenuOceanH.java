package custom;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import boutons.Bouton;

import com.Atlas.framework.R;
import composants.Animer;
import composants.Police;

/**
 * Objet qui code un menu "ocean" selon une certaine disposition (orientation
 * paysage) et avec des animations la couleur des boutons, leur forme et le
 * background est impos�e (sauf changement dans ce code) le nom des boutons et
 * leur fonction seront en revenche param�trable dans un autre classe.
 * 
 * @author Victor:Nicklos
 * 
 */
public class MenuOceanH implements Menu {

	private final Context context;
	private final RelativeLayout[] menu; // les elements du menu : un titre et 6
											// boutons
	private final RelativeLayout boutons; // le layout qui contient les boutons
	private final TypeMenu type = TypeMenu.OceanHorizontal;

	public MenuOceanH(final Context context) {
		this.context = context;
		menu = new RelativeLayout[8];
		boutons = new RelativeLayout(context);
	}

	/**
	 * cette methode permet la creation d'un certain type de menu : horizontal,
	 * avec 6 boutons sur la droite (3 rang�es de 2 boutons fusionnables) et un
	 * gros titre a gauche auquel on peut rajouter des elements et animations
	 * 
	 * @param parent
	 *            le parent de l'activite
	 * @return
	 */
	
	public RelativeLayout[] createMenu(final ViewGroup parent) {

		final int width = context.getApplicationContext().getResources()
				.getDisplayMetrics().widthPixels;
		final int height = context.getApplicationContext().getResources()
				.getDisplayMetrics().heightPixels;

		// l'orienttation de l'ecran
		final Activity a = (Activity) context;
		a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		// on definit un background general
		setBackground(parent,
				context.getResources().getDrawable(type.getBackground()));
		parent.addView(boutons);
		parent.setClipChildren(false);

		final RelativeLayout.LayoutParams boutons_params = new LayoutParams(
				width / 2, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		boutons_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		boutons.setLayoutParams(boutons_params);

		// on attribue un id a chaque emplacement de bouton/titre
		for (int i = 0; i < 7; i++) {
			menu[i] = new RelativeLayout(context);
			menu[i].setId(i);

		}

		// le placement des emplacements
		for (int i = 0; i < 7; i++) {

			final int marge = height / 40;
			final RelativeLayout.LayoutParams params = new LayoutParams(width
					/ 4 - marge, height / 7);
			switch (i) {

			// case 0 : layout du titre
			case 0:
				final RelativeLayout.LayoutParams titre_params = new LayoutParams(
						width / 2,
						android.view.ViewGroup.LayoutParams.MATCH_PARENT);
				menu[0].setLayoutParams(titre_params);
				// menu[0].setElevation(50);
				parent.addView(menu[0]);

				break;

			// layouts des boutons du menu
			case 1:
				boutons.addView(menu[1]);
				params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				params.setMargins(marge, width / 4, 0, 0);
				menu[1].setLayoutParams(params);

				break;

			case 2:
				boutons.addView(menu[2]);
				params.addRule(RelativeLayout.RIGHT_OF, menu[1].getId());
				params.setMargins(marge, width / 4, marge, 0);
				menu[2].setLayoutParams(params);

				break;

			case 3:
				boutons.addView(menu[3]);
				params.addRule(RelativeLayout.BELOW, menu[1].getId());
				params.addRule(RelativeLayout.ALIGN_LEFT, menu[1].getId());
				params.setMargins(0, marge, 0, 0);
				menu[3].setLayoutParams(params);

				break;

			case 4:
				boutons.addView(menu[4]);
				params.addRule(RelativeLayout.BELOW, menu[1].getId());
				params.addRule(RelativeLayout.RIGHT_OF, menu[3].getId());
				params.setMargins(marge, marge, marge, 0);
				menu[4].setLayoutParams(params);

				break;

			case 5:
				boutons.addView(menu[5]);
				params.addRule(RelativeLayout.BELOW, menu[3].getId());
				params.addRule(RelativeLayout.ALIGN_LEFT, menu[1].getId());
				params.setMargins(0, marge, 0, 0);
				menu[5].setLayoutParams(params);

				break;

			case 6:
				boutons.addView(menu[6]);
				params.addRule(RelativeLayout.BELOW, menu[3].getId());
				params.addRule(RelativeLayout.RIGHT_OF, menu[5].getId());
				params.setMargins(marge, marge, marge, 0);
				menu[6].setLayoutParams(params);

				break;
			}

			Animer.pop_in(boutons, 3000);

		}

		return menu;

	}

	/**
	 * on cherche a fusionner 2 layout contenus dans le menu qui sont sur une
	 * meme ligne, pour creer un layout qui part de l1 et qui a la taille de l1
	 * et l2 en comptant les marges entre les 2 remplace l1 par ce
	 * "grand layout" et supprime l2 de la scene et du menu (l2 <-null)
	 * 
	 * @param l1
	 *            l'emplacement a gauche (1,3,5)
	 * @param l2
	 *            l'emplacement a droite (2,4,6)
	 */

	
	public void rassembler(final int l1, final int l2) {
		final int height = context.getApplicationContext().getResources()
				.getDisplayMetrics().heightPixels;
		final int marge = height / 40;

		if ((l1 == 1 || l1 == 3 || l1 == 5) && (l2 == 2 || l2 == 4 || l2 == 6)) {
			final RelativeLayout l = new RelativeLayout(context);
			boutons.addView(l);
			final RelativeLayout.LayoutParams params = new LayoutParams(
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.ALIGN_LEFT, menu[l1].getId());
			params.addRule(RelativeLayout.ALIGN_RIGHT, menu[l2].getId());
			params.addRule(RelativeLayout.ALIGN_TOP, menu[l1].getId());
			params.addRule(RelativeLayout.ALIGN_BOTTOM, menu[l1].getId());
			l.setLayoutParams(params);
			params.setMargins(0, 0, marge, 0);
			menu[l1] = l;
			boutons.removeView(menu[l2]);
			menu[l2] = null;
		} else {
			Log.d("erreur",
					" les emplacements specifi�s l1 ou l2 ne sont pas corrects");
		}

	}

	/**
	 * permet d'ajouter un bouton de la couleur choisie avec un texte le bouton
	 * est ajoute a la place voulue sur l'ecran (toujour centre horizontalement)
	 * 
	 * @param text
	 *            le texte du bouton
	 * @param color
	 *            la couleur du bouton
	 * @param place
	 *            le numero de l'emplacement du bouton (entre 1 et 6)
	 */
	
	public Button addButton(final String texte, final int place) {
		if (place < 7 && place > 0 && menu[place] != null) {

			Button b = Bouton.createRoundedButton((Activity) context,
					type.getCouleur1());

			if (place == 3 || place == 4) {
				b = Bouton.createRoundedButton((Activity) context,
						type.getCouleur2());
			}
			menu[place].addView(b);
			final RelativeLayout.LayoutParams params = new LayoutParams(
					android.view.ViewGroup.LayoutParams.MATCH_PARENT,
					android.view.ViewGroup.LayoutParams.MATCH_PARENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			b.setLayoutParams(params);
			b.setText(texte);
			Police.setFont((Activity) context, b, "intsh.ttf");
			b.setTextSize(30);
			return b;
		} else {
			Log.d("Attention", "le layout designe ne convient pas ou est nul");
			return null;
		}

	}

	/**
	 * permet de specifier le titre du menu, la taille du texte, sa couleur et
	 * sa police sont imposees
	 * 
	 * @param texte
	 *            le titre du menu
	 */
	
	public void addTitre(final String texte) {

		final int height = context.getApplicationContext().getResources()
				.getDisplayMetrics().heightPixels;
		final TextView t = new TextView(context);
		menu[0].addView(t);
		final RelativeLayout.LayoutParams params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		params.setMargins(-30, height / 3 - height / 8, 0, 0);

		t.setGravity(Gravity.CENTER_HORIZONTAL);
		t.setLayoutParams(params);
		t.setText(texte);
		Police.setFont((Activity) context, t, "intsh.ttf");
		t.setTextSize(80);
		Animer.pop_in(t, 2000);
		t.setTextColor(context.getResources().getColor(R.color.orange2));

		animation();

	}

	/**
	 * methode qui permet d'ajouter au layout du titre un certain nombre de
	 * drawables et d'animations pour personnaliser le menu son contenu est
	 * impose et doit etre change dans le code si souhaite
	 */
	public void animation() {

		final int height = context.getApplicationContext().getResources()
				.getDisplayMetrics().heightPixels;
		final int width = context.getApplicationContext().getResources()
				.getDisplayMetrics().widthPixels;

		// gnar en sous-marin !
		final RelativeLayout sm = new RelativeLayout(context);
		final RelativeLayout.LayoutParams sm_params = new LayoutParams(height
				/ 3 + height / 10, height / 3 + height / 10);
		sm_params.addRule(RelativeLayout.CENTER_VERTICAL);
		sm_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		// sm_params.setMargins(-300, 0, 0,0);
		setBackground(sm, context.getApplicationContext().getResources()
				.getDrawable(R.drawable.sous_marin));
		sm.setLayoutParams(sm_params);
		menu[0].addView(sm);

		final ImageView hublot = new ImageView(context);
		final RelativeLayout.LayoutParams h_params = new LayoutParams(
				height / 5, height / 5);
		h_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		h_params.setMargins(0, 190, 0, 0);
		setBackground(hublot, context.getApplicationContext().getResources()
				.getDrawable(R.drawable.hublot));
		hublot.setLayoutParams(h_params);
		sm.addView(hublot);

		final Button gnar = new Button(context);
		final RelativeLayout.LayoutParams g_params = new LayoutParams(
				height / 8, height / 8);
		g_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		g_params.setMargins(10, 230, 0, 0);
		setBackground(gnar, context.getApplicationContext().getResources()
				.getDrawable(R.drawable.tete));
		gnar.setLayoutParams(g_params);
		sm.addView(gnar);

		Animer.translate(sm, -width / 2, 0, 2 * width, 0, 15000, true);

		gnar.setOnClickListener(new View.OnClickListener() {

			
			public void onClick(final View v) {

				Animer.translate(sm, sm.getTranslationX(), 0,
						sm.getTranslationX(), -height, 10000, false);

			}
		});

		// etoile de mer
		final Button em = new Button(context);
		final RelativeLayout.LayoutParams em_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		em_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		em_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		em_params.setMargins(0, 0, -height / 30, -60);
		setBackground(em, context.getApplicationContext().getResources()
				.getDrawable(R.drawable.etoile_mer));
		em.setLayoutParams(em_params);
		menu[0].addView(em);

		// les bulles !
		final RelativeLayout bulles = new RelativeLayout(context);
		final RelativeLayout.LayoutParams b_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		b_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		b_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		b_params.setMargins(0, 0, 0, -640);
		setBackground(bulles, context.getApplicationContext().getResources()
				.getDrawable(R.drawable.bulles));
		bulles.setLayoutParams(b_params);
		bulles.setVisibility(View.INVISIBLE);
		menu[0].addView(bulles);
		Animer.translateDecelerate(bulles, height / 2, 640, height / 2, -2
				* height, 12000);

		em.setOnClickListener(new View.OnClickListener() {

			
			public void onClick(final View v) {

				bulles.setVisibility(View.VISIBLE);
				Animer.translateDecelerate(bulles, 0, 640, 0, -2 * height, 6000);

			}
		});

	}

	/**
	 * methode qui detruit toutes les vues qui se trouvent dans l'emplacement
	 * designe et laisse donc cet emplacement vide
	 * 
	 * @param place
	 *            l'emplacement que l'on veut remettre a zeros
	 */
	
	public void destroy(final int place) {
		if (place < 7 && place > 0 && menu[place] != null) {
			menu[place].removeAllViews();
		} else {
			Log.d("Attention", "le layout designe ne convient pas ou est nul");
		}
	}

	/**
	 * sets the background of a view depending on the API
	 * 
	 * @param v
	 * @param d
	 */
	private static void setBackground(final View v, final Drawable d) {
		if (Build.VERSION.SDK_INT >= 16) {
			// v.setBackground(d);
			Method methodBackgroung;
			try {
				methodBackgroung = View.class.getMethod("setBackground",
						Drawable.class);
				methodBackgroung.invoke(v, d);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			v.setBackgroundDrawable(d);
		}
	}

}
