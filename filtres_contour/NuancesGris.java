package filtres_contour;

import java.awt.Color;

public class NuancesGris extends FiltreParPixel {
	int transform_pixel(int pixel){
		int R,B,G, newval;
		R = new Color(pixel).getRed();
		G = new Color(pixel).getGreen();
		B = new Color(pixel).getBlue();
		newval =  (int) ( 0.299*R + 0.587*G + 0.114*B) ;
		return (newval<<16 | newval<<8 | newval) ;
	}
}
