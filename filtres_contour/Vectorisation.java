package filtres_contour;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Vectorisation {
	private int img_w;
	private int img_h;
	List<ImageVector> vectorlist;
	
	public Vectorisation(int[][] img, int w, int h) {
		ImageVector v = null;
		int i,j,c,l, imgbw[][], cols,lines,d = ImageVector.DISTANCE;
		this.img_w = w;
		this.img_h = h;
		this.vectorlist = new ArrayList<ImageVector>();
		cols = (int)Math.ceil(((double)w)/d);
		lines = (int)Math.ceil(((double)h)/d);
		
		NoirBlanc nb = new NoirBlanc(40);
		imgbw = nb.process(img,w,h);
		
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
				if (r.getN() >= 2 && !Double.isNaN(r.getSlope())) { //nminimum number of pixels in section
					int ordonnee = (int)Math.round(r.getIntercept());
					if (ordonnee>=0 && r.getSlope() >=0 ) {
						int y = (int) r.predict(xstart);
						v = new ImageVector(xstart,y,r.getSlope());
					} else {
						int x = (int)((l*d-ordonnee)/(r.getSlope())) ;
						v = new ImageVector(x,ystart,r.getSlope());
						//System.out.println("2: x :"+x+" ,ystart:"+ystart);
					}
				} else if (r.getN() >= 2 && Double.isNaN(r.getSlope())) {
					v = new ImageVector((int)(avgx/cmp),ystart,r.getSlope());
					//System.out.println("3: x :"+(avgx/cmp)+" ,ystart:"+ystart);
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
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("images/test.svg"))) {
            bw.write("<svg version=\"1.1\" "+
	     "xmlns=\"http://www.w3.org/2000/svg\" baseProfile=\"full\" height=\""+img_h+"\" width=\""+img_w+"\">");
            bw.write("\n");
		for (ImageVector segment : vectorlist) {
			if (segment == null) { continue; }
			x = segment.getX();
			y = segment.getY();
			angle = segment.getAngle();
			//int maxx = x+d -1;
			//int maxy = y+d -1;
			//System.out.println("x :"+x+" ,y :"+y);
			//System.out.println("slope :"+segment.getAngle());
			if (Double.isNaN(angle)) {
				dx=0;
				dy=1;
			} else if (Math.round(angle) == 0.0) {
				dx=1;
				dy=0;
			} else if (Math.round(angle) == -1.0) {
				dx=-1;
				dy=1;
			} else /*angle == 1 */{
				dx=1;
				dy=1;
			}
			i = 0;
			//System.out.println("dx :"+dx+" ,dy :"+dy);
			bw.write("<line x1=\""+x+"\" y1=\""+y+"\" ");
			while (i<d && x<img_w && y<img_h && x>=0 && y>=0){
				//System.out.println("x :"+x+" ,y :"+y);
				image[x][y] = Color.WHITE.getRGB(); 
				i++;
				x+=dx;
				y+=dy;
			}
			
			bw.write("x2=\""+x+"\" y2=\""+y+"\" style=\"stroke:rgb(0,0,0);stroke-width:1\" />");
			bw.write("\n");
		}
		
	            bw.write("</svg>");
	       }  catch (IOException e) {}
		
		return image;
	}
	
}
