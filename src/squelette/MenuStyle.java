package squelette;

import com.Atlas.framework.R;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MenuStyle {

	private Context context;
	RelativeLayout[] menu;

	public MenuStyle(Context context) {
		this.context = context;
		menu = new RelativeLayout[8];
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
	public RelativeLayout[] createMenu() {

		int width = context.getApplicationContext().getResources().getDisplayMetrics().widthPixels;;
		int height = context.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
		
		RelativeLayout parent = new RelativeLayout(context);
		RelativeLayout.LayoutParams parent_params = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		parent.setLayoutParams(parent_params);

		menu[0] = parent;
		menu[0].setBackgroundColor(context.getResources().getColor(R.color.blanc_casse));
		for(int i=1;i<8;i++) {
			menu[i] = new RelativeLayout(context);
			parent.addView(menu[i]); 
			menu[i].setId(i);	
			menu[i].setBackgroundColor(context.getResources().getColor(R.color.bleu1));
		}
		
			
		for(int i=1;i<8;i++) {
			RelativeLayout.LayoutParams params = new LayoutParams(width/3,height/6);
			switch (i){
			
			// case 1 : layout du titre
				case 1: RelativeLayout.LayoutParams titre_params = new LayoutParams(width/2-50,height-50); 
				titre_params.setMargins(25, 25, 25, 25);
				menu[1].setLayoutParams(titre_params);
				menu[1].setElevation(5);
			
			//	layouts des options du menu
				case 2: params.addRule(RelativeLayout.ALIGN_LEFT,menu[3].getId());
				//params.setMargins(20, 20, 20, 20);
				menu[2].setLayoutParams(params);
				
				case 3: params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				//params.setMargins(20, 20, 20, 20);
				menu[3].setLayoutParams(params);
				
				case 4: params.addRule(RelativeLayout.BELOW,menu[2].getId());
				//params.setMargins(20, 20, 20, 20);
				menu[4].setLayoutParams(params);
				
				case 5: params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				params.addRule(RelativeLayout.BELOW,menu[3].getId());
				//params.setMargins(20, 20, 20, 20);
				menu[5].setLayoutParams(params);
				
				case 6: params.addRule(RelativeLayout.BELOW,menu[4].getId());
				//params.setMargins(20, 20, 20, 20);
				menu[6].setLayoutParams(params);
				
				case 7: params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				params.addRule(RelativeLayout.BELOW,menu[5].getId());
				//params.setMargins(20, 20, 20, 20);
				menu[7].setLayoutParams(params);
			}
		}
				
			
		return menu;

	}
	
	/**
	 * on cherche a fusionner 2 layout contenus dans le menu,
	 * pour creer un layout qui part de l1 et qui a la taille de l1 et l2 en comptant les marges entre les 2
	 * remplace l1 par ce "grand layout" et supprime l2 de la scene et du menu (l2 <-null)
	 * @param l1
	 * @param l2
	 */
	public void rassembler(int l1, int l2) {
		
		RelativeLayout l = new RelativeLayout(context);
		menu[0].addView(l);
		RelativeLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_LEFT,menu[l1].getId());
		params.addRule(RelativeLayout.ALIGN_RIGHT,menu[l2].getId());
		params.addRule(RelativeLayout.ALIGN_TOP,menu[l2].getId());
		params.addRule(RelativeLayout.ALIGN_BOTTOM,menu[l2].getId());
		l.setLayoutParams(params);
		menu[l1]=l;
		menu[0].removeView(menu[l2]);
		menu[l2]=null;
		
		
	}
	
	
}
