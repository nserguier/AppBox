package boutons;

import java.io.Serializable;

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
	Serializable extra;
	private String name;
	
	/**
	 * 
	 * @param context
	 * @param button
	 * @param currentActivity
	 * @param targetActivity
	 */
	public HomeActivityListener(Context context,Button button,Activity currentActivity, Class<?> targetActivity){
		this.pressed = context.getResources().getDrawable(R.drawable.home_e);
		this.button = button;
		this.currentActivity = currentActivity;
		this.targetActivity = targetActivity;
	}
	
	/**
	 * 
	 * @param context
	 * @param button
	 * @param currentActivity
	 * @param targetActivity
	 * @param extra
	 * @param name
	 */
	public HomeActivityListener(Context context,Button button,Activity currentActivity,
			Class<?> targetActivity,Serializable extra, String name){
		this.pressed = context.getResources().getDrawable(R.drawable.home_e);
		this.button = button;
		this.currentActivity = currentActivity;
		this.targetActivity = targetActivity;
		this.extra = extra;
		this.name = name;
	}

	@Override
	public void onClick(View v) {
		  
    	/* Changement de l'aspect du bouton lorsqu'on l'enfonce */  
    	button.setBackground(pressed);
    	
    	/* Retour au menu */
       Intent homeActivity = new Intent(currentActivity,targetActivity);
       if (extra != null)
			homeActivity.putExtra(name, extra);
       currentActivity.startActivity(homeActivity);
		
	}

	

}