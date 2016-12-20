package home02;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Card extends JComponent implements MouseListener {

    Game game = new Game();
    GameGraphs gg = new GameGraphs();
    String tempChar;
    int Points = 0, WordPoints = 0, PointsOfLetter = 0;
    static int dimension;
    static HashMap<Point, Character> lettersMap = new HashMap<>();
    static HashMap<Point, Integer> valuesMap = new HashMap<>();
    //static ArrayList<Integer> ValueList = new ArrayList<Integer>();
    FlowLayout fl = new FlowLayout();
    GridLayout gl = new GridLayout(11, 1);
    JButton bExit = new JButton("Διακοπή παιχνιδιού");
    JButton bCheckWord = new JButton("ΕΛΕΓΧΟΣ ΛΕΞΗΣ");
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
    JPanel jp11 = new JPanel();
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
    private boolean doubleWordValue = false;
    private char Character;
    private int Value;
    private Color ColorC;
    int LastX = 0, LastY = 0;
    private int xCoord, yCoord;
    Point LetterPoint;

    public char getCharacter() {
        return Character;
    }

    public void setCharacter(char character) {
        Character = character;
    }

    public Card() {
    }
//    public Card(char Character, int Value, Color color, int x, int y) {
//        this.Character = Character;
//        this.Value = Value;
//        this.ColorC = color;
//        this.xCoord = x;
//        this.yCoord = y;
//        rect = new Polygon();
//        rect.addPoint(xCoord, yCoord);
//        rect.addPoint(xCoord, yCoord + rectLength);
//        rect.addPoint(xCoord + rectLength, yCoord + rectLength);
//        rect.addPoint(xCoord + rectLength, yCoord);
//        setRect(rect);
//        LetterPoint = new Point(x, y);
//        letter.setPoint(LetterPoint);
//        setPoint(LetterPoint);
//        lettersMap.put(LetterPoint, Character);
//        valuesMap.put(LetterPoint, Value);
//        //System.out.println(LetterPoint + " " + Character);
//        if (GameGraphs.counter == 0) {
//            SecondWindow("", 0);
//        }
//        addMouseListener(this);
//    }

    public Card(Letter letter, int x, int y) {
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
        if (GameGraphs.counter == 0) {
            SecondWindow(null);
        }
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
                                LetterChecks(g, letterFromList, X, Y);
                            }
                        }
                    }
                    if (me.getButton() == MouseEvent.BUTTON3) {
                        JOptionPane.showMessageDialog(null, "Επέλεξες ακύρωση της τωρινής λέξης");
                        WordPoints = 0;
                    }
                }
                X += 105;
            }
            X = 5;
            Y += 105;
        }
    }

    public void LetterChecks(Graphics g, Letter l, int X, int Y) {
        if (l.getCharacter() == '?') {
            tempChar = JOptionPane.showInputDialog("Επέλεξε εσύ το γράμμα επιθυμίας σου");
            if (tempChar.length() > 0) {
                l.setCharacter(tempChar.charAt(0));
            } else {
                tempChar = JOptionPane.showInputDialog("Επέλεξε ξανά το γράμμα επιθυμίας σου");
            }
        }
        if (l.isSituation() == false) { // letter available
            if ((LastX == 0 && LastY == 0) || (!(Math.abs(LastX - X) > 105) && !(Math.abs(LastY - Y) > 105))) {
                l.setSituation(true);
                LastX = X;
                LastY = Y;
                if (l.getColor() == Color.blue) {
                    doubleWordValue = true;
                }
                System.out.println("Γράμμα με συντεταγμένες: " + l.getPoint() + ", " + l.getCharacter());
                ReDrawLetter(g, l, X, Y);
                SecondWindow(l);
            } else {
                JOptionPane.showMessageDialog(null, "Δεν είναι γειτονικό αυτό το γράμμα");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Χρησιμοποιήθηκε αυτό το γράμμα");
        }

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

    protected void SecondWindow(Letter letter) {
        jf2.setLayout(gl);
        jf2.setSize(800, 1000);
        jf2.setLocation(1000, 5);
        jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf2.setVisible(true);

        if (GameGraphs.counter == 0) {
            WordPoints = 0;
            MadeWord = "";
            gg.setPointsOfWords(0);
        } else {
            WordPoints += letter.getValue();
            MadeWord += letter.getCharacter();
            gg.setPointsOfWords(letter.getValue());
        }

        JMadeWord.setVisible(true);
        JMadeWord.setText("Η λέξη ως τώρα: " + MadeWord);
        JMadeWord.setForeground(Color.BLUE);
        JMadeWord.setFont(new Font("Courier", Font.BOLD, 40));

        JYourPoints.setVisible(true);
        JYourPoints.setText("Οι πόντοι της λέξης ως τώρα: " + WordPoints);
        JYourPoints.setForeground(Color.RED);
        JYourPoints.setFont(new Font("Courier", Font.BOLD, 40));

        options.setText("Πρόσθετες επιλογές:");
        options.setFont(new Font("Courier", Font.BOLD, 22));

        info1.setText("Τωρινή λέξη: " + gg.getNumberOfWords() + ", στόχος λέξεων: " + gg.getsuccessWords());
        info1.setFont(new Font("Courier", Font.ITALIC, 25));
        info1.setForeground(Color.DARK_GRAY);

        if (gg.getPointsOfWords() > gg.getsuccessPoints()) {
            JOptionPane.showMessageDialog(null, "Μάζεψες τους απαιτούμενους πόντους, ΝΙΚΗΣΕΣ!");
            System.exit(0);
        }

        info2.setText("Πόντοι συνολικά: " + gg.getPointsOfWords() + ", Στόχος: " + gg.getsuccessPoints());
        info2.setFont(new Font("Courier", Font.ITALIC, 25));
        info2.setForeground(Color.DARK_GRAY);

        b1.setPreferredSize(new Dimension(250, 50));
        b2.setPreferredSize(new Dimension(200, 50));
        b3.setPreferredSize(new Dimension(200, 50));
        b4.setPreferredSize(new Dimension(200, 50));
        b5.setPreferredSize(new Dimension(200, 50));

        bExit.setPreferredSize(new Dimension(250, 50));
        bExit.setBackground(Color.RED);
        bExit.setForeground(Color.white);

        bCheckWord.setPreferredSize(new Dimension(250, 50));
        bCheckWord.setBackground(Color.BLUE);
        bCheckWord.setForeground(Color.white);

        ButtonHandler bh = new ButtonHandler();
        bExit.addActionListener(bh);
        bCheckWord.addActionListener(bh);

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

        jp9.add(bExit);
        jp9.add(bCheckWord);
        jf2.add(jp9);
        jp10.add(info1);
        jf2.add(jp10);

        jp11.add(info2);
        jf2.add(jp11);
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

    class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == bExit) {
                System.exit(0);
            } else if (ae.getSource() == bCheckWord) {
                if (doubleWordValue == true) {
                    WordPoints *= 2;
                }
                game.SearchWord();
            } else if (ae.getSource() == b1) {
                game.RemakeLine(dimension);
            } else if (ae.getSource() == b2) {
                game.RearrangementLine();
            } else if (ae.getSource() == b3) {
                game.RearrangementRow();
            } else if (ae.getSource() == b4) {
                game.Rearrangement(dimension);
            } else if (ae.getSource() == b5) {
                game.Exchange_Letters();
            }
        }
    }
}
