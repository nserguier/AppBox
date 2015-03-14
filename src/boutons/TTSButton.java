package boutons;

import java.util.Locale;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

public class TTSButton extends Activity {

	private String texteALire;
	private Button ttsButton;
	TextToSpeech tts;

	public TTSButton(Button ttsButton, String texteALire) {
		this.texteALire = texteALire;
		this.ttsButton = ttsButton;
	}

	public void initialisation() {

		tts = new TextToSpeech(getApplicationContext(),
				new TextToSpeech.OnInitListener() {

					public void onInit(int status) {
						if (status != TextToSpeech.ERROR)
							tts.setLanguage(Locale.FRANCE);
					}
				});

		ttsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				speakText();

			}
		});

	}

	@Override
	public void onPause() {
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onPause();
	}

	@SuppressWarnings("deprecation")
	public void speakText() {
		/*
		 * Toast.makeText(getApplicationContext(), texteALire,
		 * Toast.LENGTH_SHORT) .show();
		 */
		// affiche le texte qui est en train d'etre lu sous forme de toast
		tts.speak(texteALire, TextToSpeech.QUEUE_FLUSH, null);
	}

}
