package boutons;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Le composant a appliquer au bouton pour quitter l'application
 * 
 */
public class ExitApplicationListener implements OnClickListener {
	
	Drawable pressed;
	Button button;
	Activity currentActivity;
	
	public ExitApplicationListener(Button button,Drawable pressed,Activity activity){
		this.pressed = pressed;
		this.button = button;
		this.currentActivity = activity;
	}

	@Override
	public void onClick(View v) {
		  
    	/* Changement de l'aspect du bouton lorsqu'on l'enfonce */  
    	button.setBackground(pressed);
    	
    	/* On quitte l'application */
    	currentActivity.finish();
    	System.exit(0);
		
	}

	

}