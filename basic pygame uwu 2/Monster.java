//2021-04-06
//By: Aileen Sun
//Ms Strelkovska
//Abstract class detailing properties of each monster

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Monster {
    public int x, y, hp, width, height, imgCount;
    public ReadImage readImage;
    public BufferedImage[] imageArray;
    public BufferedImage intent;
    public boolean imgBool;

    //Constructor
    public Monster(int x, int y){
        //Sets x and y coordinates
        this.x = x;
        this.y = y;

        //Sets variables for images
        imgCount = 0;
        readImage = new ReadImage();
        intent = readImage.setImage("intent.png");
        imgBool = false;
        imageArray = new BufferedImage[4];
    }

    //Returns amount of hp on the monster
    public int getHp(){
        return hp;
    }

    //Changes amount of hp left on this monster
    public void setHp(int change){
        hp += change;
    }

    public abstract void attack(Player p);

    //See if the monster is in mid-attack
    public boolean getImgBool(){return imgBool;}
    public void setImgBool(boolean set){
        imgBool = set;
    }

    //Change animation frame to the next one
    public void move(){
        if(imgCount == 3){
            imgCount = 0;
            imgBool = false;
        }else{
            imgCount++;
        }
    }

    public abstract void draw(Graphics g);
}
