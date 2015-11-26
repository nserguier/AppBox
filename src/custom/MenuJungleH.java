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
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import boutons.Bouton;

import com.Atlas.framework.R;
import composants.Animer;
import composants.Police;

import dragAndDrop.MyDragAndDrop;

/**
 * Objet qui code un menu "jungle" selon une certaine disposition (orientation
 * paysage) et avec des animations la couleur des boutons, leur forme et le
 * background est impos�e (sauf changement dans ce code) le nom des boutons et
 * leur fonction seront en revenche param�trable dans un autre classe.
 * 
 * @author Victor:Nicklos
 * 
 */
public class MenuJungleH implements Menu {

	private final Context context;
	private final RelativeLayout[] menu; // les elements du menu : un titre et 6
											// boutons
	private final RelativeLayout boutons; // le layout qui contient les boutons
	private final TypeMenu type = TypeMenu.JungleHorizontal;

	/**
	 * 
	 * @param context
	 *            le contexte de l'application utilisatrice du menu
	 */
	public MenuJungleH(final Context context) {
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
		params.setMargins(-30, height / 3, 0, 0);

		t.setGravity(Gravity.CENTER_HORIZONTAL);
		t.setLayoutParams(params);
		t.setText(texte);
		Police.setFont((Activity) context, t, "intsh.ttf");
		t.setTextSize(80);
		Animer.pop_in(t, 2000);
		t.setTextColor(context.getResources().getColor(R.color.orange2));
		/*
		 * int color1 = R.color.bleu1; int color2 = R.color.bleu2; int color3 =
		 * R.color.orange3; int[] colors = {color1,color2,color3};
		 * AnimatedText.addAnimatedText(context,menu[1], texte, colors, 70);
		 */

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

		// separateur
		final RelativeLayout liane = new RelativeLayout(context);
		final RelativeLayout.LayoutParams l_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		l_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		setBackground(liane, context.getApplicationContext().getResources()
				.getDrawable(R.drawable.liane));
		liane.setLayoutParams(l_params);
		menu[0].addView(liane);

		Animer.translateDecelerate(liane, 0, -height, 0, 0, 2000);

		final RelativeLayout fruit = new RelativeLayout(context);
		final RelativeLayout.LayoutParams fruit_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		fruit_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		fruit_params.setMargins(0, height / 10, 0, 0);

		fruit.setLayoutParams(fruit_params);
		menu[0].addView(fruit);
		// Animate.scale(fruit, (float) 0.8, (float) 0.9, 1000, 20, true);
		fruit.setId(125);

		final ImageView f = new ImageView(context);
		setBackground(f, context.getApplicationContext().getResources()
				.getDrawable(R.drawable.fruit));
		f.setId(124);
		final RelativeLayout.LayoutParams f_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		f_params.addRule(RelativeLayout.CENTER_IN_PARENT);
		f.setLayoutParams(f_params);
		fruit.addView(f);

		final RelativeLayout tete = new RelativeLayout(context);
		final RelativeLayout.LayoutParams tete_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		tete_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		tete_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		tete_params.setMargins(0, 0, 0, -height / 2);
		tete.setLayoutParams(tete_params);
		menu[0].addView(tete);

		setBackground(tete, context.getApplicationContext().getResources()
				.getDrawable(R.drawable.gnar));
		Animer.translate(tete, 0, height / 2, 0, -height / 9, 3000, true);

		final RotateAnimation rotate = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);

		rotate.setRepeatCount(Animation.INFINITE);
		rotate.setInterpolator(new LinearInterpolator());
		final TranslateAnimation trans = new TranslateAnimation(0, 0, 0,
				height / 2);
		final AnimationSet set = new AnimationSet(true);
		fruit.setAnimation(trans);
		fruit.setAnimation(rotate);

		set.addAnimation(trans);
		set.addAnimation(rotate);
		set.startNow();

		// dropZone

		final RelativeLayout dropZone = new RelativeLayout(context);
		final RelativeLayout.LayoutParams drop_params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		drop_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		drop_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		drop_params.setMargins(0, 0, 0, height / 8);
		dropZone.setLayoutParams(drop_params);
		menu[0].addView(dropZone);
		setBackground(dropZone, context.getApplicationContext().getResources()
				.getDrawable(R.drawable.cercle));
		dropZone.setAlpha((float) 0.3);
		dropZone.setId(126);

		final MyDragAndDrop dnd = new MyDragAndDrop(context);
		dnd.addDrag(f.getId());
		dnd.addDrop(dropZone.getId(), R.drawable.cercle, R.drawable.cercle);
		dnd.addDrop(fruit.getId(), R.drawable.fruit, R.drawable.fruit);

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
