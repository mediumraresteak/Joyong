package Model;

import java.io.IOException;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback{
	private static final String TAG = "CameraPreview";
	private SurfaceHolder mHolder;
	private Camera mCamera;
	private Size mPreviewSize; 

	@SuppressWarnings("deprecation")
	public CameraPreview(Context context, Camera camera, Activity activity) {
	    super(context);
	    mCamera = camera;
	    mHolder = getHolder();
	    mHolder.addCallback(this);
	    mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		mCamera.setDisplayOrientation(90);
	    try {
	        mCamera.setPreviewDisplay(holder);
	        mCamera.startPreview();
	    } catch (IOException e) {
	    }
	}


	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
	    if (mHolder.getSurface() == null){
	      return;
	    }

	    try{
	            mCamera.stopPreview();
	        } catch (Exception e){
	        }


	    Camera.Parameters parameters = mCamera.getParameters();
	    List<Size> localSizes = mCamera.getParameters().getSupportedPreviewSizes();
	    mPreviewSize = localSizes.get(0);
	    Log.d(TAG, "Width " + mPreviewSize.width);
	    Log.d(TAG, "Height " + mPreviewSize.height);

	    parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height );
	    requestLayout();
	    mCamera.setParameters(parameters);
	    try {
	            mCamera.setPreviewDisplay(mHolder);
	            mCamera.startPreview();
	    } catch (Exception e){
	        Log.d(TAG, "Error starting camera preview: " + e.getMessage());
	    }
	}


//	 @SuppressLint("NewApi")
//	public static void setCameraDisplayOrientation(Activity activity,
//	         int cameraId, android.hardware.Camera camera) {
//	     android.hardware.Camera.CameraInfo info =
//	             new android.hardware.Camera.CameraInfo();
//	     android.hardware.Camera.getCameraInfo(cameraId, info);
//	     int rotation = activity.getWindowManager().getDefaultDisplay()
//	             .getRotation();
//	     int degrees = 0;
//	     switch (rotation) {
//	         case Surface.ROTATION_0: degrees = 0; break;
//	         case Surface.ROTATION_90: degrees = 90; break;
//	         case Surface.ROTATION_180: degrees = 180; break;
//	         case Surface.ROTATION_270: degrees = 270; break;
//	     }
//
//	     int result;
//	     if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
//	         result = (info.orientation + degrees) % 360;
//	         result = (360 - result) % 360;
//	     } else { 
//	         result = (info.orientation - degrees + 360) % 360;
//	     }
//	     camera.setDisplayOrientation(result);
//	 }
}