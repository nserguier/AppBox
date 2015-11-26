package custom;

import com.Atlas.framework.R;

/**
 * une enumeration qui permet de recenser les differents types de menus
 * proposees avec leurs couleurs er backgrounds
 * 
 * @author Victor,Nicklos
 * 
 */
public enum TypeMenu {
	OceanHorizontal(R.drawable.ocean_h, R.color.bleu1, R.color.bleu2), JungleVertical(
			R.drawable.jungle_v, R.color.vert1, R.color.vert2), JungleHorizontal(
			R.drawable.jungle_h, R.color.vert1, R.color.vert2), Options(
			R.color.blanc_casse, R.color.blanc_casse, R.color.blanc_casse);

	private int background;
	private int couleur1;
	private int couleur2;

	/**
	 * 
	 * @param background
	 * @param couleur1
	 * @param couleur2
	 */
	private TypeMenu(int background, int couleur1, int couleur2) {
		this.background = background;
		this.couleur1 = couleur1;
		this.couleur2 = couleur2;
	}

	public int getBackground() {
		return background;
	}

	public int getCouleur2() {
		return couleur2;
	}

	public int getCouleur1() {
		return couleur1;
	}

}
