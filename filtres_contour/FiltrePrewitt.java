package filtres_contour;

class FiltrePrewitt extends FiltreContour {
	private static int noyau_x[][] = { {-1,0,+1}, {-1,0,+1}, {-1,0,+1} };
	private static int noyau_y[][] = { {1,1,1}, {0,0,0}, {-1,-1,-1} };
	
	public FiltrePrewitt() {
		super(noyau_x,noyau_y);
	}
}
