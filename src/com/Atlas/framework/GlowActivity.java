package com.Atlas.framework;

import boutons.ButtonCreator;
import glow.GlowingText;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GlowActivity extends Activity {

	float 	startGlowRadius = 3f,
			minGlowRadius   = 3f,
			maxGlowRadius   = 20f;
	GlowingText glowText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_glow);
		
		TextView textView = (TextView) findViewById(R.id.glow);
		int color = getResources().getColor(R.color.bleu2);
		glowText = new GlowingText(this,getBaseContext(),textView,minGlowRadius,maxGlowRadius,startGlowRadius,color,1);
		
		Button bouton = (Button) findViewById(R.id.glow_bouton);
		ButtonCreator.setBlueButton(this,bouton, "glowing button");
		
		Button bouton2 = (Button) findViewById(R.id.glow_bouton_2);
		ButtonCreator.setBlueButton(this,bouton2, "glowing button 2");
		
		ImageView img = (ImageView) findViewById(R.id.glow_shadow);
		img.startAnimation(AnimationUtils.loadAnimation(this, R.anim.glow_scale));
		
		ImageView img2 = (ImageView) findViewById(R.id.glow_shadow_2);
		img2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.glow_scale));
		
	}
}
