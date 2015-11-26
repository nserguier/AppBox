package composants;

import android.view.View;
import android.widget.RelativeLayout;

/**
 * Permet de redimensionner une vue
 */
public class Taille {

	/**
	 * Redimensionne une vue
	 * 
	 * @param view
	 *            La vue
	 * @param h
	 *            La hauteur souhaitee en px (0 pour wrap_content)
	 * @param w
	 *            La largeur souhaitee en px (0 pour wrap_content)
	 */
	public void setSize(View view, int h, int w) {
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view
				.getLayoutParams();
		if (h == 0) {
			params.height = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
		} else {
			params.height = h;
		}
		if (w == 0) {
			params.width = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
		} else {
			params.width = w;
		}
		view.setLayoutParams(params);
	}
}
