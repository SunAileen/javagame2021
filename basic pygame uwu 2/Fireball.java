//2021-04-06
//By: Aileen Sun
//Ms Strelkovska
//Fireball card properties

import java.awt.Graphics;
import java.util.ArrayList;

public class Fireball extends Card{

    //Constructor
    public Fireball (){
        image = readImage.setImage("fireball.png");
        energy = 1;
    }

    //Returns the card's energy cost
    public int getEnergy(){
        return energy;
    }

    //Deals 4 damage to all monsters
    public void attack(ArrayList<Monster> list){
        for(Monster m : list){
            m.setHp(-4);
        }
    }

    //Draws card on screen
    public void draw(Graphics g){
        g.drawImage(image, x, y, width, height, null);
    }
}
