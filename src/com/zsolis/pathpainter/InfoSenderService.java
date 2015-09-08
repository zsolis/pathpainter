package com.zsolis.pathpainter;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class InfoSenderService extends Service {
	private boolean threadDisable=false;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if (!threadDisable) {
						InfoSender mySender = new InfoSender("192.168.0.100",8000);
						mySender.sendPath(PathSetView.getNodeList());
						Log.v("InfoSenderService", "nodeList send");
						mySender = null;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		threadDisable = true;
		Log.v("InfoSenderService", "on destroy");
	}
	
}