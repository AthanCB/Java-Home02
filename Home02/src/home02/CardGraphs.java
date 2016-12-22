package home02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class CardGraphs extends JComponent implements MouseListener{

    Game game = new Game();
    GameGraphs gg = new GameGraphs();
    String tempChar;
    int Points = 0, WordPoints = 0, PointsOfLetter = 0;
    static int dimension;
    static HashMap<Point, Character> lettersMap = new HashMap<>();
    static HashMap<Point, Integer> valuesMap = new HashMap<>();
    ArrayList<Point> chosenLettersList = new ArrayList<>();
    private final int rectLength = 100;
    private Polygon rect, rect2;
    Graphics gp;
    private boolean doubleWordValue = false, statBlackColor = false;
    private char Character;
    private int Value;
    private Color ColorC;
    int LastX = 0, LastY = 0;
    private int xCoord, yCoord;
    Point LetterPoint;
    StringBuilder sb;

    public char getCharacter() {
        return Character;
    }

    public void setCharacter(char character) {
        Character = character;
    }

    public CardGraphs() {
    }

    public CardGraphs(Letter letter, int x, int y) {
        this.Character = letter.getCharacter();
        this.Value = letter.getValue();
        this.ColorC = letter.getColor();
        this.xCoord = x;
        this.yCoord = y;
        rect = new Polygon();
        rect.addPoint(xCoord, yCoord);
        rect.addPoint(xCoord, yCoord + rectLength);
        rect.addPoint(xCoord + rectLength, yCoord + rectLength);
        rect.addPoint(xCoord + rectLength, yCoord);
        setRect(rect);
        LetterPoint = new Point(x, y);
        letter.setPoint(LetterPoint);
        lettersMap.put(LetterPoint, Character);
        valuesMap.put(LetterPoint, Value);
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

    public void setPointsOfLetter(int p) {
        PointsOfLetter = p;
        System.out.println(PointsOfLetter);
    }

    public int getPointsOfLetter() {
        return PointsOfLetter;
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
        Point currentPoint = me.getPoint();
        int X = 5, Y = 5;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                rect2 = new Polygon();
                rect2.addPoint(X, Y);
                rect2.addPoint(X, Y + rectLength);
                rect2.addPoint(X + rectLength, Y + rectLength);
                rect2.addPoint(X + rectLength, Y);
                setRect(rect2);
                if (rect2.contains(currentPoint)) {
                    if (me.getButton() == MouseEvent.BUTTON1) {
                        for (Letter letterFromList : GameGraphs.letterList) {
                            if (rect2.contains(letterFromList.getPoint())) {
                                if (LetterChecks(g, letterFromList, X, Y)) {
                                    Point tempPoint = new Point(X, Y);
                                    chosenLettersList.add(tempPoint);
                                }
                            }
                        }
                    }
                    if (me.getButton() == MouseEvent.BUTTON3) {
                        JOptionPane.showMessageDialog(null, "Επέλεξες ακύρωση της τωρινής λέξης");
                        WordPoints = 0;
                        //make all black all again
                    }
                }
                X += 105;
            }
            X = 5;
            Y += 105;
        }
    }

    public boolean LetterChecks(Graphics g, Letter l, int X, int Y) {
        if (l.getCharacter() == '?') {
            tempChar = JOptionPane.showInputDialog("Επέλεξε εσύ το γράμμα επιθυμίας σου");
            if (tempChar.length() > 0) {
                l.setCharacter(tempChar.charAt(0));
            } else {
                tempChar = JOptionPane.showInputDialog("Επέλεξε ξανά το γράμμα επιθυμίας σου");
            }
        }
        if (LastX == X && LastY == Y) {//last letter
            int ch = JOptionPane.showConfirmDialog(null, "Θες να ακυρώσεις το τελευταίο γράμμα");
            if (ch == 0) {
                l.setSituation(false);
                statBlackColor = true;
                ReBlackLetter(g, l, X, Y);
                gg.SecondWindow(l);
                chosenLettersList.remove(0);
            }
        } else if (l.isSituation() == false) { // letter available
            if ((LastX == 0 && LastY == 0) || (!(Math.abs(LastX - X) > 105) && !(Math.abs(LastY - Y) > 105))) {
                //ChosenLetters.add(Y);
                l.setSituation(true);
                LastX = X;
                LastY = Y;
                if (l.getColor() == Color.blue) {
                    doubleWordValue = true;
                }
                System.out.println("Γράμμα με συντεταγμένες: " + l.getPoint() + ", " + l.getCharacter());
                ReDrawLetter(g, l, X, Y);
                gg.SecondWindow(l);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Δεν είναι γειτονικό αυτό το γράμμα");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Χρησιμοποιήθηκε αυτό το γράμμα");
            return false;
        }
        return true; // never runned
    }

    public void ReBlackLetter(Graphics g, Letter l, int X, int Y) {
        String LetterPoints = "" + l.getValue();
        String LetterChar = "" + l.getCharacter();
        g.setColor(Color.white);
        g.fillRect(X, Y, 100, 100);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier", Font.BOLD, 71));
        g.drawString(LetterChar, 25 + X, 75 + Y);
        g.setFont(new Font("Courier", Font.BOLD, 12));
        g.drawString(LetterPoints, 80 + X, 80 + Y);
    }

    public void ReDrawLetter(Graphics g, Letter l, int X, int Y) {
        String LetterPoints = "" + l.getValue();
        String LetterChar = "" + l.getCharacter();
        g.setColor(Color.yellow);
        g.fillRect(X, Y, 100, 100);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier", Font.BOLD, 71));
        g.drawString(LetterChar, 25 + X, 75 + Y);
        g.setFont(new Font("Courier", Font.BOLD, 12));
        g.drawString(LetterPoints, 80 + X, 80 + Y);
    }
    @Override
    public void mouseClicked(MouseEvent me) {
        Myrepaint(getGraphics(), me);
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
