package filtres_contour;

public class ImageVector {
	private int x;
	private int y;
	private double angle;
	private int distance;
	private static final int MAXDISTANCE = 10;
	
	public ImageVector(int x,int y, double angle, int d) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.distance = d;
	}
}
