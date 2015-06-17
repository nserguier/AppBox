package custom;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import boutons.Bouton;

import com.Atlas.framework.R;
import composants.Animer;

import dragAndDrop.DnDFonctions;
import dragAndDrop.MyDragAndDrop;

/**
 * Objet qui code un menu "jungle" selon une certaine disposition (orientation
 * paysage) et avec des animations la couleur des boutons, leur forme et le
 * background est imposée (sauf changement dans ce code) le nom des boutons et
 * leur fonction seront en revenche paramétrable dans un autre classe.
 * 
 * @author Victor:Nicklos
 * 
 */
public class MenuJungleH implements Menu {

	private Context context;
	RelativeLayout[] menu; // les elements du menu : un titre et 6 boutons
	RelativeLayout boutons; // le layout qui contient les boutons

	/**
	 * 
	 * @param context
	 *            le contexte de l'application utilisatrice du menu
	 */
	public MenuJungleH(Context context) {
		this.context = context;
		menu = new RelativeLayout[8];
		boutons = new RelativeLayout(context);
	}

	/**
	 * cette methode permet la creation d'un certain type de menu : horizontal,
	 * avec 6 boutons sur la droite (3 rangées de 2 boutons fusionnables) et un
	 * gros titre a gauche auquel on peut rajouter des elements et animations
	 * 
	 * @param parent
	 *            le parent de l'activite
	 * @return
	 */
	public RelativeLayout[] createMenu(ViewGroup parent, TypeMenu type) {

		int width = context.getApplicationContext().getResources()
				.getDisplayMetrics().widthPixels;
		int height = context.getApplicationContext().getResources()
				.getDisplayMetrics().heightPixels;

		// l'orienttation de l'ecran
		Activity a = (Activity) context;
		a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		// on definit un background general
		parent.setBackground(context.getResources().getDrawable(
				type.getBackground()));
		parent.addView(boutons);
		parent.setClipChildren(false);

		RelativeLayout.LayoutParams boutons_params = new LayoutParams(
				width / 2, LayoutParams.MATCH_PARENT);
		boutons_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		boutons.setLayoutParams(boutons_params);

		// on attribue un id a chaque emplacement de bouton/titre
		for (int i = 0; i < 7; i++) {
			menu[i] = new RelativeLayout(context);
			menu[i].setId(i);

		}

		// le placement des emplacements
		for (int i = 0; i < 7; i++) {

			int marge = height / 40;
			RelativeLayout.LayoutParams params = new LayoutParams(width / 4
					- marge, height / 7);
			switch (i) {

			// case 0 : layout du titre
			case 0:
				RelativeLayout.LayoutParams titre_params = new LayoutParams(
						width / 2, LayoutParams.MATCH_PARENT);
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
	public void rassembler(int l1, int l2) {
		int height = context.getApplicationContext().getResources()
				.getDisplayMetrics().heightPixels;
		int marge = height / 40;
		if ((l1 == 1 || l1 == 3 || l1 == 5) && (l2 == 2 || l2 == 4 || l2 == 6)) {
			RelativeLayout l = new RelativeLayout(context);
			boutons.addView(l);
			RelativeLayout.LayoutParams params = new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.ALIGN_LEFT, menu[l1].getId());
			params.addRule(RelativeLayout.ALIGN_RIGHT, menu[l2].getId());
			params.addRule(RelativeLayout.ALIGN_TOP, menu[l1].getId());
			params.addRule(RelativeLayout.ALIGN_BOTTOM, menu[l1].getId());
			l.setLayoutParams(params);
			params.setMargins(0, 0, marge, 0);
			menu[l1] = l;
			boutons.removeView(menu[l2]);
			menu[l2] = null;
		} else
			Log.d("erreur",
					" les emplacements specifiés l1 ou l2 ne sont pas corrects");

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
	public Button addButton(String texte, int place) {

		if (place < 7 && place > 0 && menu[place] != null) {
			Button b = Bouton.createRoundedButton((Activity) context,
					R.color.vert1);
			if (place == 3 || place == 4)
				b = Bouton.createRoundedButton((Activity) context,
						R.color.vert2);

			menu[place].addView(b);
			RelativeLayout.LayoutParams params = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			b.setLayoutParams(params);
			b.setText(texte);
			Typeface externalFont = Typeface.createFromAsset(
					context.getAssets(), "fonts/intsh.ttf");
			b.setTypeface(externalFont);
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
	public void addTitre(String texte) {

		int height = context.getApplicationContext().getResources()
				.getDisplayMetrics().heightPixels;
		TextView t = new TextView(context);
		menu[0].addView(t);
		RelativeLayout.LayoutParams params = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(-30, height / 3, 0, 0);

		t.setGravity(Gravity.CENTER_HORIZONTAL);
		t.setLayoutParams(params);
		t.setText(texte);
		Typeface externalFont = Typeface.createFromAsset(context.getAssets(),
				"fonts/intsh.ttf");
		t.setTypeface(externalFont);
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

		Activity activity = (Activity) context;
		int height = context.getApplicationContext().getResources()
				.getDisplayMetrics().heightPixels;

		// separateur
		RelativeLayout liane = new RelativeLayout(context);
		RelativeLayout.LayoutParams l_params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		l_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		liane.setBackground(context.getApplicationContext().getResources()
				.getDrawable(R.drawable.liane));
		liane.setLayoutParams(l_params);
		menu[0].addView(liane);

		Animer.translateDecelerate(liane, 0, -height, 0, 0, 2000);

		RelativeLayout fruit = new RelativeLayout(context);
		RelativeLayout.LayoutParams fruit_params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		fruit_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		fruit_params.setMargins(0, height / 10, 0, 0);

		fruit.setLayoutParams(fruit_params);
		menu[0].addView(fruit);
		// Animate.scale(fruit, (float) 0.8, (float) 0.9, 1000, 20, true);
		fruit.setId(125);

		ImageView f = new ImageView(context);
		f.setBackground(context.getApplicationContext().getResources()
				.getDrawable(R.drawable.fruit));
		f.setId(124);
		RelativeLayout.LayoutParams f_params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		f_params.addRule(RelativeLayout.CENTER_IN_PARENT);
		f.setLayoutParams(f_params);
		fruit.addView(f);

		RelativeLayout tete = new RelativeLayout(context);
		RelativeLayout.LayoutParams tete_params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		tete_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		tete_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		tete_params.setMargins(0, 0, 0, -height / 2);
		tete.setLayoutParams(tete_params);
		menu[0].addView(tete);

		tete.setBackground(context.getApplicationContext().getResources()
				.getDrawable(R.drawable.gnar));
		Animer.translate(tete, 0, height / 2, 0, -height / 9, 3000, true);

		RotateAnimation rotate = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);

		rotate.setRepeatCount(Animation.INFINITE);
		rotate.setInterpolator(new LinearInterpolator());
		TranslateAnimation trans = new TranslateAnimation(0, 0, 0, height / 2);
		AnimationSet set = new AnimationSet(true);
		fruit.setAnimation(trans);
		fruit.setAnimation(rotate);

		set.addAnimation(trans);
		set.addAnimation(rotate);
		set.startNow();

		// dropZone

		RelativeLayout dropZone = new RelativeLayout(context);
		RelativeLayout.LayoutParams drop_params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		drop_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		drop_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		drop_params.setMargins(0, 0, 0, height / 8);
		dropZone.setLayoutParams(drop_params);
		menu[0].addView(dropZone);
		dropZone.setBackground(context.getApplicationContext().getResources()
				.getDrawable(R.drawable.cercle));
		dropZone.setAlpha((float) 0.3);
		dropZone.setId(126);

		MyDragAndDrop dnd = new MyDragAndDrop(context, activity);
		dnd.addDrag(f.getId());
		DnDFonctions fonction = new DnDFonctions("play", fruit, f, context);
		dnd.addDrop(dropZone.getId(), R.drawable.cercle, R.drawable.cercle,
				fonction);
		dnd.addDrop(fruit.getId(), R.drawable.fruit, R.drawable.fruit, null);

	}

	/**
	 * methode qui detruit toutes les vues qui se trouvent dans l'emplacement
	 * designe et laisse donc cet emplacement vide
	 * 
	 * @param place
	 *            l'emplacement que l'on veut remettre a zeros
	 */
	public void destroy(int place) {
		if (place < 7 && place > 0 && menu[place] != null) {
			menu[place].removeAllViews();
		} else {
			Log.d("Attention", "le layout designe ne convient pas ou est nul");
		}
	}
}
