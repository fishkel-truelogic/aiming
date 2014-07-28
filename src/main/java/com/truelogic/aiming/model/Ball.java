package com.truelogic.aiming.model;

import com.truelogic.aiming.ui.Board;
import com.truelogic.aiming.ui.pixel.Background;
import com.truelogic.aiming.ui.pixel.Pixel;

public class Ball extends Pixel {

	private double x0 = 200;
	private double y0 = Board.Floor - 10;
	private int vx0;
	private int vy0;
	private boolean beingDragged = false;
	private boolean backgroundMoveX = false;
	private boolean backgroundMoveY = false;
	private double backgroundTime = 0;

	public void move(double time, boolean gravity) {
		int g = 0;
		Background bg = Board.getInstance().getBg();
		if (gravity) {
			g = 10;
		}
		if (backgroundMoveX) {
			bg.setX((int) (bg.getX0()  - vx0 * time));
			backgroundTime += 0.015;
			if (bg.getX() >= 1866) {
				backgroundMoveX = Boolean.FALSE;
			}
		} else {
			x = (int) (x0 + vx0 * time);
			if (x >= 900) {
				backgroundMoveX = Boolean.TRUE;
			}
		}
		
		if (backgroundMoveY) {
			bg.setY((int) (bg.getY() + (y - (y0 + vy0 * time + g / 2 * Math.pow(time, 2)))));
			if (bg.getY() <= -1200) {
				backgroundMoveY = Boolean.FALSE;
			}
		} else {
			y = (int) (y0 + vy0 * time + g / 2 * Math.pow(time, 2));
			if (y <= 50) {
				backgroundMoveY = Boolean.TRUE;
			}
		}
		
		if (crashFloor()) {
			Board.getInstance().setTime(0);
			if (backgroundMoveX) {
				bg.setX0((int) bg.getX());
			} else {
				x0 = x;
			}
			vx0 = (int) (vx0 * 0.7);
			y0 = y;
			vy0 = (int) -((vy0 + g * time) * 0.7);
		}
		
	}

	private boolean crashFloor() {
		for (int i = 11; i >= 0; i --) {
			if (y + i == Board.Floor) {
				return true;
			}
		}
		return false;
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
		return this.beingDragged;
	}

	public void setBeingDragged(boolean beingDragged) {
		this.beingDragged = beingDragged;
	}

	public boolean isMoving() {
		return vx0 != 0 && vy0 != 0;
	}

}
