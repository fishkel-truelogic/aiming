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
import com.truelogic.aiming.ui.pixel.Pixel;

public class Board extends JPanel implements ActionListener {

	private static final long serialVersionUID = 592910003380869323L;

	private static final int DELAY = 1;

	public static final int Floor = 548;
	
	public static final int M_WIDTH = 96;

	public static final int M_HEIGHT = 60;

	private static final int B_WIDTH = M_WIDTH * 10;

	private static final int B_HEIGHT = M_HEIGHT * 10;

	private static final int MARGIN_LEFT = 0;

	private static final int MARGIN_TOP = 0;

	private static Board instance;

	private Timer timer;

	private Background background;
	
	private Ball ball;

	
	private int points;
	
	private double time;

	private boolean gravity;
	
	private int difX;
	private int difY;


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintBackground(g);
		paintBall(g);

	}

	private void paintBall(Graphics g) {
		int x = (int) (MARGIN_LEFT + ball.getX() * Pixel.SIZE);
		int y = (int) (MARGIN_TOP + ball.getY() * Pixel.SIZE);
		g.drawImage(ball.getImage(), x, y, this);
		
	}


	private void paintBackground(Graphics g) {
		g.drawImage(background.getImage(), (int) background.getX(), (int) background.getY(), this);

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		time = time + 0.015;
		ball.move(time, gravity);
		repaint();
	}

	public Board() {
		super();
		this.ball = new Ball();
		this.background = new Background(0, -1200);
		setBackground(Color.BLACK);
		timer = new Timer(DELAY, this);
		timer.start();
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		setFocusable(true);
		addMouseMotionListener(new MouseArkanoidListener());
		addMouseListener(new MouseArkanoidListe());
		addKeyListener(new KeyPressListener());
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
				break;
			case KeyEvent.VK_RIGHT:
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
			if (!ball.isMoving() && isDraggingZone(e.getX(), e.getY())) {
				System.out.println("mouse pressed");
				System.out.println("x = " + e.getX());
				System.out.println("y = " + e.getY());
				x0 = e.getX();
				y0 = e.getY();
				
				ball.setBeingDragged(Boolean.TRUE);
				
				setCursor (Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(ball.isBeingDragged()){
				System.out.println("mouse released");
				System.out.println("x = " + e.getX());
				System.out.println("y = " + e.getY());
				Board.getInstance().setDifX((int) (-(e.getX() - x0)));
				Board.getInstance().setDifY((int) (-(e.getY() - y0)));
				Board.getInstance().setGravity(true);
				Board.getInstance().releaseBall();
				
				ball.setBeingDragged(Boolean.FALSE);
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
			if (isDraggingZone(e.getX(), e.getY()) && !ball.isMoving()) {
				setCursor (Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			} else {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}

	}

	public int getPoints() {
		return points;
	}

	public void releaseBall() {
		ball.setVx0(difX);
		ball.setVy0(difY);
		time = 0;
		System.out.println("dif x: " + difX);
		System.out.println("dif y: " + difY);
	}

	public boolean isDraggingZone(int x, int y){
		return Math.abs((x - ball.getX())) < 10 && Math.abs((y - ball.getY())) < 10;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public static Board getInstance() {
		if (instance == null) {
			instance = new Board();
		}
		return instance;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public boolean isGravity() {
		return gravity;
	}

	public void setGravity(boolean gravity) {
		this.gravity = gravity;
	}

	public int getDifX() {
		return difX;
	}

	public void setDifX(int difX) {
		this.difX = difX;
	}

	public int getDifY() {
		return difY;
	}

	public void setDifY(int difY) {
		this.difY = difY;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public Background getBg() {
		return this.background;
	}
	
	
}
