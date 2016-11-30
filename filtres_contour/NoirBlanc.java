package filtres_contour;

import java.awt.Color;

public class NoirBlanc extends FiltreParPixel {
	private int threshold;
	
	public NoirBlanc(int limite) {
		threshold = limite;
	}
	public int getTreshold() { return threshold; }
	public void setTreshold(int limite) { 
		if (limite >= 0 && limite <=255 ) threshold = limite;
	}
	
	int transform_pixel(int pixel){
		Color c = new Color(pixel);
        double lum =  0.299*c.getRed() + 0.587*c.getGreen() + 0.114*c.getBlue();
        if (lum >= threshold) 
        	return Color.WHITE.getRGB();
        else  
        	return Color.BLACK.getRGB();
	}
}
