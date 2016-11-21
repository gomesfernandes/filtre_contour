package filtres_contour;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.*;
 
public class View extends JFrame{
 
	private static final long serialVersionUID = 1L;
<<<<<<< HEAD
	private JButton bouton = new JButton("Filtre de Sobel");
	private JButton bouton2 = new JButton("Second Filtre");
	private JButton bouton3 = new JButton("Ouvrir...");
	private JPanel container = new JPanel();
	private JLabel label = new JLabel();
	JFileChooser filechooser= new JFileChooser();
=======
	private JButton bouton = new JButton("Sobel");
	private JButton bouton2 = new JButton("Robert's Cross");
	private JButton bouton3 = new JButton("Prewitt");
	private JButton boutonFermer = new JButton("Fermer");
	private JPanel container = new JPanel();
	private JLabel label = new JLabel();
    //private Image image ;
>>>>>>> b7267ad21759843ca75ba9e86c6bc7132802c4d8
  
	ImageOriginale i ;//= new ImageOriginale("test");
	SobelFilter f = new SobelFilter();
<<<<<<< HEAD
=======
	RobertsCrossFilter r = new RobertsCrossFilter();
	PrewittFilter p = new PrewittFilter();

  //ImageIcon image = new ImageIcon();
>>>>>>> b7267ad21759843ca75ba9e86c6bc7132802c4d8
  
  public View() throws IOException{
	  
    this.setTitle("Projet POO2");
    this.setSize(500, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
 
    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
    
    bouton.setEnabled(false);
    bouton2.setEnabled(false);
   
    bouton.addActionListener(new BoutonListener());
    bouton2.addActionListener(new Bouton2Listener());
    bouton3.addActionListener(new Bouton3Listener());
<<<<<<< HEAD
=======
    boutonFermer.addActionListener(new BoutonFermerListener());
>>>>>>> b7267ad21759843ca75ba9e86c6bc7132802c4d8
        
    JPanel south = new JPanel();
    south.add(bouton);
    south.add(bouton2);
    south.add(bouton3);
<<<<<<< HEAD
=======
    south.add(boutonFermer);
>>>>>>> b7267ad21759843ca75ba9e86c6bc7132802c4d8
    container.add(south, BorderLayout.SOUTH);

    
    label.setHorizontalAlignment(JLabel.CENTER);
    
    container.add(label, BorderLayout.CENTER);
    this.setContentPane(container);
    this.setVisible(true);
  }
       
  
  class BoutonListener implements ActionListener{
    public void actionPerformed(ActionEvent arg0) {
<<<<<<< HEAD
    	String s = "test_sobel_teeeeest.PNG";

		i.applyFilter(f);
		
		i.save(s);
		//System.out.println(filechooser.getSelectedFile().getAbsolutePath());
    	label.setIcon(new ImageIcon(s));

=======
		i.applyFilter(f);
		i.save("test_sobel.PNG");
    	label.setIcon(new ImageIcon("test_sobel.PNG"));
>>>>>>> b7267ad21759843ca75ba9e86c6bc7132802c4d8
    }
  }
      
 
  class Bouton2Listener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
    	i.applyFilter(r);
		i.save("test_sobel.PNG");
    	label.setIcon(new ImageIcon("test_sobel.PNG"));
    }
  }     
  
  class Bouton3Listener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
    	i.applyFilter(p);
		i.save("test_sobel.PNG");
    	label.setIcon(new ImageIcon("test_sobel.PNG"));
    }
<<<<<<< HEAD
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
	        
	        i = new ImageOriginale(filechooser.getSelectedFile().getAbsolutePath());
	        System.out.println(filechooser.getSelectedFile().getAbsolutePath());
	        bouton.setEnabled(true);
	        bouton2.setEnabled(true);
	    }
  }
=======
  }  
  
  class BoutonFermerListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
    	System.exit(0);
    }
  }  
  
>>>>>>> b7267ad21759843ca75ba9e86c6bc7132802c4d8
}
