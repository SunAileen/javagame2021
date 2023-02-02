//2021-04-06
//By: Aileen Sun
//Ms Strelkovska
//Panel used to display battles with monsters

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Battle extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private Timer timer;
    private Player player;
    private ArrayList<Monster> monsterList;
    private ReadImage readImage;
    private JButton endTurn, draw, discard;
    private int energy;
    private BufferedImage background;

    //Constructor
    public Battle(){
        //Adds timer and mouse
        timer = new Timer(100, this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //Initializes background
        readImage = new ReadImage();
        background = readImage.setImage("battleScene.png");

        //Creates player and monsters on the screen
        player = new Player();
        for(int i = 0; i < 2; i++){
            player.addCard(new Scratch());
        }
        player.addCard(new Fireball());
        monsterList = new ArrayList<>();
        setup();

        //Creates JButton so player can see the draw pile
        draw = new JButton("Draw Pile");
        draw.addActionListener(this);
        this.add(draw);

        //Creates JButton for player to end turn
        endTurn = new JButton("End Turn");
        endTurn.addActionListener(this);
        this.add(endTurn);

        //Creates JButton so player can see the discard pile
        discard = new JButton("Discard Pile");
        discard.addActionListener(this);
        this.add(discard);
    }

    //Adds monsters every level
    public void setup(){
        switch(player.getBattles()){
            //First level is always 1 Bone monster
            case 1:
                monsterList.add(new Bone(500, 200));
                break;
            //Last level is always a boss battle
            case 5:
                monsterList.add(new Boss(450, 120));
                break;
            //Levels 2-4 have randomized enemies
            default:
                //Possible random encounters: 2 Bones, 3 Bones, or 1 Ghost
                int randomEncounter = (int) (Math.random()*3);
                switch(randomEncounter){
                    case 0:
                        monsterList.add(new Bone(450, 200));
                        monsterList.add(new Bone(600, 200));
                        break;
                    case 1:
                        for(int i = 1; i < 4; i++){
                            monsterList.add(new Bone(150*i + 150, 200));
                        }
                        break;
                    default:
                        monsterList.add(new Ghost(500, 200));
                }
        }

        //Add 1 to total amount of battles, randomize deck and reset energy to 3
        player.setBattles(1);
        player.randomize();
        energy = 3;
    }

    public void paintComponent(Graphics g){
        //Draws background image
        super.paintComponent(g);
        this.setBackground(Color.gray);
        g.drawImage(background, 0, 0, 800, 800, null);

        //Draws player, checks for attack animation
        player.draw(g);
        if(player.getImgBool()){
            player.move();
        }

        //Removes any monsters with no hp, ends battle if player or all monsters have no hp
        for(int i = 0; i < monsterList.size(); i++){
            if(monsterList.get(i).getHp() <= 0) {
                monsterList.remove(monsterList.get(i));
            }else{
                monsterList.get(i).draw(g);
                if(monsterList.get(i).getImgBool()){
                    monsterList.get(i).move();
                }
            }
        }

        //Checks if battle's ended
        endBattle();

        //Draws energy
        g.drawImage(readImage.setImage("energy.png"), 50, 550, 75, 100, null);
        g.setColor(Color.white);
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        g.drawString("Energy", 50, 550);
        g.setColor(Color.black);
        g.drawString(energy + "", 80, 610);
    }

    public void endBattle(){
        //If player is out of hp, send to RunEnd
        if(player.getHp() <= 0){
            player.setWin(false);
            player.resetWinStreak();
            timer.stop();
            MyFrame.cards.last(MyFrame.container);
            monsterList.clear();
            player.reset();
            setup();
        }else if(monsterList.size() <= 0){
            //If monsters are all gone, send to WinPanel. If player's won the last battle, send to RunEnd
            timer.stop();
            if(player.getBattles() > 5){
                player.setWin(true);
                player.setWinStreak(1);
                MyFrame.cards.last(MyFrame.container);
                player.reset();
            }else{
                MyFrame.cards.next(MyFrame.container);
                player.setImgBool(false);
            }
            player.setBlock(false);
            setup();
        }
    }

    public void actionPerformed(ActionEvent e){
        //If the player ends the turn, make the monster attack, and reset energy count to 3, block to false
        if(e.getSource() == endTurn){
            for(Monster monster : monsterList){
                monster.attack(player);
                monster.setImgBool(true);
            }
            player.setBlock(false);
            energy = 3;
        }

        //Displays draw pile if clicked
        if(e.getSource() == draw){
            JOptionPane.showMessageDialog(null, player.countDraw(), "Draw Pile", JOptionPane.INFORMATION_MESSAGE);
        }

        //Displays discard pile if clicked
        if(e.getSource() == discard){
            JOptionPane.showMessageDialog(null, player.countDiscard(), "Discard Pile", JOptionPane.INFORMATION_MESSAGE);
        }
        repaint();
    }

    //Mouse event methods
    public void mouseClicked(MouseEvent me){
    }
    public void mouseEntered(MouseEvent me){
        timer.start();
    }
    public void mouseExited(MouseEvent me){
        timer.stop();
    }
    public void mousePressed(MouseEvent me)
    {
        //Sees if player has enough energy to play a card, if so subtract energy cost from energy count
        energy = player.cardAction(energy, me.getX(), me.getY(), monsterList);
    }
    public void mouseReleased(MouseEvent me){}
    public void mouseDragged(MouseEvent me){
    }
    public void mouseMoved(MouseEvent me){}
}