package custom;

import com.Atlas.framework.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
import boutons.ButtonCreator;

import composants.Animate;

import dragAndDrop.DnDFonctions;
import dragAndDrop.MyDragAndDrop;

public class MenuVertical {

	private Context context;
	RelativeLayout[] menu;
	RelativeLayout boutons;
	
	
	public MenuVertical(Context context) {
		this.context = context;
		menu = new RelativeLayout[8];
		boutons = new RelativeLayout(context);
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
	public RelativeLayout[] createMenu(ViewGroup parent) {

		
		
		int width = context.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
		int height = context.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
		
		parent.setBackground(context.getResources().getDrawable(R.drawable.jungle2));
		parent.addView(boutons);
		
		RelativeLayout.LayoutParams boutons_params = new LayoutParams(
				 android.view.ViewGroup.LayoutParams.MATCH_PARENT,height/2);
		boutons_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		boutons.setLayoutParams(boutons_params);
		
		
		for(int i=0;i<7;i++) {
			menu[i] = new RelativeLayout(context);
			menu[i].setId(i);	
						
		}
				
				
		for(int i=0;i<7;i++) {
			RelativeLayout.LayoutParams params = new LayoutParams(width/4-50,height/7);
			int marge = 40;
			switch (i){
			
			// case 0 : layout du titre
				case 0: 
					RelativeLayout.LayoutParams titre_params = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,height/2); 
					menu[0].setLayoutParams(titre_params);
					
					parent.addView(menu[0]);
				
				break;
			
			//	layouts des boutons du menu
				case 1:
					boutons.addView(menu[1]);
					params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
					params.setMargins(marge, 500,0, 0);
					menu[1].setLayoutParams(params);
				
				break;
								
				case 2: 
					boutons.addView(menu[2]);
					params.addRule(RelativeLayout.RIGHT_OF,menu[1].getId());
					params.setMargins(marge, marge, 0, 0);
					menu[2].setLayoutParams(params);
				
				break;
				
				case 3: 
					boutons.addView(menu[3]);
					params.addRule(RelativeLayout.BELOW,menu[1].getId());
					params.addRule(RelativeLayout.ALIGN_LEFT,menu[1].getId());
					params.setMargins(0, marge, 0, 0);
					menu[3].setLayoutParams(params);
				
				break;
				
				case 4:
					boutons.addView(menu[4]);
					params.addRule(RelativeLayout.BELOW,menu[1].getId());
					params.addRule(RelativeLayout.RIGHT_OF,menu[3].getId());
					params.setMargins(marge, marge, 0, 0);
					menu[4].setLayoutParams(params);
				
				break;
				
				case 5: 
					boutons.addView(menu[5]);
					params.addRule(RelativeLayout.BELOW,menu[3].getId());
					params.addRule(RelativeLayout.ALIGN_LEFT,menu[1].getId());
					params.setMargins(0, marge, 0, 0);
					menu[5].setLayoutParams(params);
				
				break;
				
				case 6: 
					boutons.addView(menu[6]);
					params.addRule(RelativeLayout.BELOW,menu[3].getId());
					params.addRule(RelativeLayout.RIGHT_OF,menu[5].getId());
					params.setMargins(marge, marge, 0, 0);
					menu[6].setLayoutParams(params);
					
				break;
			}
						
			Animate.pop_in(boutons, 3000);
	
			
		}
				
			
		return menu;

	}
	
	/**
	 * on cherche a fusionner 2 layout contenus dans le menu qui sont sur une meme ligne,
	 * pour creer un layout qui part de l1 et qui a la taille de l1 et l2 en comptant les margess entre les 2
	 * remplace l1 par ce "grand layout" et supprime l2 de la scene et du menu (l2 <-null)
	 * @param l1
	 * @param l2
	 */
	public void rassembler(int l1, int l2) {
		
		RelativeLayout l = new RelativeLayout(context);
		boutons.addView(l);
		RelativeLayout.LayoutParams params = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_LEFT,menu[l1].getId());
		params.addRule(RelativeLayout.ALIGN_RIGHT,menu[l2].getId());
		params.addRule(RelativeLayout.ALIGN_TOP,menu[l1].getId());
		params.addRule(RelativeLayout.ALIGN_BOTTOM,menu[l1].getId());
		params.setMargins(0, 0, 40, 0);
		l.setLayoutParams(params);
		menu[l1]=l;
		boutons.removeView(menu[l2]);
		menu[l2]=null;
		
		
	}
	/**
	 * permet d'ajouter un bouton de la couleur choisie avec un texte
	 * le bouton est ajoute a la place voulue sur l'ecran (toujour centre horizontalement)
	 * @param text
	 * @param color
	 * @param place
	 */
	
	public void addButton(String texte, int place, int color) {
		if(place <7 && place >0 && menu[place] != null) {
			Button b = ButtonCreator.createRoundedButton(context, color) ;
			menu[place].addView(b);
			RelativeLayout.LayoutParams params = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			b.setLayoutParams(params);
			b.setText(texte);
			Typeface externalFont = Typeface.createFromAsset(context.getAssets(),
					"fonts/intsh.ttf");
			b.setTypeface(externalFont);
			b.setTextSize(30);
		}else Log.d("Attention","le layout designe ne convient pas ou est nul");
			
	}
	
	public void addTitre(String texte) {
		
			TextView t = new TextView(context) ;
			menu[0].addView(t);
			RelativeLayout.LayoutParams params = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			params.setMargins(0, 400, 0, 0);
			t.setGravity(Gravity.CENTER_HORIZONTAL);
			t.setLayoutParams(params);
			t.setText(texte);
			Typeface externalFont = Typeface.createFromAsset(context.getAssets(),"fonts/intsh.ttf");
			t.setTypeface(externalFont);
			t.setTextSize(80);
			Animate.pop_in(t,2000);
			t.setTextColor(context.getResources().getColor(R.color.orange2));
			int color1 = R.color.bleu1;
			int color2 = R.color.bleu2;
			int color3 = R.color.orange3;
			int[] colors = {color1,color2,color3};
			//AnimatedText.addAnimatedText(context,menu[1], texte, colors, 70);
			
			animation();
			
	}
	
	public void animation() {
		
		Activity activity = (Activity)context;
		int width = context.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
		int height = context.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
		
		//	separateur 
		/*RelativeLayout liane = new RelativeLayout(context);
		RelativeLayout.LayoutParams l_params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
		l_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		liane.setBackground(context.getApplicationContext().getResources().getDrawable(R.drawable.liane));
		liane.setLayoutParams(l_params);
		menu[0].addView(liane);
		Animate.translateDecelerate(liane, 0, -height, 0, 0, 2000);*/
		
		
		RelativeLayout fruit = new RelativeLayout(context);
		RelativeLayout.LayoutParams fruit_params = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		fruit_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		fruit_params.setMargins(0, 100, 0, 0);
		fruit.setLayoutParams(fruit_params);
		menu[0].addView(fruit);
		Animate.scale(fruit, (float) 0.8, (float) 0.9, 1000, 20, true);
		fruit.setId(125);
		
		ImageView f = new ImageView(context);
		f.setBackground(context.getApplicationContext().getResources().getDrawable(R.drawable.fruit));
		f.setId(124);
		RelativeLayout.LayoutParams f_params = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		f_params.addRule(RelativeLayout.CENTER_IN_PARENT);
		f.setLayoutParams(f_params);
		fruit.addView(f);
		
		
		RelativeLayout tete = new RelativeLayout(context);
		RelativeLayout.LayoutParams tete_params = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		tete_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		tete_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		tete_params.setMargins(0, 0, 0, -height/2);
		tete.setLayoutParams(tete_params);
		menu[0].addView(tete);
		tete.setBackground(context.getApplicationContext().getResources().getDrawable(R.drawable.gnar));
		Animate.translate(tete, 0, height/2, 0,  -height/9, 3000, true);
		
		//	dropZone
		
		RelativeLayout dropZone = new RelativeLayout(context);
		RelativeLayout.LayoutParams drop_params = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		drop_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		drop_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		drop_params.setMargins(0, 0, 0, height/8);
		dropZone.setLayoutParams(drop_params);
		menu[0].addView(dropZone);
		dropZone.setBackground(context.getApplicationContext().getResources().getDrawable(R.drawable.cercle));
		dropZone.setAlpha((float) 0.3);
		dropZone.setId(126);
		
		
		MyDragAndDrop dnd = new MyDragAndDrop(context, activity);
		dnd.addDrag(f.getId());
		DnDFonctions fonction = new DnDFonctions("play",fruit,f,context);
		dnd.addDrop(dropZone.getId(), R.drawable.cercle, R.drawable.cercle,fonction);
		dnd.addDrop(fruit.getId(), R.drawable.fruit, R.drawable.fruit,null);
		
		
		
	}
}
