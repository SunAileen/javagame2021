//2021-04-06
//By: Aileen Sun
//Ms Strelkovska
//Panel showing the map, directing the user to each battle

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private Timer timer;
    private MapIcon enemy;
    private ReadImage readImage;
    private BufferedImage map;
    private JButton instructions;
    private Player player;

    //Constructor
    public Menu(){

        //Adds timer and mouse
        timer = new Timer(100, this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //Makes player object
        player = new Player();

        //Creates enemy images and background image
        enemy = new MapIcon();
        readImage = new ReadImage();
        map = readImage.setImage("map.png");

        //Creates JButton to display instructions
        instructions = new JButton("Instructions");
        instructions.addActionListener(this);
        this.add(instructions);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.gray);

        //Draws the background and enemy icons
        g.setColor(Color.black);
        g.drawImage(map, 0, 0, 800, 800, null);
        enemy.draw(g);

        //Draws hp bar in upper left
        player.drawHp(g, 20, 50);

        //Draws winstreak in the upper right
        player.drawWin(g);
    }

    //Creates instructions list
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == instructions){
            JOptionPane.showMessageDialog(null, "You start with 60 hp and have to defeat 5 levels filled with monsters.\n" +
                    "Monsters will make attacks to deplete your hp. The amount of damage is indicated above the monster's head.\n" +
                    "Your starting deck consists of 3 cards: 2 Scratch cards and 1 Fireball card. You can add any of 3 cards into your deck after each battle.\n" +
                    "Each turn, you have 3 energy. Each card costs energy to play. You can use any of the top 3 cards in your deck.\n" +
                    "Attacks that target 1 enemy will target the leftmost one.\n" +
                    "After your deck is used up, it will randomly shuffle itself. The deck will also shuffle each new battle.\n" +
                    "W icon in the upper right is your winstreak, meaning the number of times you've won in a row. \n" +
                    "Start by clicking the black enemy icon. Good luck and have fun!", "Instructions", JOptionPane.WARNING_MESSAGE);
        }
        repaint();
    }

    //Mouse event methods
    public void mouseClicked(MouseEvent me){
    }
    public void mouseEntered(MouseEvent me){
    }
    public void mouseExited(MouseEvent me){
        timer.stop();
    }
    public void mousePressed(MouseEvent me){
        //Makes the enemy image redirect to Battle panel
        if(enemy.onIcon(me.getX(), me.getY())){
            enemy = new MapIcon();
            MyFrame.cards.next(MyFrame.container);
        }
    }
    public void mouseReleased(MouseEvent me){}
    public void mouseDragged(MouseEvent me){
    }
    public void mouseMoved(MouseEvent me){}
}
