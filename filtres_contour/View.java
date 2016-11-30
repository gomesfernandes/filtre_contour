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
	private JButton btnorig = new JButton("Originale");
	private JButton btnsobel = new JButton("Sobel");
	private JButton btnroberts = new JButton("Roberts");
	private JButton btnouvrir = new JButton("Ouvrir...");
	private JButton btnprewitt = new JButton("Prewitt");
	private JButton btnlaplace = new JButton("Laplace");
	private JButton btnvect = new JButton("Vectoriser");
	private JButton btnfermer = new JButton("Fermer");
	private JPanel container = new JPanel();
	private JLabel label = new JLabel();
	JFileChooser filechooser= new JFileChooser(System.getProperty("user.dir"));
	
	ImageOriginale i;
	FiltreSobel sobel = new FiltreSobel();
	FiltreRoberts roberts = new FiltreRoberts();
	FiltrePrewitt prewitt = new FiltrePrewitt();
	FiltreLaplace laplace = new FiltreLaplace();
	//NuancesGris l = new NuancesGris();
  
	public View() throws IOException{ 
	    this.setTitle("Projet POO2: Filtres de contour");
	    this.setSize(600, 700);
	    this.setMinimumSize(new Dimension(450, 300));
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	 
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    
	    btnorig.setEnabled(false);
	    btnsobel.setEnabled(false);
	    btnroberts.setEnabled(false);
	    btnlaplace.setEnabled(false);
	    btnprewitt.setEnabled(false);
	    btnvect.setEnabled(false);
	   
	    btnorig.addActionListener(new OrigListener());
	    btnsobel.addActionListener(new SobelListener());
	    btnroberts.addActionListener(new RobertsListener());
	    btnlaplace.addActionListener(new LaplaceListener());
	    btnprewitt.addActionListener(new PrewittListener());
	    btnvect.addActionListener(new VectListener());
	    btnfermer.addActionListener(new FermerListener());
	    btnouvrir.addActionListener(new OuvrirListener());
	    
	    //creation d'un panel contenant 2 sous-panels pour qu'on puisse avoir 2 rangs de boutons
	    
	    JPanel all_buttons = new JPanel();  
	    all_buttons.setLayout(new BorderLayout());
	    	// d'abord boutons principaux : save, get file,...
		    JPanel boutons_principaux = new JPanel();
		    boutons_principaux.add(btnorig);
		    boutons_principaux.add(btnouvrir);
		    boutons_principaux.add(btnfermer);
		    all_buttons.add(boutons_principaux, BorderLayout.NORTH);
		    // en-dessous les filtres : sobel, roberts,... 
		    JPanel boutons_filtres = new JPanel();
		    boutons_filtres.add(btnsobel);
		    boutons_filtres.add(btnroberts);
		    boutons_filtres.add(btnprewitt);
		    boutons_filtres.add(btnlaplace);
		    boutons_filtres.add(btnvect);
		    all_buttons.add(boutons_filtres, BorderLayout.SOUTH);
	    container.add(all_buttons, BorderLayout.SOUTH);
	    
	    label.setHorizontalAlignment(JLabel.CENTER);
	    container.add(label, BorderLayout.CENTER);
	    this.setContentPane(container);
	    this.setVisible(true);
  	}
	
	class OrigListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0) {
	    	label.setIcon(new ImageIcon(i.getFilename()));
	    }
	  }
	
	class SobelListener implements ActionListener{
	    public void actionPerformed(ActionEvent arg0) {
			i.applyFilter(sobel);
			label.setIcon(new ImageIcon(i.getByteArray()));
	    }
	  }
	class RobertsListener implements ActionListener{
	    public void actionPerformed(ActionEvent arg0) {
			i.applyFilter(roberts);
			label.setIcon(new ImageIcon(i.getByteArray()));
	    }
	  }
	class PrewittListener implements ActionListener{
	    public void actionPerformed(ActionEvent arg0) {
			i.applyFilter(prewitt);
			label.setIcon(new ImageIcon(i.getByteArray()));
	    }
	  }
	class LaplaceListener implements ActionListener{
	    public void actionPerformed(ActionEvent arg0) {
			i.applyFilter(laplace);
			label.setIcon(new ImageIcon(i.getByteArray()));
	    }
	  }
	
	  class VectListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	i.vectorisation();
			label.setIcon(new ImageIcon(i.getByteArray()));
	    }
	  }
	  
	  class OuvrirListener implements ActionListener{
	    public void actionPerformed(ActionEvent ev) { 	
	    	
	    	filechooser.setDialogTitle("Choisissez un fichier..");
	        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        int returnval=filechooser.showOpenDialog(null);
	        if(returnval==JFileChooser.APPROVE_OPTION)
	        {
	            File file = filechooser.getSelectedFile();
	            BufferedImage bi;
	            try  {   
	                bi=ImageIO.read(file);
	                label.setIcon(new ImageIcon(bi));
	            }
	            catch(IOException e) {
	            	System.err.println("Echec choix d'image.");
	            }
	            pack();
	        }
	        if (filechooser.getSelectedFile() != null) {
		        i = new ImageOriginale(filechooser.getSelectedFile().getAbsolutePath());
		        System.out.println(filechooser.getSelectedFile().getAbsolutePath());
		        btnorig.setEnabled(true);
		        btnsobel.setEnabled(true);
		        btnroberts.setEnabled(true);
		        btnlaplace.setEnabled(true);
		        btnprewitt.setEnabled(true);
		        btnvect.setEnabled(true);
	        }
	    }
	  }  
	  
	  
	  class FermerListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	System.exit(0);
	    }
	  }  
}
