//2021-04-06
//By: Aileen Sun
//Ms Strelkovska
//AllNighter card properties

import java.awt.Graphics;
import java.util.ArrayList;

public class AllNighter extends Card{
    private Player player;

    //Constructor
    public AllNighter (){
        image = readImage.setImage("allNighter.png");
        energy = 1;
        player = new Player();
    }

    //Returns the card's energy cost
    public int getEnergy(){
        return energy;
    }

    //Deals 15 damage to the first monster, and take 4 damage
    public void attack(ArrayList<Monster> list){
        list.get(0).setHp(-8);
        player.setHp(-2);
    }

    //Draws card on screen
    public void draw(Graphics g){
        g.drawImage(image, x, y, width, height, null);
    }
}
