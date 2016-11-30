package filtres_contour;

import java.awt.Color;

public abstract class FiltreParRegions extends Filtre {
	
	abstract int calculer_pixel(Color pixelMatrix[][]);
	
	protected static Color[][] couleurs_voisins(int[][] img, int i, int j) {
		Color[][] couleurs=new Color[3][3];
		couleurs[0][0]=new Color(img[i-1][j-1]);
		couleurs[0][1]=new Color(img[i][j-1]);
		couleurs[0][2]=new Color(img[i+1][j-1]);
		couleurs[1][0]=new Color(img[i-1][j]);
		couleurs[1][1]=new Color(img[i][j]);
		couleurs[1][2]=new Color(img[i+1][j]);
		couleurs[2][0]=new Color(img[i-1][j+1]);
		couleurs[2][1]=new Color(img[i][j+1]);
		couleurs[2][2]=new Color(img[i+1][j+1]);
		return couleurs;
	}
	
	protected static int[][] valeurs_rouges_voisins(Color[][] couleurs) {
		int[][] valeurs_rouges = new int[3][3] ;
		for (int k=0; k<3; k++)
			for (int l=0; l<3; l++) 
				valeurs_rouges[k][l] = couleurs[k][l].getRed();
		return valeurs_rouges;
	}
	
	public int[][] process(int [][] img, int w, int h) {
		int RGBval;
		int out[][] = new int[w][h];
		Color[][] pixelMatrix;
		for(int i=1;i<w-1;i++) {
			for(int j=1;j<h-1;j++) {
				pixelMatrix = couleurs_voisins(img,i,j);
				
				RGBval = calculer_pixel(pixelMatrix);
				out[i][j] = RGBval;
			}
		}
		/* rendre les bords non traités noirs */
		for(int i=0;i<w;i++) {
			out[i][0] = Color.BLACK.getRGB();
			out[i][h-1] = Color.BLACK.getRGB();
		}
		for(int j=0;j<h;j++) {
			out[0][j] = Color.BLACK.getRGB();
			out[w-1][j] = Color.BLACK.getRGB();
		}
		return out;
	}
	
}
