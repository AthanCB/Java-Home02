package home02;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameGraphs extends JFrame {

    static int successPoints,successWords;
    private int x = 5, y = 5;
    Container pane = new Container();
    JFrame jf = new JFrame();
    JButton button1, button3, button2;
    JPanel row1, row2;
    JLabel label;
    Card card;
    static int counter;

    public void setsuccessPoints(int sp)
    {
        successPoints=sp;
    }
    public int getsuccessPoints()
    {
        return successPoints;
    }
    
    public void setsuccessWords(int sw)
    {
        successWords=sw;
    }
    public int getsuccessWords()
    {
        return successWords;
    }
    
    public GameGraphs(){}
            
    public GameGraphs(int dimension, ArrayList<Letter> Shuffled_Chars) {
        super("Window");
        card = new Card();
        card.setArrayDimension(dimension);
        jf.setSize(dimension * 110, dimension * 115);
        counter = 0;
        for (int i = 0;
                i < dimension;
                i++) {
            for (int j = 0; j < dimension; j++) {
                Color c = Shuffled_Chars.get(counter).getColor();
                jf.getContentPane().add(new Card(Shuffled_Chars.get(counter).getCharacter(), Shuffled_Chars.get(counter).getValue(), c, x, y));
                jf.setVisible(true);
                counter++;
                x += 105;
            }
            x = 5;
            y += 105;
        }
    //card.SecondWindow("", 0);
    }
}
