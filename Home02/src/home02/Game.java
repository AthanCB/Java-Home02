package home02;
//icsd14134 Bonis Athanasios - icsd11039 Dimopoulos Georgios

/*
 Εδώ το παιχνίδι, όπου δημιουργείται αντικείμενο τύπου ManageList
 και παιζεί ο χρήστης ουσιαστικά με τις επιλογές γραμμάτων για τη λέξη. 
 Υπάρχουν σε αυτή τη κλάσση με άλλα λόγια μέθοδοι
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game extends JFrame {

    // άδειος constructor
    public Game() {

    }

    // πίνακας τύπου γράμματος/Letter 
    GameGraphs gameG;
    //Letter Array[][];
    GameGraphs gg;
    static int size;
    CardGraphs c;
    Graphics g;
    ManageList managefile = new ManageList();// δημιουργία αντικειμένου τύπου κλάσσης ManageList για τη πρόσβαση μετά στις συναρτήσεις στη ManageList
    ArrayList<Letter> TempArray = new ArrayList();//λίστα που δέχεται αντικείμενα τύπου Letters, για διευκόλυνση του πίνακα χρησιμοποιείται 
    HashMap<Point, Letter> tempMap = new HashMap<>();
    private boolean stat = true, statPoints = false;// μεταβλητές boolean για τις while και τον έλεγχο πόντων στο τέλος 
    private String Word = "";//η λέξη που αρχικοποιείται με κενό και γεμίζει με τα γράμματα που δίνει ο χρήστης
    private int LettersCounter = 0; //μετρητής των γραμμάτων
    private int WordCounter = 0;//μετρητής για τον αριθμό λέξεων έτσι ώστε μόνο όταν αυτός είναι πάνω από 5 να μπορεί να τελειώσει το παιχνίδι 
    //και να δει ο χρήστης αν νικησε ή όχι
    private final int WinPoints = 50;// για να δει ο χρήστης αν νίκησε θα πρέπει να έχει μαζέψει 50 πόντους
    // υπόψιν τους πόντους για να νικήσει ο χρήστης και τον αριθμό των λέξεων των ορίζουμε εμείς
    private int Points = 0, AllPoints = 0, counterDismiss = 0;
    // μεταβλητές των πόντων της λέξης, όλων των πόντων του χρήστη και μετρητής για την εμφάνιση του μενού αν είναι 0
    static int FirstCount = 0, SecondCount = 0, localCount = 15, ThirdCount = 0, FourthCount = 0, FifthCount = 0;

    public void setArraySize(int size) {
        this.size = size;
    }

    public int getArraySize() {
        return size;
    }

    //μέθοδος για τη δημιουργία του πίνακα μέσω συναρτήσεων που είναι στην κλασση ManageList και τη καλεί το αντικείμενο file
    public void CreateTable(User user) {
        //Array = new Letter[size][size];// δημιουργία πίνακα με τις διαστάσεις που μας έδωσε ο χρήστης 
        //παρακάτω καλούμε τις μεθόδους για τη διαχείρηση του αρχείου και των λεξεών του 
        managefile.OpenFile();
        managefile.ReadFile();
        managefile.Selected_Words(size);
        managefile.CharList();

        gameG = new GameGraphs();
        gameG.setreadyWindow(false);
        gameG.MakeGameGraphs(size, managefile.Shuffled_Chars);
        gameG.setsuccessPoints(50);
        gameG.setPointsOfWords(0);
        gameG.setNumberOfWords(1);
        gameG.repaint();
        gameG.revalidate();
        //c = new CardGraphs(gameG);
        //LetsPlay(Choice, Word, user);
    }

    //συνάρτηση για την εμφάνιση του μενου τη πρώτη φορά ή για το ξεκίνημα επιλογής γραμμάτων από τον χρήστη
    public void LetsPlay(int Choice, String Word, User user, Graphics g) {
        if (counterDismiss == 0) {//τη πρώτη φορά καλείται το μενου με τις επιλογές που μπορεί να κάνει ο χρήστης με τα γράμματα πριν παίξει
            User_Menu(Choice, g);
            counterDismiss++;
        }
        //ChosenLetter(0, 0, user, Choice);// καλείται η συνάρτηση αυτή για την επιλογή του γράμματος
    }

    //συνάρτηση για τη σύγκριση γραμμάτων κλπ
//    public void ChosenLetter(int l, int r, User user, int Choice) {
//        Scanner in = new Scanner(System.in);//για την εισαγωγή γράμματος από τον χρήστη
//        //αφού έχουμε 0 πόντους σημαίνει ότι είμαστε στο πρώτο γράμμα, που δεν έχει κάποιο γειτονικό για να έχουμε κάποιον έλεγχο γειτνίασης
//        if (Points == 0) {
//            System.out.print("Επέλεξε γραμμή πρώτου γράμματος: ");
//            l = in.nextInt() - 1;// στον πίνακα θα είναι στη προηγούμενη θέση πρακτικά από αυτή που έδωσε ο χρήστης
//            while (l < 0 || l >= Array.length) {
//                System.out.print("Δώσε έγκυρο αριθμό μέσα στον πίνακα και θετικό: ");
//                l = in.nextInt() - 1;
//            }
//            System.out.print("Επέλεξε στήλη πρώτου γράμματος: ");
//            r = in.nextInt() - 1;
//            while (r < 0 || r >= Array.length) {
//                System.out.print("Δώσε έγκυρο αριθμό μέσα στον πίνακα και θετικό: ");
//                r = in.nextInt() - 1;
//            }
//            if (Array[l][r].getCharacter() == '?') {// για την περίπτωση που είναι ο χαρακτήρας του Letter μπαλαντερ
//                System.out.print("Δώσε επιθυμητό γράμμα: ");
//                char balader = in.next().charAt(0);//διαβάζεται μεν String αλλά το πρώτο γράμμα, άρα σαν να διαβάζει char, δεν υπάρχει nextChar()
//                Array[l][r] = managefile.SetBalader(balader);// βάζουμε στη θέση του πίνακα το γράμμα που αντικατέστησε τον balader στη συνάρτηση που καλούμε 
//            }
//            if (Color.BLUE.equals(Array[l][r].getColor())) {//αν βρεθεί γράμμα μπλε τότε αλλάζει η boolean για να διπλασιαστεί η λέξη 
//                statPoints = true;
//            }
//            Word = ManagePoints(l, r, user, Choice, g);//καλούμε τη συνάρτηση για τη διαχείριση των πόντων στη λέξη
//            //αναδρομικά καλούμε τη συνάρτηση πάλι για να πάει ο χρήστης στο δεύτερο γράμμα μέσω της ίδιας συνάρτησης
//            //που θα δέχεται παράμετρο τις συντεταγμένες του προηγούμενος γράμματος για τη γειτνιαση και τον user και το μέγεθος του τετραγωνικού πίνακα
//            ChosenLetter(l, r, user, Choice);
//        } else {
//            CheckWords(Choice, user);
//        }
//    }
    public void CheckWords(int Choice, User user) {
        Scanner in = new Scanner(System.in);
        if (WordCounter == 5) {
            JOptionPane.showMessageDialog(null, "Έγραψες 5 λέξεις, το παιχνίδι τελείωσε.");
            if (AllPoints >= WinPoints) {
                System.out.println("Νίκησες το παιχνίδι! Συγχαρητήρια!");
            } else {
                System.out.println("Λυπάμαι, έχασες :( ");
            }
            //System.exit(0);//έμεσο τέλος παιχνιδιού 
        } else {// αν ακόμα δεν έχουμε περάσει τις 5 λέξεις και μπορεί ο χρήστης να γράψει και άλλη λέξη
            //System.out.println("Θες να διακόψεις το παιχνίδι;(ν/ο) ");
            char inCh;
            do {// δίνει απάντηση ο ή ν, ότιδήποτε άλλο δεν είναι αποδεκτό για αυτό του ξαναζητάμε απάντηση
                inCh = in.next().charAt(0);
                if (inCh == 'ο') {
                    LetsPlay(Choice, Word, user, g);//για τη συνέχεια του παιχνιδιού ξανακαλούμε την LetsPlay για να ξαναψάξει από την αρχή λέξη
                } else if (inCh == 'ν') {
                    System.exit(0);//τέλος συστήματος και παιχνιδιού
                }
            } while (inCh != 'ν' && inCh != 'ο');
        }
    }

    //συνάρτηση για τη διαχείριση των πόντων στη λέξη και των επιλογών για τη λέξη σε κάθε φορά 
//    public String ManagePoints(int l, int r, User user, int Choice, Graphics g) {
//        Scanner in = new Scanner(System.in);
//        Array[l][r].setSituation(true);//αλλάζει η κατάσταση του Letter έτσι ώστε να μη μπορεί να ξαναχρησιμοποιηθεί το γράμμα
//        System.out.println(Array[l][r].getCharacter());
//        System.out.println("Θες να πας μήπως στο μενού των βοηθητικών επιλογών; Αν ναι πάτα 1");
//        System.out.println("Η θες να ακυρώσεις μήπως τις επιλογές σου; Αν ναι πάτα 2");
//        System.out.println("Διαφορετικά πάτα άλλο αριθμό");
//        char nextCho = in.next().charAt(0);
//        if (nextCho == '1')//πάμε στο μενού των βοηθητικών επιλογών 
//        {
//            System.out.println("Πάμε πίσω στο μενού επιλογών. Υπόψιν οποιαδήποτε αλλαγή θα αλλάξει και το χτύσιμο ως τώρα της λέξης");
//            User_Menu(Choice, g);
//        } else if (nextCho == '2') {//αν ακυρώσει τις επιλογές του όλα τα γράμματα πρέπει να μπορούν να χρησιμοποιηθούν πάλι ξανα
//            for (int k = 0; k < Array.length; k++) {
//                for (int m = 0; m < Array[k].length; m++) {
//                    Array[k][m].setSituation(false);//έμμεση ελευθέρωση του γράμματος
//                }
//            }
//            Word = "";// και αφού ξεκινάμε πάλι από την αρχή τη λέξη, την θέτουμε ως κενή
//            LetsPlay(Choice, Word, user, g);
//        } //else 
//        Word += Array[l][r].getCharacter();//η λέξη αυξάνεται με το γράμμα επιλογής του χρήστη
//        System.out.println("Ως τώρα η λέξη: " + Word);
//        LettersCounter++;//αυξάνεται ο αριθμός γραμμάτων
//        Points += Array[l][r].getValue();//οι πόντοι του γράμματος προσθέτονται στην λέξη
//        System.out.println("Ως τώρα οι πόντοι: " + Points);
//        if (LettersCounter > 2) {//από τρία και πάνω γράμματα μπορείς ο χρήστης να ελέγξει την λε΄ξη που σχημάτησε
//            System.out.println("Θές να ελέγξεις τη λέξη;(ν/ο) ");
//            char inCh = in.next().charAt(0);
//            if (inCh == 'y') {
//                stat = false;
//                if (statPoints == true) {//αν υπήρξε γράμμα μπλε, τότε διπλασιάζεται η βαθμολογία της λέξης
//                    Points = 2 * Points;
//                }
//                //παρουσιάζουμε τους πόντους της λέξης που έγραψε ο χρήστης αν τη βρούμε στο αρχείο μέσω της SearchWord
//                if (SearchWord(Word) == true) {
//                    AllPoints += Points;
//                    WordCounter++;
//                    System.out.println("Συνολικόι πόντοι λέξης: " + Points);
//                    System.out.println("Συνολικόι πόντοι λέξεων: " + AllPoints);
//                    user.setPoints(AllPoints);
//                    Points = 0;
//                }
//            }
//        }
//        return Word;//πρακτικά δε θα καλεστεί ποτέ αυτή η εντολή
//    }
    // εύρεση της δωσμένης από τον χρήστη λέξη στο αρχείο
    public boolean SearchWord(String word) {
        boolean counter = true;//μετρητής για την εύρεση της λέξης
        for (int i = 0; i < managefile.AllWordsList.size(); i++) {
            if (word.equals(managefile.AllWordsList.get(i))) {//αν βρέθηκε η λέξη στο αρχείο
                counter = false;
                //Word = "";// η λέξη γίνεται πάλι έμμεσα κενή για να γεμίσει ξανά από τα γράμματα της επόμενης λέξης
                //LettersCounter = 0;//αντίστοιχα ο μετρητής των γραμμάτων της λέξης μηδενίζεται και αυτός
            }
        }
        // δε βρέθηκε η λέξη
        if (counter == true) {
            //ξαναθέτουμε όλα τις καταστάσεις των γράμματων σαν false για να είναι διαθέσιμα τα γράμματα όλα πάλι
            JOptionPane.showMessageDialog(null, "Δε βρήκες την λέξη, συνέχισε τη προσπάθεια");
            return false;//false επιστροφή αφού δε βρέθηκε η λέξη και μπήκε στην if
        }//αλλιώς επιστροφή true αφού βρέθηκε η λέξη και δε μπήκε στην if πριν
        else {
            JOptionPane.showMessageDialog(null, "Βρήκες την λέξη");
            WordCounter++;
        }
        if (WordCounter == 5) {
            JOptionPane.showMessageDialog(null, "Βρήκες 5 λέξεις, ας δούμε αν μάζεψες 50 πόντους");
            if (gg.getPointsOfWords() >= gg.getsuccessPoints()) {
                JOptionPane.showMessageDialog(null, "Μάζεψες τους απαιτούμενους πόντους, ΝΙΚΗΣΕΣ!");
            } else {
                JOptionPane.showMessageDialog(null, "Έχασες δε μάζεψες 50 πόντους");
            }
            System.exit(0);
        } else {
            if (gg.getPointsOfWords() > gg.getsuccessPoints()) {
                JOptionPane.showMessageDialog(null, "Μάζεψες τους απαιτούμενους πόντους, ΝΙΚΗΣΕΣ!");
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(null, "Δε μάζεψες 50 πόντους αλλά έχεις ως τώρα: " + gg.getPointsOfWords());
            }
        }
        return true;
    }

    //αντικατάσταση λέξεων οι οποίες βρήκε ο χρήστης
//    public void ReplaceWords() {
//        int count = 0;//για την θέση του γράμματος που θα πάρουμε από την Shuffled_Chars για να το πετάξουμε στον πίνακα
//        managefile.Selected_Words(Array.length);//νέ
//        for (int k = 0; k < Array.length; k++) {//τρέχουμε όλα τα στοιχεία του πίνακα τύπου Letters
//            for (int m = 0; m < Array[k].length; m++) {
//                //αν βρεθεί γράμμα που έχει χαρακτηριστεί false η κατάσταση του, δηλαδή χρησιμοποιήθηκε για λέξη αντικαθιστούμε με νέο γράμμα
//                if (Array[k][m].isSituation() == true) {
//                    Array[k][m] = managefile.Shuffled_Chars.get(count);//το χρησιμοποιημένο γράμμα το αντικαθιστούμε με αυτό στην θέση count στην Shuffled_Chars 
//                    count++;//στην επόμενη θέση της Shuffled_Chars πάμε
//                    Array[k][m].setSituation(false);//και θέτουμε την κατάσταση του false για να μπορεί να χρησιμοποιηθεί
//                }
//            }
//        }
//    }
    //εμφάνιση μενού επιλογών για τη διαμορφωποίηση του πίνακα πριν ξεκινήσει ο χρήστης να επιλέγει γράμματα
    public void User_Menu(int Choice, Graphics g) {
        gg = new GameGraphs();
        boolean stat = true;
//        localCount = 0; //μετρητής για τις φόρες που επιλέγονται οι επιλογές αφού ο χρήστης μπορεί ως 15 φόρες να τις επιλέξει
//        FirstCount = 0;//μετρητής για τις φόρες που επιλέγεται η πρώτη επιλογή αφού ο χρήστης μπορεί ως 3 φόρες να τη επιλέξει
//        //ομοίως και για τις άλλες επιλογές, ως 3 φορές μπορούν να επιλεχθούν
//        SecondCount = 0;
//        ThirdCount = 0;
//        FourthCount = 0;
//        FifthCount = 0;
        while (stat == true) {//όσο η μεταβλητή είναι true
            String ch = Choice + "";
//            }//θεωρούμε ότι ο χρήστης δίνει αριθμό και όχι κάποιο χαρακτήρα πχ
            if (ch.equals("1")) {
                if (FirstCount < 3) {
                    localCount--;
                    FirstCount++;
                    gg.Exchange_Letters(size);//μέθοδος ανταλλαγής γραμμάτων                    
                } else {
                    JOptionPane.showMessageDialog(null, "Επέλεξες ήδη 3 φορές την επιλογή, δε γίνεται άλλο");
                }
            } else if (ch.equals("2")) {
                if (SecondCount < 3) {
                    SecondCount++;
                    localCount--;
                    //gg.dispose();
                    RemakeLine(size);//μέθοδος αντικατάσταση γραμμής                        
                } else {
                    JOptionPane.showMessageDialog(null, "Επέλεξες ήδη 3 φορές την επιλογή RemakeLine, δε γίνεται άλλο");
                }
            } else if (ch.equals("3")) {
                if (ThirdCount < 3) {
                    //JOptionPane.showConfirmDialog(null, "Επέλεξες να ανακατέψεις όλα τα γράμματα, έχεις ακόμη " + (3 - ThirdCount) + " προσπάθειες για την επιλογή αυτή και συνολικά " + localCount);
                    localCount--;
                    ThirdCount++;
                    Rearrangement(size);//μέθοδο αναδιάταξης όλων των γραμμάτων του πίνακα
                } else {
                    JOptionPane.showMessageDialog(null, "Επέλεξες ήδη 3 φορές την επιλογή Rearrangement, δε γίνεται άλλο");
                }
            } else if (ch.equals("4")) {
                if (FourthCount < 3) {                   
                    localCount--;
                    FourthCount++;
                    //gg.dispose();
                    RearrangementRow(size);//μέθοδο αναδιάταξη όλων των γραμμάτων μιας στηλης                    
                } else {
                    JOptionPane.showMessageDialog(null, "Επέλεξες ήδη 3 φορές την επιλογή RearrangementRow, δε γίνεται άλλο");
                }
            } else if (ch.equals("5")) {
                if (FifthCount < 3) {
                    localCount--;
                    FifthCount++;
                    //gg.dispose();
                    RearrangementLine(size);//μέθοδο αναδιάταξη γραμμάτων μίας γραμμής                        

                } else {
                    JOptionPane.showMessageDialog(null, "Επέλεξες ήδη 3 φορές την επιλογή RearrangementLine, δε γίνεται άλλο");
                }
            } else if (ch.equals("10")) {
                stat = false;//με την επιλογή 0 ο χρήστης προχωράει στην επιλογή γραμμάτων και φεύγει από το μενού
            }
            if (localCount <= 0) {
                JOptionPane.showMessageDialog(null, "Επέλεξες ήδη 15 φορές τις επιλογές, δε γίνεται άλλο");
                stat = false;
            }
            stat = false;
        }
    }

    //αναδιάταξη γραμμάτων πρώτα της λίστας με τα γράμματα, απλά μέσω της έτοιμης εντολής από την βιβλιοθήκη shuffle
    public void Rearrangement(int SIZE) {
        tempMap.clear();
        Collections.shuffle(managefile.Shuffled_Chars);
        gg.MakeGameGraphs(size, ManageList.Shuffled_Chars);
        JOptionPane.showMessageDialog(null, "Επέλεξες να ανακατέψεις όλα τα γράμματα, έχεις ακόμη " + (3 - ThirdCount) + " προσπάθειες για την επιλογή αυτή και συνολικά " + localCount);
    }

    //αντικατάσταη γραμμάτων μίας γραμμής που επιλέγει παρακάτω ο χρήστης    
    // ΔΕ ΔΟΥΛΕΥΕΙ ΣΤΗ ΓΡΑΜΜΗ 352
    public void RemakeLine(int size) {
        boolean statt = true, statt2 = true;
        char extraC;
        Point tempPoint, CLetterPoint;
        Letter l = null;
        Random rnd = new Random();// για τυχαία επιλογή γράμματος
        //String alphabet = "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ";
        int x, y = 5;// για τις συντεταγμένες των γραμμάτων της σειράς
        while (statt) {
            String InputChoice = JOptionPane.showInputDialog(null, "Επέλεξε γραμμή διαγραφής: ");
            int line = Integer.parseInt(InputChoice) - 1;
            if ((line < size) && (line >= 0)) {//έλεγχος εγκυρότητας
                x = line * 105 + 5;
                tempMap.clear();
                while (statt2 == true) {
                    for (int i = 0; i < size; i++) {
                        if (y < (size - 1) * 105) {
                            tempPoint = new Point(x, y);
                            for (int j = 0; j < managefile.Shuffled_Chars.size(); j++) {
                                CLetterPoint = managefile.Shuffled_Chars.get(j).getPoint();
                                if (CLetterPoint.x == tempPoint.x && CLetterPoint != null) {
                                    System.out.println(CLetterPoint);
                                    int o = rnd.nextInt(managefile.AllWordsList.size());
                                    int p = rnd.nextInt(managefile.AllWordsList.get(o).length());
                                    extraC = ManageList.AllWordsList.get(o).charAt(p);
                                    l = managefile.SetColorAndValue();
                                    l.setCharacter(extraC);
                                    l.setValue(ManageList.GivenValue(extraC));
                                    managefile.Shuffled_Chars.set(j, l);
                                } else {
                                    statt2 = false;
                                }
                            }
                            y += 105;
                        }
                    }
                }
                statt = false;
                gg.MakeGameGraphs(size, managefile.Shuffled_Chars);                
            } else {
                JOptionPane.showMessageDialog(null, "Δεν έχει τέτοια γραμμή ο πίνακας ξαναπροσπάθησε");
            }
        }
    }

    //αναδιάταξη γραμμάτων μίας στήλης που επιλέγει παρακάτω ο χρήστης
    public void RearrangementRow(int size) {
        boolean statt = true;
        int x, y = 5;
        while (statt == true) {
            String InputChoice = JOptionPane.showInputDialog(null, "Επέλεξε στήλη για ανακάτεμα: ");
            int row = Integer.parseInt(InputChoice) - 1;
            if (row >= 0 && row < size) {//αν είναι έγκυρος ο δωσμένος αριθμός από τον χρήστη
                x = row * 105 + 5;
                tempMap.clear(); //καθαρίζεται εξ ολοκλήρου η tempMap
                for (int a = 0; a < size; a++) {
                    Point tempPoint = new Point(x, y);
                    for (Map.Entry<Point, Letter> entry : CardGraphs.LettersMap.entrySet()) {
                        Point Pkey = entry.getKey();
                        Letter val = entry.getValue();
                        if ((Pkey.x == tempPoint.x) && (Pkey.y == tempPoint.y)) {
                            tempMap.put(Pkey, val);
                        }
                    }
                    y += 105;
                }
                List<Letter> values = new ArrayList(tempMap.values());
                Collections.shuffle(values);//ανακατεύουμε την List με τα γράμματα που μόλις δώσαμε μέσω της tempMap
                for (int k = 0; k < size * size; k++) {
                    if (managefile.Shuffled_Chars.get(k).getPoint().x == x) {
                        managefile.Shuffled_Chars.set(k, values.get(0));
                        values.remove(0);
                    }
                }//ουσιαστικά πετάξαμε και ανακατέψαμε τα γράμματα της στήλης του πίνακα σε μία λίστα και τα ξαναπήραμε ανακατεμένα πίσω στην στήλη
                statt = false;//για να σταματήσει να τρέχει η while
            } else {
                JOptionPane.showMessageDialog(null, "Δεν έχει τέτοια στήλη ο πίνακας ξαναπροσπάθησε");
            }
        }
        //int Counter = 0;
//        for (int h = 0; h < size; h++) {
//            for (int k = 0; k < size; k++) {
//                System.out.print(managefile.Shuffled_Chars.get(Counter).getCharacter() + " ");
//                Counter++;
//            }
//            System.out.println();            
//        }
        gg.dispose();
        gg.MakeGameGraphs(size, ManageList.Shuffled_Chars);        
    }
    //παρομοίως κάνουμε αναδιάταξη σε μία γραμμή που δίνει ο χρηστης

    public void RearrangementLine(int size) {
        boolean statt = true;
        int y, x = 5;// για τις συντεταγμένες των γραμμάτων της σειράς
        Point Pkey, tempPoint; // για σύγκριση των σημείων των γραμμάτων
        while (statt) {
            String InputChoice = JOptionPane.showInputDialog(null, "επέλεξε γραμμή διαγραφής: ");
            int line = Integer.parseInt(InputChoice) - 1;
            if (line >= 0 && line < size) {//έλεγχος εγκυρότητας αριθμού
                y = line * 105 + 5;
                tempMap.clear();//άδειασμα της TempArray λίστας
                for (int a = 0; a < size; a++) {//τρέχει όσες φορές είναι το αριθμός γραμμάτων στη γραμμή
                    tempPoint = new Point(x, y); //manual πηγαίνει στο επόμενο σημείο, ίδια σειρά άλλη στήλη
                    for (Map.Entry<Point, Letter> entry : CardGraphs.LettersMap.entrySet()) {
                        Pkey = entry.getKey();
                        Letter val = entry.getValue();
                        if ((Pkey.x == tempPoint.x) && (Pkey.y == tempPoint.y)) {
                            tempMap.put(Pkey, val);
                        }
                    }
                    x += 105;
                }
                List<Letter> values = new ArrayList(tempMap.values());
                Collections.shuffle(values);//ανακατεύουμε την List με τα γράμματα που μόλις δώσαμε μέσω της tempMap
                //ξαναεπιστρέφουμε τα γράμματα πίσω με άλλη σειρά τώρα στην δωθείσα γραμμή
                for (int k = 0; k < size * size; k++) {
                    if (managefile.Shuffled_Chars.get(k).getPoint().y == y) {
                        managefile.Shuffled_Chars.set(k, values.get(0));
                        values.remove(0);
                    }
                }
                //ουσιαστικά πετάξαμε και ανακατέψαμε τα γράμματα της στήλης του πίνακα σε μία λίστα και τα ξαναπήραμε ανακατεμένα πίσω στην στήλη
                statt = false;//για να σταματήσει να τρέχει η while
            } else {
                JOptionPane.showMessageDialog(null, "Δεν έχει τέτοια γραμμή ο πίνακας ξαναπροσπάθησε");
            }
        }        
        gg.MakeGameGraphs(size, ManageList.Shuffled_Chars); //ανοίγει το παράθυρο με τα νέα δεδομένα        
    }
}
