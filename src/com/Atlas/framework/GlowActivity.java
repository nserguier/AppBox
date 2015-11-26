package com.Atlas.framework;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
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
			bouton.setBackground(getResources().getDrawable(R.drawable.home));
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
