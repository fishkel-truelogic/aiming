package com.truelogic.aiming.ui.pixel;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import com.truelogic.aiming.ui.Board;

public class Golfer extends Pixel {
	
	private static final String IMAGES_FOLDER = Pixel.IMAGE_PATH + "Golfer/";

	public static final int IMG_HEIGHT = 280;

	public static final int IMAGE_WIDTH = 180;
	
	private Image image1;
	private Image image2;
	private Image image3;
	private Image image4;
	private Image image5;
	
	private double absX;
	private double absY;
	
	public Golfer() {
		this.absY = Background.IMG_HEIGHT - IMG_HEIGHT  - (Board.BOARD_HEIGHT - Background.FLOOR);
		this.setImage(getImage2());
	}
	
	public Image getImage1() {
		if (image1 == null) {
			image1 = getImage(1);
		}
		return image1;
	}

	public Image getImage2() {
		if (image2 == null) {
			image2 = getImage(2);
		}
		return image2;
	}

	public Image getImage3() {
		if (image3 == null) {
			image3 = getImage(3);
		}
		return image3;
	}

	public Image getImage4() {
		if (image4 == null) {
			image4 = getImage(4);
		}
		return image4;
	}
	
	public Image getImage5() {
		if (image5 == null) {
			image5 = getImage(5);
		}
		return image5;
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

	private Image getImage(int index) {
		URL imgURL = getClass().getResource(IMAGES_FOLDER + this.getClass().getSimpleName() + index + ".png");
		ImageIcon i = new ImageIcon(imgURL);
		return i.getImage();
	}

	public void move(double x, double y) {
		this.x = absX + x;
		this.y = absY + Math.abs(Background.MAIN_Y_POSITION - y) - (Background.IMG_HEIGHT - Board.BOARD_HEIGHT);
	}
	

}
