package com.truelogic.aiming.ui.pixel;

public class Background extends Pixel {

	public static final int FLOOR = 555;
	public static final int MAIN_Y_POSITION = -1200;
	public static final int MAIN_X_POSITION = 0;
	public static final int IMG_WIDTH = 8000;
	public static final int IMG_HEIGHT = 1800;

	private Target target;
	
	private int x0;
	private int y0;

	public Background() {
		x = MAIN_X_POSITION;
		y = MAIN_Y_POSITION;
		x0 = MAIN_X_POSITION;
		y0 = MAIN_Y_POSITION;
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

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}
	
}
