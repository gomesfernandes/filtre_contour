package filtres_contour;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
 

public class View extends JFrame{
 
	private static final long serialVersionUID = 1L;

	private JButton orig = new JButton("Originale");
	private JButton bouton = new JButton("Filtre de Sobel");
	private JButton bouton2 = new JButton("Filtre de roberts");
	private JButton bouton3 = new JButton("Ouvrir...");
	private JButton bouton4 = new JButton("Filtre de Prewitt");
	private JButton boutonFermer = new JButton("Fermer");
	private JPanel container = new JPanel();
	private JLabel label = new JLabel();
	JFileChooser filechooser= new JFileChooser(System.getProperty("user.dir"));

	ImageOriginale i ;

	SobelFilter f = new SobelFilter();
	RobertsCrossFilter r = new RobertsCrossFilter();
	PrewittFilter p = new PrewittFilter();

  
  public View() throws IOException{
	  
	  
	  	//PARTIE VECTORISATION
	   // J'AI PAS ENCORE UN MEILLEUR ENDROIT POUR METTRE ÇA :( 
	  	ImageOriginale line = new ImageOriginale("images/stuff.png");
	  	int h = line.getHeight();
		int w = line.getWidth();
		int[][] img = line.getPixels();
		Vectorisation v = new Vectorisation(img,w,h);
		int[][]pix = v.vectorsToRGB();
		BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < w; x++) {
		    for (int y = 0; y < h; y++) {
		        bufferedImage.setRGB(x, y, pix[x][y]);
		    }
		}
		File outputfile = new File("images/linetest2.png");
		ImageIO.write(bufferedImage, "png", outputfile);
		//FIN PARTIE VECTORISATION
	  
    this.setTitle("Projet POO2");
    this.setSize(600, 700);
    this.setMinimumSize(new Dimension(450, 300));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
 
    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
    
    orig.setEnabled(false);
    bouton.setEnabled(false);
    bouton2.setEnabled(false);
    bouton4.setEnabled(false);
   
    orig.addActionListener(new OrigListener());
    bouton.addActionListener(new BoutonListener());
    bouton2.addActionListener(new Bouton2Listener());
    bouton4.addActionListener(new Bouton4Listener());
    bouton3.addActionListener(new Bouton3Listener());
    boutonFermer.addActionListener(new BoutonFermerListener());
    
    /*
    JPanel south = new JPanel();
    south.add(orig);
    south.add(bouton);
    south.add(bouton2);
    south.add(bouton3);
    south.add(bouton4);
    south.add(boutonFermer);
    container.add(south, BorderLayout.SOUTH);
    */
    
    //creation d'un panel contenant 2 sous-panels pour qu'on puisse avoir 2 rangs de boutons
    
    JPanel all_buttons = new JPanel();  
    all_buttons.setLayout(new BorderLayout());
    	// d'abord boutons principaux : save, get file,...
	    JPanel boutons_principaux = new JPanel();
	    boutons_principaux.add(orig);
	    boutons_principaux.add(bouton3);
	    boutons_principaux.add(boutonFermer);
	    all_buttons.add(boutons_principaux, BorderLayout.NORTH);
	    // en-dessous les filtres : sobel, roberts,... 
	    JPanel boutons_filtres = new JPanel();
	    boutons_filtres.add(bouton);
	    boutons_filtres.add(bouton2);
	    boutons_filtres.add(bouton4);
	    all_buttons.add(boutons_filtres, BorderLayout.SOUTH);
    container.add(all_buttons, BorderLayout.SOUTH);
    
    label.setHorizontalAlignment(JLabel.CENTER);
    container.add(label, BorderLayout.CENTER);
    this.setContentPane(container);
    this.setVisible(true);
  }
    
  class OrigListener implements ActionListener{
    public void actionPerformed(ActionEvent arg0) {
    	label.setIcon(new ImageIcon(i.getFilename()));
    }
  }
  
  class BoutonListener implements ActionListener{
    public void actionPerformed(ActionEvent arg0) {
		i.applyFilter(f);
		label.setIcon(new ImageIcon(i.getByteArray()));
    }
  }
      
 
  class Bouton2Listener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
    	i.applyFilter(r);
		label.setIcon(new ImageIcon(i.getByteArray()));
    }
  }     
  
  class Bouton4Listener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
    	i.applyFilter(p);
		label.setIcon(new ImageIcon(i.getByteArray()));
    }
  }
  
  class Bouton3Listener implements ActionListener{
	    public void actionPerformed(ActionEvent ev) { 	
	    	
	    	filechooser.setDialogTitle("Choisissez un fichier..");
	        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        int returnval=filechooser.showOpenDialog(null);
	        if(returnval==JFileChooser.APPROVE_OPTION)
	        {
	            File file = filechooser.getSelectedFile();
	            BufferedImage bi;
	            try
	            {   
	                bi=ImageIO.read(file);
	                label.setIcon(new ImageIcon(bi));
	            }
	            catch(IOException e)
	            {

	            }
	            pack();
	        }
	        if (filechooser.getSelectedFile() != null) {
		        i = new ImageOriginale(filechooser.getSelectedFile().getAbsolutePath());
		        System.out.println(filechooser.getSelectedFile().getAbsolutePath());
		        orig.setEnabled(true);
		        bouton.setEnabled(true);
		        bouton2.setEnabled(true);
		        bouton4.setEnabled(true);
	        }
	    }
  }  
  
  
  class BoutonFermerListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
    	System.exit(0);
    }
  }  
  
}
