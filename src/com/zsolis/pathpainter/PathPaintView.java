package com.zsolis.pathpainter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PathPaintView extends SurfaceView implements SurfaceHolder.Callback,Runnable {
	private SurfaceHolder holder;
	private Boolean isRunning;
	private Canvas canvas;
	private Paint paint;
    private List<int[]> path;
    
    public PathPaintView(Context context, AttributeSet attrs) {
        super(context,attrs);
        // TODO Auto-generated constructor stub
        holder = this.getHolder();
        isRunning = true;
        holder.addCallback(this);
        canvas = new Canvas();
        paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(6);
        path = new ArrayList<int[]>();
    }
    
    public void addPosition2Path(int position[]) {
		path.add(position);
	}
    
    public void destroyView() {
		this.surfaceDestroyed(holder);
	}
    
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    	isRunning = true;
    	new Thread(this).start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    	isRunning = false;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning) {
			try {
	            synchronized (holder) {
	                canvas = holder.lockCanvas();//锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
	                canvas.drawColor(Color.LTGRAY);//设置画布背景颜色
	                if (path == null || path.size() == 0) {
						continue;
					}
	                canvas.drawCircle(path.get(0)[0], path.get(0)[1], 3, paint);
	                if (path.size() > 1) {
	                	for (int i = 1; i < path.size(); i++) {
							int curNode [] = path.get(i);
							canvas.drawCircle(curNode[0], curNode[1], 3, paint);
							int preNode [] = path.get(i-1);
							canvas.drawLine(preNode[0], preNode[1], curNode[0], curNode[1], paint);
						}
					}
	                
	            }
	        }
	        catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	        }
	        finally {
	            if(canvas!= null) {
	                holder.unlockCanvasAndPost(canvas);//结束锁定画图，并提交改变。
	            }
	        }
		}
	}
}
