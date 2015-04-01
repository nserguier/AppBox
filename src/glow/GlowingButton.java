package glow;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import boutons.ButtonCreator;

import com.Atlas.framework.R;

public class GlowingButton {

	Context context;
	private Button b;
	ImageView glowi;
	
	
	public GlowingButton(Context context, Button button, ImageView imageView){
			this.b = button;
			this.context = context;
			this.glowi = imageView;
	}
	
	public void start(){
	
		
		// coordonnees du centre du bouton
		
		int x0 = b.getMinimumWidth()/2 + b.getLeft();
		int y0 = b.getMinimumHeight()/2 + b.getTop();
		
		int[]coord = new int[2];
		coord[0]=x0;
		coord[1]=y0;
		
		// l'image de base du glow et animation
		Drawable glow = (Drawable) context.getResources().getDrawable(R.drawable.glow_circle) ;
				
		glowi.setBackground(glow);
		glowi.getLocationOnScreen(coord);
		glowi.setX(x0);
		glowi.setY(y0);
		
		glowi.setMinimumWidth(b.getWidth()+5); 
		glowi.setMinimumHeight(b.getHeight()+5); 
		
			
		glowi.setElevation(0);
		
		glowi.startAnimation(AnimationUtils.loadAnimation(context, R.anim.glow_scale));
		
		
		
		
			
		
	}
	
	
	
	
}
