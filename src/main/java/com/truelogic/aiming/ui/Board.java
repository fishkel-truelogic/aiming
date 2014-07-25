package com.truelogic.aiming.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.truelogic.aiming.model.Ball;
import com.truelogic.aiming.pixel.Background;
import com.truelogic.aiming.pixel.Pixel;

public class Board extends JPanel implements ActionListener {

	private static final long serialVersionUID = 592910003380869323L;

	private static final int DELAY = 40;

	public static final int M_WIDTH = 96;

	public static final int M_HEIGHT = 60;

	private static final int B_WIDTH = M_WIDTH * Pixel.SIZE;

	private static final int B_HEIGHT = M_HEIGHT * Pixel.SIZE;

	private static final int MARGIN_LEFT = 0;

	private static final int MARGIN_TOP = 0;

	private static Board instance;

	private Timer timer;

	private Background background;
	
	private Ball ball;

	private int lives;
	
	private int points;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintBackground(g);
		paintPoints(g);

	}

	private void paintPoints(Graphics g) {
		String msg = "Points: " + points;
		Font small = new Font("Helvetica", Font.BOLD, 22);
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, 5 * Pixel.SIZE, 2 * Pixel.SIZE);
	}

	private boolean paintLives(Graphics g) {
		String msg = "Lives: " + lives;
		Font small = new Font("Helvetica", Font.BOLD, 22);
		FontMetrics metr = getFontMetrics(small);
		g.setColor(Color.white);
		g.setFont(small);
		if (lives >= 0) {
			g.drawString(msg, M_WIDTH / 2 * Pixel.SIZE, 2 * Pixel.SIZE);
			return true;
		} else {
			msg = "Game Over";
			g.drawString(msg,
					(M_WIDTH / 2) * Pixel.SIZE - metr.stringWidth(msg),
					M_HEIGHT / 2 * Pixel.SIZE);
			return false;
		}
	}

	private void paintBackground(Graphics g) {
		g.drawImage(background.getImage(), -20, -20, this);

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	private void checkLives() {
		if (lives < 0) {
			timer.stop();
		}

	}

	public Board() {
		super();
		this.ball = new Ball();
		this.background = new Background();
		setBackground(Color.BLACK);
		timer = new Timer(DELAY, this);
		timer.start();
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		setFocusable(true);
		BufferedImage cursorImg = new BufferedImage(16, 16,
				BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				cursorImg, new Point(0, 0), "blank cursor");
		setCursor(blankCursor);
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

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

	}

	private class MouseArkanoidListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			Ball ball = Board.getInstance().getBall();
			if (e.getX() == ball.getX() && e.getY() == ball.getY()) {
				
			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
		}

	}

	public int getPoints() {
		return points;
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
	
	
	
	

}
