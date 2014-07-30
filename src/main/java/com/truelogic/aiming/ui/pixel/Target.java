package com.truelogic.aiming.ui.pixel;

import com.truelogic.aiming.ui.Board;



public class Target extends Pixel {

	private static final double IMG_HEIGHT = 157; 
	private double absX;
	private double absY;
	
	public Target() {
		this.absY = Background.IMG_HEIGHT - IMG_HEIGHT  - (Board.BOARD_HEIGHT - Background.FLOOR);
		this.absX = Math.random() * Background.IMG_WIDTH;
	}


	public double getAbsX() {
		return absX;
	}

	public void setAbsX(double absX) {
		this.absX = absX;
	}
	
	public double getAbsY() {
		return absY;
	}


	public void setAbsY(double absY) {
		this.absY = absY;
	}

	public void move(double x, double y) {
		this.x = absX + x;
		this.y = absY + Math.abs(Background.MAIN_Y_POSITION - y) - (Background.IMG_HEIGHT - Board.BOARD_HEIGHT);
	}

}
