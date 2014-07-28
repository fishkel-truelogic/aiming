package com.truelogic.aiming.model;

import com.truelogic.aiming.ui.Board;
import com.truelogic.aiming.ui.pixel.Pixel;

public class Ball extends Pixel {
	
	private static final int RESISTENCE_FORCE = 3;
	private double x0 = 200;
	private double y0 = Board.M_HEIGHT * 10 - 30;
	private int vx0;
	private int vy0;
	private boolean beingDragged = false;

	public void move(double time, boolean gravity) {
		int g = 0;
		if (gravity) {
			g = 10;
		}
		x = (int) (x0 + vx0 * time);
		y = (int) (y0 + vy0 * time + g / 2 * Math.pow(time, 2)); 
		if (crashFloor()) {
			Board.getInstance().setTime(0);
			x0 = x;
			y0 = y;
			vy0 = (int) - ((vy0 + g * time) * 0.7);	
			vx0 = (int) (vx0 * 0.7);
		}
	}

	private boolean crashFloor() {
		// TODO cambiar este method cuando cambie el fondo y el movimiento de la camara
		return y + 11 == Board.M_HEIGHT * 10;
	}

	public int getVx0() {
		return vx0;
	}

	public void setVx0(int vx0) {
		this.vx0 = vx0;
	}

	public int getVy0() {
		return vy0;
	}

	public void setVy0(int vy0) {
		this.vy0 = vy0;
	}

	public boolean isBeingDragged() {
		return this.beingDragged ;
	}
	
	public void setBeingDragged(boolean beingDragged) {
		this.beingDragged = beingDragged;
	}

	
}
