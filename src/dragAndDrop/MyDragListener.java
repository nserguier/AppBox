package dragAndDrop;


import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnDragListener;
import android.widget.RelativeLayout;
import android.content.Context;

/**
 * Le composant a appliquer a un LinearLayout qui sert de zone de drop au Drag-and-drop
 *
 */
public class MyDragListener implements OnDragListener {
	
    private Drawable dropZoneSurbrillante; // l'image a utiliser lorsque l'objet drag passe dans la zone de drop
    private Drawable dropZone; // l'image a utiliser lorsque l'objet drag ne passe pas dans la zone de drop
    private boolean vide = true;
    DnDFonctions fonction;
    
    
    public MyDragListener(Context context, int dropZoneID,int dropZoneSurbrillanteID,DnDFonctions fonction){
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
    	this.fonction = fonction;
    	
    }
    
    @Override    
    public boolean onDrag(View v, DragEvent event) {
    	String s = "";
    	if(fonction!=null) s = fonction.getString();
        switch (event.getAction()) {
        
        case DragEvent.ACTION_DRAG_STARTED:
          // do nothing
          break;
          
        case DragEvent.ACTION_DRAG_ENTERED: /* Passage du drag dans la zone de drop */
      	if(dropZoneSurbrillante!=null)
          v.setBackground(dropZoneSurbrillante);
          break;
          
        case DragEvent.ACTION_DRAG_EXITED: /* Sortie du drag de la zone de drop */
      	if(dropZone!=null){
          v.setBackground(dropZone);
      	}
      	vide = true;
          break;
          
        case DragEvent.ACTION_DROP: /* Drop de l'objet */
      	if(vide){  
  	    	vide= !vide;
  	        View view = (View) event.getLocalState(); // la vue qui est drag au dessus
  	        ViewGroup owner = (ViewGroup) view.getParent();
  	        owner.removeView(view);
  	        RelativeLayout dropTarget = (RelativeLayout) v; // la vue qui sert de drop zone
  	        dropTarget.addView(view);
  	        view.setVisibility(View.VISIBLE);

      	}
      	else{
      		
      	}
          break;
          
        case DragEvent.ACTION_DRAG_ENDED: /* Fin du drag-and-drop */
      	if(dropZone!=null)
          v.setBackground(dropZone);
          View view2 = (View) event.getLocalState();
          view2.setVisibility(View.VISIBLE);
          if(s.equals("play")) fonction.play();
          default:
          break;
        }
        return true;
      }
  }