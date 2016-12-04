package filtres_contour;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

class ImageOriginale {
   private BufferedImage image;
   private BufferedImage imageModifiee;
   private String nomfichier;
   private int largeur;
   private int hauteur;
   private int[][] pixels;
   
   public ImageOriginale(String nom) {
	   nomfichier = nom;
		try {
			image = ImageIO.read(new File(nomfichier));
			largeur = image.getWidth();
			hauteur = image.getHeight();
			
			pixels = new int[largeur][hauteur];
			int i,j;
			for (i=0; i<largeur; i++) {
				for (j=0; j<hauteur; j++) {
					pixels[i][j] = image.getRGB(i,j);
				}
			} 
			
			int [] rgb = image.getRGB(0, 0, largeur, hauteur, null, 0, largeur);
			imageModifiee = new BufferedImage(largeur,hauteur,image.getType()); 
			imageModifiee.setRGB(0, 0, largeur, hauteur, rgb, 0, largeur);
		} catch (Exception e) {
		   e.printStackTrace();
		}
   }
   
   public int getHeight() {return hauteur;}
   public int getWidth() {return largeur;}
   public String getFilename() {return nomfichier;}
   public int[][] getPixels() {return pixels;}
   
   public byte[] getByteArray() {
		byte[] imageInByte = null;
		String extension = getFileExtension(nomfichier);
		// convert BufferedImage to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(imageModifiee, extension, baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			System.err.println("Echec de conversion en bytes");
		}
		return imageInByte;
   }
   
   public void applyFilter(Filtre f) {
	   int i, j;
	   int[][] output;
	   
	   NuancesGris ng = new NuancesGris();
	   output = ng.process(pixels,largeur,hauteur);
	   
	   // reduire bruit ou pas ? 
	   FiltreMedian noise_reduction = new FiltreMedian();
	   output = noise_reduction.process(output,largeur,hauteur);
	  
	   output = f.process(output,largeur,hauteur);
		for (i=0; i<largeur; i++) {
			for (j=0; j<hauteur; j++) {
				imageModifiee.setRGB(i,j,output[i][j]); 
			}
		} 
   }
   
   public void vectorisation() {
	   int i, j;
	   int [][] output = new int[largeur][hauteur];
	   for (i=0; i<largeur; i++) {
			for (j=0; j<hauteur; j++) {
				output[i][j] = imageModifiee.getRGB(i,j);
			}
		} 
	   
	   Vectorisation v = new Vectorisation(output,largeur,hauteur);
	   output = v.vectorsToRGB();
		for (i=0; i<largeur; i++) {
			for (j=0; j<hauteur; j++) {
				imageModifiee.setRGB(i,j,output[i][j]); 
			}
		} 
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
	   String extension = getFileExtension(nomfichier);
		try {
			ImageIO.write(imageModifiee, extension,new File(nomfichier));
		} catch (IOException io) {
			System.err.println("Echec de sauvegarde.");
		}
   }
   
    public void save(String newname) {
		String extension = getFileExtension(newname);
		if (extension == "") extension = "jpg";
		try {
			File outputfile = new File(newname);
			ImageIO.write(imageModifiee, extension,outputfile);
		} catch (IOException io) {
			System.err.println("Echec de sauvegarde.");
		}
   }
    
}
