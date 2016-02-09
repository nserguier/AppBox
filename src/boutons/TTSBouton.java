package boutons;

import java.util.Locale;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;

public class TTSBouton {

	private static TextToSpeech tts;

	/**
	 * cree un TextToSpeech sur un bouton
	 */
	private static void initialisation(final View ttsButton, final String texte,
			Context ctx, final boolean muet, final EditText edit) {

		tts = new TextToSpeech(ctx, new TextToSpeech.OnInitListener() {

			public void onInit(int status) {
				if (status != TextToSpeech.ERROR)
					tts.setLanguage(Locale.FRANCE);
			}
		});

		ttsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String t = texte;
				if (!muet) {
					if (edit != null)
						t = edit.getText().toString();
					tts.speak(t, TextToSpeech.QUEUE_FLUSH, null);
				}
			}
		});
	}

	/**
	 * pour faire taire un bouton initialise
	 */
	public static void fermer(View ttsButton, Context ctx) {
		initialisation(ttsButton, "", ctx, true, null);

	}

	/**
	 * Creer un listener sur la vue pour lire le texte au clic
	 * @param ttsButton
	 * @param texte
	 * @param ctx
	 */
	public static void parle(View ttsButton, String texte, Context ctx) {
		initialisation(ttsButton, texte, ctx, false, null);

	}

	/**
	 * si a la place d'un texte on veut en ecrire un puis le lire
	 * 
	 * @param ttsButton
	 * @param edit
	 * @param ctx
	 */
	public static void parleEdit(View ttsButton, EditText edit, Context ctx) {
		initialisation(ttsButton, null, ctx, false, edit);

	}

}
