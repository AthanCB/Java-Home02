package home02;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Card extends JComponent implements MouseListener {

    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();

    int Points = 0, AllPoints = 0;
    static int dimension;
    static HashMap<Point, Character> lettersMap = new HashMap<Point, Character>();
    static HashMap<Point, Integer> valuesMap = new HashMap<Point, Integer>();
    //static ArrayList<Integer> ValueList = new ArrayList<Integer>();
    FlowLayout fl = new FlowLayout();
    GridLayout gl;
    JButton b1 = new JButton("1)Αντικατάσταση γραμμάτων γραμμής");
    JButton b2 = new JButton("2)Αναδιάταξη γραμμής");
    JButton b3 = new JButton("3)Αναδιάταξη στήλης");
    JButton b4 = new JButton("4)Αναδιάταξη γραμμάτων");
    JButton b5 = new JButton("5)Εναλλαγή γραμμάτων");
    JPanel jp1 = new JPanel();
    JPanel jp2 = new JPanel();
    JPanel jp4 = new JPanel();
    JPanel jp5 = new JPanel();
    JPanel jp6 = new JPanel();
    JPanel jp7 = new JPanel();
    JPanel jp8 = new JPanel();
    JPanel jp9 = new JPanel();
    JPanel jp10 = new JPanel();
    JPanel jp3 = new JPanel();
    JLabel options = new JLabel();
    JLabel info2 = new JLabel();
    JLabel info1 = new JLabel();
    JFrame jf2 = new JFrame();
    JLabel JMadeWord = new JLabel();// for row1
    JLabel JYourPoints = new JLabel();
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

    public void setArrayDimension(int d) {
        dimension = d;
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
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
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
                                System.out.println("ChangeTheWord");
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
        gl = new GridLayout(11, 1);
        jf2.setLayout(gl);
        jf2.setSize(800, 1000);
        jf2.setLocation(1000, 5);
        jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf2.setVisible(true);

        AllPoints += Points;
        MadeWord += cLetter;
        setCharacter(cLetter);

        JMadeWord.setForeground(Color.blue);
        JMadeWord.setFont(new Font("Courier", Font.BOLD, 40));
        JMadeWord.setVisible(true);
        JMadeWord.setText("Η λέξη ως τώρα: " + MadeWord);

        JYourPoints.setForeground(Color.RED);
        JYourPoints.setFont(new Font("Courier", Font.BOLD, 40));
        JYourPoints.setVisible(true);
        JYourPoints.setText("Οι πόντοι σου ως τώρα: " + AllPoints);

        options.setText("Πρόσθετες επιλογές:");
        options.setFont(new Font("Courier", Font.BOLD, 22));

        info1.setText("Στόχος λέξεων:");
        info1.setFont(new Font("Courier", Font.BOLD, 15));

        info2.setText("Στόχος πόντων:");
        info2.setFont(new Font("Courier", Font.BOLD, 15));

        b1.setPreferredSize(new Dimension(250, 50));
        b2.setPreferredSize(new Dimension(200, 50));
        b3.setPreferredSize(new Dimension(200, 50));
        b4.setPreferredSize(new Dimension(200, 50));
        b5.setPreferredSize(new Dimension(200, 50));

        jp1.add(JMadeWord);
        jf2.add(jp1);

        jp2.add(JYourPoints);
        jf2.add(jp2);

        jp3.add(options);
        jf2.add(jp3);

        jp4.add(b1);
        jf2.add(jp4);

        jp5.add(b2);
        jf2.add(jp5);

        jp6.add(b3);
        jf2.add(jp6);

        jp7.add(b4);
        jf2.add(jp7);

        jp8.add(b5);
        jf2.add(jp8);

        jp9.add(info1);
        jf2.add(jp9);

        jp10.add(info2);
        jf2.add(jp10);

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
