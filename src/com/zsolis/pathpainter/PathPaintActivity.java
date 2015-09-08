package com.zsolis.pathpainter;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class PathPaintActivity extends Activity implements OnClickListener {
	private Button backBtn;
	private Button exitBtn;
	private PathPaintView paintView;
	
	private PositionReceiver receiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//隐去标题（应用的名字)  
        //此设定必须要写在setContentView之前，否则会有异常）  
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_path_paint);
		backBtn = (Button)findViewById(R.id.paint_back_btn);
		backBtn.setOnClickListener(this);
		exitBtn = (Button)findViewById(R.id.paint_exit_btn);
		exitBtn.setOnClickListener(this);
		paintView = (PathPaintView)findViewById(R.id.paint_view);
		
		startService(new Intent(this, InfoReceiverService.class));
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//注册广播接收器
		receiver=new PositionReceiver();
		IntentFilter filter=new IntentFilter();
		filter.addAction("com.zsolis.pathpainter.InfoReceiverService");
		this.registerReceiver(receiver,filter);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		this.unregisterReceiver(receiver);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.stopService(new Intent(this, InfoReceiverService.class));
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.paint_back_btn:
			paintView.destroyView();
			this.startActivity(new Intent(this, PathSetActivity.class));
			this.finish();
			break;
			
		case R.id.paint_exit_btn:
			System.exit(0);
			break;
		
		default:
			break;
		}
	}
	
	private class PositionReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle=intent.getExtras();
			int curPosition [] = bundle.getIntArray("com.zsolis.pathpainter.position");
			paintView.addPosition2Path(curPosition);
		}
	}
}
