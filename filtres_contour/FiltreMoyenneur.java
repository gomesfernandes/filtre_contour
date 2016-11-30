package filtres_contour;

import java.awt.Color;

public class FiltreMoyenneur extends FiltreParRegions {
	
	int calculer_pixel(Color pixelMatrix[][]) {
		int sumR,sumG,sumB;
		sumR=0; sumB=0; sumG=0;
		for (int k=0;k<3;k++)
			for (int l=0; l<3; l++) {
				sumR += pixelMatrix[k][l].getRed();
				sumB += pixelMatrix[k][l].getBlue();
				sumG += pixelMatrix[k][l].getGreen();
			}
		sumR = sumR/9;
		sumB = sumB/9;
		sumG = sumG/9;
		if (sumR> 255) sumR=255;
		if (sumG> 255) sumG=255;
		if (sumB> 255) sumB=255;
		return new Color(sumR,sumG,sumB).getRGB();
	}
	
	/*
	public int[][] process(int [][] img, int w, int h) {
		int sumR,sumG,sumB;
		int out[][] = new int[w][h];
		Color[][] pixelMatrix=new Color[3][3];
		for(int i=1;i<w-1;i++){
			for(int j=1;j<h-1;j++){
				pixelMatrix = couleurs_voisins(img,i,j);
				
				sumR=0; sumB=0; sumG=0;
				for (int k=0;k<3;k++)
					for (int l=0; l<3; l++) {
						sumR += pixelMatrix[k][l].getRed();
						sumB += pixelMatrix[k][l].getBlue();
						sumG += pixelMatrix[k][l].getGreen();
					}
				
				sumR = sumR/9;
				sumB = sumB/9;
				sumG = sumG/9;
				if (sumR> 255) sumR=255;
				if (sumG> 255) sumG=255;
				if (sumB> 255) sumB=255;
				
				out[i][j] = new Color(sumR,sumG,sumB).getRGB();
			}
		}
		return out;
	}
	*/
}
