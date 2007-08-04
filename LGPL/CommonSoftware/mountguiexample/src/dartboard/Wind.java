package dartboard;

import java.awt.*;
/**
 * Insert the type's description here.
 * Creation date: (11/7/00 8:45:12 PM)
 * @author: 
 */
public class Wind extends Dart {
	public double[] WIND_LIMITS = {12, 18};
	public Color[] WIND_COLORS = {Color.green, Color.yellow, Color.red};

	private double speed;
	private static int[] xdefault = {-3, 3, 3, 8, 0, -8, -3};
	private static int[] ydefault = {-10, -10, 0, 0, 10, 0, 0};	
	private Arrow arrow;

	private class Arrow extends java.awt.Polygon {
			
		public Arrow() {
			super(xdefault, ydefault, ydefault.length);
		}

		public void rotate(double angle) {
			double sin = Math.sin(angle * Math.PI / 180);
			double cos = Math.cos(angle * Math.PI / 180);

			for (int i = 0; i < npoints; i++) {
				xpoints[i] = (int)(xdefault[i] * cos - ydefault[i] * sin);
				ypoints[i] = (int)(xdefault[i] * sin + ydefault[i] * cos);
			}
		}
	}
/**
 * Wind constructor comment.
 * @param x int
 * @param y int
 */
public Wind() {
	this(0,0);
}
/**
 * Wind constructor comment.
 * @param x int
 * @param y int
 */
public Wind(double direction, double speed) {
	super();
	this.azimuth = direction;
	this.speed = speed;
	arrow = new Arrow();
}
protected void drawDart(Graphics g) {
	// here we manage the wind arrow color;
	int i = 0;
	while (i < WIND_LIMITS.length && speed > WIND_LIMITS[i]) i++;
	g.setColor(WIND_COLORS[i]);

	arrow.rotate(azimuth);
	g.fillPolygon(arrow);
	g.setColor(Color.black);
	g.drawPolygon(arrow);
}
public double getDirection() {
	return azimuth;
}
public double getSpeed() {
	return speed;
}
public void setDirection(double direction) {
	this.azimuth = direction;
	setPosition(0, direction);
}
public void setSpeed(double speed) {
	this.speed = speed;
}
public void setVelocity(double direction, double speed) {
	setSpeed(speed);
	setDirection(direction);
}

public void setError(boolean err) {}
}
