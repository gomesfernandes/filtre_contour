import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class test_sobel_3 {
	public static void main(String args[]) {
		ImageOriginale i = new ImageOriginale("sobel2.jpg");
		sobelFilter f = new sobelFilter();
		
		i.applyFilter(f);
		
		i.save("test_sobel.jpg");
	}
}
