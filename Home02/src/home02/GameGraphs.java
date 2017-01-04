package home02;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameGraphs extends JFrame {

    String tempChar, FileWord = "";
    int Points = 0, WordPoints = 0, PointsOfLetter = 0;
    static int dimension;
    FlowLayout fl = new FlowLayout();
    GridLayout gl = new GridLayout(13, 1);
//    JPanel panels[] = new JPanel[20];
//    JLabel labels[] = new JLabel[5];
//    JButton buttons[] = new JButton[10];
    JButton bExit = new JButton("Διακοπή παιχνιδιού");
    JButton bRestart = new JButton("Επανεκκίνηση παιχνιδιού");
    JButton bCheckWord = new JButton("ΕΛΕΓΧΟΣ ΛΕΞΗΣ");
    JButton bGameHelp = new JButton("Οδηγίες για το παιχνίδι");
    JButton bUsers = new JButton("ABOUT/χρήστες");
    JButton b1 = new JButton("1)Αντικατάσταση γραμμάτων γραμμής");
    JButton b2 = new JButton("2)Αναδιάταξη γραμμής");
    JButton b3 = new JButton("3)Αναδιάταξη στήλης");
    JButton b4 = new JButton("4)Αναδιάταξη γραμμάτων");
    JButton b5 = new JButton("5)Εναλλαγή γραμμάτων");
    JFrame jf2 = new JFrame();
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
    JLabel options = new JLabel();
    JLabel info2 = new JLabel();
    JLabel info1 = new JLabel();
    JLabel JMadeWord = new JLabel();// for row1
    JLabel JYourPoints = new JLabel();
    boolean tempcheck = true;
    private static String MadeWord = "";
    Graphics gp;
    int LastX = 0, LastY = 0, answer;
    Point LetterPoint;
    StringBuilder sb;
    static int successPoints, successWords = 5;
    int PointsOfWords = 0, NumberOfWords = 1;
    private int x = 5, y = 5;
    Scanner scan;
    static ArrayList<Letter> letterList = new ArrayList<>();
    Container pane = new Container();
    JFrame jf = new JFrame();
    JButton button1, button3, button2;
    JPanel row1, row2;
    JLabel label;
    CardGraphs card;
    Game game = new Game();
    int counter;
    //counter = 0;

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

    public GameGraphs() {
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public GameGraphs(int dimension, ArrayList<Letter> Shuffled_Chars) {
        super("Window");
        card = new CardGraphs();
        card.setArrayDimension(dimension);
        jf.setSize(dimension * 110, dimension * 115);
        setCounter(0);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Letter currentLetter = Shuffled_Chars.get(counter);
                Color c = currentLetter.getColor();
                jf.getContentPane().add(new CardGraphs(currentLetter, x, y));
                currentLetter.setSituation(false);
                letterList.add(currentLetter);
                jf.setVisible(true);
                counter++;
                x += 105;
            }
            x = 5;
            y += 105;
        }
        setCounter(counter);
    }

    public void manageWindow(Letter letter, boolean stBC) {
        jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf2.setVisible(true);
        jf2.setLayout(gl);
        jf2.setSize(800, 1000);
        jf2.setLocation(1000, 5);
        if (getCounter() == 0) { // first letter
            //System.out.println("begin");
            WordPoints = 0;
            setMadeWord("");
            setPointsOfWords(0);
        } else {
            if (stBC == true && getCounter() != 0) {//remove last letter
                //System.out.println("remove last letter");
                WordPoints -= letter.getValue();
                sb = new StringBuilder(MadeWord);
                sb.deleteCharAt(MadeWord.length() - 1);
                setMadeWord(sb.toString());
            } else {// (stBC == false && counter != 0) 
                //System.out.println("just a letter");
                WordPoints += letter.getValue();
                MadeWord += letter.getCharacter();
                //setPointsOfWords(WordPoints);
            }
        }
        JMadeWord.setVisible(true);
        JMadeWord.setText("Η λέξη ως τώρα: " + getMadeWord());
        JMadeWord.setForeground(Color.BLUE);
        JMadeWord.setFont(new Font("Courier", Font.BOLD, 40));

        JYourPoints.setVisible(true);
        JYourPoints.setText("Οι πόντοι της λέξης ως τώρα: " + WordPoints);
        JYourPoints.setForeground(Color.RED);
        JYourPoints.setFont(new Font("Courier", Font.BOLD, 40));

        options.setText("Πρόσθετες επιλογές:");
        options.setFont(new Font("Courier", Font.BOLD, 22));

        info1.setText("Τωρινή λέξη: " + getNumberOfWords() + ", στόχος λέξεων: " + successWords);
        info1.setFont(new Font("Courier", Font.ITALIC, 25));
        info1.setForeground(Color.DARK_GRAY);

        if (getPointsOfWords() > getsuccessPoints()) {
            JOptionPane.showMessageDialog(null, "Μάζεψες τους απαιτούμενους πόντους, ΝΙΚΗΣΕΣ!");
            System.exit(0);
        }

        info2.setText("Εξασφαλισμένοι πόντοι συνολικά: " + getPointsOfWords() + ", Στόχος: " + getsuccessPoints());
        info2.setFont(new Font("Courier", Font.ITALIC, 25));
        info2.setForeground(Color.DARK_GRAY);

        b1.setPreferredSize(new Dimension(250, 50));
        b2.setPreferredSize(new Dimension(200, 50));
        b3.setPreferredSize(new Dimension(200, 50));
        b4.setPreferredSize(new Dimension(200, 50));
        b5.setPreferredSize(new Dimension(200, 50));

        bExit.setPreferredSize(new Dimension(150, 50));
        bExit.setBackground(Color.RED);
        bExit.setForeground(Color.white);

        bGameHelp.setPreferredSize(new Dimension(200, 50));
        bGameHelp.setBackground(Color.GRAY);
        bGameHelp.setForeground(Color.white);

        bUsers.setPreferredSize(new Dimension(220, 50));
        bUsers.setBackground(Color.GRAY);
        bUsers.setForeground(Color.white);

        bCheckWord.setPreferredSize(new Dimension(150, 50));
        bCheckWord.setBackground(Color.BLUE);
        bCheckWord.setForeground(Color.white);

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

        jp1.add(JMadeWord);
        jf2.add(jp1);

        jp2.add(JYourPoints);
        jf2.add(jp2);

        jp10.add(info1);
        jf2.add(jp10);

        jp11.add(info2);
        jf2.add(jp11);

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

        jp9.add(bCheckWord);
        jf2.add(jp9);

        jp12.add(bGameHelp);
        jp12.add(bExit);
        jp12.add(bUsers);
        jf2.add(jp12);

        //for( JButton buttons : )
    }

    public void Instructions() {
        FileWord = "";
        try {
            scan = new Scanner(new File("Instructions.txt"));
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
            scan = new Scanner(new File("About.txt"));
            while (scan.hasNext()) {
                FileWord += scan.next();
                System.out.println(FileWord);
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
//                if (card.getDoubleWordValue() == true) {
//                    WordPoints *= 2;
//                }
            if (game.SearchWord(MadeWord) == true) {
                game.ReplaceWords();
                card.ClearAllLetters(gp);// everytime
                setMadeWord("");
                setPointsOfWords(WordPoints);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Δεν μπορείς να ελέγξεις τη λέξη, πρέπει το λιγότερο 3 γράμματα να διαλέξεις ", "File error", JOptionPane.ERROR_MESSAGE);
        }
    }

    class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == bExit) {
                System.exit(0);
            } else if (ae.getSource() == bCheckWord) {
                if (tempcheck == true) {
                    CheckWord();
                }
                tempcheck = false;
            } else if (ae.getSource() == b1) {
                if (JOptionPane.showConfirmDialog(null, "Επέλεξε να αντικατασήσεις μια σειρά") == 0) {
                    game.User_Menu(2);
                    //game.RemakeLine(dimension);
                }
            } else if (ae.getSource() == b2) {
                if (JOptionPane.showConfirmDialog(null, "Επέλεξες να ανακατέψεις μια σειρά") == 0) {
                    game.User_Menu(5);
                    //game.RearrangementLine();
                }
            } else if (ae.getSource() == b3) {
                if (JOptionPane.showConfirmDialog(null, "Επέλεξες να ανακατέψεις μια στήλη") == 0) {
                    game.User_Menu(4);
                    //game.RearrangementRow();
                }
            } else if (ae.getSource() == b4) {
                if (JOptionPane.showConfirmDialog(null, "Επέλεξες να ανακατέψεις όλα τα γράμματα") == 0) {
                    game.User_Menu(3);
                    //game.Rearrangement(dimension);
                }
            } else if (ae.getSource() == b5) {
                if (JOptionPane.showConfirmDialog(null, "Επέλεξες να ανταλλάξεις δύο γράμματα μεταξύ τους") == 0) {
                    game.User_Menu(1);
                    //game.Exchange_Letters();
                }
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
