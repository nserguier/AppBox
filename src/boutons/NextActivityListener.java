package boutons;

import android.app.Activity;
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
	
	Drawable pressed;
	Button button;
	Activity currentActivity;
	Class<?> targetActivity;
	
	public NextActivityListener(Button button, Drawable pressed,Activity currentActivity, Class<?> targetActivity){
		this.pressed = pressed;
		this.button = button;
		this.currentActivity = currentActivity;
		this.targetActivity = targetActivity;
	}

	@Override
	public void onClick(View v) {
		  
    	/* Changement de l'aspect du bouton lorsqu'on l'enfonce */  
    	button.setBackground(pressed);
    	
    	/* Passage a l'autre activite */
       Intent secondActivity = new Intent(currentActivity,targetActivity);
       currentActivity.startActivity(secondActivity);
		
	}

	

}