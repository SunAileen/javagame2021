//2021-04-06
//By: Aileen Sun
//Ms Strelkovska
//Class to show the grey, black and boss icons on the map

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MapIcon{
    private ReadImage readImage;
    private BufferedImage image;
    private int x, y, width;
    private Player player;

    //Constructor
    public MapIcon (){
        y = 400;
        width = 50;
        readImage = new ReadImage();
        image = readImage.setImage("enemy.png");
        player = new Player();

        //Determines the x-coordinate of the black enemy icon based on amount of battles passed
            switch(player.getBattles()){
                case 2:
                    x = 250;
                    break;
                case 3:
                    x = 350;
                    break;
                case 4:
                    x = 450;
                    break;
                case 5:
                    x = 550;
                    image = readImage.setImage("boss.png");
                    break;
                default:
                    x = 150;
                    break;
        }
    }

    //Sees if mouse clicked on the icon
    public boolean onIcon(int mouseX, int mouseY){
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + width;
    }

    public void draw(Graphics g){
        //Draws greyed out enemy icon across the map
        for(int i = 1; i <= 4; i++){
            g.drawImage(readImage.setImage("enemyGrey.png"), 100*i + 50, y, width, width, null);
        }
        //Draws boss icon at the right end of the map
        g.drawImage(readImage.setImage("boss.png"), 550, y, width, width,null);

        //Necessary to reset properly
        if(player.getBattles() == 1){
            image = readImage.setImage("enemy.png");
            x = 150;
            player.setBattles(1);
        }

        //Draws black enemy icon
        g.drawImage(image, x, y, width, width, null);
    }
}
