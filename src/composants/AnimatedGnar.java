package composants;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import boutons.ButtonCreator;

import com.Atlas.framework.R;

public class AnimatedGnar {
	
	public static void addAnimatedGnar(Context context,RelativeLayout rl){
		final Resources r = context.getResources();
		rl.setClipChildren(false);
		
		// Creation des parties de gnar
		final ImageButton tete = ButtonCreator.create(context).setBack(r.getDrawable(R.drawable.tete)).buildImage();
		ImageButton oreille_gauche = ButtonCreator.create(context).setBack(r.getDrawable(R.drawable.oreille)).buildImage();
		ImageButton oreille_droite = ButtonCreator.create(context).setBack(r.getDrawable(R.drawable.oreille)).mirror().buildImage();
		ImageButton corps = ButtonCreator.create(context).setBack(r.getDrawable(R.drawable.corps)).buildImage();
		ImageButton bras_gauche = ButtonCreator.create(context).setBack(r.getDrawable(R.drawable.bras)).buildImage();
		ImageButton bras_droit = ButtonCreator.create(context).setBack(r.getDrawable(R.drawable.bras)).mirror().buildImage();
		ImageButton jambe_gauche = ButtonCreator.create(context).setBack(r.getDrawable(R.drawable.jambe)).buildImage();
		ImageButton jambe_droit = ButtonCreator.create(context).setBack(r.getDrawable(R.drawable.jambe)).mirror().buildImage();
		
		// Tailles des drawable
		Bitmap bit_tete = ((BitmapDrawable) r.getDrawable(R.drawable.tete)).getBitmap();
		int W_tete = bit_tete.getWidth();
		int H_tete = bit_tete.getHeight();
		
		//Placements des parties
		corps.setId(177);
		tete.setId(178);
		MyLayoutParams params_tete = new MyLayoutParams().centerHorizontal();
		MyLayoutParams params_oreille_gauche = new MyLayoutParams();
		MyLayoutParams params_oreille_droite = new MyLayoutParams().marginLeft(W_tete);
		MyLayoutParams params_corps = new MyLayoutParams().centerHorizontal().below(tete).marginTop(-H_tete/6);
		MyLayoutParams params_bras_gauche = new MyLayoutParams().marginTop(14*H_tete/15).toLeftOf(corps).marginRight(-W_tete/11);
		MyLayoutParams params_bras_droit = new MyLayoutParams().marginTop(14*H_tete/15).toRightOf(corps).marginLeft(-W_tete/11);
		MyLayoutParams params_jambe_gauche = new MyLayoutParams().marginTop((int)(H_tete*1.47f)).toLeftOf(corps).marginRight(-W_tete/5);
		MyLayoutParams params_jambe_droit = new MyLayoutParams().marginTop((int)(H_tete*1.47f)).toRightOf(corps).marginLeft(-W_tete/5);
		
		// Animation
		int T = 1000;
		Animate.rotate(oreille_gauche, T,-2, 3,1f,1f, true);
		Animate.rotate(oreille_droite, T,3,-2,0f,1f, true);
		Animate.rotate(bras_gauche, T,-3, 3,1f,0f, true);
		Animate.rotate(bras_droit, T,3,-3,0f,0f, true);
		Animate.translate(corps, 0, -H_tete/30, 0, H_tete/100, T,true);
		
		//Clics
		OnClickListener ocl = new View.OnClickListener() {
			int num_tete = 2;
			@Override
			public void onClick(View v) {
				int random = (int) Math.floor(Math.random()*3);
				switch(random){
				case 0:
					if(num_tete==0){
						tete.setBackground(r.getDrawable(R.drawable.tete3));
					}
					else{
						tete.setBackground(r.getDrawable(R.drawable.tete2));
					}
					num_tete =0;
					break;
				case 1:
					if(num_tete==1){
						tete.setBackground(r.getDrawable(R.drawable.tete));
					}
					else{
						tete.setBackground(r.getDrawable(R.drawable.tete3));
					}
					num_tete = 1;
					break;
				default :
					if(num_tete==2){
						tete.setBackground(r.getDrawable(R.drawable.tete2));
					}
					else{
						tete.setBackground(r.getDrawable(R.drawable.tete));
					}
					num_tete = 2;
					break;
					
				}
				
			}
		};
		tete.setOnClickListener(ocl);
		oreille_droite.setOnClickListener(ocl);
		oreille_gauche.setOnClickListener(ocl);
		jambe_droit.setOnClickListener(ocl);
		jambe_gauche.setOnClickListener(ocl);
		corps.setOnClickListener(ocl);
		bras_droit.setOnClickListener(ocl);
		bras_gauche.setOnClickListener(ocl);
		
		//Ajout des elements au layout
		rl.addView(bras_gauche,params_bras_gauche);
		rl.addView(bras_droit,params_bras_droit);
		rl.addView(oreille_gauche,params_oreille_gauche);
		rl.addView(oreille_droite,params_oreille_droite);
		rl.addView(jambe_droit,params_jambe_droit);
		rl.addView(jambe_gauche,params_jambe_gauche);
		rl.addView(corps,params_corps);
		rl.addView(tete,params_tete);

	}
}
