package custom;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Dernier maillon de la fabrique qui regroupe plusieurs objets sous un même
 * type commun Menu pour un meilleur découplage: l'application "cliente" qui
 * souhaite creer un menu ne reçoit que ce type commun.
 * 
 * @author Victor, Nicklos
 * 
 */
public interface Menu {

	RelativeLayout[] createMenu(ViewGroup parent);

	void rassembler(int l1, int l2);

	View addButton(String texte, int place);

	void addTitre(String texte);

	void destroy(int place);

}
