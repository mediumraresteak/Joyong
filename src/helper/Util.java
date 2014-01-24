package helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.widget.Toast;

public class Util {
	public static Util		instance		= new Util();
	public static String	SEND_GPS		= "http://applepi.kr:5000/send_gps";
	public static String	GET_MSN			= "http://applepi.kr:5000/get_mission";
	
	private Util() {
	}

	public static Util getInstance() {
		return instance;
	}
	
	
	public void startActivity(Context here, Class<?> next) {
		Intent i = new Intent(here, next);
		here.startActivity(i);
	}

	public void showToast(Context c, String text) {
		Toast.makeText(c, text, 0).show();
	}
	
	
}
