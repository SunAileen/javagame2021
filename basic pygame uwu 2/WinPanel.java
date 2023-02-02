//2021-04-06
//By: Aileen Sun
//Ms Strelkovska
//Panel shown after each monster is defeated, giving card reward options

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class WinPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private Timer timer;
    private JButton toBattle;
    private ReadImage readImage;
    private String[] cards;
    private Player player;
    private BufferedImage background;

    //Constructor
    public WinPanel(){

        //Creates timer, mouse and player object
        timer = new Timer(100, this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        player = new Player();

        //toBattle button redirects to Menu panel
        toBattle = new JButton(" Next Battle");
        toBattle.addActionListener(this);
        this.add(toBattle);

        //Creates card options
        readImage = new ReadImage();
        cards = new String[3];
        setup();

        //Creates background image
        background = readImage.setImage("cardSelect.png");
    }

    //Add card options on screen
    public void setup(){

        //Randomizes 3 card names between the 4 card options
        int option;
        for(int i = 0; i < 3; i++){
            option = (int) (Math.random()*4);
            switch(option){
                case 0:
                    cards[i] = "scratch.png";
                    break;
                case 1:
                    cards[i] = "fireball.png";
                    break;
                case 2:
                    cards[i] = "allNighter.png";
                    break;
                default:
                    cards[i] = "shield.png";
                    break;
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //Draws background and states the player won the battle
        this.setBackground(Color.gray);
        g.drawImage(background, 0, 0, 800, 800, null);

        //Draws card options
        for(int i = 0; i < cards.length; i++){
            if(!cards[i].equals("claimed")){
                g.drawImage(readImage.setImage(cards[i]), 100 + i*210, 420, 200, 320, null);
            }
        }

        //Draws winstreak
        player.drawWin(g);
    }

    public void actionPerformed(ActionEvent e){
        //Brings user to Menu panel if they click on the button
        if(e.getSource() == toBattle){
            MyFrame.cards.first(MyFrame.container);
            setup();
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
    public void mousePressed(MouseEvent me){
        //If the player clicks on a card, add it to the deck and stops drawing it
        for(int i = 0; i < cards.length; i++){
            if(me.getX() > i*210 + 100 && me.getX() < i*210 + 100 + 200 && me.getY() > 420 && me.getY() < 320 + 420){
                switch(cards[i]){
                    case "scratch.png":
                        player.addCard(new Scratch());
                        break;
                    case "fireball.png":
                        player.addCard(new Fireball());
                        break;
                    case "shield.png":
                        player.addCard(new Shield());
                        break;
                    case "allNighter.png":
                        player.addCard(new AllNighter());
                        break;
                    default:
                        break;
                }
                cards[i] = "claimed";
            }
        }

    }
    public void mouseReleased(MouseEvent me){}
    public void mouseDragged(MouseEvent me){
    }
    public void mouseMoved(MouseEvent me){}
}
