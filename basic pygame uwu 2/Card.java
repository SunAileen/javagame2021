//2021-04-06
//By: Aileen Sun
//Ms Strelkovska
//Abstract class outlining properties of a card

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Card {

    //Abstract class variables and constructor
    public int energy, x, y, width, height;
    public BufferedImage image;
    public ReadImage readImage;
    public Card(){
        x = 360;
        y = 420;
        width = 200;
        height = 320;
        readImage = new ReadImage();
    }

    //Changes card's x and y coordinates
    public void changePos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract int getEnergy();

    public abstract void attack(ArrayList<Monster> list);

    //Returns whether the mouse clicked on the card
    public boolean onCard(int mouseX, int mouseY){
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    public abstract void draw(Graphics g);
}
