//2021-04-06
//By: Aileen Sun
//Ms Strelkovska
//Used to change desktop images to BufferedImages in other classes

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ReadImage{
    private static BufferedImage image;

    public ReadImage(){
    }

    //Takes image name and returns BufferedImage
    public BufferedImage setImage(String name){
            try {
                image = ImageIO.read(new File(name));
            } catch (IOException e) {
                System.out.println("Something is wrong with file reading " + e);
            } catch (Exception e2) {
                System.out.println(e2);
            }
            return image;
    }
}
