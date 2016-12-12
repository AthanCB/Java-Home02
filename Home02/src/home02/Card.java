package home02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

public class Card extends JComponent implements MouseListener {

    int repaintCounter = 0;
    final int rectLength = 100;
    private Polygon rect;
    Graphics gp;
    private boolean statRight = true, statLeft = true;
    private char Character;
    private int Value;
    private Color ColorC;
    private int xCoord, yCoord;

    public void setStatClick(boolean st) {
        statRight = st;
    }

    public boolean getStatClick() {
        return statRight;
    }

    //empty constructor
//    public Card() {
//        Character ='a';
//        Value = Value;
//        ColorC = Color.WHITE;
//        xCoord = 0;
//        yCoord = 0;
//    }
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

    public void setLeftClick(boolean stat) {
		statLeft = stat;
	}

	public boolean getLeftClick() {
		return statLeft;
	}

	public void setRightClick(boolean st) {
		statRight = st;
	}

	public boolean getRightClick() {
		return statRight;
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
    }

    @Override
    public void repaint() {
        System.out.println("repaint: " + repaintCounter);
        repaintCounter++;
    }

    public void Myrepaint(Graphics g, MouseEvent me) {
        System.out.println("\nMyrepaint: ");
//		if(rect.contains(getMousePosition()))
//			System.out.println("WORKS");
        if (statLeft == false) {// left button, chosen letter
            g.setColor(Color.yellow);
            g.fillRect(xCoord, yCoord, rectLength, rectLength);
        }
        if (statRight == false) { // right button
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        Point point = me.getPoint();
        System.out.println("POINT: " + point);
        if (this.contains(point)) {
            System.out.println("Contains");
            if (me.getButton() == MouseEvent.BUTTON1) {
                setLeftClick(false);// gia na allaksei to xrwma tou tetragwnou               
                System.out.println("left");
                Myrepaint(this.getGraphics(),me);
            } else if (me.getButton() == MouseEvent.BUTTON3) {
                setRightClick(false);
                System.out.println("right");
                Myrepaint(this.getGraphics(),me);
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
