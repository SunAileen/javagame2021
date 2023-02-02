//2021-04-06
//By: Aileen Sun
//Ms Strelkovska
//Main frame including all the panels

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Container;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Point;

public class MyFrame extends JFrame {
    public Menu menu;
    public Battle myPanel;
    public WinPanel winPanel;
    public RunEnd runEnd;
    static Container container;
    static CardLayout cards;

    public MyFrame(String title){
        super(title);

        //Creates all the panels
        menu = new Menu();
        myPanel = new Battle();
        winPanel = new WinPanel();
        runEnd = new RunEnd();

        //Creates container
        container = this.getContentPane();
        container.setLayout(new BorderLayout());
        cards = new CardLayout();
        container.setLayout(cards);

        //Adds cards to container, in order
        container.add(menu, BorderLayout.CENTER);
        container.add(myPanel, BorderLayout.CENTER);
        container.add(winPanel, BorderLayout.CENTER);
        container.add(runEnd, BorderLayout.CENTER);

        //Sets the image icon
        ImageIcon imageIcon = new ImageIcon("icon.png");
        setIconImage(imageIcon.getImage());

        //Sets cursor
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("cursor.png");
        Cursor c = toolkit.createCustomCursor(image, new Point(this.getX(), this.getY()), "img");
        this.setCursor(c);

        //Sets the game window
        this.setVisible(true);
        this.setSize(800, 800);
    }
}
