package boutons;

import java.io.Serializable;

import com.Atlas.framework.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Le composant a appliquer au bouton pour passer d'une vue a une autre
 * 
 */
public class NextActivityListener implements OnClickListener {
	
	private Drawable pressed;  
	private Button button;
	private Activity currentActivity;
	private Class<?> targetActivity;
	private String name;
	private Serializable extra;
	
	/**
	 * 
	 * @param button  le bouton sur lequel on applique le listener
	 * @param pressed  l'image du bouton en position pressee
	 * @param currentActivity  l'activite actuelle
	 * @param targetActivity  l'activite sur laquelle on souhaite passer
	 */
	public NextActivityListener(Button button, Drawable pressed,Activity currentActivity, Class<?> targetActivity){
		this.pressed = pressed;
		this.button = button;
		this.currentActivity = currentActivity;
		this.targetActivity = targetActivity;
	}
	
	/**
	 * 
	 * @param button  le bouton sur lequel on applique le listener
	 * @param pressed  l'image du bouton en position pressee
	 * @param currentActivity  l'activite actuelle
	 * @param targetActivity  l'activite sur laquelle on souhaite passer
	 * @param extra un serialisable qu'on passe en parametre d'une activite a l'autre
	 * @param name l'identifiant du serialisable qui permet de le charger dans la nouvelle activite
	 * 
	 */
	public NextActivityListener(Button button, Drawable pressed,Activity currentActivity, Class<?> targetActivity,
			Serializable extra, String name){
		this.pressed = pressed;
		this.button = button;
		this.currentActivity = currentActivity;
		this.targetActivity = targetActivity;
		this.name = name;
		this.extra = extra;
	}
	
		
	@Override
	public void onClick(View v) {
		  
    	/* Changement de l'aspect du bouton lorsqu'on l'enfonce */  
    	if(pressed!=null) button.setBackground(pressed);
    	
    	/* Passage a l'autre activite */
       Intent secondActivity = new Intent(currentActivity,targetActivity);
       if (extra != null)
    	   secondActivity.putExtra(name, extra);
       currentActivity.startActivity(secondActivity);
		
	}

	

}