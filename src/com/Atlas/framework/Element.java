package com.Atlas.framework;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.Atlas.framework.R.color;

public class Element {

	private int pair;
	private ImageView img;
	private boolean trouve;
	private int place;
	private RelativeLayout zone;
	private Context context;
	private TextView item;
	
	/**
	 * @param pair
	 * @param trouve
	 * @param place
	 * @param context
	 */
	public Element(int pair, boolean trouve, int place,Context context) {
		this.pair = pair;
		this.trouve = trouve;
		this.place = place;
		this.context = context;
		zone = new RelativeLayout(context);
		
		item = new TextView(context);
		item.setText(Integer.toString(pair));
		item.setTextColor(color.orange2);
		item.setTextSize(30f);
		item.setVisibility(View.INVISIBLE);
		
		zone.setBackground(context.getResources().getDrawable(R.drawable.buisson));
		RelativeLayout.LayoutParams params = new LayoutParams(
				 LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		item.setLayoutParams(params);
		zone.addView(item);
		
		
	}

	
	public boolean isTrouve() {
		return trouve;
	}

	public void setTrouve(boolean trouve) {
		this.trouve = trouve;
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

	public int getPlace() {
		return place;
	}

	

	public RelativeLayout getZone() {
		return zone;
	}
	
	public TextView getItem(){
		return this.item;
	}


	
	
}
