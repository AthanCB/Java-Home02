package home02;
//icsd14134 Bonis Athanasios - icsd11039 Dimopoulos Goergios

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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// σε αυτη τη κλασση χτιζουμε τα γραφικα του παραθυρου ουσιαστικα
// συν οτι εδω εχουμε τις βοηθητικες λειτουργιες για τα γραμματα μεσω των κουμπιων
public class GameGraphs extends JFrame {

    String tempChar, FileWord = "";

    // λιστα για τις λεξεις των αρχειων με τους χρηστες και τιςς οδηγιες
    ArrayList<String> WordsFile = new ArrayList<String>();
    int PointsOfLetter = 0;//ποντοι του γραμματος
    static int WordPoints;//ποντοι για τη λεξη
    static int dimension;//διασταση του πινακα

    //labels με τις πληροφοριες για τη λεξη τους ποντους της, τους συνολικους ποντους κλπ
    JLabel options = new JLabel();
    JLabel info2;
    JLabel info1;
    JLabel JMadeWord;
    JLabel JYourPoints;
    //αναφορα και δημιουργια των κουμπιων για τις βοηθητικες επιλογες για τον πινακα γραμματων
    JButton bExit = new JButton();
    JButton bRestart = new JButton();
    JButton bCheckWord = new JButton();
    JButton bGameHelp = new JButton();
    JButton bUsers = new JButton();
    JButton bWordsFile = new JButton();
    JButton b1 = new JButton();
    JButton b2 = new JButton();
    JButton b3 = new JButton();
    JButton b4 = new JButton();
    JButton b5 = new JButton();
    boolean tempcheck = true;
    private static String MadeWord = "";//η λεξη που φτιαχνει ο χρηστη
    Graphics g; //χρηση γραφικων για μια συναρτηση παρακατω που τα χρειαζεται, η setWindow δηλαδη
    private boolean readyWindow; //boolean, αν το παραθυρο φτιαχτει 
    int LastX = 0, LastY = 0; //συντεταγμενες για το τελευταιο γραμμα της λεξηες, για ελεγχο γειτνιασης
    //Point LetterPoint;
    StringBuilder sb; // για τη καλυτερη διαχειριση της λεξης οταν σε αυτη μειωνουμε το τελευταιο γραμμα
    static int successPoints, successWords = 5; //απο μονοι μας επιλεγουμε οτι οταν βρει ο χρηστης 5 λεξεις να μη παει για αλλη
    static int PointsOfWords = 0, NumberOfWords = 1; //αρχικοποιηση των ποντων της λεξης και του αριθμου των λεξεων
    private int x = 5, y = 5; //αρχικοποιηση στις συντεταγμενες για τα γραμματα
    static ArrayList<Letter> letterList = new ArrayList<>(); //λιστα με τα γραμματα που εχει ο πινακας, εχει τα ιδια γραμματα με την shuffle_chars λιστα 
    Scanner scan;//για την ευρεση του αρχειου
//    JPanel row1, row2;
    CardGraphs c; // αντικειμενου τυπου CardGraphs για την ευχερεια των μεθοδων του
    Game game = new Game();// παρομοιως δημιουργουμε και αντικειμενο τυπου Game
    int counter;//μετρητης για τα γραμματα του πινακα

    //παρακατω εχουμε καποιους setters & getters σε καποιες μεταβλητες για την πιο ευκολη προσβαση και διαχειριση τους απο αλλες κλασεις και οχι μονο
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

    //empty constructor
    public GameGraphs() {
    }

    //μεθοδος που δημιουργει το παραθυρο αναλογως με το μεγεθος του πινακα γραμματων που θελουμε
    // και συμπληρωνεται με τα γραμματα που δεχεται απο την λιστα που χει σαν παραμετρο
    public void MakeGameGraphs(int dimension, ArrayList<Letter> Shuffled_Chars) {
        setDimension(dimension);//οριζουμε διασταση στο frame
        setBackground(Color.lightGray);// φοντο χρωματος παραθυρου
        setMinimumSize(new Dimension(1350, 750));//θετουμε ελαχιστο μεγεθος παραθυρου
        setSize(dimension * 110 * 2 + 100, dimension * 115);//οριζουμε μεγεθος στο παραθυρο αναλογως με το μεγεθος του πινακα γραμματων
        setWindow(true, null, g);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //αν παταμε το Χ να τερματιζεται το προγραμμα μας με ασφαλεια
        setResizable(false);//να μην αλλαζει το μεγεθος παραθυρου χειροκινητα, για ασφαλεια
        setCounter(0);//θετουμε τον μετρητη γραμματων ισο με 0 γιατι ξεκιναμε απο το πρωτα γραμμα στη σχεδιαση του πινακα στο παραθυρο
        setreadyWindow(true); // αν ανοιξε το παραθυρο για πρωτη φορα αλλαζει η boolean για μετεπειτα χρησιμοποιηση της boolean
        //τρεχει διπλη for, οσο ειναι συνολικα τα γραμματα του πινακα
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Letter currentLetter = Shuffled_Chars.get(counter);//παιρνουμε ενα ενα τα γραμαμτα απο τη λιστα
                c = new CardGraphs(currentLetter, x, y);
                c.setArrayDimension(dimension);
                getContentPane().add(c);//προσθετουμε στο παραθυρο το καθε γραμμα μεσω του constructor της CardGraphs
                currentLetter.setSituation(false);//θετουμε αυτοματα και ελευθερο το γραμμα για χρησιμοποιηση στη υποηφια λεξη
                letterList.add(currentLetter);//προσθετουμε το γραμμα σε μια τοπικα λιστα, οπως ειναι η Shuffled_Chars
                setVisible(true); //να ειναι εμφανισημο το παραθυρο
                counter++;//μετρητης για τα γραμματα
                x += 105;// πηγαινει στο επομενο γραμμα, δεξια ουσιαστικα κατα 105 πιξελς
                //System.out.print(currentLetter.getCharacter() + " ");
            }
            //System.out.println();
            x = 5; // παμε στην αρχη της καθε σειρας στον πινακα που ξεκινα στον αξονα Χ στο 5ο πιξελ
            y += 105;// παει στην επομενη γραμμης, δηλαδη συν 105 πιξελς κατω
            //ουσιαστικα κατεβαινει στην επομενη σειρα στην αρχη της για να ζωγραφιστει το επομενο γραμμα
        }
        revalidate();//για την ασφαλη ανανεωση του παραθυρου, στο ιντερνετ τις βρηκαμε τις μεθοδους και τις πεταξαμε
        repaint();
        setCounter(counter);
    }

    //σχεδιαζουμε το παραθυρο ουσιαστικα εδω, πριν απλα το δημιουργησαμε, εδω του πεταμε ολα τα components
    //και καλειται ξανα οταν επηρεαζεται ο πινακας με τα γραμματα, η λεξη και η ποντοι
    // αφου ολα αλληλεπιδρουν μεταξυ τους, δηλαδη αν αυξηθουν οι ποντοι τοτε επηρεαζεται και ο πινακας σαν γραφικα
    public void setWindow(boolean stBC, Letter letter, Graphics g) {
        SwingUtilities.updateComponentTreeUI(this);
        info1 = new JLabel();
        info2 = new JLabel();
        JMadeWord = new JLabel();
        if (getCounter() == 0 && getreadyWindow() == false) {
//            WordPoints = 0;
//            setPointsOfTheWord(0);
//            setMadeWord("");
//            System.out.println("nooooooooo");
//            //setPointsOfWords(getPointsOfWords());
        } else { // οταν δεν ειαμστε στο πρωτο γραμμα της λεξης και ενημερωνονται γραφικα λεξη και ποντοι
            remove(info2);
            remove(info1);
            remove(JMadeWord);
            info1.setVisible(false);
            if (stBC == true && getCounter() != 0) {//εδω θα μπει αν ο χρηστης επιλεξει το τελευταιο γραμμα
                setPointsOfTheWord(getPointsOfTheWord() - letter.getValue());//αφαιρουνται στους πντους της λεξης οι ποντοι του τελευταιου γραμματος
                sb = new StringBuilder(MadeWord);// αφαιρειται απο τη λεξη και το τελεταιο γραμμα
                sb.deleteCharAt(MadeWord.length() - 1);
                setMadeWord(sb.toString());
            } else {// (stBC == false && counter != 0) 
                //αλλιως προσθετουμε το γραμμα στη λεξη και την αξια στην συνολικη αξια της λεξης
                setPointsOfTheWord(getPointsOfTheWord() + letter.getValue());
                MadeWord += letter.getCharacter();
            }
        }
        //παρακατω δημιουργουμε και πεταμε στο παραθυρο τα απαραιτητα components
        bExit = new JButton("Διακοπή παιχνιδιού");
        bRestart = new JButton("Επανεκκίνηση παιχνιδιού");
        bCheckWord = new JButton("ΕΛΕΓΧΟΣ ΛΕΞΗΣ");
        bGameHelp = new JButton("Οδηγίες για το παιχνίδι");
        bUsers = new JButton("ABOUT/χρήστες");
        bWordsFile = new JButton("Αναζήτηση Αρχείου Λέξεων");

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
        info2.setText("Εξασφαλισμένοι πόντοι συνολικά: " + getPointsOfWords() + ", Στόχος: " + getsuccessPoints());
        info2.setFont(new Font("Courier", Font.ITALIC, 25));
        info2.setForeground(Color.DARK_GRAY);
        info2.setBounds(getDimension() * 110 + 100, 30, 600, 40);

        //αν φτιαχτηκε το παραθυρο τοτε καθε φορα που καλειται αυτη η συναρτηση πεταγεται ενημερωση με τη κατασταση της λεξης και των γραμματων
        if (getreadyWindow() == true) {
            JOptionPane.showMessageDialog(null,
                    JYourPoints.getText()
                    + " \n " + JMadeWord.getText()
                    + " \n " + info1.getText()
                    + " \n " + info2.getText());
        }
        //οριζουμε διαστασεις στα κουμπια αναλογως τις διαστασεις του πινακα που εχουμε, χειροκινητα
        b1.setBounds(getDimension() * 110 + 200, 110, 250, 40);
        b2.setBounds(getDimension() * 110 + 200, 160, 250, 40);
        b3.setBounds(getDimension() * 110 + 200, 210, 250, 40);
        b4.setBounds(getDimension() * 110 + 200, 260, 250, 40);
        b5.setBounds(getDimension() * 110 + 200, 310, 250, 40);

        bExit.setForeground(Color.white);
        bCheckWord.setForeground(Color.white);
        bUsers.setForeground(Color.white);
        bGameHelp.setForeground(Color.white);
        bWordsFile.setForeground(Color.white);

        bUsers.setBackground(Color.GRAY);
        bGameHelp.setBackground(Color.GRAY);
        bCheckWord.setBackground(Color.BLUE);
        bExit.setBackground(Color.RED);
        bWordsFile.setBackground(Color.GRAY);
        //ομοιως οπως για τα παραπανω κουμπια  
        bExit.setBounds(getDimension() * 110 + 380, 410, 200, 40);
        bUsers.setBounds(getDimension() * 110 + 80, 360, 200, 40);
        bGameHelp.setBounds(getDimension() * 110 + 380, 360, 200, 40);
        bCheckWord.setBounds(getDimension() * 110 + 80, 410, 200, 40);
        bWordsFile.setBounds(getDimension() * 110 + 200, 470, 250, 40);
        //αντικειμενο της εσωτερικης κλασσης για την διευκολυνση στη χρησιμοποιηση των κουμπιων και των ActionListener τους
        ButtonHandler bh = new ButtonHandler();

        //ενεργοποιουμε ουσιαστικα τα κουμπια, αποκτουν ουσιαστικα ουσια και λειτουργια μεσω της εσωτερικης κλασσης
        bExit.addActionListener(bh);
        bCheckWord.addActionListener(bh);
        bUsers.addActionListener(bh);
        bGameHelp.addActionListener(bh);
        bWordsFile.addActionListener(bh);
        b1.addActionListener(bh);
        b2.addActionListener(bh);
        b3.addActionListener(bh);
        b4.addActionListener(bh);
        b5.addActionListener(bh);
        bRestart.addActionListener(bh);
        add(options);

        //προσθετουμε στο frame τα κουμπια
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(bCheckWord);
        add(bGameHelp);
        add(bExit);
        add(bUsers);
        add(bWordsFile);
        //τοιμες μεθοδοι που βρηκαμε στο ιντερνετ και βοηθανε ισως στη ομαλη λειτουργια ανανεωσης του παραθυρου
        this.revalidate();
        SwingUtilities.updateComponentTreeUI(this);
    }

    //μεθοδος που ανοιγει τον φακελο με τις οδηγιες του παιχνιδιου
    public void Instructions() {
        FileWord = "";
        //για ασφαλεια κανουμε εδω χρηση try catch
        try {
            //δοκιμαζουμε να βρουμε μεσω μονοπατιου το αρχειο
            scan = new Scanner(new File("src/home02/Instructions.txt"));
            while (scan.hasNext()) {// οσο στο αρχειο υπαρχει λεξη να διαβαστει
                FileWord += scan.next();//προσθετουμε σε μια συμβολοσειρα καθε λεξη
                //System.out.println(FileWord);
            }
            //εμφανιζει τις οδηγιες
            JOptionPane.showMessageDialog(null, FileWord, "File infos", JOptionPane.INFORMATION_MESSAGE);
            scan.close();//κλεινει το αρχειο με ασφαλεια
        } catch (FileNotFoundException ex) {//αν δε βρεθει το αρχειο
            JOptionPane.showMessageDialog(null, "Το αρχείο με τις οδηγίες δε βρέθηκε", "File error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //μεθοδος για το ανοιγμα του αρχειου με τα ονοματα των φοιτητων, ομοιως οπως η απο πανω συναρτηση δουλευει
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
                FileWord += WordsFile.get(i) + " ";//προσθετει σε μια λιστα τη καθε λεξη
            }//εμφανιζει τους χρηστες μεσω της λιστας που αποθηκευσε τις λεξεις του αρχειου
            JOptionPane.showMessageDialog(null, FileWord, "File infos", JOptionPane.INFORMATION_MESSAGE);
            scan.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Το αρχείο με τους χρήστες δε βρέθηκε", "File error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //μεθοδος που ελεγχει τη λεξη, αν υπαρχει δηλαδη στο αρχειο
    public void CheckWord() {
        if (MadeWord.length() > 2) {
            JOptionPane.showMessageDialog(null, "Θα ελέγξουμε τη λέξη " + MadeWord);
            if (c.getDoubleWordValue() == true) {//ν περιεχει η λεξη δηλαδη μπλε γραμμα
                WordPoints *= 2;
            }
            if (game.SearchWord(MadeWord) == true) {//ο ελεγχος θα γινει ουσιαστικα στην συναρτηση αυτη
                //game.ReplaceWords();
                //card.ClearAllLetters(gp);// everytime
                //αν οντως βρεθηκε η λεξη σβηνουμε τη τωρινη λεξη και ποντους της, αφου ξεκιναμε απο την αρχη την αναζητηση νεας λεξης
                setMadeWord("");
                setPointsOfWords(WordPoints);
                setPointsOfTheWord(0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Δεν μπορείς να ελέγξεις τη λέξη, πρέπει το λιγότερο 3 γράμματα να διαλέξεις ", "File error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //μεθοδος για την ανταλλαγη γραμματων
    public void Exchange_Letters(int dimension) {
        boolean statt = true;//για τη while
        Point tempP, tempP2, p1 = null, p2 = null;
        int x1 = 0, x2 = 0, y2 = 0, y1 = 0;//συντεταγμενες για τα 2 γραμματα αναταλλαγης
        Letter valL = null, valL2 = null;//για τα δυο γραμματα που θα ανταλλαξουμε
        String InputChoiceLine = "", InputChoiceRow = "";
        while (statt == true) {
            do {
                InputChoiceLine = JOptionPane.showInputDialog(null, "Πρώτο Γράμμα: γραμμή: ");
                InputChoiceRow = JOptionPane.showInputDialog(null, "Πρώτο Γράμμα: στήλη: ");
            } while (Character.isDigit(InputChoiceLine.charAt(0)) != true);
            y1 = Integer.parseInt(InputChoiceLine) - 1;
            x1 = Integer.parseInt(InputChoiceRow) - 1;
            //εγκυροι αριθμοι, να υπαρχουν στον πινακα δηλαδη η στηλ και η σειρα του γραμματος
            if (x1 < dimension && x1 >= 0 && y1 < dimension && y1 >= 0) {
                do {
                    InputChoiceLine = JOptionPane.showInputDialog(null, "Δεύτερο Γράμμα: γραμμή: ");
                    InputChoiceRow = JOptionPane.showInputDialog(null, "Δεύτερο Γράμμα: στήλη: ");
                } while (Character.isDigit(InputChoiceLine.charAt(0)) != true);
                y2 = Integer.parseInt(InputChoiceLine) - 1;
                x2 = Integer.parseInt(InputChoiceRow) - 1;
                //ελεγχος εγκυροτητας στηλης και γραμμης και για το δευτερο γραμμα
                if (x2 < dimension && x2 >= 0 && y2 < dimension && y2 >= 0) {
                    //προσαρμοζουμε τις συντεταγμενες σε πραγματικα πιξελς, αναλογως με τον πινακα
                    x1 = x1 * 105 + 5;
                    y1 = y1 * 105 + 5;
                    x2 = x2 * 105 + 5;
                    y2 = y2 * 105 + 5;
                    //δυο ποιντς δημιουργουμε για τα δυο γραμματα στον πινακα
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
            //ψαχνουμε στην ακολουθη λιστα να βρουμε το πρωτο γραμμα, αναλογα με το ID, δηλαδη το ποιντ του γραμματος
            for (Map.Entry<Point, Letter> entry : CardGraphs.LettersMap.entrySet()) {
                tempP = entry.getKey();
                valL = entry.getValue();
                //αν βρεθουν οι συντεταγμενες του πρωτου δωθεντος γραμματος στην λιστα
                if (p1.x == tempP.x && p1.y == tempP.y) {
                    //ψαχνουμε για το αλλο γραμμα τωρα, παλι στη λιστα, παλι με του ID του, δηλαδη του σημειου του
                    for (Map.Entry<Point, Letter> entry2 : CardGraphs.LettersMap.entrySet()) {
                        tempP2 = entry2.getKey();
                        valL2 = entry2.getValue();
                        //αν βρεθηκαν οι συντεταγμενες και του δευτερου γραμματος στη λιστα
                        if (p2.x == tempP2.x && p2.y == tempP2.y) {
                            //παρακατω αν βρεθουν τα γραμματα, μεσω των συντεταγμενων στην παρακατω λιστα τοτε θα κανουμε swap μεταξυ τους
                            for (int i = 0; i < letterList.size(); i++) {
                                if (letterList.get(i).getPoint() == tempP) {
                                    for (int j = 0; j < letterList.size(); j++) {
                                        if (letterList.get(j).getPoint() == tempP2) {
                                            //ετοιμη συναρτηση για swap δυο Letters
                                            Collections.swap(ManageList.Shuffled_Chars, i, j);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //dispose();  //Κλείνει το τρεχόν παράθυρο 
            //ξαναχτιζεται το παραθυρο ατυπα με τα νεα δεδομενα, δηλαδη την ανταλλαγη θεσεων μεταξυ δυο γραμματων
            MakeGameGraphs(dimension, ManageList.Shuffled_Chars);
        }
    }

    //εσωτερικη κλαση οπου υιοθετει τις μεθοδους για την λειτουργια των κουμπιων, χρηση interface
    class ButtonHandler implements ActionListener {

        //αν το ae παρακατω που πατηθει αντιστοιχει στο συγκρεκριμενο κουμπι καλειται η αντιστοιχη λειτουργια
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == bExit) {//κουμπι για εξοδο απο το παιχνιδι
                System.exit(0);
            } else if (ae.getSource() == bCheckWord) {//κουμπι για ελεγχο της λεξεης
                if (tempcheck == true) {//αν γινεται να ελεγξουμε τη λεξη
                    CheckWord();
                }
                tempcheck = false;
            } else if (ae.getSource() == b1) {
                game.User_Menu(2, getGraphics());
                dispose();  //Κλείνει το τρεχόν παράθυρο                 
                JOptionPane.showMessageDialog(null, "Επέλεξε να αντικαταστήσεις μια σειρά, έχεις ακόμη " + (3 - Game.SecondCount) + " προσπάθειες για την επιλογή αυτή και συνολικά " + Game.localCount);
                //game.RemakeLine(dimension);

            } else if (ae.getSource() == b2) {
                game.User_Menu(5, getGraphics());
                dispose();  //Κλείνει το τρεχόν παράθυρο 
                JOptionPane.showMessageDialog(null, "Επέλεξες να ανακατέψεις μια σειρά, έχεις ακόμη " + (3 - Game.FifthCount) + " προσπάθειες για την επιλογή αυτή και συνολικά " + Game.localCount);

            } else if (ae.getSource() == b3) {
                game.User_Menu(4, getGraphics());
                dispose();  //Κλείνει το τρεχόν παράθυρο 
                JOptionPane.showMessageDialog(null, "Επέλεξες να ανακατέψεις μια στήλη, έχεις ακόμη " + (3 - Game.FourthCount) + " προσπάθειες για την επιλογή αυτή και συνολικά " + Game.localCount);

            } else if (ae.getSource() == b4) {
                if (JOptionPane.showConfirmDialog(null, "Όλα μηδενίζονται και παίζεις από την αρχή για τη λέξη οκ;") == 0) {
                    c.YellowLetterPoints.clear();
                    setMadeWord("");
                    setPointsOfTheWord(0);
                    dispose();  //Κλείνει το τρεχόν παράθυρο 
                    game.User_Menu(3, getGraphics());
                    //  setPointsOfTheWord(tempWordPoints);
                }
            } else if (ae.getSource() == b5) {
                game.User_Menu(1, getGraphics());
                dispose();  //Κλείνει το τρεχόν παράθυρο 
                JOptionPane.showMessageDialog(null, "Επέλεξες να ανταλλάξεις δύο γράμματα μεταξύ τους, έχεις ακόμη " + (3 - Game.FirstCount) + " προσπάθειες για την επιλογή αυτή και συνολικά " + Game.localCount);
                //  setPointsOfTheWord(tempWordPoints);

            } else if (ae.getSource() == bUsers) {
                if (JOptionPane.showConfirmDialog(null, "Επέλεξες να δεις τους χρήστες της εργασίας") == 0) {
                    ReadUsersFile();
                }
            } else if (ae.getSource() == bGameHelp) {
                if (JOptionPane.showConfirmDialog(null, "Οδηγίες για το πως παίζεται το παιχνίδι") == 0) {
                    Instructions();
                }
            } else if (ae.getSource() == bWordsFile) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File("src/home02/Wordlist.txt"));
                fc.setDialogTitle("WordList");
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (fc.showOpenDialog(bWordsFile) == JFileChooser.APPROVE_OPTION) {
                    //
                }

            }

        }
    }
}
