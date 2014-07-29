package com.truelogic.aiming.model;

import com.truelogic.aiming.ui.Board;
import com.truelogic.aiming.ui.pixel.Background;
import com.truelogic.aiming.ui.pixel.Pixel;

public class Ball extends Pixel {

	private static final int IMAGE_SIZE = 11;
	private static final int RIGHT_LIMIT = 900;
	private static final int TOP_LIMIT = 50;
	private static final int LEFT_LIMIT = 60;

	private double x0 = 200;
	private double y0 = Background.FLOOR - 10;

	private int vx0;
	private int vy0;

	private double time;

	private boolean beingDragged;
	private boolean backgroundMoveX;
	private boolean backgroundMoveY;

	public void move(Background bg) {
		time += 0.015;
		
		int nextX = positionX(time);
		int nextY = positionY(time);

		checkBackgroundMove(nextX, nextY, bg);
		
		if (backgroundMoveX) {
			bg.setX((int) (bg.getX0() - nextX + RIGHT_LIMIT));
		} else {
			x = nextX;
		}

		if (backgroundMoveY) {
			bg.setY((int) (bg.getY0() - nextY + TOP_LIMIT));
		} else {
			y = nextY;
		}
		
		bounceFloor(bg);

	}

	private void checkBackgroundMove(int nextX, int nextY, Background bg) {
		backgroundMoveX = (nextX > RIGHT_LIMIT && nextX > x && bg.getX() >= - 1820) || (nextX < LEFT_LIMIT && nextX < x && bg.getX() <= Background.MAIN_X_POSITION);
		backgroundMoveY = (nextY <= TOP_LIMIT && nextY < y && bg.getY() >= Background.MAIN_Y_POSITION) || (nextY <= TOP_LIMIT && nextY > y && bg.getY() < Background.MAIN_Y_POSITION) ;
		
	}

	private void bounceFloor(Background bg) {
		if (crashFloor()) {
			time = 0;
			x0 = backgroundMoveX ? -bg.getX() + RIGHT_LIMIT : x;
			y0 = y;
			vx0 = (int) (vx0 * 0.9);
			vy0 = (int) -((vy0 + Board.GRAVITY * time) * 0.9);
		}
	}

	private int positionX(double time) {
		return (int) (x0 + vx0 * time);
	}

	private int positionY(double time) {
		return (int) (y0 + vy0 * time + Board.GRAVITY / 2 * Math.pow(time, 2));
	}

	private boolean crashFloor() {
		for (int i = IMAGE_SIZE; i >= 0; i--) {
			if (y + i == Background.FLOOR) {
				return true;
			}
		}
		return false;
	}

	public boolean isMoving() {
		return vx0 != 0 || vy0 != 0;
	}

	public boolean isDraggingZone(int x, int y) {
		return Math.abs((x - this.x - 5)) < 10 && Math.abs((y - this.y - 10)) < 10;
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

	public void setTime(double time) {
		this.time = time;
	}

}
