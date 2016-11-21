package filtres_contour;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

class ImageOriginale {
   private BufferedImage image;
   private String filename;
   private int width;
   private int height;
   private int[] rgb;
   private int[][] pixels;
   
   public ImageOriginale(String nom) {
		filename = nom;
		try {
			image = ImageIO.read(new File(filename));
			width = image.getWidth();
			height = image.getHeight();
			
			pixels = new int[width][height];
			int i,j;
			for (i=0; i<width; i++) {
				for (j=0; j<height; j++) {
					pixels[i][j] = image.getRGB(i,j);
				}
			} 
			
			rgb = image.getRGB(0, 0, width, height, null, 0, width);
			image.setRGB(0, 0, width, height, rgb, 0, width);
		} catch (Exception e) {
		   e.printStackTrace();
		}
   }
   
   public int getHeight() {return height;}
   public int getWidth() {return width;}
   
   public void applyFilter(Filtre f) {
	   int i, j;
		pixels = f.process(pixels,width,height);
		for (i=0; i<width; i++) {
			for (j=0; j<height; j++) {
				image.setRGB(i,j,pixels[i][j]); 
			}
		} 
		rgb = image.getRGB(0, 0, width, height, null, 0, width);
   }
   
   private static String getFileExtension(String filename) {
		String extension = "";
		int i = filename.lastIndexOf('.');
		if (i > 0) {
			extension = filename.substring(i+1).toLowerCase();
		}
		return extension;
   }
   
   public void save() {
	   String extension = getFileExtension(filename);
		try {
			ImageIO.write(image, extension,new File(filename));
		} catch (IOException io) {
			System.out.println("Cannot save to file.");
		}
   }
   
    public void save(String newname) {
		String extension = getFileExtension(newname);
		try {
			File outputfile = new File(newname);
			ImageIO.write(image, extension,outputfile);
		} catch (IOException io) {
			System.out.println("Cannot save to file.");
		}
   }
   
}
