package filtres_contour;

class FiltreSobel extends FiltreContour {
	private static int noyau_x[][] = { {-1,0,1}, {-2,0,2}, {-1,0,1} };
	private static int noyau_y[][] = { {1,2,1}, {0,0,0}, {-1,-2,-1} };
	
	public FiltreSobel() {
		super(noyau_x,noyau_y);
	}

}
