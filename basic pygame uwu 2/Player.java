//2021-04-06
//By: Aileen Sun
//Ms Strelkovska
//Player class keeping track of all static variables and player actions

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private static ArrayList<Card> deck = new ArrayList<>();
    private ReadImage readImage;
    private int index, imgCount;
    private BufferedImage[] imageArray;
    private BufferedImage blockImg, streak;
    private boolean imgBool;
    private static int battles = 1;
    private static int hp = 60;
    private static boolean block = false;
    private static boolean hasWon = false;
    private static int winStreak = 0;

    //Constructor
    public Player(){
        index = 0;

        //Initialize images, including attack animation
        readImage = new ReadImage();
        imgBool = false;
        imgCount = 0;
        BufferedImage image = readImage.setImage("ib.png");
        blockImg = readImage.setImage("block.png");
        streak = readImage.setImage("winStreak.png");
        imageArray = new BufferedImage[4];
        for(int i = 0; i < 3; i++){
            imageArray[i] = image.getSubimage(i*600, 0, 600, 600);
        }
        imageArray[3] = image.getSubimage(600, 0, 600, 600);   //gets middle image so the animation loops well
    }

    //See if the player is in mid-attack
    public boolean getImgBool(){
        return imgBool;
    }
    public void setImgBool(boolean set){imgBool = set;};

    //Changes hp value
    public void setHp(int change){
        //If there is block, reduce enemy attack by half
        if(block){
            hp += change/2;
        }else{
            hp += change;
        }
    }

    //Adds to the winstreak
    public void setWinStreak(int change){
        winStreak += change;
    }

    //Resets winstreak to 0
    public void resetWinStreak(){
        winStreak = 0;
    }

    //Adds block to the player
    public void setBlock(boolean change){
        block = change;
    }

    //Returns amount of hp left on the player
    public int getHp(){
        return hp;
    }

    //Adds to the number of battles passed
    public void setBattles(int change){
        battles += change;
    }

    //Returns amount of battles passed
    public int getBattles(){
        return battles;
    }

    //Adds a card to the deck
    public void addCard(Card c){
        deck.add(c);
    }

    //Shuffles order of the deck and resets index
    public void randomize(){
        Collections.shuffle(deck);
        index = 0;
    }

    //Goes to next card in the order in the pile
    public void nextCard(){
        if(index < deck.size() - 1){
            index++;
        }else{
            randomize();   //shuffles the deck
        }
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

    //Resets everything in player (deck, hp, shield, battles and index)
    public void reset(){
        deck.clear();
        for(int i = 0; i < 2; i++){
            addCard(new Scratch());
        }
        addCard(new Fireball());
        hp = 60;
        battles = 1;
        setBlock(false);
        index = 0;
    }

    //Counts the amount of each card type in the draw pile
    public String countDraw(){
        //Sees if draw pile is empty
        if(index + 3 >= deck.size()){
            return "Your draw pile is empty";
        }else{
            //Initializes variables to hold the counting
            int scratch = 0;
            int fireball = 0;
            int shield = 0;
            int allNighter = 0;

            //Sees which subclass each card belongs to
            for(int i = index + 3; i < deck.size(); i++){
                if(deck.get(i) instanceof Scratch){
                    scratch++;
                }else if(deck.get(i) instanceof Fireball){
                    fireball++;
                }else if(deck.get(i) instanceof Shield){
                    shield++;
                }else{
                    allNighter++;
                }
            }

            //Returns the string with the count
            return "Your draw pile contains: \n" +
                    scratch + " Scratch card(s) \n" +
                    fireball + " Fireball card(s) \n" +
                    shield + " Shield card(s) \n" +
                    allNighter + " All-Nighter card(s) \n";
        }
    }

    //Counts the amount of each card type in the discard pile
    public String countDiscard(){
        //Sees if discard pile is empty
        if(index == 0){
            return "Your discard pile is empty";
        }else{
            //Initializes variables to hold the counting
            int scratch = 0;
            int fireball = 0;
            int shield = 0;
            int allNighter = 0;

            //Sees which subclass each card belongs to
            for(int i = index - 1; i >= 0; i--){
                if(deck.get(i) instanceof Scratch){
                    scratch++;
                }else if(deck.get(i) instanceof Fireball){
                    fireball++;
                }else if(deck.get(i) instanceof Shield){
                    shield++;
                }else{
                    allNighter++;
                }
            }

            //Returns the string with the count
            return "Your discard pile contains: \n" +
                    scratch + " Scratch card(s) \n" +
                    fireball + " Fireball card(s) \n" +
                    shield + " Shield card(s) \n" +
                    allNighter + " All-Nighter card(s) \n";
        }
    }

    //Used to see if the player's won
    public void setWin(boolean change){
        hasWon = change;
    }

    //Returns whether the player's won
    public boolean getWin(){
        return hasWon;
    }

    //Checks for next 3 cards in deck onto the screen. If mouse clicks on one, activate card effect
    //(if enough energy) and replace card with the next card. Activate player attack animation.
    //Does not draw cards if there are none (reached the end of the deck)
    public int cardAction(int energy, int mouseX, int mouseY, ArrayList<Monster> monsterList){
        Card temp;
        //First card
        if(deck.get(index).getEnergy() <= energy && deck.get(index).onCard(mouseX, mouseY)){
            deck.get(index).attack(monsterList);
            energy -= deck.get(index).getEnergy();
            nextCard();
            imgBool = true;
        }else if(index < deck.size() - 1 && deck.get(index + 1).getEnergy() <= energy && deck.get(index + 1).onCard(mouseX, mouseY)) {
            //Second card
            deck.get(index + 1).attack(monsterList);
            energy -= deck.get(index + 1).getEnergy();
            temp = deck.get(index);
            deck.set(index, deck.get(index + 1));
            deck.set(index + 1, temp);
            nextCard();
            imgBool = true;
        }else if(index < deck.size() - 2 && deck.get(index + 2).getEnergy() <= energy && deck.get(index + 2).onCard(mouseX, mouseY)) {
            //Third card
            deck.get(index + 2).attack(monsterList);
            energy -= deck.get(index + 2).getEnergy();
            temp = deck.get(index);
            deck.set(index, deck.get(index + 2));
            deck.set(index + 2, temp);
            nextCard();
            imgBool = true;
        }
        return energy;
    }

    public void drawHp(Graphics g, int hpX, int hpY){
        //Draws hp bar
        g.setColor(Color.black);
        g.drawRect(hpX, hpY, 300, 5);
        g.setColor(Color.red);
        g.fillRect(hpX, hpY, 300, 5);
        g.setColor(Color.green);
        g.fillRect(hpX, hpY, (int) (300*(hp/60.0)), 5);
        g.drawString(hp + "", hpX + 20, hpY - 20);
    }

    public void drawWin(Graphics g){
        //Draws the winstreak
        g.drawImage(streak, 720, 20, 50, 50, null);
        g.setColor(Color.black);
        g.drawString(winStreak + "", 740, 50);
    }

    public void draw(Graphics g){
        g.drawImage(imageArray[imgCount], 50, 100, 300, 300, null);
        drawHp(g, 50, 100);
        drawWin(g);

        //Draws block
        if(block) {
            g.drawImage(blockImg, 40, 80, 50, 50, null);
            g.setColor(Color.white);
        }

        //Draws next 3 cards on screen, or the amount left in the deck
        deck.get(index).changePos(150, 420);
        deck.get(index).draw(g);
        if(index < deck.size() - 1){
            deck.get(index + 1).changePos(360, 420);
            deck.get(index + 1).draw(g);
        }
        if(index < deck.size() - 2){
            deck.get(index + 2).changePos(570, 420);
            deck.get(index + 2).draw(g);
        }
    }

}
