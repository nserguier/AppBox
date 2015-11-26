package boutons;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Le composant a appliquer a un bouton pour quitter l'application
 */
public class ExitApplicationListener implements OnClickListener {

	Drawable pressed;
	Button button;
	Activity currentActivity;

	public ExitApplicationListener(final Button button, final Drawable pressed,
			final Activity activity) {
		this.pressed = pressed;
		this.button = button;
		currentActivity = activity;
	}

	public void onClick(final View v) {

		/* Changement de l'aspect du bouton lorsqu'on l'enfonce */
		if (Build.VERSION.SDK_INT >= 16) {
			//button.setBackground(pressed);
			Method methodBackgroung;
			try {
				methodBackgroung = View.class.getMethod("setBackground",
						Drawable.class);
				methodBackgroung.invoke(button, pressed);
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
			button.setBackgroundDrawable(pressed);
		}

		/* On quitte l'application */
		final Context ctx = v.getContext();
		final Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		ctx.startActivity(intent);

	}

}