package home02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class Card extends JComponent{
    private char Character;
    private int Value;
    private Color ColorC;
    private int xCoord,yCoord;
    
    public Card(char Character, int Value, Color color,int x, int y) {
        this.Character = Character;
        this.Value = Value;
        this.ColorC = color;
        this.xCoord = x;
        this.yCoord = y;
       
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        String Letter = "" + Character;
         String valueS = "" + Value;
         
        g.drawRect(xCoord, yCoord, 100, 100);
        g.setColor(ColorC);
        g.fillRect(xCoord, yCoord, 100, 100);
        g.setColor(Color.BLACK);
        g.drawString(Letter, 50+xCoord, 50+yCoord);
        g.drawString(valueS, 80+xCoord, 80+yCoord);
        

    }
    
    
    
   
}
