package custom;

import com.Atlas.framework.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomMenu extends RelativeLayout{
	private Button bouton;
	private TextView titre;

	public CustomMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context,attrs);
	}
        // ce constructeur ne devrait jamais être appelé, car il n'a pas d'AttributeSet en paramètre.
	public CustomMenu(Context context) {
		super(context);
	}
	public CustomMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context,attrs);
	}

	private void init(Context ctx, AttributeSet attrs){  

      // inflation du modèle "customtitle", et initialisation des composants Button et ImageView
      // on cherche le service Android pour instancier des vues
		LayoutInflater li = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      // on instancie notre vue customisée (celle créée dans l'étape 1, qui se trouve dans res/layout/customtitle)
		ViewGroup frame = (ViewGroup) findViewById(R.id.custom);
		//View v = li.inflate(R.layout.custom_menu, parent,true);
		View v = li.inflate(R.layout.custom_menu, frame,false);
		bouton = (Button)v.findViewById(R.id.bouton);
		titre = (TextView)v.findViewById(R.id.titre);
        addView(v,1700,1400);

      // Le modèle est chargé, on a plus qu'à l'initialiser avec les attributs qu'on a reçus en paramètre

	TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.stylableTitle);

      // on obtient un TypedArray, une classe qui a plein de méthodes getString(int index),
      // getInteger(int index) (...) pour obtenir la valeur String, Integer (...) d'un attribut.

      // on vérifie que l'attribut "txt" n'est pas null: ce test est important! on verra par la suite pourquoi
	if(a.getString(R.styleable.stylableTitle_titre)!=null){
	        titre.setText(a.getString(R.styleable.stylableTitle_titre));
	        /* Changement de police du titre */
			Typeface externalFont = Typeface.createFromAsset(ctx.getAssets(),"fonts/onthemove.ttf");
			titre.setTypeface(externalFont);

	}
	if(a.getString(R.styleable.stylableTitle_bouton_txt)!=null){
        bouton.setText(a.getString(R.styleable.stylableTitle_bouton_txt));
        /* Changement de police du titre */
		Typeface externalFont = Typeface.createFromAsset(ctx.getAssets(),"fonts/onthemove.ttf");
		titre.setTypeface(externalFont);

}
      // et on recommence pour l'attribut "drawable"
	if(a.getDrawable(R.styleable.stylableTitle_android_drawable)!=null)
	        bouton.setBackground(a.getDrawable(R.styleable.stylableTitle_android_drawable));

      // on recycle, c'est pour sauver mère nature
	        a.recycle();
	}
}
