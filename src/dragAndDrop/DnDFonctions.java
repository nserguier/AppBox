package dragAndDrop;

import com.Atlas.framework.R;

import composants.Animate;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class DnDFonctions {

	private String s;
	private RelativeLayout parent;
	private View view;
	private Context context;
	
	public DnDFonctions(String s,RelativeLayout parent, View view, Context context) {
		this.s = s;
		this.parent = parent;
		this.view = view;
		this.context = context; 
	}
	
	public String getString() {
		return s;
	}
	
	public void play() {
		Animate.fade_out(view, 1000, true);
		parent.removeView(view);
		/*ImageView f = new ImageView(context);
		f.setBackground(context.getApplicationContext().getResources().getDrawable(R.drawable.fruit));
		f.setId(124);
		RelativeLayout.LayoutParams f_params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		f_params.addRule(RelativeLayout.CENTER_IN_PARENT);
		f.setLayoutParams(f_params);
		parent.addView(f);
		MyDragAndDrop dnd = new MyDragAndDrop(context, (Activity)context);
		dnd.addDrag(f.getId());*/
		
	}

	
}
