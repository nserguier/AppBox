package dragAndDrop;


import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnDragListener;
import android.widget.LinearLayout;
import android.content.Context;

/**
 * Le composant a appliquer a un LinearLayout qui sert de zone de drop au Drag-and-drop
 *
 */
public class MyDragListener implements OnDragListener {
	
    private Drawable dropZoneSurbrillante; // l'image a utiliser lorsque l'objet drag passe dans la zone de drop
    private Drawable dropZone; // l'image a utiliser lorsque l'objet drag ne passe pas dans la zone de drop

    
    public MyDragListener(Context context, int dropZoneID,int dropZoneSurbrillanteID){
    	if(dropZoneID!=0){
        	this.dropZone = context.getResources().getDrawable(dropZoneID);
    	}else{
    		this.dropZone = null;
    	}
    	if(dropZoneSurbrillanteID!=0){
        	this.dropZoneSurbrillante = context.getResources().getDrawable(dropZoneSurbrillanteID);
    	}else{
    		this.dropZoneSurbrillante = null;
    	}

    }
    
    @Override
    public boolean onDrag(View v, DragEvent event) {
      switch (event.getAction()) {
      
      case DragEvent.ACTION_DRAG_STARTED:
        // do nothing
        break;
        
      case DragEvent.ACTION_DRAG_ENTERED: /* Passage du drag dans la zone de drop */
    	if(dropZoneSurbrillante!=null)
        v.setBackground(dropZoneSurbrillante);
        break;
        
      case DragEvent.ACTION_DRAG_EXITED: /* Sortie du drag de la zone de drop */
    	if(dropZone!=null)
        v.setBackground(dropZone);
        break;
        
      case DragEvent.ACTION_DROP: /* Drop de l'objet */
    	  
        View view = (View) event.getLocalState(); // la vue qui est drag au dessus
        ViewGroup owner = (ViewGroup) view.getParent();
        owner.removeView(view);
        LinearLayout dropTarget = (LinearLayout) v; // la vue qui sert de drop zone
        
    	//Si une vue a deja ete depose ici, il y aura un tag
    	Object tag = dropTarget.getTag();
    	
        dropTarget.addView(view);
        view.setVisibility(View.VISIBLE);
        break;
        
      case DragEvent.ACTION_DRAG_ENDED: /* Fin du drag-and-drop */
    	if(dropZone!=null)
        v.setBackground(dropZone);
        View view2 = (View) event.getLocalState();
        view2.setVisibility(View.VISIBLE);
      default:
        break;
      }
      return true;
    }
  }