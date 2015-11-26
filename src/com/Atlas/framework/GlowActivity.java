package com.Atlas.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import composants.GlowingButton;

public class GlowActivity extends Activity {

	float startGlowRadius = 3f, minGlowRadius = 3f, maxGlowRadius = 20f;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_glow);

		final TextView textView = (TextView) findViewById(R.id.glow);
		final int color = getResources().getColor(R.color.bleu2);

		final Button bouton = (Button) findViewById(R.id.glow_bouton);
		if (Build.VERSION.SDK_INT >= 16) {
			//bouton.setBackground(getResources().getDrawable(R.drawable.home));
			Method methodBackgroung;
			try {
				methodBackgroung = View.class.getMethod("setBackground",
						Drawable.class);
				methodBackgroung.invoke(bouton, getResources().getDrawable(R.drawable.home));
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			bouton.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.home));
		}

		// Button bouton2 = (Button) findViewById(R.id.glow_bouton_2);
		// ButtonCreator.setBlueButton(this,bouton2, "glowing button 2");

		GlowingButton.makeGlow(bouton, this, 115);

		final ImageView img2 = (ImageView) findViewById(R.id.glow_shadow_2);
		img2.startAnimation(AnimationUtils.loadAnimation(this,
				R.anim.glow_scale_rect));

	}
}
