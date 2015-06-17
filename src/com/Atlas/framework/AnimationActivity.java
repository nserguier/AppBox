package com.Atlas.framework;

import composants.Animer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class AnimationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation);
		
		ImageView img1 = (ImageView) findViewById(R.id.image1);
		ImageView img2 = (ImageView) findViewById(R.id.image2);
		ImageView img3 = (ImageView) findViewById(R.id.image3);
		ImageView img4 = (ImageView) findViewById(R.id.image4);
	
		Animer.scale(img1,1f, 1.2f,500,0, true);
		Animer.rotateInfinite(img2, 3000);
		Animer.rotate(img3, 1000, 120, true);
		img4.setVisibility(View.INVISIBLE);
		Animer.pop_in(img4, 700);
	}
}
