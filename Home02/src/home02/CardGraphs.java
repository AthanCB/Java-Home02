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

/*Σε αυτή την κλάση δημιουργούμε τα γραφικά του πίνακα με τις κάρτες και διαχειριζόμαστε τις επιλογές του χρήστη 
ώστε να γίνουν οι απαραίτητες χρωματικές και λειτουργικές αλλαγές στα γραφικά*/
public class CardGraphs extends JComponent implements MouseListener {
    
    Game game = new Game();
    GameGraphs gg = new GameGraphs();
    String tempChar;
    private Letter Cletter;
    int Points = 0, counter = 1, PointsOfLetter = 0;
    static int dimension;
    static ArrayList<Point> YellowLetterPoints = new ArrayList<>();
    static HashMap<Point, Integer> valuesMap = new HashMap<>();
    ArrayList<Point> chosenLettersList = new ArrayList<>();
    static HashMap<Point, Letter> LettersMap = new HashMap<>();
    private final int rectLength = 100;
    private Polygon rect, rect2;
    private boolean doubleWordValue = false, statBlackColor = false, begin = false;
    private char Character;
    private int Value;
    private Color ColorC;
    int LastX = 0, LastY = 0;
    boolean STAT;
    private int xCoord, yCoord;
    Point LetterPoint;
    StringBuilder sb;
    /*Setters και Getters για τις private ιδιότητες της κλάσης*/
    public boolean getStatBlackColor() {
        return statBlackColor;
    }

    public void setStatBlackColor(boolean statBlackColor) {
        this.statBlackColor = statBlackColor;
    }

    public boolean getDoubleWordValue() {
        return doubleWordValue;
    }

    public void setDoubleWordValue(boolean doubleWordValue) {
        this.doubleWordValue = doubleWordValue;
    }

    public char getCharacter() {
        return Character;
    }

    public void setCharacter(char character) {
        Character = character;
    }
 //Άδειος constructor 
    public CardGraphs() {
    }
    /*Ο Constructor μας θέτει τις τιμές στα γραφικά και ζωγραφίζει τα τετράγωνα στις παρακάτω συντενταγμένες
    που μεταβάλλονται ανάλογα κάθε φορά που δημιουργεί ενα γράμμα*/
    public CardGraphs(Letter letter, int x, int y) {
        this.Cletter = letter;
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
        LettersMap.put(LetterPoint, letter);
        //lettersMap.put(LetterPoint, Character);
        valuesMap.put(LetterPoint, Value);
        //gg.setCounter(counter++);
        addMouseListener(this);
        //System.out.println(LetterPoint);
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
    public void paint(Graphics g) { /*Η paint μας γεμίζει το χρώμα του τετραγώνου και απο πάνω το γράμμα με την
        αξία του στα πλάγια.*/
        super.paintComponent(g);
        String LetterS = "" + Character;
        String valueS = "" + Value;
        g.setColor(ColorC);
        g.fillRect(xCoord, yCoord, rectLength, rectLength);
        for (int i = 0; i < YellowLetterPoints.size(); i++) { /*Αν τα γράμματα περιέχονται μέσα στην λίστα με 
            τα επιλεγμένα τότε τα ζωγραφίζει κίτρινα.*/
            if (Cletter.getPoint().equals(YellowLetterPoints.get(i))) {
                g.setColor(Color.yellow);
                g.fillRect(xCoord, yCoord, rectLength, rectLength);
                break;
            }
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier", Font.BOLD, 71));
        g.drawString(LetterS, 25 + xCoord, 75 + yCoord);

        g.setFont(new Font("Courier", Font.BOLD, 17));
        g.drawString(valueS, 80 + xCoord, 80 + yCoord);
        g.drawPolygon(rect);
    }

    @Override
    public void repaint() {
        revalidate();
    }
    /*Όταν ο χρήστης επιλέγει οτιδήποτε πάνω στον πίνακα γραμμάτων τότε καλείται η Myrepaint η οποία κάνει όλες
    τις αλλάγες πάνω στον πίνακα και την αλληλεπίδραση του χρήστη.*/
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
                                if (LetterChecks(g, letterFromList, X, Y) == true) {
                                    chosenLettersList.add(new Point(X, Y));
                                } else {
                                    System.out.println("Δε προστέθηκε κάτι τώρα");
                                }
                            }
                        }
                    }
                    if (me.getButton() == MouseEvent.BUTTON3) {
                        int answer = JOptionPane.showConfirmDialog(null, "Θες να ακυρώσεις τη τωρινή λέξη");
                        if (answer == 0) {
                            gg.WordPoints = 0;
                            gg.setMadeWord("");
                            ClearAllLetters(g);
                            chosenLettersList.clear();
                        }
                    }
                }
                X += 105;
            }
            X = 5;
            Y += 105;
        }
        revalidate();
        repaint();
    }
    /*Αυτή η συνάρτηση χρησιμοποιείται όταν ο χρήστης θέλει να ξεκινήσει την τωρινή λέξη απο την αρχή 
    επομένως δηλαδή έχει πατήσει το δεξί κλικ.*/
    public void ClearAllLetters(Graphics g) {
        int X = 5, Y = 5;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Point tP = new Point(X, Y);
                for (Letter letterFromList : GameGraphs.letterList) {
                    if ((letterFromList.getPoint().x == tP.x) && (letterFromList.getPoint().y == tP.y)) {
                        if (letterFromList.isSituation() == true) { // if used
                            letterFromList.setSituation(false);
                            setStatBlackColor(true);
                            g.setColor(letterFromList.getColor());
                            g.fillRect(X, Y, 100, 100);

                            g.setColor(Color.BLACK);
                            g.setFont(new Font("Courier", Font.BOLD, 71));
                            g.drawString("" + letterFromList.getCharacter(), 25 + X, 75 + Y);
                            g.setFont(new Font("Courier", Font.BOLD, 12));
                            g.drawString("" + letterFromList.getValue(), 80 + X, 80 + Y);
                        }
                    }
                }
                X += 105;
            }
            X = 5;
            Y += 105;
        }
        JOptionPane.showMessageDialog(null, "Ξανά απο την αρχή λοιπόν.");
        X = 5;
        Y = 5;
        LastX = 0;
        LastY = 0;
        gg.setCounter(0);
        gg.setWindow(true, null, g);
    }
    /*Η LetterChecks κάνει όλους τους ελέγχους για τα γράμματα ανάλογα με την επιλογή του χρήστη.*/
    public boolean LetterChecks(Graphics g, Letter l, int X, int Y) {
        if (LastX == X && LastY == Y) {//last letter
            int ch = JOptionPane.showConfirmDialog(null, "Θες να ακυρώσεις το τελευταίο γράμμα");
            if (ch == 0) {
                l.setSituation(false);//Ξανακάνει το γράμμα ξανά διαθέσιμο
                setStatBlackColor(true); 
                STAT = getStatBlackColor();
                DrawLetter(g, l, X, Y, STAT); //Καλεί την DrawLetter ωστε να ξαναζωγραφίσει το γράμμα
                gg.setWindow(STAT, l, g);
                chosenLettersList.remove(chosenLettersList.size() - 1);
                if (chosenLettersList.size() > 0) { 
                    LastX = chosenLettersList.get(chosenLettersList.size() - 1).x;
                    LastY = chosenLettersList.get(chosenLettersList.size() - 1).y;
                } else { //chosenLettersList.size() == 0
                    JOptionPane.showMessageDialog(null, "Κανένα διαθέσιμο γράμμα πλέον");
                    //chosenLettersList.clear(); //for safety
                    LastX = 0;
                    LastY = 0;
                }
                return false;
            }
        } else if (l.isSituation() == false) { //Αν το γράμμα είναι διαθέσιμο 
            if ((LastX == 0 && LastY == 0) || (!(Math.abs(LastX - X) > 105) && !(Math.abs(LastY - Y) > 105))) {
                if (l.getCharacter() == '?') { //Αν είναι balader τότε να ζητάει απο τον χρήστη το γράμμα
                    tempChar = JOptionPane.showInputDialog("Επέλεξε εσύ το γράμμα επιθυμίας σου");
                    tempChar.toUpperCase(); 
                    if (tempChar.length() == 1) { //Έλεγχος για το αν έδωσε 1 γράμμα μόνο 
                        l.setCharacter(tempChar.charAt(0));
                        while (ManageList.GivenValue(l.getCharacter()) == 100) {
                            tempChar = JOptionPane.showInputDialog("Επέλεξε εσύ το γράμμα επιθυμίας σου ΣΩΣΤΑ");                            
                            tempChar.toUpperCase();
                            l.setCharacter(tempChar.charAt(0));
                        }
                        l.setValue(ManageList.GivenValue(l.getCharacter()));
                    } else {
                        tempChar = JOptionPane.showInputDialog("Επέλεξε ξανά το γράμμα επιθυμίας σου");
                    }
                }
                LastX = X;
                LastY = Y;
                if (l.getColor() == Color.blue) { //Διπλασιάζει τους πόντους όλης της λέξης αν επιλέξει μπλε γράμμα
                    setDoubleWordValue(true);
                    JOptionPane.showMessageDialog(null, "Γράμμα για διπλασιαμός πόντων στη λέξη");
                }
                System.out.println("Γράμμα με συντεταγμένες: " + l.getPoint() + ", " + l.getCharacter() + ", " + l.getValue());
                l.setSituation(true);
                YellowLetterPoints.add(l.getPoint());
                counter++;
                gg.setCounter(counter);
                setStatBlackColor(false);
                STAT = getStatBlackColor();
                DrawLetter(g, l, X, Y, STAT);
                gg.setWindow(STAT, l, g);
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
/*Η DrawLetter ζωγραφίζει το γράμμα l στις συγκεκριμένες συντεταγμένες x,y 
    και καλείται κάθε φορα που ζωγραφίζουμε ένα γράμμα*/
    public static void DrawLetter(Graphics g, Letter l, int X, int Y, boolean stat) {
        String LetterPoints = "" + l.getValue();
        String LetterChar = "" + l.getCharacter();
        if (stat == true) {
            g.setColor(l.getColor());
            g.fillRect(X, Y, 100, 100);
        } else {
            g.setColor(Color.yellow);
            g.fillRect(X, Y, 100, 100);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier", Font.BOLD, 71));
        g.drawString(LetterChar, 25 + X, 75 + Y);

        g.setFont(new Font("Courier", Font.BOLD, 12));
        g.drawString(LetterPoints, 80 + X, 80 + Y);
    }
 /*mouseClicked για κάθε φορά που ο χρήστης κάνει click σε κάρτα να ξανακαλεί την Myrepaint ώστε να ζωγραφίζει
    απο πάνω τα χρώματα και να κάνει τις απαραίτητες λειτουργίες για την σωστή εκτέλεση του προγράμματος και την 
    ομαλή λειτουργία του.*/
    @Override
    public void mouseClicked(MouseEvent me) {
        gg.setreadyWindow(true);
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
