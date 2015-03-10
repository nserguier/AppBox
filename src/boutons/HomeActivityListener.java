package boutons;

import com.Atlas.framework.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.Atlas.framework.*;

/**
 * Le composant a appliquer au bouton home pour revenir au menu
 * 
 */
public class HomeActivityListener implements OnClickListener {
	
	Drawable pressed;
	Button button;
	Activity currentActivity;
	Class<?> targetActivity;
	
	public HomeActivityListener(Context context,Button button,Activity currentActivity, Class<?> targetActivity){
		this.pressed = context.getResources().getDrawable(R.drawable.home_e);
		this.button = button;
		this.currentActivity = currentActivity;
		this.targetActivity = targetActivity;
	}

	@Override
	public void onClick(View v) {
		  
    	/* Changement de l'aspect du bouton lorsqu'on l'enfonce */  
    	button.setBackground(pressed);
    	
    	/* Retour au menu */
       Intent homeActivity = new Intent(currentActivity,targetActivity);
       currentActivity.startActivity(homeActivity);
		
	}

	

}