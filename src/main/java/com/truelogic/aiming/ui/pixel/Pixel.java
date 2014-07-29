package com.truelogic.aiming.ui.pixel;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public abstract class Pixel {

	public static final String IMAGE_PATH = "/META-INF/";
	protected double x;
	protected double y;
	protected Image image;

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImage() {
		if (image == null) {
			URL imgURL = getClass().getResource(Pixel.IMAGE_PATH + this.getClass().getSimpleName() + ".png");
			ImageIcon i = new ImageIcon(imgURL);
			image = i.getImage();
		}
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public boolean inSameSpot(Pixel other) {
		return this.getX() == other.getX() && this.getY() == other.getY();
	}

}
