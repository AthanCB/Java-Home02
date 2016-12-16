package home02;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Card extends JComponent implements MouseListener {

    int Points = 0, AllPoints = 0;
    static HashMap<Point, Character> lettersMap = new HashMap<Point, Character>();
    static HashMap<Point, Integer> valuesMap = new HashMap<Point, Integer>();
    //static ArrayList<Integer> ValueList = new ArrayList<Integer>();
    FlowLayout fl = new FlowLayout();
    JFrame jf2 = new JFrame();
    JLabel JMadeWord = new JLabel();// for row1
    JLabel JYourPoints = new JLabel();
    JPanel jp1 = new JPanel();
    JPanel jp2 = new JPanel();
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
        Point LetterPoint = new Point(x, y);
        lettersMap.put(LetterPoint, Character);
        valuesMap.put(LetterPoint, Value);
        System.out.println(LetterPoint + " " + Character);
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
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        char value = 'a';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                rect2 = new Polygon();
                rect2.addPoint(X, Y);
                rect2.addPoint(X, Y + rectLength);
                rect2.addPoint(X + rectLength, Y + rectLength);
                rect2.addPoint(X + rectLength, Y);
                setRect(rect2);
                if (rect2.contains(point)) {
                    if (me.getButton() == MouseEvent.BUTTON1) {
                        //System.out.println("Rect has X: " + rect2.getBounds().x + " Y: " + rect2.getBounds().y);
                        for (Map.Entry<Point, Character> entry : lettersMap.entrySet()) {
                            Point CurrentPoint = entry.getKey();
                            if (CurrentPoint.getX() == rect2.getBounds().x && CurrentPoint.getY() == rect2.getBounds().y) {
                                System.out.println("In coords:" + CurrentPoint + " found the letter: " + value);
                                g.setColor(Color.yellow);
                                g.fillRect(X, Y, 100, 100);
                                for (Map.Entry<Point, Integer> entry2 : valuesMap.entrySet()) {
                                    Point CurrentPoint2 = entry2.getKey();
                                    if (CurrentPoint2 == CurrentPoint) {
                                        value = entry.getValue();
                                        Points = entry2.getValue();
                                        String LetterPoints = "" + Points;
                                        String LetterChar = "" + value;
                                        g.setColor(Color.BLACK);
                                        g.setFont(new Font("Courier", Font.BOLD, 71));
                                        g.drawString(LetterChar, 25 + X, 75 + Y);

                                        g.setFont(new Font("Courier", Font.BOLD, 12));
                                        g.drawString(LetterPoints, 80 + X, 80 + Y);
                                    }
                                }
                                ChangeTheWord(value, Points);
                            }
                        }
                    }
                    if (me.getButton() == MouseEvent.BUTTON3) {
                        System.out.println("RIGHT BUTTON IN PROGRESS");
                    }
                }
                X += 105;
            }
            X = 5;
            Y += 105;
        }
    }

    private void ChangeTheWord(char cLetter, int Points) {
        AllPoints += Points;
        jf2.setLayout(fl);
        jf2.setSize(500, 200);
        jf2.setLocationRelativeTo(null);
        jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf2.setVisible(true);
        MadeWord += cLetter;
        setCharacter(cLetter);
        JMadeWord.setFont(new Font("Courier", Font.BOLD, 30));
        JMadeWord.setText("Η λέξη ως τώρα: " + MadeWord);
        JYourPoints.setFont(new Font("Courier", Font.BOLD, 30));
        JYourPoints.setText("Οι πόντοι σου ως τώρα: " + AllPoints);
        jp1.add(JMadeWord);
        jp2.add(JYourPoints);
        jf2.add(jp1);
        jf2.add(jp2);
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
