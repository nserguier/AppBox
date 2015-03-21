package boutons;

import com.Atlas.framework.R;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;

public class ButtonCreator {

	public static Button createBlueButton(Context c,String nomBouton){
		Button b = new Button(c);
		b.setBackground(c.getResources().getDrawable(R.drawable.bouton_bleu));
		b.setLayoutParams(new LinearLayout.LayoutParams(400, 150));
		b.setText(nomBouton);
		b.setTextColor(c.getResources().getColor(R.color.fushia));
		b.setPadding(0, 0, 0, 10);
		return b;
	}
	
}
