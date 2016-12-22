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

    String tempChar;
    int Points = 0, WordPoints = 0, PointsOfLetter = 0;
    static int dimension;
    static HashMap<Point, Character> lettersMap = new HashMap<>();
    static HashMap<Point, Integer> valuesMap = new HashMap<>();
    ArrayList<Point> chosenLettersList = new ArrayList<>();
    FlowLayout fl = new FlowLayout();
    GridLayout gl = new GridLayout(13, 1);
    JButton bExit = new JButton("Διακοπή παιχνιδιού");
    JButton bRestart = new JButton("Επανεκκίνηση παιχνιδιού");
    JButton bCheckWord = new JButton("ΕΛΕΓΧΟΣ ΛΕΞΗΣ");
    JButton bGameHelp = new JButton("Οδηγίες για το παιχνίδι");
    JButton bUsers = new JButton("Πληροφορίες για τους χρήστες");
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
    JPanel jp12 = new JPanel();
    JPanel jp3 = new JPanel();
    JLabel options = new JLabel();
    JLabel info2 = new JLabel();
    JLabel info1 = new JLabel();
    JFrame jf2 = new JFrame();
    JLabel JMadeWord = new JLabel();// for row1
    JLabel JYourPoints = new JLabel();
    private static String MadeWord = "";
    Graphics gp;
    private boolean doubleWordValue = false, statBlackColor = false;
    int LastX = 0, LastY = 0;
    Point LetterPoint;
    StringBuilder sb;

    static int successPoints, successWords;
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
    Game game;
    static int counter;

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

    public void setsuccessWords(int sw) {
        successWords = sw;
    }

    public int getsuccessWords() {
        return successWords;
    }

    public GameGraphs() {
    }

    public GameGraphs(int dimension, ArrayList<Letter> Shuffled_Chars) {
        super("Window");
        card = new CardGraphs();
        card.setArrayDimension(dimension);
        jf.setSize(dimension * 110, dimension * 115);
        counter = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Letter currentLetter = Shuffled_Chars.get(counter);
                Color c = currentLetter.getColor();
                //jf.getContentPane().add(new Card(currentLetter.getCharacter(), Shuffled_Chars.get(counter).getValue(), c, x, y));
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
    }

    public void SecondWindow(Letter letter) {
        jf2.setLayout(gl);
        jf2.setSize(800, 1000);
        jf2.setLocation(1000, 5);
        jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf2.setVisible(true);
        if (GameGraphs.counter == 0) {
            WordPoints = 0;
            MadeWord = "";
            setPointsOfWords(0);
        } else if (statBlackColor == true) {
            WordPoints -= letter.getValue();
            sb = new StringBuilder(MadeWord);
            sb.deleteCharAt(MadeWord.length() - 1);
            MadeWord = sb.toString();
        } else {
            WordPoints += letter.getValue();
            MadeWord += letter.getCharacter();
            //gg.setPointsOfWords(WordPoints);
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

        info1.setText("Τωρινή λέξη: " + getNumberOfWords() + ", στόχος λέξεων: " + getsuccessWords());
        info1.setFont(new Font("Courier", Font.ITALIC, 25));
        info1.setForeground(Color.DARK_GRAY);

        if (getPointsOfWords() > getsuccessPoints()) {
            JOptionPane.showMessageDialog(null, "Μάζεψες τους απαιτούμενους πόντους, ΝΙΚΗΣΕΣ!");
            System.exit(0);
        }

        info2.setText("Πόντοι συνολικά: " + getPointsOfWords() + ", Στόχος: " + getsuccessPoints());
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

//        jp10.add(info1);
//        jf2.add(jp10);
//
//        jp11.add(info2);
//        jf2.add(jp11);
    }

    public void ReadUsersFile() {
        try {
            scan = new Scanner(new File("About.txt"));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Το αρχείο με τους χρήστες δε βρέθηκε", "File error", JOptionPane.ERROR_MESSAGE);
        }
        String FileWord = "";
        while (scan.hasNext()) {
            FileWord += scan.next();
            System.out.println(FileWord);
        }
        scan.close();
        JOptionPane.showMessageDialog(null, FileWord, "File infos", JOptionPane.INFORMATION_MESSAGE);
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
                setPointsOfWords(WordPoints);
                game.SearchWord(MadeWord);
                MadeWord = "";
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
            } else if (ae.getSource() == bUsers) {
                System.out.println("ReadUsersFile");
                ReadUsersFile();
            }
        }
    }
}
