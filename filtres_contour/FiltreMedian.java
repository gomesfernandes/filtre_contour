package filtres_contour;

import java.awt.Color;
import java.util.Arrays;

public class FiltreMedian extends FiltreParRegions {
	
	int calculer_pixel(Color pixelMatrix[][]) {
		int redval,blueval,greenval;
		int[] blues = new int[9]; 
		int[] reds = new int[9]; 
		int[] greens = new int[9]; 
		for (int k=0;k<3;k++) {
			for (int l=0;l<3;l++) {
				reds[3*k+l] = pixelMatrix[k][l].getRed();
				blues[3*k+l] = pixelMatrix[k][l].getBlue();
				greens[3*k+l] = pixelMatrix[k][l].getGreen();
			}
		}
		Arrays.sort(reds);
		Arrays.sort(blues);
		Arrays.sort(greens);
		redval = reds[4];
		blueval = blues[4];
		greenval = greens[4];
		
		return (redval<<16 | greenval<<8 | blueval);
	}
}
