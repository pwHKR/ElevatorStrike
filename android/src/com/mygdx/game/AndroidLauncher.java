package com.mygdx.game;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.provider.Settings;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import java.sql.SQLException;




public class AndroidLauncher extends AndroidApplication {


	//Variable for brightness value
	private int brightness;
	//Content resolver used as handle to the systems settings
	private ContentResolver contentResolver;
	//Window object that stores a reference to the current window
	private Window window;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Brightness adaption for battery level

		IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = getContext().registerReceiver(null, iFilter);

		//Check if phone is charging
		int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
				status == BatteryManager.BATTERY_STATUS_FULL;

		int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

		float batteryPct = level / (float)scale;

		Log.d("Battery: ", String.valueOf(batteryPct));

		contentResolver = getContentResolver();

		//Get the current window

		window = getWindow();

		if (Build.VERSION.SDK_INT < 21) {
			try {
				// To handle the auto
				Settings.System.putInt(contentResolver,
						Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
				//Get the current system brightness
				brightness = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS);
			}
			catch (Settings.SettingNotFoundException e) {
				Log.e("Error", "Cannot access system brightness");
				e.printStackTrace();
			}

			//Set the system brightness in if statement below
			if(batteryPct < 0.5 || !isCharging){
				Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, 125);
			}

			//Test
			//Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, 40);
		}

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Thread testConn = new DBTest();


		testConn.start();



		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();


		initialize(new MyGdxGame(), config);













		//Log.d("room names" ,dbh.getRoomNames().get(0));
	}
}
