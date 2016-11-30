package filtres_contour;

class FiltreRoberts extends FiltreContour {
	private static int noyau_x[][] = { {0,0,0}, {0,1,0}, {0,0,-1} };
	private static int noyau_y[][] = { {0,0,0}, {0,0,1}, {0,-1,0} };
	
	public FiltreRoberts() {
		super(noyau_x,noyau_y);
	}
}
