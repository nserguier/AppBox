package dragAndDrop;


import composants.MyLayoutParams;

import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnDragListener;
import android.widget.RelativeLayout;
import android.content.Context;

/**
 * Le composant a appliquer a un RelativeLayout qui sert de zone de drop au Drag-and-drop
 *
 */
public class MyDragListener implements OnDragListener {
	
    private Drawable dropZoneSurbrillante; // l'image a utiliser lorsque l'objet drag passe dans la zone de drop
    private Drawable dropZone; // l'image a utiliser lorsque l'objet drag ne passe pas dans la zone de drop
    private boolean vide = true;
    
    
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
    	String s = "";
        switch (event.getAction()) {
        
        case DragEvent.ACTION_DRAG_STARTED:
        	//TODO ICI PLACER LE CODE A EXECUTER LORSQUE L'OBJET COMMENCE A ETRE GLISSE
          break;
          
        case DragEvent.ACTION_DRAG_ENTERED: /* Passage du drag dans la zone de drop */
      	if(dropZoneSurbrillante!=null)
          v.setBackground(dropZoneSurbrillante);
      	  //TODO ICI PLACER LE CODE A EXECUTER LORSQUE L'OBJET EST GLISSE DANS LA ZONE
          break;
          
        case DragEvent.ACTION_DRAG_EXITED: /* Sortie du drag de la zone de drop */
      	if(dropZone!=null){
          v.setBackground(dropZone);
      	}
      	vide = true;
      	//TODO ICI PLACER LE CODE A EXECUTER LORSQUE L'OBJET EST GLISSE EN DEHORS DE LA ZONE
          break;
          
        case DragEvent.ACTION_DROP: /* Drop de l'objet dans la zone*/
      	if(vide){  
  	    	vide= !vide;
  	        View view = (View) event.getLocalState(); // la vue qui est drag au dessus
  	        MyLayoutParams params_view = new MyLayoutParams().centerInParent();
  	        ViewGroup owner = (ViewGroup) view.getParent(); // ancien parent
  	        owner.removeView(view);
  	        RelativeLayout dropTarget = (RelativeLayout) v; // la vue qui sert de drop zone
  	        dropTarget.addView(view,params_view);
  	        view.setVisibility(View.VISIBLE);
  	        //TODO ICI PLACER LE CODE A EXECUTER LORSQUE L'OBJET EST LACHE DANS LA ZONE
      	}
      	else{
      		
      	}
          break;
          
        case DragEvent.ACTION_DRAG_ENDED: /* Fin du drag-and-drop */
      	if(dropZone!=null)
          v.setBackground(dropZone);
          View view2 = (View) event.getLocalState();
          view2.setVisibility(View.VISIBLE);
        //TODO ICI PLACER LE CODE A EXECUTER LORSQUE L'OBJET EST RELACHE EN DEHORS DE LA ZONE
          default:
          break;
        }
        return true;
      }
  }