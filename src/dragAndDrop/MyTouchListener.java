package dragAndDrop;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnTouchListener;

/**
 * Le composant a appliquer a la vue qu'on veut pouvoir drag-and-drop
 * 
 */
public class MyTouchListener implements OnTouchListener {
	
	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {

		
		switch (motionEvent.getAction()) {
		
		case MotionEvent.ACTION_DOWN: /* Objet clique */
			ClipData data = ClipData.newPlainText("", "");
			DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
			view.startDrag(data, shadowBuilder, view, 0);
			view.setVisibility(View.INVISIBLE);
			return true;

		}
		return false;

	}
}