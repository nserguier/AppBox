package com.Atlas.framework;

import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.Atlas.framework.R.color;

import custom.TypeMenu;

public class Element {

	private int pair;
	private ImageView img;
	private int place;
	private RelativeLayout zone;
	private Context context;
	private TextView item;
	private boolean isVisible = false;
	private int drawable;
	
	/**
	 * @param pair
	 * @param trouve
	 * @param context
	 * @param drawable
	 */
	public Element(int pair,int drawable, Context context) {
		this.pair = pair;
		this.context = context;
		zone = new RelativeLayout(context);
		
		item = new TextView(context);
		item.setText(Integer.toString(pair));
		item.setTextColor(context.getApplicationContext().getResources().getColor(R.color.orange6));
		item.setTextSize(100f);
		item.setTextAlignment(Gravity.CENTER);
		item.setVisibility(View.INVISIBLE);
		
		zone.setBackground(context.getResources().getDrawable(drawable));
		RelativeLayout.LayoutParams params = new LayoutParams(
				 LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		item.setLayoutParams(params);
		zone.addView(item);
		
		
	}

	
	public ImageView getImg() {
		return img;
	}

	public void setImg(ImageView img) {
		this.img = img;
	}

	public int getPair() {
		return pair;
	}

	public void setPair(int pair) {
		this.pair = pair;
	}


	public RelativeLayout getZone() {
		return zone;
	}
	
	public TextView getItem(){
		return this.item;
	}


	public boolean isVisible() {
		return isVisible;
	}


	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}


	
	
}
