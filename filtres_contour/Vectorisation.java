package filtres_contour;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Vectorisation {
	List<ImageVector> vectorlist = new ArrayList<ImageVector>();
	
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
	
	public void vectoriser(int[][] img, int w, int h) {
		//ImageVector v;
		SimpleRegression r = new SimpleRegression(true);
		int i = 0, j = 0, imgbw[][];
		imgbw = treshold(img,w,h);
		for(i=0;i<w;i++){
			for(j=0;j<h;j++){
				if (imgbw[i][j] == Color.WHITE.getRGB())
					r.addData(i,j);
			}
		}
		System.out.println("intercept :"+r.getIntercept());
		System.out.println("slope :"+r.getSlope());
	}
	
	public List<ImageVector> getImageVector() {return vectorlist;}
}
