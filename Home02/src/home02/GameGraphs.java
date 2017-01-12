package home02;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameGraphs extends JFrame {

    String tempChar, FileWord = "";
    ArrayList<String> WordsFile = new ArrayList<String>();
    int Points = 0, PointsOfLetter = 0;
    static int WordPoints;
    static int tempWordPoints;
    static int dimension;
    JLabel bReplaceLineResults = new JLabel();
    JLabel bRearrangeLineResults = new JLabel();
    JLabel bRearrangeRowresults = new JLabel();
    JLabel bRearrangeResults = new JLabel();
    JLabel bExchangeLettersResults = new JLabel();
    JLabel options = new JLabel();
    JLabel info2;
    JLabel info1;
    JLabel JMadeWord;
    JLabel JYourPoints;
    JButton bExit = new JButton();
    JButton bRestart = new JButton();
    JButton bCheckWord = new JButton();
    JButton bGameHelp = new JButton();
    JButton bUsers = new JButton();
    JButton b1 = new JButton();
    JButton b2 = new JButton();
    JButton b3 = new JButton();
    JButton b4 = new JButton();
    JButton b5 = new JButton();
    JPanel jp1 = new JPanel();
    JPanel jp2 = new JPanel();
    JPanel jp3 = new JPanel();
    JPanel jp4 = new JPanel();
    JPanel jp5 = new JPanel();
    JPanel jp6 = new JPanel();
    JPanel jp7 = new JPanel();
    JPanel jp8 = new JPanel();
    JPanel jp9 = new JPanel();
    JPanel jp10 = new JPanel();
    JPanel jp11 = new JPanel();
    JPanel jp12 = new JPanel();
    boolean tempcheck = true;
    private static String MadeWord = "";
    Graphics g;
    public boolean readyWindow;
    int LastX = 0, LastY = 0, answer;
    Point LetterPoint;
    StringBuilder sb;
    static int successPoints, successWords = 5;
    int PointsOfWords = 0, NumberOfWords = 1;
    private int x = 5, y = 5;
    Scanner scan;
    static ArrayList<Letter> letterList = new ArrayList<>();
    Container pane = new Container();
    JButton button1, button3, button2;
    JPanel row1, row2;
    JLabel label;
    CardGraphs c;
    Game game = new Game();
    int counter;

    public void setreadyWindow(boolean rW) {
        readyWindow = rW;
    }

    public boolean getreadyWindow() {
        return readyWindow;
    }

    public void setMadeWord(String w) {
        MadeWord = w;
    }

    public String getMadeWord() {
        return MadeWord;
    }

    public void setNumberOfWords(int n) {
        NumberOfWords = n;
    }

    public int getNumberOfWords() {
        return NumberOfWords;
    }

    public void setPointsOfWords(int p) {
        PointsOfWords += p;
    }

    public int getPointsOfWords() {
        return PointsOfWords;
    }

    public void setPointsOfTheWord(int p) {
        WordPoints = p;
    }

    public int getPointsOfTheWord() {
        return WordPoints;
    }

    public void setsuccessPoints(int sp) {
        successPoints = sp;
    }

    public int getsuccessPoints() {
        return successPoints;
    }

    public void setCounter(int c) {
        counter = c;
    }

    public int getCounter() {
        return counter;
    }

    public void setDimension(int d) {
        dimension = d;
    }

    public int getDimension() {
        return dimension;
    }

    public GameGraphs() {
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public void MakeGameGraphs(int dimension, ArrayList<Letter> Shuffled_Chars, boolean ready) {
        //super("Window");
        setDimension(dimension);
        setBackground(Color.lightGray);
        setMinimumSize(new Dimension(1350, 750));
        setSize(dimension * 110 * 2 + 100, dimension * 115);
        setWindow(true, null, g);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setCounter(0);
        if(getreadyWindow()==true)
            WordPoints = 0;
        //setreadyWindow(false);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Letter currentLetter = Shuffled_Chars.get(counter);
                c = new CardGraphs(currentLetter, x, y);
                c.setArrayDimension(dimension);
                getContentPane().add(c);
                currentLetter.setSituation(false);
                letterList.add(currentLetter);
                setVisible(true);
                counter++;
                x += 105;
                System.out.print(currentLetter.getCharacter() + " ");
            }
            System.out.println();
            x = 5;
            y += 105;
        }
        revalidate();
        repaint();
        setCounter(counter);
    }

    public void remakeArrayGraphs(ArrayList<Letter> Shuffled_Chars) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Letter currentLetter = Shuffled_Chars.get(counter);
                c = new CardGraphs(currentLetter, x, y);
                c.setArrayDimension(dimension);
                getContentPane().add(c);
                currentLetter.setSituation(false);
                letterList.add(currentLetter);
                setVisible(true);
                counter++;
                x += 105;
                System.out.print(currentLetter.getCharacter() + " ");
            }
        }
    }

//  
//    @Override
//    public void repaint() {
//        System.out.println("counter: " + counter);
//        if (getreadyWindow() == true) {
//            b1.setBackground(Color.blue);
//            revalidate();
//            System.out.println("22222222222222222");
//        }
//    }
    public void setWindow(boolean stBC, Letter letter, Graphics g) {
        SwingUtilities.updateComponentTreeUI(this);
        info1 = new JLabel();
        info2 = new JLabel();
        JMadeWord = new JLabel();

        if (getCounter() == 0) { // first letter
            //WordPoints = 0;
            setPointsOfTheWord(0);
            setMadeWord("");
            setPointsOfWords(getPointsOfWords());
        } else {
            remove(info2);
            remove(info1);
            remove(JMadeWord);
            info1.setVisible(false);

//            jf.remove(JMadeWord);
//            jf.remove(JYourPoints);
            if (stBC == true && getCounter() != 0) {//remove last letter
                setPointsOfTheWord(getPointsOfTheWord() - letter.getValue());
                sb = new StringBuilder(MadeWord);
                sb.deleteCharAt(MadeWord.length() - 1);
                setMadeWord(sb.toString());
            } else {// (stBC == false && counter != 0) 
                //WordPoints += letter.getValue();
                setPointsOfTheWord(getPointsOfTheWord() + letter.getValue());
                MadeWord += letter.getCharacter();
                setPointsOfWords(WordPoints);
            }
        }
        bExit = new JButton("Διακοπή παιχνιδιού");
        bRestart = new JButton("Επανεκκίνηση παιχνιδιού");
        bCheckWord = new JButton("ΕΛΕΓΧΟΣ ΛΕΞΗΣ");
        bGameHelp = new JButton("Οδηγίες για το παιχνίδι");
        bUsers = new JButton("ABOUT/χρήστες");

        b1.setText("1)Αντικατάσταση γραμμάτων γραμμής");
        b2.setText("2)Αναδιάταξη γραμμής");
        b3.setText("3)Αναδιάταξη στήλης");
        b4.setText("4)Αναδιάταξη γραμμάτων");
        b5.setText("5)Εναλλαγή γραμμάτων");
        JMadeWord = new JLabel();
        JMadeWord.setText("Η λέξη ως τώρα: " + getMadeWord());
        JMadeWord.setForeground(Color.BLUE);
        JMadeWord.setFont(new Font("Courier", Font.BOLD, 40));
        JMadeWord.setBounds(getDimension() * 110 + 100, 10, 600, 40);
        JMadeWord.setVisible(true);
        //JYourPoints.setText("Οι πόντοι της λέξης ως τώρα: " + getPointsOfTheWord());// instead of WordPoints        
        JYourPoints = new JLabel("Οι πόντοι της λέξης ως τώρα: " + getPointsOfTheWord());
        JYourPoints.setForeground(Color.RED);

        //System.out.println(JYourPoints.getText() + " " + JMadeWord.getText());
        JYourPoints.setFont(new Font("Courier", Font.BOLD, 40));
        JYourPoints.setBounds(getDimension() * 110 + 100, 60, 600, 40);
        JYourPoints.setVisible(true);
        options.setText("Πρόσθετες επιλογές:");
        options.setFont(new Font("Courier", Font.BOLD, 22));
        options.setBounds(getDimension() * 110 + 220, 60, 600, 40);
        info1.setText("Τωρινή λέξη: " + getNumberOfWords() + ", στόχος λέξεων: " + successWords);
        info1.setFont(new Font("Courier", Font.ITALIC, 25));
        info1.setForeground(Color.DARK_GRAY);
        info1.setBounds(getDimension() * 110 + 150, 60, 600, 40);

        if (getPointsOfWords() > getsuccessPoints()) {
            JOptionPane.showMessageDialog(null, "Μάζεψες τους απαιτούμενους πόντους, ΝΙΚΗΣΕΣ!");
            System.exit(0);
        }
        info2.setText("Εξασφαλισμένοι πόντοι συνολικά: " + getPointsOfWords() + ", Στόχος: " + getsuccessPoints());
        info2.setFont(new Font("Courier", Font.ITALIC, 25));
        info2.setForeground(Color.DARK_GRAY);
        info2.setBounds(getDimension() * 110 + 100, 30, 600, 40);

        if (getreadyWindow() == true) {
            JOptionPane.showMessageDialog(null,
                    JYourPoints.getText()
                    + " \n " + JMadeWord.getText()
                    + " \n " + info1.getText()
                    + " \n " + info2.getText());
        }
        b1.setBounds(getDimension() * 110 + 200, 110, 250, 40);
        b2.setBounds(getDimension() * 110 + 200, 160, 250, 40);
        b3.setBounds(getDimension() * 110 + 200, 210, 250, 40);
        b4.setBounds(getDimension() * 110 + 200, 260, 250, 40);
        b5.setBounds(getDimension() * 110 + 200, 310, 250, 40);

        bReplaceLineResults.setBounds(getDimension() * 110 + 500, 110, 250, 40);
        bExchangeLettersResults.setBounds(getDimension() * 110 + 500, 160, 250, 40);
        bRearrangeLineResults.setBounds(getDimension() * 110 + 500, 210, 250, 40);
        bRearrangeRowresults.setBounds(getDimension() * 110 + 500, 260, 250, 40);
        bRearrangeResults.setBounds(getDimension() * 110 + 500, 310, 250, 40);

        bRearrangeLineResults.setText("/" + 3);
        bExchangeLettersResults.setText("/" + 3);
        bReplaceLineResults.setText("/" + 3);
        bRearrangeResults.setText("/" + 3);
        bRearrangeRowresults.setText("/" + 3);

        bExit.setForeground(Color.white);
        bCheckWord.setForeground(Color.white);
        bUsers.setForeground(Color.white);
        bGameHelp.setForeground(Color.white);
        bRearrangeResults.setForeground(Color.black);
        bRearrangeRowresults.setForeground(Color.black);
        bReplaceLineResults.setForeground(Color.black);
        bRearrangeLineResults.setForeground(Color.black);
        bExchangeLettersResults.setForeground(Color.black);

        bUsers.setBackground(Color.GRAY);
        bGameHelp.setBackground(Color.GRAY);
        bCheckWord.setBackground(Color.BLUE);
        bExit.setBackground(Color.RED);

        bExit.setBounds(getDimension() * 110 + 100, 360, 200, 40);
        bUsers.setBounds(getDimension() * 110 + 100, 410, 200, 40);
        bGameHelp.setBounds(getDimension() * 110 + 400, 360, 200, 40);
        bCheckWord.setBounds(getDimension() * 110 + 400, 410, 200, 40);

        ButtonHandler bh = new ButtonHandler();

        bExit.addActionListener(bh);
        bCheckWord.addActionListener(bh);
        bUsers.addActionListener(bh);
        bGameHelp.addActionListener(bh);
        b1.addActionListener(bh);
        b2.addActionListener(bh);
        b3.addActionListener(bh);
        b4.addActionListener(bh);
        b5.addActionListener(bh);
        bRestart.addActionListener(bh);
//        add(JMadeWord);
//        add(JYourPoints);
//        add(info1);
//        add(info2);
        add(options);
        add(bRearrangeLineResults);
        add(bRearrangeResults);
        add(bRearrangeRowresults);
        add(bReplaceLineResults);
        add(bExchangeLettersResults);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(bCheckWord);
        add(bGameHelp);
        add(bExit);
        add(bUsers);
        this.revalidate();
        //this.repaint();
        SwingUtilities.updateComponentTreeUI(this);
    }

//    @Override
//    public void paint(Graphics g) {
//        System.out.println("dddddd");
//    }    
    public void Instructions() {
        FileWord = "";
        try {
            scan = new Scanner(new File("src/home02/Instructions.txt"));
            while (scan.hasNext()) {
                FileWord += scan.next();
                System.out.println(FileWord);
            }
            JOptionPane.showMessageDialog(null, FileWord, "File infos", JOptionPane.INFORMATION_MESSAGE);
            scan.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Το αρχείο με τις οδηγίες δε βρέθηκε", "File error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ReadUsersFile() {
        FileWord = "";
        try {
            scan = new Scanner(new File("src/home02/About.txt"));
            while (scan.hasNext()) {
                WordsFile.add(scan.nextLine());
                // FileWord += scan.next();
                //System.out.println(FileWord);
            }
            for (int i = 0; i < WordsFile.size(); i++) {
                FileWord += WordsFile.get(i) + " ";
            }
            JOptionPane.showMessageDialog(null, FileWord, "File infos", JOptionPane.INFORMATION_MESSAGE);
            scan.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Το αρχείο με τους χρήστες δε βρέθηκε", "File error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void CheckWord() {
        if (MadeWord.length() > 2) {
            JOptionPane.showMessageDialog(null, "Θα ελέγξουμε τη λέξη " + MadeWord);
            if (c.getDoubleWordValue() == true) {
                WordPoints *= 2;
            }
            if (game.SearchWord(MadeWord) == true) {
                game.ReplaceWords();
                //card.ClearAllLetters(gp);// everytime
                setMadeWord("");
                setPointsOfWords(WordPoints);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Δεν μπορείς να ελέγξεις τη λέξη, πρέπει το λιγότερο 3 γράμματα να διαλέξεις ", "File error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Exchange_Letters(int dimension) {
        boolean statt = true;
        Point tempP, tempP2, p1 = null, p2 = null;
        int x1 = 0, x2 = 0, y2 = 0, y1 = 0;
        Letter valL = null, valL2 = null;
        String InputChoiceLine = "", InputChoiceRow = "";
        while (statt == true) {
            do {
                InputChoiceLine = JOptionPane.showInputDialog(null, "Πρώτο Γράμμα: γραμμή: ");
                InputChoiceRow = JOptionPane.showInputDialog(null, "Πρώτο Γράμμα: στήλη: ");
            } while (Character.isDigit(InputChoiceLine.charAt(0)) != true);
            y1 = Integer.parseInt(InputChoiceLine) - 1;
            x1 = Integer.parseInt(InputChoiceRow) - 1;
            if (x1 < dimension && x1 >= 0 && y1 < dimension && y1 >= 0) {
                do {
                    InputChoiceLine = JOptionPane.showInputDialog(null, "Δεύτερο Γράμμα: γραμμή: ");
                    InputChoiceRow = JOptionPane.showInputDialog(null, "Δεύτερο Γράμμα: στήλη: ");
                } while (Character.isDigit(InputChoiceLine.charAt(0)) != true);
                y2 = Integer.parseInt(InputChoiceLine) - 1;
                x2 = Integer.parseInt(InputChoiceRow) - 1;
                if (x2 < dimension && x2 >= 0 && y2 < dimension && y2 >= 0) {
                    x1 = x1 * 105 + 5;
                    y1 = y1 * 105 + 5;
                    x2 = x2 * 105 + 5;
                    y2 = y2 * 105 + 5;
                    p1 = new Point(x1, y1);
                    p2 = new Point(x2, y2);
                    if (p1.x == p2.x && p1.y == p2.y) {
                        JOptionPane.showMessageDialog(null, "Επέλεξες το ίδιο γράμμα");
                    } else {
                        statt = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Λάθος συντεταγμένες για το γράμμα 2 ξανά και τα δύο");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Λάθος συντεταγμένες για το γράμμα 1");
            }
        }
        //αν και οι δύο θέσεις για τα γράμματα είναι όντως στον πίνακα, και θετικές
        if (statt == false) {
            for (Map.Entry<Point, Letter> entry : CardGraphs.LettersMap.entrySet()) {
                tempP = entry.getKey();
                valL = entry.getValue();
                if (p1.x == tempP.x && p1.y == tempP.y) {
                    for (Map.Entry<Point, Letter> entry2 : CardGraphs.LettersMap.entrySet()) {
                        tempP2 = entry2.getKey();
                        valL2 = entry2.getValue();
                        if (p2.x == tempP2.x && p2.y == tempP2.y) {
                            for (int i = 0; i < letterList.size(); i++) {
                                if (letterList.get(i).getPoint() == tempP) {
                                    for (int j = 0; j < letterList.size(); j++) {
                                        if (letterList.get(j).getPoint() == tempP2) {
                                            Collections.swap(ManageList.Shuffled_Chars, i, j);
                                        }
                                    }
                                }
//                                tempP.x = p1.x;
//                                p1 = p2;
//                                p2 = tempP;
//                                System.out.println(p1 + " " + p2);
//                                CardGraphs.DrawLetter(g, valL, p1.x, p1.y, true);
//                                CardGraphs.DrawLetter(g, valL2, p2.x, p2.y, true);
                            }
                        }
                    }
//                    if ((Pkey.x == tempPoint.x) && (Pkey.y == tempPoint.y)) {
//                        tempMap.put(Pkey, val);
//                    }
                }
            }
            MakeGameGraphs(dimension, ManageList.Shuffled_Chars, true);
        }
    }

    class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == bExit) {
                tempWordPoints = WordPoints;
                System.exit(0);
            } else if (ae.getSource() == bCheckWord) {
                if (tempcheck == true) {
                    CheckWord();
                }
                tempcheck = false;
            } else if (ae.getSource() == b1) {                 
                    game.User_Menu(2, getGraphics());                    
                    dispose();
                    setPointsOfTheWord(tempWordPoints);                    
                    //game.RemakeLine(dimension);
                
            } else if (ae.getSource() == b2) {
                
                    game.User_Menu(5, getGraphics());
                    dispose();
                    setPointsOfTheWord(tempWordPoints);                    
                
            } else if (ae.getSource() == b3) {
               
                    game.User_Menu(4, getGraphics());
                    dispose();
                    setPointsOfTheWord(tempWordPoints);                    
                
            } else if (ae.getSource() == b4) {
                
                    c.YellowLetterPoints.clear();
                    game.User_Menu(3, getGraphics());
                    dispose();
                    setPointsOfTheWord(tempWordPoints);                    
                
            } else if (ae.getSource() == b5) {
                
                    game.User_Menu(1, getGraphics());
                    dispose();
                    setPointsOfTheWord(tempWordPoints);                    
                
            } else if (ae.getSource() == bUsers) {
                if (JOptionPane.showConfirmDialog(null, "Επέλεξες να δεις τους χρήστες της εργασίας") == 0) {
                    ReadUsersFile();
                }
            } else if (ae.getSource() == bGameHelp) {
                if (JOptionPane.showConfirmDialog(null, "Οδηγίες για το πως παίζεται το παιχνίδι") == 0) {
                    Instructions();
                }
            }
        }
    }
}
