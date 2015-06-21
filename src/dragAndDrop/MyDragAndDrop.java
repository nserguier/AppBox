package dragAndDrop;

import android.app.Activity;
import android.content.Context;

/**
 * Composant permettant de mettre en place un drag-and-drop entre plusieurs objets
 *
 */
public class MyDragAndDrop {
	
	Context context; // Le contexte de l'application
	
	
	public MyDragAndDrop(Context context){
		this.context = context;
	}
	
	/**
	 * 
	 * @param toDragID l'id de la View qu'on veut pouvoir deplacer
	 */
	public void addDrag(int toDragID){
		/* Affectation du composant permettant le drag */
		((Activity) context).findViewById(toDragID).setOnTouchListener(new MyTouchListener());
	}
	
	/**
	 * 
	 * @param dropZoneID l'id du LinearLayout servant de drop zone
	 * @param dropZoneDrawID l'id de l'image servant de fond a la drop zone
	 * @param dropZoneSurbrillantDrawID l'id de l'image servant de fond a la drop zone en surbrillance
	 */
	public void addDrop(int dropZoneID, int dropZoneDrawID, int dropZoneSurbrillantDrawID,DnDFonctions fonction){
		/* Affectation du composant permettant le drop*/
		((Activity) context).findViewById(dropZoneID).setOnDragListener(new MyDragListener(context,dropZoneDrawID,dropZoneSurbrillantDrawID,fonction));
	}
}
