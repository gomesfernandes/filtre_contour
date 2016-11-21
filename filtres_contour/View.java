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
	private JButton bouton = new JButton("Sobel");
	private JButton bouton2 = new JButton("Robert's Cross");
	private JButton bouton3 = new JButton("Prewitt");
	private JButton bouton4 = new JButton("NB");
	private JButton boutonFermer = new JButton("Fermer");
	private JPanel container = new JPanel();
	private JLabel label = new JLabel();
    //private Image image ;
  
	ImageOriginale i = new ImageOriginale("images/Valve.png");
	SobelFilter f = new SobelFilter();
	RobertsCrossFilter r = new RobertsCrossFilter();
	PrewittFilter p = new PrewittFilter();

  //ImageIcon image = new ImageIcon();
  
  public View() throws IOException{
	  
	  
	  	ImageOriginale line = new ImageOriginale("images/fuck.png");
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
	  
    this.setTitle("Projet POO2");
    this.setSize(600, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
 
    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
   
    orig.addActionListener(new OrigListener());
    bouton.addActionListener(new BoutonListener());
    bouton2.addActionListener(new Bouton2Listener());
    bouton3.addActionListener(new Bouton3Listener());
    bouton4.addActionListener(new Bouton4Listener());
    boutonFermer.addActionListener(new BoutonFermerListener());
        
    JPanel south = new JPanel();
    south.add(orig);
    south.add(bouton);
    south.add(bouton2);
    south.add(bouton3);
    south.add(bouton4);
    south.add(boutonFermer);
    container.add(south, BorderLayout.SOUTH);

    /*label = new JLabel()
    {
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            image.paintIcon(this, g, image.getIconWidth(), image.getIconHeight());
        }
    };*/
    
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setIcon(new ImageIcon("images/Valve.png"));
    container.add(label, BorderLayout.CENTER);
    this.setContentPane(container);
    this.setVisible(true);
  }
    
  class OrigListener implements ActionListener{
    public void actionPerformed(ActionEvent arg0) {
    	label.setIcon(new ImageIcon("images/Valve.png"));
    }
  }
  
  class BoutonListener implements ActionListener{
    public void actionPerformed(ActionEvent arg0) {
		i.applyFilter(f);
		//i.save("images/test_sobel.PNG");
    	//label.setIcon(new ImageIcon("images/test_sobel.PNG"));
		label.setIcon(new ImageIcon(i.getByteArray()));
    }
  }
      
 
  class Bouton2Listener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
    	i.applyFilter(r);
		//i.save("images/test_sobel.PNG");
    	//label.setIcon(new ImageIcon("images/test_sobel.PNG"));
		label.setIcon(new ImageIcon(i.getByteArray()));
    }
  }     
  
  class Bouton3Listener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
    	i.applyFilter(p);
		//i.save("images/test_sobel.PNG");
		//label.setIcon(new ImageIcon("images/test_sobel.PNG"));
		label.setIcon(new ImageIcon(i.getByteArray()));
    }
  }  
  
  class Bouton4Listener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		i.applyTreshold();
		//i.save("images/test_sobel.PNG");
		//label.setIcon(new ImageIcon("images/test_sobel.PNG"));
		label.setIcon(new ImageIcon(i.getByteArray()));
	}
  }  
  
  class BoutonFermerListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
    	System.exit(0);
    }
  }  
  
}
