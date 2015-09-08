package com.zsolis.pathpainter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.zsolis.pathpainter.common.Position;;

public class InfoReceiverService extends Service {
	private boolean threadDisable=false;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					InfoReceiver myReceiver = new InfoReceiver(8111);
					List<Position> path = new ArrayList<Position>();
					path = myReceiver.receivePath();
					myReceiver = null;
					for(int i=0; i<path.size() && !threadDisable; i++){
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Position node = path.get(i);
						Log.v( "InfoReceiverService", String.valueOf(i) +" "
								+ String.valueOf(node.getxOffset()) +" "
								+ String.valueOf(node.getyOffset()) );
						
						//·¢ËÍ¹ã²¥
						Intent intent=new Intent();
						intent.putExtra("com.zsolis.pathpainter.position", new int [] {node.getxOffset(), node.getyOffset()});
						//intent.putExtra("com.zsolis.pathpainter.position", node);
						intent.setAction("com.zsolis.pathpainter.InfoReceiverService");
						sendBroadcast(intent);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
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
		Log.v("InfoReceiverService", "on destroy");
	}
}