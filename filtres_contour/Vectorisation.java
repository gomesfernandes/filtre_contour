package filtres_contour;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Vectorisation {
	private int img_w;
	private int img_h;
	List<ImageVector> vectorlist;
	
	public static int[][] treshold(int[][] img, int w, int h) {
        int THRESHOLD = 70, i=0, j=0;
        int bw[][] = new int[w][h];
        for (i = 0; i < w; i++) {
            for (j = 0; j < h; j++) {
                Color c = new Color(img[i][j]);
                double lum =  0.299*c.getRed() + 0.587*c.getGreen() + 0.114*c.getBlue();
                if (lum >= THRESHOLD) 
                	bw[i][j] = Color.WHITE.getRGB();
                else  
                	bw[i][j] = Color.BLACK.getRGB();
            }
        }
        return bw;
    }
	
	public Vectorisation(int[][] img, int w, int h) {
		ImageVector v = null;
		int i,j,c,l, imgbw[][], cols,lines,d = ImageVector.DISTANCE;
		this.img_w = w;
		this.img_h = h;
		this.vectorlist = new ArrayList<ImageVector>();
		cols = (int)Math.ceil(((double)w)/d);
		lines = (int)Math.ceil(((double)h)/d);
		imgbw = treshold(img,w,h);
		
		for (c=0; c<cols; c++) {
			for (l=0;l<lines;l++) {
				int xstart = c*d;
				int ystart = l*d;
				double avgx = 0.0;
				int cmp=0;
				SimpleRegression r = new SimpleRegression(true);
				for(i=xstart;i<xstart+d && i<w; i++){
					for(j=ystart; j<ystart+d &&j<h; j++){
						if (imgbw[i][j] == Color.WHITE.getRGB()) {
							r.addData(i,j);
							avgx += i;
							cmp++;
						}
					}
				}
				if (r.getN() > 3 && !Double.isNaN(r.getSlope())) { //nminimum number of pixels in section
					int ordonnee = (int)Math.round(r.getIntercept());
					if (ordonnee>=0) {
						v = new ImageVector(xstart,ordonnee,r.getSlope());
					} else {
						int x = (int)((-ordonnee)/(r.getSlope())) + c*d;
						v = new ImageVector(x,ystart,r.getSlope());
						System.out.println("x :"+x+" ,ystart:"+ystart);
					}
				} else if (r.getN() > 3 && Double.isNaN(r.getSlope())) {
					v = new ImageVector((int)(avgx/cmp),ystart,r.getSlope());
				}
				vectorlist.add(v);
			}
			
		}
		
	}
	
	public int[][] vectorsToRGB() {
		int i,j, x, y, dx, dy, d = ImageVector.DISTANCE;
		double angle;
		int[][] image = new int[img_w][img_h];
		for (i=0; i<img_w; i++) {
			for (j=0; j<img_h; j++) {
				image[i][j] = Color.BLACK.getRGB(); 
			}
		}
		for (ImageVector segment : vectorlist) {
			if (segment == null) { continue; }
			x = segment.getX();
			y = segment.getY();
			angle = segment.getAngle();
			//int maxx = x+d -1;
			//int maxy = y+d -1;
			System.out.println("x :"+x+" ,y :"+y);
			System.out.println("slope :"+segment.getAngle());
			if (Double.isNaN(angle)) {
				dx=0;
				dy=1;
			} else if (Math.floor(angle) == 0.0) {
				dx=1;
				dy=0;
			} else if (Math.floor(angle) == -1.0) {
				dx=-1;
				dy=1;
			} else /*angle == 1 */{
				dx=1;
				dy=1;
			}
			i = 0;
			System.out.println("dx :"+dx+" ,dy :"+dy);
			while (i<d && x<img_w && y<img_h){
				System.out.println("x :"+x+" ,y :"+y);
				image[x][y] = Color.WHITE.getRGB(); 
				i++;
				x+=dx;
				y+=dy;
			}
		}
		
		return image;
	}
	
}
