package filtres_contour;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

class ImageOriginale {
   private BufferedImage image;
   private BufferedImage imageModifiee;
   private String filename;
   private int width;
   private int height;
   //private int[] rgb;
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
			
			int [] rgb = image.getRGB(0, 0, width, height, null, 0, width);
			imageModifiee = new BufferedImage(width,height,image.getType()); 
			imageModifiee.setRGB(0, 0, width, height, rgb, 0, width);
		} catch (Exception e) {
		   e.printStackTrace();
		}
   }
   
   public int getHeight() {return height;}
   public int getWidth() {return width;}
   //public int[] getRGB() {return rgb;}
   public int[][] getPixels() {return pixels;}
   
   public void applyFilter(Filtre f) {
	   int i, j;
	   int[][] output = f.process(pixels,width,height);
		for (i=0; i<width; i++) {
			for (j=0; j<height; j++) {
				imageModifiee.setRGB(i,j,output[i][j]); 
			}
		} 
		//rgb = image.getRGB(0, 0, width, height, null, 0, width);
   }
   
   public void applyTreshold() {
	   int i, j;
	   int[][] output = Vectorisation.treshold(pixels,width,height);
		for (i=0; i<width; i++) {
			for (j=0; j<height; j++) {
				imageModifiee.setRGB(i,j,output[i][j]); 
			}
		} 
		//rgb = image.getRGB(0, 0, width, height, null, 0, width);
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
			ImageIO.write(imageModifiee, extension,new File(filename));
		} catch (IOException io) {
			System.out.println("Cannot save to file.");
		}
   }
   
    public void save(String newname) {
		String extension = getFileExtension(newname);
		try {
			File outputfile = new File(newname);
			ImageIO.write(imageModifiee, extension,outputfile);
		} catch (IOException io) {
			System.out.println("Cannot save to file.");
		}
   }
    
    public byte[] getByteArray() {
    	byte[] imageInByte = null;
    	String extension = getFileExtension(filename);
    	// convert BufferedImage to byte array
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	try {
    		ImageIO.write(imageModifiee, extension, baos);
    		baos.flush();
    		imageInByte = baos.toByteArray();
    		baos.close();
    	} catch (IOException e) {}
    	return imageInByte;
    }
   
}
