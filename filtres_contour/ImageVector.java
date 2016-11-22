package filtres_contour;

public class ImageVector {
	private int x;
	private int y;
	private double angle;
	public static final int DISTANCE = 3;
	
	public ImageVector(int x,int y, double angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public double getAngle() {return angle;}
}
