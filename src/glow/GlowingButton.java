package glow;

import java.util.ArrayList;

import com.Atlas.framework.R;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class GlowingButton {

	
	/**
	 * 
	 * @param bouton
	 * @param ctx
	 * @return
	 */
	public static ImageView makeGlow(Button bouton, Context ctx) {
		ViewGroup parent = (ViewGroup) bouton.getParent();

		float elevation = bouton.getElevation();

		RelativeLayout.LayoutParams params = (LayoutParams) bouton
				.getLayoutParams();
		RelativeLayout rl = new RelativeLayout(ctx);
		rl.setId(117);
		rl.setLayoutParams(params);
		parent.addView(rl);

		parent.setClipChildren(false);

		RelativeLayout.LayoutParams bouton_params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		bouton_params.addRule(RelativeLayout.CENTER_VERTICAL);
		bouton_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		bouton.setLayoutParams(bouton_params);
		parent.removeView(bouton);
		rl.addView(bouton);
		rl.setElevation(elevation);

		ImageView glow = new ImageView(ctx);
		glow.setBackground(ctx.getResources().getDrawable(
				R.drawable.glow_circle));
		glow.setLayoutParams(bouton_params);
		glow.setAlpha(0.7f);

		rl.addView(glow);
		glow.startAnimation(AnimationUtils
				.loadAnimation(ctx, R.anim.glow_scale));

		return glow;
	}
	
	/**
	 * 
	 * @param bouton
	 * @param ctx
	 * @param relativeID L'id que l'on veut donner au relative layout qui va englober bouton + glow
	 * @return
	 */
	public static ImageView makeGlow(Button bouton, Context ctx,int relativeID) {
		ViewGroup parent = (ViewGroup) bouton.getParent();

		float elevation = bouton.getElevation();

		RelativeLayout.LayoutParams params = (LayoutParams) bouton
				.getLayoutParams();
		RelativeLayout rl = new RelativeLayout(ctx);
		rl.setId(relativeID);
		rl.setLayoutParams(params);
		parent.addView(rl);

		parent.setClipChildren(false);

		RelativeLayout.LayoutParams bouton_params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		bouton_params.addRule(RelativeLayout.CENTER_VERTICAL);
		bouton_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		bouton.setLayoutParams(bouton_params);
		parent.removeView(bouton);
		rl.addView(bouton);
		rl.setElevation(elevation);

		ImageView glow = new ImageView(ctx);
		glow.setBackground(ctx.getResources().getDrawable(
				R.drawable.glow_circle));
		glow.setLayoutParams(bouton_params);
		glow.setAlpha(0.7f);

		rl.addView(glow);
		glow.startAnimation(AnimationUtils
				.loadAnimation(ctx, R.anim.glow_scale));

		return glow;
	}
	
	public static void stopGlow(Button bouton) {
		ViewGroup rl = (ViewGroup) bouton.getParent();
		RelativeLayout.LayoutParams params = (LayoutParams) rl
				.getLayoutParams();
		ViewGroup parent = (ViewGroup) rl.getParent();
		
			rl.removeAllViews();
			parent.removeView(rl);
			bouton.setLayoutParams(params);
			parent.addView(bouton);
		
		
		
	}

}
