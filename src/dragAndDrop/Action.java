package dragAndDrop;

import android.widget.ImageView;
import android.widget.RelativeLayout;

public interface Action {

	void destroyOrigin(RelativeLayout parent,ImageView toDestroy);
}
