package Model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.util.Log;

public class RetrieveImage {
	public boolean storeImage(Bitmap imageData, String filename) {
		String iconsStoragePath = Environment.getExternalStorageDirectory() + "/MatchingPang";
		File sdIconStorageDir = new File(iconsStoragePath);

		sdIconStorageDir.mkdirs();

		try {
			
			File image = new File(sdIconStorageDir, "test.jpg");
			FileOutputStream fileOutputStream = new FileOutputStream(image);

			BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

			imageData.compress(CompressFormat.JPEG, 100, bos);
			
			bos.flush();
			bos.close();
			Log.e("success", "saved");

		} catch (FileNotFoundException e) {
			Log.w("TAG", "Error saving image file: " + e.getMessage());
			return false;
		} catch (IOException e) {
			Log.w("TAG", "Error saving image file: " + e.getMessage());
			return false;
		}

		return true;
	}
	
	public Bitmap getBitmapFromURL(String imageUrl) {
		try {
			URL url = new URL(imageUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			Log.e("get", "bitmap");
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
