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

	private int absX;
	private int absY;

	private double time;

	private boolean beingDragged;
	private boolean backgroundMoveRightX;
	private boolean backgroundMoveLeftX;
	private boolean backgroundMoveY;

	private Background background;

	public Ball(Background background) {
		x = x0;
		y = y0;
		this.background = background;
	}

	public void move(Background bg) {
		
		absoluteMove();
		relativeMove();

		if (crashFloor()) {
			bounceVertically();
		}

		if (crashWall()) {
			bounceHorizontally();
		}

	}

	private void absoluteMove() {
		time += 0.025;
		absX = positionX(time);
		absY = positionY(time);
	}

	private void relativeMove() {
		
		checkBackgroundMove();

		if (backgroundMoveRightX) {
			background.setX((int) (background.getX0() - absX + RIGHT_LIMIT));
		} else if (backgroundMoveLeftX) {
			background.setX((int) (background.getX0() - absX + LEFT_LIMIT));
		} else {
			x = absX + background.getX();
		}

		if (backgroundMoveY) {
			background.setY((int) (background.getY0() - absY + TOP_LIMIT));
		} else {
			y = absY;
		}

	}

	private int positionX(double time) {
		return (int) (x0 + vx0 * time);
	}

	private int positionY(double time) {
		return (int) (y0 + vy0 * time + Board.GRAVITY / 2 * Math.pow(time, 2));
	}

	private void checkBackgroundMove() {

		backgroundMoveLeftX = (x < LEFT_LIMIT && vx0 < 0 && background.getX() <= Background.MAIN_X_POSITION);
		backgroundMoveRightX = (x > RIGHT_LIMIT && vx0 > 0 && background.getX() >= Board.BOARD_WIDTH
				- Background.IMG_WIDTH);
		backgroundMoveY = (absY <= TOP_LIMIT && absY < y && background.getY() >= Background.MAIN_Y_POSITION)
				|| (absY <= TOP_LIMIT && absY > y && background.getY() < Background.MAIN_Y_POSITION);

	}
	
	private void bounceHorizontally() {
		vx0 = (int) (-vx0 * 0.6);
		x0 = absX;
		y0 = absY;
		vy0 = (int) ((vy0 + Board.GRAVITY * time) * 0.7);
		time = 0;
	}

	private void bounceVertically() {
		x0 = absX;
		y0 = absY;
		vx0 = (int) (vx0 * 0.9);
		vy0 = (int) -((vy0 + Board.GRAVITY * time) * 0.8);
		time = 0;
	}
	
	private boolean crashWall() {

		for (int i = IMAGE_SIZE; i >= 0; i--) {
			if (absX + i == Background.IMG_WIDTH) {
				return true;
			}
			if (absX - i == 0) {
				absX += 1;
				return true;
			}
		}
		return false;
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
		return Math.abs((x - this.x - 5)) < 10
				&& Math.abs((y - this.y - 10)) < 10;
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
