package boutons;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class TTSButton extends Activity {

	
	private Button ttsButton;
	private EditText edit;
	private String texte;
	Context ctx;
	TextToSpeech tts;

	public TTSButton(Button ttsButton, EditText edit,Context ctx) {
		
		this.edit = edit;
		this.ttsButton = ttsButton;
		this.ctx = ctx;
	}
	
	public TTSButton(Button ttsButton, String texte,Context ctx) {
		Log.d("graboudibou","niki");
		this.edit = null;
		this.texte = texte;
		this.ttsButton = ttsButton;
		this.ctx = ctx;
	}

	/*
	 * methode qui cree un bouton cliquable a partir de l'attribut bouton 
	 * et qui lit le texte de l'attribut quand on clique dessus
	 */
	public void initialisation() {

		Log.d("blabla","fdp");
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
				if(edit!=null)	texte = edit.getText().toString();
				Toast.makeText(ctx, texte, 
				Toast.LENGTH_SHORT).show();
				tts.speak(texte, TextToSpeech.QUEUE_FLUSH, null);

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
