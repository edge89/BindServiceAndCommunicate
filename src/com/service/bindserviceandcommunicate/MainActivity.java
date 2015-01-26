package com.service.bindserviceandcommunicate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.bt_bind:
		case R.id.bt_start:
		case R.id.bt_stop:
		case R.id.bt_unbind:

			break;

		default:
			break;
		}

	}
}
