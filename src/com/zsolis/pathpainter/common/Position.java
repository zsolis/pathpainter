package com.zsolis.pathpainter.common;

import java.io.Serializable;

public class Position implements Serializable{
	private static final long serialVersionUID = 1L;
	private int xOffset;
	private int yOffset;
	
	public Position (int x, int y) {
		xOffset = x;
		yOffset = y;
	}
	
	public int getxOffset() {
		return xOffset;
	}
	
	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}
	
	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}
}
