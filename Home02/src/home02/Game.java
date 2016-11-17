package home02;

import java.util.*;

public class Game {

    public Game() {
    }

    Letter Array[][]; //global array
    private ManageList file = new ManageList();
    ArrayList<Letter> TempArray = new ArrayList();
    String Word = "";
    boolean stat=true;
    int LettersCounter = 0;
     
    public void StartGame(User user, int Choice) {
        Scanner in = new Scanner(System.in);
        Create_Table(Choice);
        LetsPlay(Choice);
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

    public void LetsPlay(int Choice) {

        User_Menu(Choice);
       
        System.out.println("Ας παίξουμε λοιπόν!");
        System.out.println("Επέλεξε γράμμα: ");
        ChosenLetter();
    }

    public void ChosenLetter() {
        Scanner in = new Scanner(System.in);
        System.out.print("Επέλεξε γραμμή γράμματος: ");
        int l = in.nextInt()-1;
        while (l < 0 && l >= Array.length) {
            System.out.print("Δώσε έγκυρο αριθμό: ");
            l = in.nextInt()-1;
        }
        System.out.print("Επέλεξε στήλη γράμματος: ");
        int r = in.nextInt()-1;
        while (r < 0 && r >= Array.length) {
            System.out.print("Δώσε έγκυρο αριθμό: ");
            r = in.nextInt()-1;
        }
        Word += Array[l][r].getCharacter();
        System.out.println("Ως τώρα η λέξη: " + Word);
       LettersCounter++;
        if (LettersCounter >0) {
            while(stat==true){
                CheckLetter(l,r);
            }
        }    
    }

    private void CheckLetter(int l,int r) {
        System.out.println("Επέλεξε γειτονικό γράμμα για να συνεχιστεί η λέξη: ");
        Scanner in = new Scanner(System.in);
        System.out.print("Επέλεξε γραμμή γράμματος: ");
        int l2 = in.nextInt()-1;
        while (Math.abs(l2-l) > 1) {
            System.out.print("Δώσε έγκυρο αριθμό: ");
            l2 = in.nextInt()-1;
        }
        System.out.print("Επέλεξε στήλη γράμματος: ");
        int r2 = in.nextInt()-1;
        while (Math.abs(r2-r) >1) {
            System.out.print("Δώσε έγκυρο αριθμό: ");
            r2 = in.nextInt()-1;
        }
        Word += Array[l2][r2].getCharacter();
        System.out.println("Ως τώρα η λέξη: " + Word);
        LettersCounter++;
        if(LettersCounter>2){
            System.out.println("Θές να συνεχίσεις;(y/n) ");
             char inCh=in.next().charAt(0);
             if(inCh=='n')
                  stat=false;
        }
    }

    public void User_Menu(int Choice) {
        boolean stat = true;
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
                Exchange_Letters();
            } else if (ch.equals("2")) {
                RemakeLine(Choice);
            } else if (ch.equals("3")) {
                Rearrangement(Choice);
            } else if (ch.equals("4")) {
                RearrangementRow();
            } else if (ch.equals("5")) {
                RearrangementLine();
            } else if (ch.equals("0")) {
                stat = false;
            }
            Display_Array();
        }
    }

    public void Exchange_Letters() {
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
            }
        }

    }

    public void RemakeLine(int Choice) {
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

    public void RearrangementRow() {
        System.out.println("Επέλεξε στήλη διαγραφής: ");
        Scanner in = new Scanner(System.in);
        int row = in.nextInt() - 1;
        TempArray.clear();
        for (int k = 0; k < Array.length; k++) {
            TempArray.add(Array[k][row]);
        }
        Collections.shuffle(TempArray);
        for (int k = 0; k < TempArray.size(); k++) {
            Array[k][row] = TempArray.get(k);
        }

    }

    public void RearrangementLine() {
        System.out.println("Επέλεξε γραμμή διαγραφής: ");
        Scanner in = new Scanner(System.in);
        int line = in.nextInt() - 1;
        TempArray.clear();
        for (int k = 0; k < Array.length; k++) {
            TempArray.add(Array[line][k]);
        }
        Collections.shuffle(TempArray);
        for (int k = 0; k < TempArray.size(); k++) {
            Array[line][k] = TempArray.get(k);
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
