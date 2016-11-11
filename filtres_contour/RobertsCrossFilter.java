package filtres_contour;

import java.awt.*;

class RobertsCrossFilter extends Filtre {
	public int[][] process(int [][] img, int w, int h) {
		int y,x, R;
		int out[][] = new int[w][h];
		int[][] pixelMatrix=new int[2][2];
		for(int i=0;i<w-1;i++){
			for(int j=0;j<h-1;j++){
				pixelMatrix[0][1]=new Color(img[i][j+1]).getRed();
				pixelMatrix[1][0]=new Color(img[i+1][j]).getRed();
				
				pixelMatrix[0][0]=new Color(img[i][j]).getRed();
				pixelMatrix[1][1]=new Color(img[i+1][j+1]).getRed();

				x = (pixelMatrix[0][1])+
					(pixelMatrix[1][0]*-1);
				y = (pixelMatrix[0][0]*1)+
					(pixelMatrix[1][1]*-1);
					
				R = (int) Math.sqrt(Math.pow(y,2)+Math.pow(x,2));
				
				out[i][j] = (R<<16 | R<<8 | R);
			}
		}
		return out;
	}

}
