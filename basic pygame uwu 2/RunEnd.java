//2021-04-06
//By: Aileen Sun
//Ms Strelkovska
//Panel shown after the game's over, either showing a win or a loss

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class RunEnd extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private Timer timer;
    private Player player;
    private JButton replay;
    private ReadImage readImage;
    private BufferedImage winBack, loseBack;

    //Constructor
    public RunEnd(){

        //Creates timer, mouse and player object
        timer = new Timer(100, this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        player = new Player();

        //Creates background images
        readImage = new ReadImage();
        winBack = readImage.setImage("winScreen.png");
        loseBack = readImage.setImage("loseScreen.png");

        //Creates JButton to reset everything and bring player to Menu
        replay = new JButton("Replay Game");
        replay.addActionListener(this);
        this.add(replay);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.gray);

        //Checks if the player's out of hp, if so state they died, otherwise say they won
        if(player.getWin()){
            g.drawImage(winBack, 0, 0, 800, 800, null);
        }else{
            g.drawImage(loseBack, 0, 0, 800, 800, null);
        }
    }

    public void actionPerformed(ActionEvent e){
        //If the player clicks replay, reset everything in-game and bring player to Menu
        if(e.getSource() == replay){
            MyFrame.cards.first(MyFrame.container);
            player.reset();
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
    }
    public void mouseReleased(MouseEvent me){}
    public void mouseDragged(MouseEvent me){
    }
    public void mouseMoved(MouseEvent me){}
}
