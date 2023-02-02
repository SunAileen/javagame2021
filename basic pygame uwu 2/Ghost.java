//2021-04-06
//By: Aileen Sun
//Ms Strelkovska
//Ghost monster properties

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Ghost extends Monster {

    //Constructor
    public Ghost(int x, int y){
        super(x, y);   //calls on Monster's constructor
        hp = 30;
        width = 200;
        height = 200;

        //Initialize images, including attack animation
        BufferedImage image = readImage.setImage("ghost.png");
        for(int i = 0; i < 3; i++){
            imageArray[i] = image.getSubimage(i*500, 0, 500, 500);
        }
        imageArray[3] = image.getSubimage(500, 0, 500, 500);   //gets middle image so the animation loops well
    }

    //Deals 10 damage to the player
    public void attack(Player p){
        p.setHp(-10);
    }

    public void draw(Graphics g){
        //Draw enemy and enemy name
        g.drawImage(imageArray[imgCount], x, y, width, height, null);
        g.setColor(Color.white);
        g.drawString("Ghost", x, y + height);

        //Draws enemy intent
        g.drawImage(intent, x + 10, y - 80, 50, 50, null);
        g.setColor(Color.red);
        g.drawString("10", x + 50, y - 70);

        //Draws hp bar
        g.setColor(Color.black);
        g.drawRect(x, y, width, 5);
        g.setColor(Color.red);
        g.fillRect(x, y, width, 5);
        g.setColor(Color.green);
        g.fillRect(x, y, (int) (width*(hp/30.0)), 5);
        g.drawString(hp + "", x + 20, y - 10);
    }
}
