package filtres_contour;

import java.awt.Color;

public class FiltreLaplace extends FiltreParRegions {
	int calculer_pixel(Color pixelMatrix[][]) {
		int R;
		int[][] rouges = valeurs_rouges_voisins(pixelMatrix);
		R=  (rouges[0][1])+
			(rouges[1][0])+
			(rouges[1][1]*-4)+
			(rouges[1][2])+
			(rouges[2][1]);

		if (R< 0) R=0;
		else if (R>255) R=255;
		
		return (R<<16 | R<<8 | R);
	}
}
