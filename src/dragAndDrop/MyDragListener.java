package dragAndDrop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import composants.MyLayoutParams;

/**
 * Le composant a appliquer a un RelativeLayout qui sert de zone de drop au
 * Drag-and-drop
 */
public class MyDragListener implements OnDragListener {

	private Drawable dropZoneSurbrillante; // l'image a utiliser lorsque l'objet
											// drag passe dans la
											// zone de drop

	private Drawable dropZone; // l'image a utiliser lorsque l'objet drag ne
								// passe pas dans la zone
								// de drop

	private boolean vide = true;

	public MyDragListener(final Context context, final int dropZoneID,
			final int dropZoneSurbrillanteID) {
		if (dropZoneID != 0) {
			dropZone = context.getResources().getDrawable(dropZoneID);
		} else {
			dropZone = null;
		}
		if (dropZoneSurbrillanteID != 0) {
			dropZoneSurbrillante = context.getResources().getDrawable(
					dropZoneSurbrillanteID);
		} else {
			dropZoneSurbrillante = null;
		}

	}

	public boolean onDrag(final View v, final DragEvent event) {
		final String s = "";
		switch (event.getAction()) {

		case DragEvent.ACTION_DRAG_STARTED:
			// TODO ICI PLACER LE CODE A EXECUTER LORSQUE L'OBJET COMMENCE A
			// ETRE GLISSE
			break;

		case DragEvent.ACTION_DRAG_ENTERED: /*
											 * Passage du drag dans la zone de
											 * drop
											 */
			if (dropZoneSurbrillante != null) {
				setBackground(v, dropZoneSurbrillante);
			}
			// TODO ICI PLACER LE CODE A EXECUTER LORSQUE L'OBJET EST GLISSE
			// DANS LA ZONE
			break;

		case DragEvent.ACTION_DRAG_EXITED: /* Sortie du drag de la zone de drop */
			if (dropZone != null) {
				setBackground(v, dropZone);
			}
			vide = true;
			// TODO ICI PLACER LE CODE A EXECUTER LORSQUE L'OBJET EST GLISSE EN
			// DEHORS DE LA
			// ZONE
			break;

		case DragEvent.ACTION_DROP: /* Drop de l'objet dans la zone */
			if (vide) {
				vide = !vide;
				final View view = (View) event.getLocalState(); // la vue qui
																// est drag au
																// dessus
				final MyLayoutParams params_view = new MyLayoutParams()
						.centerInParent();
				final ViewGroup owner = (ViewGroup) view.getParent(); // ancien
																		// parent
				owner.removeView(view);
				final RelativeLayout dropTarget = (RelativeLayout) v; // la vue
																		// qui
																		// sert
																		// de
																		// drop
																		// zone
				dropTarget.addView(view, params_view);
				view.setVisibility(View.VISIBLE);
				// TODO ICI PLACER LE CODE A EXECUTER LORSQUE L'OBJET EST LACHE
				// DANS LA ZONE
			} else {

			}
			break;

		case DragEvent.ACTION_DRAG_ENDED: /* Fin du drag-and-drop */
			if (dropZone != null) {
				setBackground(v, dropZone);
			}
			final View view2 = (View) event.getLocalState();
			view2.setVisibility(View.VISIBLE);
			// TODO ICI PLACER LE CODE A EXECUTER LORSQUE L'OBJET EST RELACHE EN
			// DEHORS DE LA
			// ZONE
		default:
			break;
		}
		return true;
	}

	/**
	 * sets the background of a view depending on the API
	 * 
	 * @param v
	 * @param d
	 */
	private static void setBackground(final View v, final Drawable d) {
		if (Build.VERSION.SDK_INT >= 16) {
			// v.setBackground(d);
			Method methodBackgroung;
			try {
				methodBackgroung = View.class.getMethod("setBackground",
						Drawable.class);
				methodBackgroung.invoke(v, d);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			v.setBackgroundDrawable(d);
		}
	}
}
