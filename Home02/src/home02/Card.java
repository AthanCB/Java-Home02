package home02;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.Rect;

public class Card extends JComponent implements MouseListener {

    boolean stat = true;
    final int rectLength = 100;
    private Polygon rect;
    Graphics gp;
    private char Character;
    private int Value;
    private Color ColorC;
    private int xCoord, yCoord;

    public Card(char Character, int Value, Color color, int x, int y) {
        this.Character = Character;
        this.Value = Value;
        this.ColorC = color;
        this.xCoord = x;
        this.yCoord = y;
        rect = new Polygon();
        rect.addPoint(xCoord, yCoord);
        rect.addPoint(xCoord, yCoord + rectLength);
        rect.addPoint(xCoord + rectLength, yCoord + rectLength);
        rect.addPoint(xCoord + rectLength, yCoord);
        setRect(rect);
        addMouseListener(this);
    }

    public void setRect(Polygon rect) {
        this.rect = rect;
    }

    public Polygon getRect() {
        return rect;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        String Letter = "" + Character;
        String valueS = "" + Value;

        g.setColor(Color.BLACK);
        g.drawRect(xCoord, yCoord, rectLength, rectLength); // rectLength=100
        g.setColor(ColorC);
        g.fillRect(xCoord, yCoord, rectLength, rectLength);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier", Font.BOLD, 71));
        g.drawString(Letter, 25 + xCoord, 75 + yCoord);
        g.setFont(new Font("Courier", Font.BOLD, 12));
        g.drawString(valueS, 80 + xCoord, 80 + yCoord);
        g.drawPolygon(rect);
        if(stat==false) {
            g.setColor(Color.YELLOW);
            g.fillRect(xCoord, yCoord, rectLength, rectLength);
        } 
       
    }
    
    @Override
    public void repaint(){
        
    }
    @Override
    public void mouseClicked(MouseEvent me) {
        Point point = me.getPoint();
        if (rect.contains(point)) {
            System.out.println("Contains");
            if (me.getButton() == MouseEvent.BUTTON1) {
                stat = false;
                System.out.println("fdwwwg");
            }
            
        }
    }

    public void mousePressed(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }
}
