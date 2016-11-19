package filtres_contour;

import java.awt.*;

class SobelFilter extends Filtre {
	public int[][] process(int [][] img, int w, int h) {
		int y,x, R;
		int out[][] = new int[w][h];
		int[][] pixelMatrix=new int[3][3];
		for(int i=1;i<w-1;i++){
			for(int j=1;j<h-1;j++){
				pixelMatrix[0][0]=new Color(img[i-1][j-1]).getRed();
				pixelMatrix[0][1]=new Color(img[i][j-1]).getRed();
				pixelMatrix[0][2]=new Color(img[i+1][j-1]).getRed();
				pixelMatrix[1][0]=new Color(img[i-1][j]).getRed();
				//pixel at position i,j not needed since always * 0
				pixelMatrix[1][2]=new Color(img[i+1][j]).getRed();
				pixelMatrix[2][0]=new Color(img[i-1][j+1]).getRed();
				pixelMatrix[2][1]=new Color(img[i][j+1]).getRed();
				pixelMatrix[2][2]=new Color(img[i+1][j+1]).getRed();

				y = (pixelMatrix[0][0]*-1)+
					(pixelMatrix[0][1]*-2)+
					(pixelMatrix[0][2]*-1)+
					(pixelMatrix[2][0])+
					(pixelMatrix[2][1]*2)+
					(pixelMatrix[2][2]);
				x = (pixelMatrix[0][0]*-1)+
					(pixelMatrix[0][2])+
					(pixelMatrix[1][0]*-2)+
					(pixelMatrix[1][2]*2)+
					(pixelMatrix[2][0]*-1)+
					(pixelMatrix[2][2]);
					
				R = (int) Math.sqrt(x*x+y*y);
				
				if (R< 0)
					R=0;
				else if (R>255)
					R=255;
				
				out[i][j] = (R<<16 | R<<8 | R);
			}
		}
		return out;
	}

}
