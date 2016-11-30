package filtres_contour;

public abstract class FiltreParPixel extends Filtre {
	
	abstract int transform_pixel(int pixel);
	
	public int[][] process(int [][] img, int w, int h) {
		int RGBval;
		int out[][] = new int[w][h];
		for(int i=0;i<w;i++) {
			for(int j=0;j<h;j++) {
				RGBval = transform_pixel(img[i][j]);
				out[i][j] = RGBval;
			}
		}
		return out;
	}
}
