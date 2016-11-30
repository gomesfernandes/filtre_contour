package filtres_contour;

import java.awt.Color;

public abstract class FiltreContour extends FiltreParRegions {
	int noyau_x[][];
	int noyau_y[][];
	
	public FiltreContour(int kernel_x[][],int kernel_y[][]) {
		noyau_x = kernel_x;
		noyau_y = kernel_y;
	}
	
	int calculer_pixel(Color pixelMatrix[][]){
		int x=0,y=0,R;
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				x += (pixelMatrix[i][j]).getRed()*noyau_x[i][j];
				y += (pixelMatrix[i][j]).getRed()*noyau_y[i][j];
			}
		}
		R = (int) Math.sqrt(x*x+y*y);
		if (R< 0) R=0;
		if (R>255) R=255;
		return (R<<16 | R<<8 | R);
	}
}
