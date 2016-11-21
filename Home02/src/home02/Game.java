package home02;
//icsd14134 Bonis Athanasios - icsd11039 Dimopoulos Georgios

/*
Εδώ το παιχνίδι, όπου δημιουργείται αντικείμενο τύπου ManageList
και παιζεί ο χρήστης ουσιαστικά με τις επιλογές γραμμάτων για τη λέξη. 
Υπάρχουν σε αυτή τη κλάσση με άλλα λόγια μέθοδοι
*/
import java.awt.Color;
import java.util.*;

public class Game {

    // άδειος constructor
    public Game() {}
     
    // πίνακας τύπου γράμματος/Letter 
    Letter Array[][];

    // δημιουργία αντικειμένου τύπου κλάσσης ManageList για τη πρόσβαση μετά στις συναρτήσεις στη ManageList
    ManageList file = new ManageList();
    ArrayList<Letter> TempArray = new ArrayList();
    private boolean stat = true, statPoints = false;
    private String Word = "";
    private int LettersCounter = 0;
    private int WordCounter = 0;
    private final int WinPoints = 50;
    private int Points = 0, AllPoints = 0, counterDismiss = 0;

    public void StartGame(User user, int Choice) {
        Create_Table(Choice);
        LetsPlay(Choice, Word, user);
    }

    public void Create_Table(int Choice) {
        Array = new Letter[Choice][Choice];

        file.OpenFile();
        file.ReadFile();
        file.Selected_Words(Choice);
        int count = 0;
        for (int k = 0; k < Choice; k++) {
            for (int m = 0; m < Choice; m++) {
                Array[k][m] = file.Shuffled_Chars.get(count);
                count++;
            }
        }
        Display_Array();
    }

    public void LetsPlay(int Choice, String Word, User user) {

        if (counterDismiss == 0) {
            User_Menu(Choice);
            counterDismiss++;
        }
        System.out.println("Ας παίξουμε λοιπόν!");
        System.out.println("Επέλεξε γράμμα: ");
        ChosenLetter(0, 0, user, Choice);
    }

    public void ChosenLetter(int l, int r, User user, int Choice) {
        Scanner in = new Scanner(System.in);
        Display_Array();
        if (Points == 0) { //first letter
            System.out.print("Επέλεξε γραμμή πρώτου γράμματος: ");
            l = in.nextInt() - 1;
            while (l < 0 || l >= Array.length) {
                System.out.print("Δώσε έγκυρο αριθμό: ");
                l = in.nextInt() - 1;
            }
            System.out.print("Επέλεξε στήλη πρώτου γράμματος: ");
            r = in.nextInt() - 1;
            while (r < 0 || r >= Array.length) {
                System.out.print("Δώσε έγκυρο αριθμό: ");
                r = in.nextInt() - 1;
            }
            if (Array[l][r].getCharacter() == '?') {
                System.out.print("Δώσε επιθυμητό γράμμα: ");
                char balader = in.next().charAt(0);
                Array[l][r] = file.SetBalader(balader);
            }
            if (Color.BLUE.equals(Array[l][r].getColor())) {
                statPoints = true;
            }
            Word = ManagePoints(l, r, user, Choice);
            ChosenLetter(l, r, user, Choice);
        } else { // next letters
            int l2;
            int r2;
            do {
                System.out.println("Επέλεξε γειτονικό γράμμα για να συνεχιστεί η λέξη: ");
                System.out.print("Επέλεξε γραμμή γράμματος: ");
                l2 = in.nextInt() - 1;
                while (Math.abs(l2 - l) > 1) {
                    System.out.print("Δώσε έγκυρο αριθμό: ");
                    l2 = in.nextInt() - 1;
                }
                System.out.print("Επέλεξε στήλη γράμματος: ");
                r2 = in.nextInt() - 1;
                while (Math.abs(r2 - r) > 1) {
                    System.out.print("Δώσε έγκυρο αριθμό: ");
                    r2 = in.nextInt() - 1;
                }
                if(l2==l && r2==r){
                    System.out.println("Επέλεξες να ακυρώσεις το τελευταίο γράμμα");
                    Array[l2][r2].setSituation(false);
                    StringBuilder sb = new StringBuilder(Word);
                    sb.deleteCharAt(Word.length()-1);
                    Word = sb.toString();
                    ChosenLetter(l, r, user, Choice);
                }
            } while (Array[l2][r2].isSituation() == true);
            if (Array[l2][r2].getCharacter() == '?') {
                System.out.print("Δώσε επιθυμητό γράμμα: ");
                char balader = in.next().charAt(0);
                Array[l2][r2] = file.SetBalader(balader);
            }
            if (Color.BLUE.equals(Array[l][r].getColor())) {
                statPoints = true;
            }
            Word = ManagePoints(l2, r2, user, Choice);
            while (stat == true) {
                ChosenLetter(l2, r2, user, Choice);
            }
        }
        if (WordCounter == 5) {
            System.out.println("Έγραψες 5 λέξεις, το παιχνίδι τελείωσε.");
            if (AllPoints >= WinPoints) {
                System.out.println("Νίκησες το παιχνίδι! Συγχαρητήρια!");
            } else {
                System.out.println("Λυπάμαι, έχασες :( ");
            }
            System.exit(0);
        } else {
            System.out.println("Θες να διακόψεις το παιχνίδι;(ν/ο) ");
            char inCh;
           do{
            inCh = in.next().charAt(0);
            if (inCh == 'ο') {
                LetsPlay(Choice, Word, user);
            } else if (inCh == 'ν') {
                System.exit(0);
            }
           }while(inCh!='ν' && inCh!='ο');
        }
    }

    public String ManagePoints(int l, int r, User user, int Choice) {
        Scanner in = new Scanner(System.in);
        Array[l][r].setSituation(true);
        System.out.println(Array[l][r].getCharacter());
        System.out.println("Θες να ακυρώσεις μήπως τις επιλογές σου;(ν/ο)");
        if (in.next().charAt(0) == 'ν') {
            for (int k = 0; k < Array.length; k++) {
                for (int m = 0; m < Array[k].length; m++) {
                    Array[k][m].setSituation(false);
                }
            }
            Word = "";
            LetsPlay(Choice, Word, user);
        }
        Word += Array[l][r].getCharacter();
        System.out.println("Ως τώρα η λέξη: " + Word);
        LettersCounter++;
        Points += Array[l][r].getValue();
        System.out.println("Ως τώρα οι πόντοι: " + Points);
        if (LettersCounter > 2) {
            System.out.println("Θές να ελέγξεις τη λέξη;(ν/ο) ");
            char inCh = in.next().charAt(0);
            if (inCh == 'ν') {
                stat = false;
                if (statPoints == true) {
                    Points = 2 * Points;
                }
                if (SearchWord() == true) {
                    AllPoints += Points;
                    WordCounter++;
                    System.out.println("Συνολικόι πόντοι λέξης: " + Points);
                    System.out.println("Συνολικόι πόντοι λέξεων: " + AllPoints);
                    user.setPoints(AllPoints);
                    Points = 0;

                }
            }
        }
        return Word;
    }

    public boolean SearchWord() {
        int counter = 0;
        for (int i = 0; i < file.AllWordsList.size(); i++) {
            if (Word.equals(file.AllWordsList.get(i))) {
                counter++;
                System.out.println("Βρήκες την λέξη");
                ReplaceWords();
                Word = "";
                LettersCounter = 0;
            }
        }
        if (counter == 0) {
            System.out.println("Δεν υπάρχει αυτή η λέξη στο αρχείο");
            for (int k = 0; k < Array.length; k++) {
                for (int m = 0; m < Array[k].length; m++) {
                    Array[k][m].setSituation(false);
                }
            }
            return false;
        }
        return true;
    }

    public void ReplaceWords() {
        int count = 0;
        file.Selected_Words(Array.length);
        for (int k = 0; k < Array.length; k++) {
            for (int m = 0; m < Array[k].length; m++) {
                if (Array[k][m].isSituation() == true) {
                    Array[k][m] = file.Shuffled_Chars.get(count);
                    count++;
                    Array[k][m].setSituation(false);
                }

            }
        }
    }

    public void User_Menu(int Choice) {
        boolean stat = true;
        int localCount = 0;
        while (stat) {
            System.out.println("1) Αντάλλαξε 2 γράμματα.");
            System.out.println("2) Διαγραφή γραμμής και αντικατάσταση της.");
            System.out.println("3) Αναδιάταξη γραμμάτων.");
            System.out.println("4) Αναδιάταξη στήλης.");
            System.out.println("5) Αναδιάταξη γραμμής.");
            System.out.println("Επέλεξε ένα απο τις παραπάνω κατηγορίες ή πληκτρολόγησε 0 για να συνεχίσεις.");
            Scanner in = new Scanner(System.in);
            String ch = in.next();
            while (!ch.equals("0") && !ch.equals("1") && !ch.equals("2") && !ch.equals("3") && !ch.equals("4") && !ch.equals("5")) {
                System.out.println("Δεν υπάρχει αυτή η επιλογή, ξαναδώσε.");
                ch = in.next();
            }
            if (ch.equals("1")) {
                localCount++;
                Exchange_Letters();
            } else if (ch.equals("2")) {
                localCount++;
                RemakeLine(Choice);
            } else if (ch.equals("3")) {
                localCount++;
                Rearrangement(Choice);
            } else if (ch.equals("4")) {
                localCount++;
                RearrangementRow();
            } else if (ch.equals("5")) {
                localCount++;
                RearrangementLine();
            } else if (ch.equals("0")) {
                stat = false;
            }
            if (!ch.equals("0")) {
                Display_Array();
            }
            if (localCount == 5) {
                System.out.println("Χρησιμοποίησες ήδη 5 φορές τις επιλογές. Δε γίνεται άλλο");
                stat = false;
            }
        }
    }

    public void Exchange_Letters() {
        boolean statt = true;
        while (statt) {
            System.out.println("Πρώτο Γράμμα: ");
            Scanner in = new Scanner(System.in);
            System.out.print("Δώσε την θέση στην γραμμή: ");
            int x1 = in.nextInt() - 1;
            System.out.print("Δώσε την θέση στην στήλη: ");
            int y1 = in.nextInt() - 1;
            System.out.println("Δεύτερο Γράμμα: ");
            System.out.print("Δώσε την θέση στην γραμμή: ");
            int x2 = in.nextInt() - 1;
            System.out.print("Δώσε την θέση στην στήλη: ");
            int y2 = in.nextInt() - 1;
            if (x1 < Array.length && x2 < Array.length && x1 >= 0 && x2 >= 0) {
                if (y1 < Array[x1].length && y2 < Array[x2].length) {
                    Letter temp = Array[x1][y1];
                    Array[x1][y1] = Array[x2][y2];
                    Array[x2][y2] = temp;
                    statt = false;
                }
            } else {
                System.out.println("Λάθος συντεταγμένες για τα γράμματα ξαναδώσε");
            }
        }

    }

    public void Rearrangement(int Choice) {
        Collections.shuffle(file.Shuffled_Chars);
        int count = 0;
        for (int k = 0; k < Array.length; k++) {
            for (int m = 0; m < Array.length; m++) {
                Array[k][m] = file.Shuffled_Chars.get(count);
                count++;
            }

        }
    }

    public void RemakeLine(int Choice) {
        boolean statt = true;
        while (statt) {
            System.out.println("Επέλεξε γραμμή διαγραφής: ");
            Scanner in = new Scanner(System.in);
            int line = in.nextInt() - 1;
            int count = 0;
            file.Selected_Words(Choice);
            if (line < Array.length && line >= 0) {
                for (int k = 0; k < Array.length; k++) {
                    Array[line][k] = file.Shuffled_Chars.get(count);
                    count++;
                }
                statt = false;
            } else {
                System.out.println("Δεν έχει τέτοια γραμμή ο πίνακας ξαναπροσπάθησε");
            }
        }
    }

    public void RearrangementRow() {
        boolean statt = true;
        while (statt) {
            System.out.println("Επέλεξε στήλη διαγραφής: ");
            Scanner in = new Scanner(System.in);
            int row = in.nextInt() - 1;
            if (row >= 0 && row < Array.length) {
                TempArray.clear();
                for (int k = 0; k < Array.length; k++) {
                    TempArray.add(Array[k][row]);
                }
                Collections.shuffle(TempArray);
                for (int k = 0; k < TempArray.size(); k++) {
                    Array[k][row] = TempArray.get(k);
                }
                statt = false;
            } else {
                System.out.println("Δεν έχει τέτοια στήλη ο πίνακας ξαναπροσπάθησε");
            }
        }
    }

    public void RearrangementLine() {
        boolean statt = true;
        while (statt) {
            System.out.println("Επέλεξε γραμμή διαγραφής: ");
            Scanner in = new Scanner(System.in);
            int line = in.nextInt() - 1;
            if (line >= 0 && line < Array.length) {
                TempArray.clear();
                for (int k = 0; k < Array.length; k++) {
                    TempArray.add(Array[line][k]);
                }
                Collections.shuffle(TempArray);
                for (int k = 0; k < TempArray.size(); k++) {
                    Array[line][k] = TempArray.get(k);
                }
                statt = false;
            } else {
                System.out.println("Δεν έχει τέτοια στήλη ο πίνακας ξαναπροσπάθησε");
            }
        }
    }

    public void Display_Array() {
        for (int k = 0; k < Array.length; k++) {
            for (int m = 0; m < Array.length; m++) {
                System.out.print(Array[k][m].toString() + " ");
            }
            System.out.println("\n");

        }

    }
}
