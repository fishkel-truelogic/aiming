package com.truelogic.aiming.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.truelogic.aiming.model.Ball;
import com.truelogic.aiming.ui.pixel.Background;
import com.truelogic.aiming.ui.pixel.Golfer;
import com.truelogic.aiming.ui.pixel.Pixel;
import com.truelogic.aiming.ui.pixel.Target;

public class Board extends JPanel implements ActionListener {

	private static final long serialVersionUID = 592910003380869323L;

	private static final int DELAY = 1;
	public static final int BOARD_HEIGHT = 600;

	public static final int BOARD_WIDTH = 960;
	public static final int GRAVITY = 10;

	private static int margin_left = 0;
	private static int margin_top = 0;

	private Background background;
	private Ball ball;
	private Target target;
	private Timer timer;
	private Golfer golfer;


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintPixel(background, g);
		paintPixel(target, g);
		paintGolfer(g);
		paintPixel(ball, g);
	}

	private void paintGolfer(Graphics g) {
		if (ball.isBeingDragged()) {
			golfer.setImage(golfer.getImage1());
		} else if (!ball.isMoving() && !ball.isBeingDragged()) {
			golfer.setImage(golfer.getImage2());
		} else if (ball.isMoving() && golfer.getImage2().equals(golfer.getImage())){
			golfer.setImage(golfer.getImage3());
		} 
		
		if (ball.getY() < golfer.getY() + Golfer.IMG_HEIGHT / 2 && golfer.getImage3().equals(golfer.getImage())) {
			golfer.setImage(golfer.getImage4());
		}
		
		if (ball.getY() < golfer.getY() && golfer.getImage4().equals(golfer.getImage())) {
			golfer.setImage(golfer.getImage5());
		}
		
		paintPixel(golfer, g);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ball.move(background);
		if (!ball.isMoving()) {
			golfer.setAbsX(ball.getX0() - Golfer.IMAGE_WIDTH / 2);
		}
		repaint();
	}

	public Board() {
		super();
		fixMargin();
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
		setFocusable(true);
		addMouseMotionListener(new MouseArkanoidListener());
		addMouseListener(new MouseArkanoidListe());
		addKeyListener(new KeyPressListener());
		init();
	}

	private class KeyPressListener extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			super.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {

			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (background.getX() < 0)
					background.setX((int) (background.getX() + 100));
				break;
			case KeyEvent.VK_RIGHT:
				if (background.getX() >= Board.BOARD_WIDTH - Background.IMG_WIDTH + 50)
					background.setX((int) (background.getX() - 100));
				break;
			case KeyEvent.VK_SPACE:
				break;
			}
		}

	}

	private class MouseArkanoidListe implements MouseListener {

		private int x0;
		private int y0;

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (!ball.isMoving() && ball.isDraggingZone(e.getX(), e.getY())) {
				x0 = e.getX();
				y0 = e.getY();
				ball.setBeingDragged(Boolean.TRUE);
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (ball.isBeingDragged()) {
				ball.setVx0((int) (-(e.getX() - x0)));
				ball.setVy0((int) (-(e.getY() - y0)));
				ball.setTime(0);
				ball.setBeingDragged(Boolean.FALSE);
				golfer.setImage(golfer.getImage2());
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}

	}

	private class MouseArkanoidListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (ball.isDraggingZone(e.getX(), e.getY()) && !ball.isMoving()) {
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			} else {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}

	}

	private void paintPixel(Pixel pixel, Graphics g) {
		int x = (int) (margin_left + pixel.getX());
		int y = (int) (margin_top + pixel.getY());
		g.drawImage(pixel.getImage(), x, y, this);
	}

	private void init() {
		this.background = new Background();
		this.ball = new Ball(background);
		this.target = new Target();
		this.golfer = new Golfer();
		golfer.setAbsX(ball.getX0() - Golfer.IMAGE_WIDTH / 2);
		background.setTarget(target);
		background.setGolfer(golfer);
		timer = new Timer(DELAY, this);
		timer.start();
	}

	private void fixMargin() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("win") >= 0) {
			margin_top = 10;
		}
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public Background getBg() {
		return this.background;
	}

}
