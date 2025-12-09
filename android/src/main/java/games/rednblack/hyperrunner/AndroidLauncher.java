package games.rednblack.hyperrunner;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import games.rednblack.hyperrunner.HyperRunner;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
		configuration.useGL30 = true;
		initialize(new HyperRunner(), configuration);
	}

	@Override
	public void onBackPressed() {
		finish();
	}
}