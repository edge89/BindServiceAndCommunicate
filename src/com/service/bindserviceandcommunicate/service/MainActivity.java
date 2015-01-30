package com.service.bindserviceandcommunicate.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.service.bindserviceandcommunicate.R;

public class MainActivity extends Activity implements OnClickListener {
	protected static final String TAG = "Music_Activity";
	private Button bt_bind, bt_start, bt_stop, bt_unbind, bt_music_on,
			bt_music_off;
	private MyService myService;
	private boolean music_flag, service_flag;

	// private MyService.MyBinder binder;
	private class MyBroadCastReciver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			myService.play();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyService myService;
		bt_bind = (Button) findViewById(R.id.bt_bind);
		bt_start = (Button) findViewById(R.id.bt_start);
		bt_stop = (Button) findViewById(R.id.bt_stop);
		bt_unbind = (Button) findViewById(R.id.bt_unbind);
		bt_music_on = (Button) findViewById(R.id.bt_music_off);
		bt_music_off = (Button) findViewById(R.id.bt_music_on);
		bt_bind.setOnClickListener(this);
		bt_start.setOnClickListener(this);
		bt_stop.setOnClickListener(this);
		bt_bind.setOnClickListener(this);
		bt_unbind.setOnClickListener(this);
		bt_music_on.setOnClickListener(this);
		bt_music_off.setOnClickListener(this);

		Intent intent_service = new Intent("com.edge.service");
		startService(intent_service);
		ServiceConnection connection1 = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				System.out.println("fuck-----------------");
			}
		};

		bindService(intent_service, connection1, 0);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.bt_bind:
			if (null == myService)
				bind();
			else {
				return;

			}
			break;
		case R.id.bt_start:
			if (null == myService) {
				bind();
			} else {

				if (!myService.mediaPlayer.isPlaying()) {
					myService.play();
				} else {
					return;
				}
			}
			break;
		case R.id.bt_stop:
			if (null == myService) {
				bind();
			} else {
				if (myService.mediaPlayer.isPlaying()) {
					myService.stop();
				}
			}
			break;
		case R.id.bt_unbind:

			if (null != myService) {
				unbindService(connection);
			}

			break;
		case R.id.bt_music_on:
			if (null == myService) {
				bind();
			} else {
				if (myService.mediaPlayer.isPlaying()) {
					return;
				} else {
					myService.play();
					bt_music_on.setText("SET off");
					bt_music_off.setText("set OFF");
				}
			}
			break;
		case R.id.bt_music_off:
			if (null == myService) {
				bind();
			} else {
				if (myService.mediaPlayer.isPlaying()) {
					myService.pause();
					bt_music_on.setText("SET ON");
					bt_music_off.setText("SET ON");
				} else {
					return;
				}
			}
			break;

		default:
			break;
		}

	}

	private void bind() {
		// Intent service_intent = new Intent("com.edge.service");
		// bindService(service_intent, connection, 1);
	}

	private ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			if (null != myService) {
				// myService.mediaPlayer.release();
				myService = null;
				Log.d(TAG, "in onServiceDisconnected");
				System.out.println("in onServiceDisconnected");
			}
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			myService = ((MyService.MyBinder) service).getService();
		}
	};

}
