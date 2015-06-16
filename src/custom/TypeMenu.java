package custom;

import com.Atlas.framework.R;

import android.graphics.drawable.Drawable;

public enum TypeMenu {OceanHorizontal(R.drawable.ocean_h,R.drawable.une_bulle),
	JungleVertical(R.drawable.jungle_v,R.drawable.buisson),
	JungleHorizontal(R.drawable.jungle_h,R.drawable.buisson);

	private int background;
	private int cache;
		
	/**
	 * 
	 * @param background
	 * @param cache
	 */
	private TypeMenu(int background, int cache) {
		this.background = background;
		this.cache = cache;
	}


	public int getBackground() {
		return background;
	}


	public int getCache() {
		return cache;
	}



	
}
