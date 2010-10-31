package com.maize.CardMagic;

import android.app.AlertDialog;
import android.content.Context;

public class UIUtils 
{
	/**
	 * Shows a message dialog and notifies user that his/her device does not have a light sensor. 
	 * @param context
	 */
	public static void showSensorMissingDialog(Context context)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Hardware not found");
		builder.setMessage("Unfortunately, your device does NOT have a light sensor. Get a new phone or play it on your friends' phones!");
		builder.setPositiveButton("OK", null);
		builder.create().show();
	}
}
