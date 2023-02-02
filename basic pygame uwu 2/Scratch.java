//2021-04-06
//By: Aileen Sun
//Ms Strelkovska
//Scratch card properties

import java.awt.Graphics;
import java.util.ArrayList;

public class Scratch extends Card{

    //Constructor
    public Scratch (){
        image = readImage.setImage("scratch.png");
        energy = 1;
    }

    //Returns the card's energy cost
    public int getEnergy(){
        return energy;
    }

    //Deals 6 damage to the first monster
    public void attack(ArrayList<Monster> list){
        list.get(0).setHp(-6);
    }

    //Draws card on screen
    public void draw(Graphics g){
        g.drawImage(image, x, y, width, height, null);
    }
}
