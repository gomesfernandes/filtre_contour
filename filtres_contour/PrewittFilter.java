package filtres_contour;

import java.awt.*;

class PrewittFilter extends Filtre {
	public int[][] process(int [][] img, int w, int h) {
		int y,x, R;
		int out[][] = new int[w][h];
		int[][] pixelMatrix=new int[3][3];
		for(int i=1;i<w-1;i++){
			for(int j=1;j<h-1;j++){
				pixelMatrix[0][0]=new Color(img[i-1][j-1]).getRed();
				pixelMatrix[0][1]=new Color(img[i-1][j]).getRed();
				pixelMatrix[0][2]=new Color(img[i-1][j+1]).getRed();
				pixelMatrix[1][0]=new Color(img[i][j-1]).getRed();
				
				pixelMatrix[1][2]=new Color(img[i][j+1]).getRed();
				pixelMatrix[2][0]=new Color(img[i+1][j-1]).getRed();
				pixelMatrix[2][1]=new Color(img[i+1][j]).getRed();
				pixelMatrix[2][2]=new Color(img[i+1][j+1]).getRed();

				y = (pixelMatrix[0][0]*-1)+
					(pixelMatrix[0][1]*-1)+
					(pixelMatrix[0][2]*-1)+
					(pixelMatrix[2][0])+
					(pixelMatrix[2][1])+
					(pixelMatrix[2][2]);
				x = (pixelMatrix[0][0]*-1)+
					(pixelMatrix[0][2])+
					(pixelMatrix[1][0]*-1)+
					(pixelMatrix[1][2])+
					(pixelMatrix[2][0]*-1)+
					(pixelMatrix[2][2]);
					
				R = (int) Math.sqrt(Math.pow(y,2)+Math.pow(x,2));
				
				out[i][j] = (R<<16 | R<<8 | R);
			}
		}
		return out;
	}

}
