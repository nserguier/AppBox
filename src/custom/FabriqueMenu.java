package custom;

import android.content.Context;

import com.Atlas.framework.MenuActivity;


public class FabriqueMenu {
	
	/**
	 * Premier maillon de la fabrique qui reçoit l'appel du client et détermine
	 * quel objet creer (ici un filtre) pour répondre à cette demande.
	 * 
	 * @param typeMenu
	 *            la chaine de caractere qui represente le menu
	 * @param context
	 * @return Menu Type commun à plusieurs objet qui permet un meilleur
	 *         découplage.
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException
	 * @author Victor,Nicklos 
	 */
	public static Menu create(TypeMenu typeMenu,Context context) 
			throws IllegalArgumentException, InstantiationException, IllegalAccessException {

		switch(typeMenu){
		case JungleHorizontal: return new MenuJungleH(context);
		case JungleVertical: return new MenuJungleV(context);
		case OceanHorizontal: return new MenuOceanH(context);
		case Options: return new MenuOptions(context);
		}
		throw new IllegalArgumentException(
				"wrong argument, should of type TypeMenu ");
	}
}
