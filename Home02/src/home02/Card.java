package home02;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Card extends JComponent implements MouseListener {

    FlowLayout fl = new FlowLayout();
    JFrame jf2 = new JFrame();
    JLabel JMadeWord = new JLabel();// for row1
    JPanel jp1 = new JPanel();
    private static String MadeWord = "";
    private final int rectLength = 100;
    private Polygon rect, rect2;
    Graphics gp;
    private boolean statRight = true, statLeft = true;
    private char Character;
    private int Value;
    private Color ColorC;
    private int xCoord, yCoord;

    public char getCharacter() {
        return Character;
    }

    public void setCharacter(char character) {
        Character = character;
    }

    public void setStatClick(boolean st) {
        statRight = st;
    }

    public boolean getStatClick() {
        return statRight;
    }

    public Card() {
    }

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
    }

    @Override
    public void repaint() {
    }

    public void Myrepaint(Graphics g, MouseEvent me) {
        Point point = me.getPoint();
        int X = 5, Y = 5;
        if (me.getButton() == MouseEvent.BUTTON1) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    rect2 = new Polygon();
                    rect2.addPoint(X, Y);
                    rect2.addPoint(X, Y + rectLength);
                    rect2.addPoint(X + rectLength, Y + rectLength);
                    rect2.addPoint(X + rectLength, Y);
                    setRect(rect);
                    if (rect2.contains(point)) {
                        if (me.getButton() == MouseEvent.BUTTON1) {
                            g.setColor(Color.YELLOW);
                            g.fillRect(X, Y, rectLength, rectLength);
                        }
                        if (me.getButton() == MouseEvent.BUTTON3) {
                            g.setColor(Color.GREEN);
                            g.fillRect(X, Y, rectLength, rectLength);
                        }
                        String Letter = "" + Character;
                        String valueS = "" + Value;
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("Courier", Font.BOLD, 71));
                        g.drawString(Letter, 25 + X, 75 + Y);
                        g.setFont(new Font("Courier", Font.BOLD, 12));
                        g.drawString(valueS, 80 + X, 80 + Y);
                    }
                    X += 105;
                }
                X = 5;
                Y += 105;
            }
            ChangeTheWord();
            //MadeWord += Letter;
        }
    }

    private void ChangeTheWord() {
        jf2.setLayout(fl);
        jf2.setSize(200, 100);
        jf2.setLocationRelativeTo(null);
        jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf2.setVisible(true);
        // Game game = new Game();
        MadeWord += Character;
        setCharacter(Character);
        JMadeWord.setText("Word: " + MadeWord);
        JMadeWord.setVisible(true);
        jp1.add(JMadeWord);
        jf2.add(jp1);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        Myrepaint(getGraphics(), me);
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
