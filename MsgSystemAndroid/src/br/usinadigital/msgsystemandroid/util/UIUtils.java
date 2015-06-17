package br.usinadigital.msgsystemandroid.util;

import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.widget.Toast;
import br.usinadigital.msgsystemandroid.R;

public class UIUtils {

	public static CharSequence printWithDate(String txt, Date data){
		String dateLine =  txt + " " + Utils.dateToStringLocale(data);
		return dateLine;
	}
	
	public static CharSequence printOn2lineWithDate(String first, String txt, Date data){
		String dateLine =  txt + " " + Utils.dateToStringLocale(data);
		return printOn2line(first, dateLine);
	}
	
	public static CharSequence printOn2line(String first, String second){
		final String btnInnerHTML = "%s<br/><small><small>%s</small></small>";
		return Html.fromHtml(String.format(btnInnerHTML, first, second));
	}
	 
	public static void styleActionBar(ActionBar ab, String color){
	    ab.setDisplayShowHomeEnabled(true);
	    ab.setIcon(R.drawable.usina_cultural);
	    ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color)));
	}
	
	public static void showDialog(Context context, String title, String message){
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setIcon(R.drawable.usina_cultural);
		
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		alertDialog.show();
	}
	
	public static void showToast(Context context, String message) {
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.show();
	}
	
}
