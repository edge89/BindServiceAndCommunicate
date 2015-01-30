package com.service.bindserviceandcommunicate.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.service.bindserviceandcommunicate.R;

public class MyService extends Service {
	private final static String TAG = "service_music";

	MediaPlayer mediaPlayer;

	private IBinder binder = new MyBinder();

	public class MyBinder extends Binder {

		MyService getService() {
			return MyService.this;
		};

	}

	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
		System.out.println("onCreate");
		super.onCreate();

	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind");
		play();
		System.out.println("onBind");
		return binder;
	}

	public void play() {
		if (null == mediaPlayer) {
			mediaPlayer = MediaPlayer.create(this, R.raw.sendaway);
			mediaPlayer.setLooping(true);
		} else {
			if (!mediaPlayer.isPlaying()) {
				mediaPlayer.start();
				System.out.println("play");
			}
		}
	}

	public void pause() {
		if (null != mediaPlayer && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
			System.out.println("pause");
		}
	}

	public void stop() {
		if (null != mediaPlayer && mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
			mediaPlayer.prepareAsync();
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		if (null != mediaPlayer) {
			mediaPlayer.release();
		}
		Log.d(TAG, "onUnbind");
		System.out.println("onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		System.out.println("onDestroy");
		super.onDestroy();
	}
}
