package com.zsolis.pathpainter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class PathSetActivity extends Activity implements OnClickListener {
	private Button startBtn;
	private Button resetBtn;
	private PathSetView setView;
	
	@Override
	protected void onCreate(Bundle s) {
		super.onCreate(s);
		//»´∆¡œ‘ æ
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_path_set);
		
		startBtn = (Button)findViewById(R.id.set_start_btn);
		startBtn.setOnClickListener(this);
		resetBtn = (Button)findViewById(R.id.set_reset_btn);
		resetBtn.setOnClickListener(this);
		setView = (PathSetView)findViewById(R.id.set_view);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.stopService(new Intent(this, InfoSenderService.class));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.set_start_btn:
			this.startService(new Intent(this, InfoSenderService.class));
			setView.destroyView();
			this.startActivity(new Intent(this, PathPaintActivity.class));
			this.finish();
			break;
			
		case R.id.set_reset_btn:
			setView.resetCanvasPath();
			break;
		default:
			break;
		}
	}
}
