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
    public Game() {
    }

    // πίνακας τύπου γράμματος/Letter 
    Letter Array[][];
    GameGraphics gp;
    ManageList file = new ManageList();// δημιουργία αντικειμένου τύπου κλάσσης ManageList για τη πρόσβαση μετά στις συναρτήσεις στη ManageList
    ArrayList<Letter> TempArray = new ArrayList();//λίστα που δέχεται αντικείμενα τύπου Letters, για διευκόλυνση του πίνακα χρησιμοποιείται 
    private boolean stat = true, statPoints = false;// μεταβλητές boolean για τις while και τον έλεγχο πόντων στο τέλος 
    private String Word = "";//η λέξη που αρχικοποιείται με κενό και γεμίζει με τα γράμματα που δίνει ο χρήστης
    private int LettersCounter = 0; //μετρητής των γραμμάτων
    private int WordCounter = 0;//μετρητής για τον αριθμό λέξεων έτσι ώστε μόνο όταν αυτός είναι πάνω από 5 να μπορεί να τελειώσει το παιχνίδι 
    //και να δει ο χρήστης αν νικησε ή όχι
    private final int WinPoints = 50;// για να δει ο χρήστης αν νίκησε θα πρέπει να έχει μαζέψει 50 πόντους
    // υπόψιν τους πόντους για να νικήσει ο χρήστης και τον αριθμό των λέξεων των ορίζουμε εμείς
    private int Points = 0, AllPoints = 0, counterDismiss = 0;
    // μεταβλητές των πόντων της λέξης, όλων των πόντων του χρήστη και μετρητής για την εμφάνιση του μενού αν είναι 0

    //η συνάρτηση αυτή καλείται από την κλάσση Home02 όπου και παίρνει παράμετρο τον χρήστη και την επιλογή μεγέθους για τον πίνακα
    public void StartGame(User user, int Choice) {
        Create_Table(Choice);//ξεκίνημα παιχνιδιού με τοκάλεσμα της συνάρτησης για τη δημιουργία και τις ενέργεις που κάνουμε στον πίνακα 
        LetsPlay(Choice, Word, user);// αφού δημιουργήθηκαν οι λίστες στην άλλη κλάσση ξεκινάει ο χρήστης και παίζει μέσω του καλέσματος της LetsPlay()
    }

    //μέθοδος για τη δημιουργία του πίνακα μέσω συναρτήσεων που είναι στην κλασση ManageList και τη καλεί το αντικείμενο file
    public void Create_Table(int Choice) {
        Array = new Letter[Choice][Choice];// δημιουργία πίνακα με τις διαστάσεις που μας έδωσε ο χρήστης 
        //παρακάτω καλούμε τις μεθόδους για τη διαχείρηση του αρχείου και των λεξεών του 
        file.OpenFile();
        file.ReadFile();
        file.Selected_Words(Choice);
        int count = 0;//μετρητής για τη θέση του γράμματος στην Shuffled_Chars
        GameGraphs gp = new GameGraphs(Choice, file.Shuffled_Chars);
//        for (int k = 0; k < Choice; k++) {//για τα γράμματα που θα βάλουμε στις θέσεις στον πίνακα έχουμε διπλή for 
//            // και η κάθε loop θα τρέχει μέχρι την επιλογή του χρήστη για τον τετραγωνικό πίνακα
//            for (int m = 0; m < Choice; m++) {
//                //στη κάθε θέση του πίνακα, γραμμή k και στήλη m βάζουμε το γράμμα που είναι στην θέση count στη λίστα Shuffled_Chars
//                Array[k][m] = file.Shuffled_Chars.get(count);
//                
//                count++;//για το γράμμα στην επόμενη θέση στην λίστα
//            }
//        }
//        Display_Array();//εμφάνιση του πίνακα μέσω συνάρτησης
    }

    //συνάρτηση για την εμφάνιση του μενου τη πρώτη φορά ή για το ξεκίνημα επιλογής γραμμάτων από τον χρήστη
    public void LetsPlay(int Choice, String Word, User user) {
        if (counterDismiss == 0) {//τη πρώτη φορά καλείται το μενου με τις επιλογές που μπορεί να κάνει ο χρήστης με τα γράμματα πριν παίξει
            User_Menu(Choice);
            counterDismiss++;
        }
        //αλλιώς ξεκινάει η επιλογή γραμμάτων από τον χρήστη για τη δημιουργία της λέξης
        //System.out.println("Ας παίξουμε λοιπόν!");
        System.out.println("Επέλεξε γράμμα: ");
        ChosenLetter(0, 0, user, Choice);// καλείται η συνάρτηση αυτή για την επιλογή του γράμματος
    }

    //συνάρτηση για τη σύγκριση γραμμάτων κλπ
    public void ChosenLetter(int l, int r, User user, int Choice) {
        Scanner in = new Scanner(System.in);//για την εισαγωγή γράμματος από τον χρήστη
        Display_Array();
        //αφού έχουμε 0 πόντους σημαίνει ότι είμαστε στο πρώτο γράμμα, που δεν έχει κάποιο γειτονικό για να έχουμε κάποιον έλεγχο γειτνίασης
        if (Points == 0) {
            System.out.print("Επέλεξε γραμμή πρώτου γράμματος: ");
            l = in.nextInt() - 1;// στον πίνακα θα είναι στη προηγούμενη θέση πρακτικά από αυτή που έδωσε ο χρήστης
            while (l < 0 || l >= Array.length) {
                System.out.print("Δώσε έγκυρο αριθμό μέσα στον πίνακα και θετικό: ");
                l = in.nextInt() - 1;
            }
            System.out.print("Επέλεξε στήλη πρώτου γράμματος: ");
            r = in.nextInt() - 1;
            while (r < 0 || r >= Array.length) {
                System.out.print("Δώσε έγκυρο αριθμό μέσα στον πίνακα και θετικό: ");
                r = in.nextInt() - 1;
            }
            if (Array[l][r].getCharacter() == '?') {// για την περίπτωση που είναι ο χαρακτήρας του Letter μπαλαντερ
                System.out.print("Δώσε επιθυμητό γράμμα: ");
                char balader = in.next().charAt(0);//διαβάζεται μεν String αλλά το πρώτο γράμμα, άρα σαν να διαβάζει char, δεν υπάρχει nextChar()
                Array[l][r] = file.SetBalader(balader);// βάζουμε στη θέση του πίνακα το γράμμα που αντικατέστησε τον balader στη συνάρτηση που καλούμε 
            }
            if (Color.BLUE.equals(Array[l][r].getColor())) {//αν βρεθεί γράμμα μπλε τότε αλλάζει η boolean για να διπλασιαστεί η λέξη 
                statPoints = true;
            }
            Word = ManagePoints(l, r, user, Choice);//καλούμε τη συνάρτηση για τη διαχείριση των πόντων στη λέξη
            //αναδρομικά καλούμε τη συνάρτηση πάλι για να πάει ο χρήστης στο δεύτερο γράμμα μέσω της ίδιας συνάρτησης
            //που θα δέχεται παράμετρο τις συντεταγμένες του προηγούμενος γράμματος για τη γειτνιαση και τον user και το μέγεθος του τετραγωνικού πίνακα
            ChosenLetter(l, r, user, Choice);
        } else {
            CheckWords(Choice, user);
        }
    }

    public void CheckWords(int Choice, User user) {
        Scanner in = new Scanner(System.in);
        if (WordCounter == 5) {
            System.out.println("Έγραψες 5 λέξεις, το παιχνίδι τελείωσε.");
            if (AllPoints >= WinPoints) {
                System.out.println("Νίκησες το παιχνίδι! Συγχαρητήρια!");
            } else {
                System.out.println("Λυπάμαι, έχασες :( ");
            }
            System.exit(0);//έμεσο τέλος παιχνιδιού 
        } else {// αν ακόμα δεν έχουμε περάσει τις 5 λέξεις και μπορεί ο χρήστης να γράψει και άλλη λέξη
            System.out.println("Θες να διακόψεις το παιχνίδι;(ν/ο) ");
            char inCh;
            do {// δίνει απάντηση ο ή ν, ότιδήποτε άλλο δεν είναι αποδεκτό για αυτό του ξαναζητάμε απάντηση
                inCh = in.next().charAt(0);
                if (inCh == 'ο') {
                    LetsPlay(Choice, Word, user);//για τη συνέχεια του παιχνιδιού ξανακαλούμε την LetsPlay για να ξαναψάξει από την αρχή λέξη
                } else if (inCh == 'ν') {
                    System.exit(0);//τέλος συστήματος και παιχνιδιού
                }
            } while (inCh != 'ν' && inCh != 'ο');
        }
    }

    //συνάρτηση για τη διαχείριση των πόντων στη λέξη και των επιλογών για τη λέξη σε κάθε φορά 
    public String ManagePoints(int l, int r, User user, int Choice) {
        Scanner in = new Scanner(System.in);
        Array[l][r].setSituation(true);//αλλάζει η κατάσταση του Letter έτσι ώστε να μη μπορεί να ξαναχρησιμοποιηθεί το γράμμα
        System.out.println(Array[l][r].getCharacter());
        System.out.println("Θες να πας μήπως στο μενού των βοηθητικών επιλογών; Αν ναι πάτα 1");
        System.out.println("Η θες να ακυρώσεις μήπως τις επιλογές σου; Αν ναι πάτα 2");
        System.out.println("Διαφορετικά πάτα άλλο αριθμό");
        char nextCho = in.next().charAt(0);
        if (nextCho == '1')//πάμε στο μενού των βοηθητικών επιλογών 
        {
            System.out.println("Πάμε πίσω στο μενού επιλογών. Υπόψιν οποιαδήποτε αλλαγή θα αλλάξει και το χτύσιμο ως τώρα της λέξης");
            User_Menu(Choice);
        } else if (nextCho == '2') {//αν ακυρώσει τις επιλογές του όλα τα γράμματα πρέπει να μπορούν να χρησιμοποιηθούν πάλι ξανα
            for (int k = 0; k < Array.length; k++) {
                for (int m = 0; m < Array[k].length; m++) {
                    Array[k][m].setSituation(false);//έμμεση ελευθέρωση του γράμματος
                }
            }
            Word = "";// και αφού ξεκινάμε πάλι από την αρχή τη λέξη, την θέτουμε ως κενή
            LetsPlay(Choice, Word, user);
        } //else 
        Word += Array[l][r].getCharacter();//η λέξη αυξάνεται με το γράμμα επιλογής του χρήστη
        System.out.println("Ως τώρα η λέξη: " + Word);
        LettersCounter++;//αυξάνεται ο αριθμός γραμμάτων
        Points += Array[l][r].getValue();//οι πόντοι του γράμματος προσθέτονται στην λέξη
        System.out.println("Ως τώρα οι πόντοι: " + Points);
        if (LettersCounter > 2) {//από τρία και πάνω γράμματα μπορείς ο χρήστης να ελέγξει την λε΄ξη που σχημάτησε
            System.out.println("Θές να ελέγξεις τη λέξη;(ν/ο) ");
            char inCh = in.next().charAt(0);
            if (inCh == 'y') {
                stat = false;
                if (statPoints == true) {//αν υπήρξε γράμμα μπλε, τότε διπλασιάζεται η βαθμολογία της λέξης
                    Points = 2 * Points;
                }
                //παρουσιάζουμε τους πόντους της λέξης που έγραψε ο χρήστης αν τη βρούμε στο αρχείο μέσω της SearchWord
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
        return Word;//πρακτικά δε θα καλεστεί ποτέ αυτή η εντολή
    }

    // εύρεση της δωσμένης από τον χρήστη λέξη στο αρχείο
    public boolean SearchWord() {
        int counter = 0;//μετρητής για την εύρεση της λέξης
        for (int i = 0; i < file.AllWordsList.size(); i++) {
            if (Word.equals(file.AllWordsList.get(i))) {//αν βρέθηκε η λέξη στο αρχείο
                counter++;
                System.out.println("Βρήκες την λέξη");
                ReplaceWords();//αντικάτασταση της λέξης μέσω της μεθόδου αυτής
                Word = "";// η λέξη γίνεται πάλι έμμεσα κενή για να γεμίσει ξανά από τα γράμματα της επόμενης λέξης
                LettersCounter = 0;//αντίστοιχα ο μετρητής των γραμμάτων της λέξης μηδενίζεται και αυτός
            }
        }// αν ο μετρητής δεν είναι θετικός τότε σημαίνει ότι δε βρέθηκε η λέξη
        if (counter == 0) {
            System.out.println("Δεν υπάρχει αυτή η λέξη στο αρχείο");
            //ξαναθέτουμε όλα τις καταστάσεις των γράμματων σαν false για να είναι διαθέσιμα τα γράμματα όλα πάλι
            for (int k = 0; k < Array.length; k++) {
                for (int m = 0; m < Array[k].length; m++) {
                    Array[k][m].setSituation(false);
                }
            }
            return false;//false επιστροφή αφού δε βρέθηκε η λέξη και μπήκε στην if
        }//αλλιώς επιστροφή true αφού βρέθηκε η λέξη και δε μπήκε στην if πριν
        return true;
    }

    //αντικατάσταση λέξεων οι οποίες βρήκε ο χρήστης
    public void ReplaceWords() {
        int count = 0;//για την θέση του γράμματος που θα πάρουμε από την Shuffled_Chars για να το πετάξουμε στον πίνακα
        file.Selected_Words(Array.length);//νέ
        for (int k = 0; k < Array.length; k++) {//τρέχουμε όλα τα στοιχεία του πίνακα τύπου Letters
            for (int m = 0; m < Array[k].length; m++) {
                //αν βρεθεί γράμμα που έχει χαρακτηριστεί false η κατάσταση του, δηλαδή χρησιμοποιήθηκε για λέξη αντικαθιστούμε με νέο γράμμα
                if (Array[k][m].isSituation() == true) {
                    Array[k][m] = file.Shuffled_Chars.get(count);//το χρησιμοποιημένο γράμμα το αντικαθιστούμε με αυτό στην θέση count στην Shuffled_Chars 
                    count++;//στην επόμενη θέση της Shuffled_Chars πάμε
                    Array[k][m].setSituation(false);//και θέτουμε την κατάσταση του false για να μπορεί να χρησιμοποιηθεί
                }
            }
        }
    }

    //εμφάνιση μενού επιλογών για τη διαμορφωποίηση του πίνακα πριν ξεκινήσει ο χρήστης να επιλέγει γράμματα
    public void User_Menu(int Choice) {
        boolean stat = true;
        int localCount = 0; //μετρητής για τις φόρες που επιλέγονται οι επιλογές αφού ο χρήστης μπορεί ως 15 φόρες να τις επιλέξει
        int FirstCount = 0;//μετρητής για τις φόρες που επιλέγεται η πρώτη επιλογή αφού ο χρήστης μπορεί ως 3 φόρες να τη επιλέξει
        //ομοίως και για τις άλλες επιλογές, ως 3 φορές μπορούν να επιλεχθούν
        int SecondCount = 0;
        int ThirdCount = 0;
        int FourthCount = 0;
        int FifthCount = 0;

        while (stat) {//όσο η μεταβλητή είναι true
//            System.out.println("1) Αντάλλαξε 2 γράμματα.");
//            System.out.println("2) Διαγραφή γραμμής και αντικατάσταση της.");
//            System.out.println("3) Αναδιάταξη γραμμάτων.");
//            System.out.println("4) Αναδιάταξη στήλης.");
//            System.out.println("5) Αναδιάταξη γραμμής.");
//            System.out.println("Επέλεξε ένα απο τις παραπάνω κατηγορίες ή πληκτρολόγησε 0 για να συνεχίσεις.");
            int ch0 = 0;
            //ch0 = gp.UserMenu_Display();
            String ch = ch0 + "";
            if (!ch.equals("0") && !ch.equals("1") && !ch.equals("2") && !ch.equals("3") && !ch.equals("4") && !ch.equals("5")) {
                System.out.println("Δεν υπάρχει αυτή η επιλογή, ξαναδώσε.");

            }//θεωρούμε ότι ο χρήστης δίνει αριθμό και όχι κάποιο χαρακτήρα πχ
            if (ch.equals("1")) {
                if (FirstCount < 3) {
                    localCount++;
                    FirstCount++;
                    Exchange_Letters();//μέθοδος ανταλλαγής γραμμάτων
                } else {
                    System.out.println("Επέλεξες ήδη 5 φορές αυτή την επιλογή δε γίνεται άλλο");
                }
            } else if (ch.equals("2")) {
                if (SecondCount < 3) {
                    localCount++;
                    SecondCount++;
                    RemakeLine(Choice);//μέθοδος αντικατάσταση γραμμής
                } else {
                    System.out.println("Επέλεξες ήδη 5 φορές αυτή την επιλογή δε γίνεται άλλο");
                }
            } else if (ch.equals("3")) {
                if (ThirdCount < 3) {
                    localCount++;
                    ThirdCount++;
                    Rearrangement(Choice);//μέθοδο αναδιάταξης όλων των γραμμάτων του πίνακα
                } else {
                    System.out.println("Επέλεξες ήδη 5 φορές αυτή την επιλογή δε γίνεται άλλο");
                }
            } else if (ch.equals("4")) {
                if (FourthCount < 3) {
                    localCount++;
                    FourthCount++;
                    RearrangementRow();//μέθοδο αναδιάταξη όλων των γραμμάτων μιας σειράς
                } else {
                    System.out.println("Επέλεξες ήδη 5 φορές αυτή την επιλογή δε γίνεται άλλο");
                }
            } else if (ch.equals("5")) {
                if (FifthCount < 3) {
                    localCount++;
                    FifthCount++;
                    RearrangementLine();//μέθοδο αναδιάταξη γραμμάτων μίας γραμμής
                } else {
                    System.out.println("Επέλεξες ήδη 5 φορές αυτή την επιλογή δε γίνεται άλλο");
                }
            } else if (ch.equals("10")) {
                stat = false;//με την επιλογή 0 ο χρήστης προχωράει στην επιλογή γραμμάτων και φεύγει από το μενού
            }
            if (!ch.equals("0")) {
                Display_Array();//κάθε φόρα που επιλέγεται κάποια από τις παραπάνω επιλογές πέραν της τελευταίας εμφανίζεται ο πίνακας ξανά αλλαγμένος
            }
            if (localCount >= 15) {
                System.out.println("Χρησιμοποίησες ήδη 15 φορές όλες τις επιλογές. Δε γίνεται περισσότερο");
                stat = false;
            }
        }
    }

    //ανταλλαγή 2 γραμμάτων στον πίνακα από τον χρήστη
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
            //όσο και οι δύο θέσεις για τα γράμματα είναι όντως στον πίνακα, και θετικές
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

    //αναδιάταξη γραμμάτων πρώτα της λίστας με τα γράμματα, απλά μέσω της έτοιμης εντολής από την βιβλιοθήκη shuffle
    public void Rearrangement(int Choice) {
        Collections.shuffle(file.Shuffled_Chars);
        int count = 0;
        //μετά την αλλαγμένη λίστα πετάμε τα γράμματα στον πίνακα, ουσιαστικά ανακατέψαμε έμμεσα τον πίνακα 
        //μέσω του ανακατέματος της λίστας που έχει ακριβώς τα ίδια γράμματα, αφού εκείνη έδωσε τα γράμματα στον πίνακα
        for (int k = 0; k < Array.length; k++) {
            for (int m = 0; m < Array.length; m++) {
                Array[k][m] = file.Shuffled_Chars.get(count);
                count++;
            }
        }
    }

    //αντικατάσταση γραμμάτων μίας γραμμής που επιλέγει παρακάτω ο χρήστης
    public void RemakeLine(int Choice) {
        boolean statt = true;
        while (statt) {
            System.out.println("Επέλεξε γραμμή διαγραφής: ");
            Scanner in = new Scanner(System.in);
            int line = in.nextInt() - 1;
            int count = 0;//για την θέση επιλογής του γράμματος από την Shuffled_Chars
            file.Selected_Words(Choice);// για μία γραμμή παίρνουμε μέσω της Selected_Words νέα γράμματα 
            if (line < Array.length && line >= 0) {//έλεγχος εγκυρότητας
                for (int k = 0; k < Array.length; k++) {
                    //γεμίζουμε την επιλεγμένη γραμμή με τα νέα γράμματα από την Shuffled_Chars
                    Array[line][k] = file.Shuffled_Chars.get(count);
                    count++;//στην επόμενη θέση/γράμμα της Shuffled_Chars
                }
                statt = false;
            } else {
                System.out.println("Δεν έχει τέτοια γραμμή ο πίνακας ξαναπροσπάθησε");
            }
        }
    }

    //αναδιάταξη γραμμάτων μίας στήλης που επιλέγει παρακάτω ο χρήστης
    public void RearrangementRow() {
        boolean statt = true;
        while (statt) {
            System.out.println("Επέλεξε στήλη αναδιάταξης: ");
            Scanner in = new Scanner(System.in);
            int row = in.nextInt() - 1;
            if (row >= 0 && row < Array.length) {//αν είναι έγκυρος ο δωσμένος αριθμός από τον χρήστη
                TempArray.clear();//καθαρίζεται εξ ολοκλήρου η λίστα temp
                for (int k = 0; k < Array.length; k++) {
                    TempArray.add(Array[k][row]);// και πετάμε τα γράμματα της στήλης row του πίνακα στην λίστα μας
                }
                Collections.shuffle(TempArray);//ανακατεύουμε την TempArray με τα γράμματα που μόλις δώσαμε
                for (int k = 0; k < TempArray.size(); k++) {
                    Array[k][row] = TempArray.get(k);//και πίσω πάλι στον πίνακα στη στήλη row πετάμε τα γράμματης της TempArray
                }//ουσιαστικά πετάξαμε και ανακατέψαμε τα γράμματα της στήλης του πίνακα σε μία λίστα και τα ξαναπήραμε ανακατεμένα πίσω στην στήλη
                statt = false;//για να σταματήσει να τρέχει η while
            } else {
                System.out.println("Δεν έχει τέτοια στήλη ο πίνακας ξαναπροσπάθησε");
            }
        }
    }

    //παρομοίως κάνουμε αναδιάταξη σε μία γραμμή που δίνει ο χρληστης
    public void RearrangementLine() {
        boolean statt = true;
        while (statt) {
            System.out.println("Επέλεξε γραμμή αναδιάταξης: ");
            Scanner in = new Scanner(System.in);
            int line = in.nextInt() - 1;
            if (line >= 0 && line < Array.length) {//έλεγχος εγκυρότητας αριθμού
                TempArray.clear();//άδειασμα της TempArray λίστας
                for (int k = 0; k < Array.length; k++) {
                    TempArray.add(Array[line][k]);//της προσθέτουμε τα γράμματα της επιθυμητής γραμμής
                }
                Collections.shuffle(TempArray);//ανακατεύουμε τα γράμματα της TempArray
                for (int k = 0; k < TempArray.size(); k++) {
                    Array[line][k] = TempArray.get(k);//και τα πετάμε πίσω στην γραμμή ανακατεμένα
                }
                statt = false;//σταματάει η επανάληψη έμεσα
            } else {
                System.out.println("Δεν έχει τέτοια στήλη ο πίνακας ξαναπροσπάθησε");
            }
        }
    }

    // εμφανιση των Letters του πίνακα μέσω κάλεσματος της toString που έχει γίνει overwrite στην κλάσση Letter 
    //για την εμφάνιση των ιδιοτήτων της κλάσσης της
    public void Display_Array() {
        for (int k = 0; k < Array.length; k++) {
            for (int m = 0; m < Array.length; m++) {
                System.out.print(Array[k][m].toString() + " ");
            }
            System.out.println("\n");

        }

    }
}
