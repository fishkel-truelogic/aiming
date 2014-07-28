package com.truelogic.aiming.ui.pixel;


public class Background extends Pixel {

	private int x0;
	private int y0;
	private int vy0;
	
	public Background(int i, int j) {
		x = i;
		y = j;
		x0 = i;
		y0 = j;
	}

	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public int getY0() {
		return y0;
	}

	public void setY0(int y0) {
		this.y0 = y0;
	}

	public int getVy0() {
		return vy0;
	}

	public void setVy0(int vy0) {
		this.vy0 = vy0;
	}
	
	
	

}
