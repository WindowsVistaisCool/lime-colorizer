import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
	public static void main(String args[])
		throws IOException
	{
		int width = 820;
		int height = 569;

		BufferedImage image = null;

		try {
			File input_file = new File("lime.png");

			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			// Reading input file
			image = ImageIO.read(input_file);

			System.out.println("Reading complete.");
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                int a = (pixel>>24)&0xff;
                int r = (pixel>>16)&0xff;
                int g = (pixel>>8)&0xff;
                int b = pixel&0xff;
                
                if (r >= 245 && g >= 245 && b >= 245) {
                    // a = 0;
                    r = 0;
                    g = 0;
                    b = 0;
                    pixel = (a<<24) | (r<<16) | (g<<8) | b;
                    image.setRGB(x, y, pixel);
                    continue;
                }

                r = b;
                g = g;
                b = r;
                pixel = (a<<24) | (r<<16) | (g<<8) | b;
                image.setRGB(x, y, pixel); 
            }
        }
        
		try {
			File output_file = new File("p.png");

			ImageIO.write(image, "png", output_file);

			System.out.println("Writing complete.");
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}
	} // main() ends here
} // class ends here
