package boutons;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TTSButton extends Activity {

	private String texteALire;
	private Button ttsButton;
	private EditText edit;
	Context ctx;
	TextToSpeech tts;

	public TTSButton(Button ttsButton, EditText edit,Context ctx) {
		this.texteALire = null;
		this.edit = edit;
		this.ttsButton = ttsButton;
		this.ctx = ctx;
	}

	public void initialisation() {

		tts = new TextToSpeech(ctx,
				new TextToSpeech.OnInitListener() {

					public void onInit(int status) {
						if (status != TextToSpeech.ERROR)
							tts.setLanguage(Locale.FRANCE);
					}
				});

		ttsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				texteALire = edit.getText().toString();
				tts.speak(texteALire, TextToSpeech.QUEUE_FLUSH, null);

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


}
