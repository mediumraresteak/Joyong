package com.example.cultureview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoginActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_login);
	    Intent i = new Intent(LoginActivity.this, MainActivity.class);
	    startActivity(i);
	    finish();
	}

}
