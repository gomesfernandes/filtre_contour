package filtres_contour;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
 
public class View extends JFrame{
 
	private static final long serialVersionUID = 1L;
	private JButton bouton = new JButton("Sobel");
	private JButton bouton2 = new JButton("Robert's Cross");
	private JButton bouton3 = new JButton("Prewitt");
	private JButton boutonFermer = new JButton("Fermer");
	private JPanel container = new JPanel();
	private JLabel label = new JLabel();
    //private Image image ;
  
	ImageOriginale i = new ImageOriginale("Valve_original.png");
	SobelFilter f = new SobelFilter();
	RobertsCrossFilter r = new RobertsCrossFilter();
	PrewittFilter p = new PrewittFilter();

  //ImageIcon image = new ImageIcon();
  
  public View() throws IOException{
	  
    this.setTitle("Projet POO2");
    this.setSize(500, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
 
    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
   
    bouton.addActionListener(new BoutonListener());
    bouton2.addActionListener(new Bouton2Listener());
    bouton3.addActionListener(new Bouton3Listener());
    boutonFermer.addActionListener(new BoutonFermerListener());
        
    JPanel south = new JPanel();
    south.add(bouton);
    south.add(bouton2);
    south.add(bouton3);
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
    label.setIcon(new ImageIcon("Valve_original.png"));
    container.add(label, BorderLayout.CENTER);
    this.setContentPane(container);
    this.setVisible(true);
  }
       
  
  class BoutonListener implements ActionListener{
    public void actionPerformed(ActionEvent arg0) {
		i.applyFilter(f);
		i.save("test_sobel.PNG");
    	label.setIcon(new ImageIcon("test_sobel.PNG"));
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
  }  
  
  class BoutonFermerListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
    	System.exit(0);
    }
  }  
  
}
