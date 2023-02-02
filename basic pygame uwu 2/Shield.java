//2021-04-06
//By: Aileen Sun
//Ms Strelkovska
//Shield card properties

import java.awt.Graphics;
import java.util.ArrayList;

public class Shield extends Card{
    private Player player;

    //Constructor
    public Shield (){
        image = readImage.setImage("shield.png");
        energy = 2;
        player = new Player();
    }

    //Returns the card's energy cost
    public int getEnergy(){
        return energy;
    }

    //Sets block to true (parameter unused)
    public void attack(ArrayList<Monster> list){
        player.setBlock(true);
    }

    //Draws card on screen
    public void draw(Graphics g){
        g.drawImage(image, x, y, width, height, null);
    }
}
