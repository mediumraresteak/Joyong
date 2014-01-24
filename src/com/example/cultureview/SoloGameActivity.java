package com.example.cultureview;

import Model.CameraPreview;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class SoloGameActivity extends Activity{
	private Camera mCamera;
	private CameraPreview mPreview;
	private FrameLayout preview;
	private static final String TAG = "CameraActivity";
	public static final int MEDIA_TYPE_IMAGE = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solo_game);
		if (checkCameraHardware(getBaseContext())) {
			mCamera = getCameraInstance();
			mPreview = new CameraPreview(this, mCamera, SoloGameActivity.this);
			preview = (FrameLayout) findViewById(R.id.camerapreview);
			preview.addView(mPreview);
		}
		
		Button captureButton = (Button) findViewById(R.id.capture);
		captureButton.setOnClickListener(
		    new View.OnClickListener() {
		        @Override
		        public void onClick(View v) {
		            // get an image from the camera
		            mCamera.takePicture(null, null, mPicture);
		        }
		    }
		);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mCamera.release();
	}
	private boolean checkCameraHardware(Context context) {
		if (context.getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA)) {
			Log.d(TAG, "Camera Available");
			return true;
		} else {
			Log.d(TAG, "No Camera Found");
			return false;
		}
	}

	public static Camera getCameraInstance(){
	    Camera c = null;
	    try {
	        c = Camera.open(); // attempt to get a Camera instance
	    }
	    catch (Exception e){
	        // Camera is not available (in use or does not exist)
	    }
	    return c; // returns null if camera is unavailable
	}
	
	private PictureCallback mPicture = new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			Log.e("Camera", "picture taken");
		}
	};

}
