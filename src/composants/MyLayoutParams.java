package composants;

import android.view.View;
import android.widget.RelativeLayout;

/**
 * Classe permettant de simplifier la creation les layout params
 * 
 * @author Nicklos,Victor
 * 
 */
public class MyLayoutParams extends RelativeLayout.LayoutParams {

	public MyLayoutParams() {
		super(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
	}

	public MyLayoutParams(int width, int height) {
		super(width, height);
	}

	public MyLayoutParams(View vue) {
		super(vue.getLayoutParams());
	}

	/**
	 * Se placer a gauche d'une vue
	 * 
	 * @param v
	 *            La vue de reference
	 * @return params
	 */
	public MyLayoutParams toLeftOf(View v) {
		this.addRule(RelativeLayout.START_OF, v.getId());
		return this;
	}

	/**
	 * Se placer a droite d'une vue
	 * 
	 * @param v
	 *            La vue de reference
	 * @return params
	 */
	public MyLayoutParams toRightOf(View v) {
		this.addRule(RelativeLayout.RIGHT_OF, v.getId());
		return this;
	}

	/**
	 * Se placer au dessus d'une vue
	 * 
	 * @param v
	 *            La vue de reference
	 * @return params
	 */
	public MyLayoutParams below(View v) {
		this.addRule(RelativeLayout.BELOW, v.getId());
		return this;
	}

	/**
	 * Se placer au dessus d'une vue
	 * 
	 * @param v
	 *            La vue de reference
	 * @return params
	 */
	public MyLayoutParams above(View v) {
		this.addRule(RelativeLayout.ABOVE, v.getId());
		return this;
	}

	/**
	 * Aligner le bord gauche au bord gauche d'une autre vue
	 * 
	 * @param v
	 *            La vue de reference
	 * @return params
	 */
	public MyLayoutParams alignStart(View v) {
		this.addRule(RelativeLayout.ALIGN_START, v.getId());
		return this;
	}

	/**
	 * Aligner le bord droit au bord droit d'une autre vue
	 * 
	 * @param v
	 *            La vue de reference
	 * @return params
	 */
	public MyLayoutParams alignEnd(View v) {
		this.addRule(RelativeLayout.ALIGN_END, v.getId());
		return this;
	}

	/**
	 * Aligner le bord haut au bord haut d'une autre vue
	 * 
	 * @param v
	 *            La vue de reference
	 * @return params
	 */
	public MyLayoutParams alignTop(View v) {
		this.addRule(RelativeLayout.ALIGN_TOP, v.getId());
		return this;
	}

	/**
	 * Aligner le bord bas au bord bas d'une autre vue
	 * 
	 * @param v
	 *            La vue de reference
	 * @return params
	 */
	public MyLayoutParams alignBottom(View v) {
		this.addRule(RelativeLayout.ALIGN_TOP, v.getId());
		return this;
	}

	/**
	 * Aligner le bord droit au bord droit du layout parent
	 * 
	 * @return params
	 */
	public MyLayoutParams alignParentRight() {
		this.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		return this;
	}

	/**
	 * Aligner le bord gauche au bord gauche du layout parent
	 * 
	 * @return params
	 */
	public MyLayoutParams alignParentLeft() {
		this.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		return this;
	}

	/**
	 * Aligner le bord bas au bord droit du layout parent
	 * 
	 * @return params
	 */
	public MyLayoutParams alignParentBottom() {
		this.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		return this;
	}

	/**
	 * Aligner le bord haut au bord haut du layout parent
	 * 
	 * @return params
	 */
	public MyLayoutParams alignParentTop() {
		this.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		return this;
	}

	/**
	 * Centrer horizontalement dans le layout parent
	 * 
	 * @return params
	 */
	public MyLayoutParams centerHorizontal() {
		this.addRule(RelativeLayout.CENTER_HORIZONTAL);
		return this;
	}

	/**
	 * Centrer verticalement dans le layout parent
	 * 
	 * @return params
	 */
	public MyLayoutParams centerVertical() {
		this.addRule(RelativeLayout.CENTER_VERTICAL);
		return this;
	}

	/**
	 * Centrer verticalement et horizontalement dans le layout parent
	 * 
	 * @return params
	 */
	public MyLayoutParams centerInParent() {
		this.addRule(RelativeLayout.CENTER_VERTICAL);
		return this;
	}

	/**
	 * Fixer une marge sur le bord haut
	 * 
	 * @param margin
	 *            valeur de la marge
	 * @return params
	 */
	public MyLayoutParams marginTop(int margin) {
		this.topMargin = margin;
		return this;
	}

	/**
	 * Fixer une marge sur le bord bas
	 * 
	 * @param margin
	 *            valeur de la marge
	 * @return params
	 */
	public MyLayoutParams marginBottom(int margin) {
		this.bottomMargin = margin;
		return this;
	}

	/**
	 * Fixer une marge sur le bord gauche
	 * 
	 * @param margin
	 *            valeur de la marge
	 * @return params
	 */
	public MyLayoutParams marginLeft(int margin) {
		this.leftMargin = margin;
		return this;
	}

	/**
	 * Fixer une marge sur le bord droit
	 * 
	 * @param margin
	 *            valeur de la marge
	 * @return params
	 */
	public MyLayoutParams marginRight(int margin) {
		this.rightMargin = margin;
		return this;
	}

	/**
	 * Fixer une marge sur chaque bord
	 * 
	 * @param left
	 *            valeur de la marge gauche
	 * @param top
	 *            valeur de la marge haute
	 * @param right
	 *            valeur de la marge droite
	 * @param bottom
	 *            valeur de la marge basse
	 * @return params
	 */
	public MyLayoutParams margins(int left, int top, int right, int bottom) {
		this.rightMargin = right;
		this.leftMargin = left;
		this.topMargin = top;
		this.bottomMargin = bottom;
		return this;
	}

}
