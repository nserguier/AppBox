package com.Atlas.framework;

import composants.Ecran;
import composants.Utile;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;

public class FullScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* Passage en plein ecran */
		Utile.fullScreen(this);
		setContentView(R.layout.activity_full_screen);
	}

	@Override
	/* L'activite revient sur le devant de la scene */
	public void onResume() {
		super.onResume();
		executeDelayed();

	}

	private void executeDelayed() {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				// execute after 500ms
				hideNavBar();
			}
		}, 500);
	}

	private void hideNavBar() {
		View v = getWindow().getDecorView();
		v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		/*if (Build.VERSION.SDK_INT >= 19) {
			v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}*/
	}
}
