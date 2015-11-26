package composants;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public final class Police {

	public static void setDefaultFont(Context context, String fontAssetName) {
		final Typeface regular = Typeface.createFromAsset(context.getAssets(),
				fontAssetName);
		replaceFont("SERIF", regular);
	}

	protected static void replaceFont(String staticTypefaceFieldName,
			final Typeface newTypeface) {
		try {
			final Field staticField = Typeface.class
					.getDeclaredField(staticTypefaceFieldName);
			staticField.setAccessible(true);
			staticField.set(null, newTypeface);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void setFont(Activity a, View v, String font) {
		Typeface externalFont = Typeface.createFromAsset(a.getAssets(),
				"fonts/" + font);
		if (v instanceof TextView) {
			((TextView) v).setTypeface(externalFont);

		} else if (v instanceof Button) {
			((Button) v).setTypeface(externalFont);
		}

	}
}
