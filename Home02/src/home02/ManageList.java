package home02;
//icsd14134 Bonis Athanasios - icsd11039 Dimopoulos Georgios

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ManageList {

    // καλούμε εκτός συναρτήσεων τον Scanner από την βιβλιοθήκη της Java
    private Scanner in;
    
    /* η AllWordsList αποθηκεύει από το αρχείο που έχουμε κάνει στον ίδιο φάκελο
    όλες τις λέξεις που βρίσκει, όπου σε κάθε σειρά του αρχείου έχουμε βάλει μία λέξη*/
    ArrayList<String> AllWordsList = new ArrayList();
    
    /* η λίστα WordList κρατάει και αυτή τις λέξεις του αρχείου αλλά κάθε φορά που χρησιμοποιούνται τα γράμματα μίας λέξης 
    στο παιχνίδι τότε στην WordList αφαιρούνται αυτά τα γράμματα, δηλαδή η λέξη
    έτσι ώστε να μην ξαναχρησιμοποιούνται σε κάποιο γύρο στο παιχνίδι μετά*/
    ArrayList<String> WordList = new ArrayList();
    
    /*η TempWordList χρησιμοποιείται για να αποθηκεύσουμε τις λέξεις στον κάθε γύρο.*/
    ArrayList<String> TempWordList = new ArrayList();
    /*Από τις λέξεις της TempWordList παίρνουμε μετα και πετάμε τα γράμματα τους στην Shuffled_Chars
    για τη δημιουργία μετά του πίνακα με του οποίου τα γράμματα ο χρήστη θα παίξει και θα φτιάξει λέξεις*/
    ArrayList<Letter> Shuffled_Chars = new ArrayList();

    //μέθοδος μέσω της οποίας ανόιγουμε το αρχείο που είναι στον ίδιο φάκελο με τον κώδικα στο source
    public void OpenFile() {
        try {// ανοίγουμε μέσα σε try catch το αρχείο με τον scanner 
            in = new Scanner(new File("src/home02/Wordlist.txt"));
        } catch (FileNotFoundException e) { // αν δε βρέθηκε το αρχείο με τις λέξεις
            System.out.println("Το αρχείο δεν βρέθηκε.");
        }
    }

    // διαβάζουμε το αρχείο τώρα και ταυτόχρονα πετάμε τη λέξη που βρίσκει στη κάθε σειρά στις 2 λίστες
    public void ReadFile() {
        String Word = in.next();// ανάγνωση των λέξεων του αρχείου
        while (in.hasNext()) {//όσο διαβάζει ο scanner λέξεις στο αρχείο
            Word = in.next();//αποθηκεύουμε τη λέξη σε συμβολοσειρά
            WordList.add(Word); // τη λέξη την προσθέτουμε στην WordList, αργότερα αφαιρούνται αυτές που έχουν παιχτεί τα γραμματά τους
            AllWordsList.add(Word); // τη λέξη την προσθέτουμε και στην AllWordList που κρατά όλες τις λέξεις του αρχείου μόνιμα
        }
        Collections.shuffle(WordList);// ανακατεύουμε τις λέξεις της Wordlist για να μην επιλεχθούν μετά με τη σειρά τα γραμματά τους
        in.close(); //ασφαλές κλείσιμο του Scanner
    }

    /*στη μέθοδο αυτή στέλνουμε στην TempWordList από την WordList τόσες λέξεις ώστε το σύνολο των γραμμάτων των λέξεων αυτών
    να είναι μικρότερο ή ίσο από το μέγεθος*μέγεθος που δίνει ο χρήστης  */
    public void Selected_Words(int Choice) {
        int counter = 0; // μετρητής για όλα τα γράμματα των λέξεων που παίρνει η WordList
        for (int i = 0; i < WordList.size(); i++) { 
            //εάν ο μετρητής  συν τον αριθμό των γραμμάτων της τωρινής λέξης είναι μικρότερος από το δυνατό αριθμό γραμμάτων 
            //που χωραέι ο πίνακας με το δωσμένο μεγεθος, δηλαδή χωράνε στον πίνακα ακόμα γράμματα από τις λέξεις της WordList
            if (counter + WordList.get(i).length() < Choice * Choice) {
                TempWordList.add(WordList.get(i));//πετάμε στην TempWordList τη λέξη που θα χρησιμοποιηθούν μετά τα γραμματά της για το παιχνίδι
                counter += WordList.get(i).length();//αυξάνουμε τον μετρητή των γραμμάτων των λέξεων οι οποίες είναι στην WordList
                WordList.remove(i); // αφαιρούμε από την WordList την λέξη έτσι ώστε να μη ξαναπάιξει ο χρήστης σε επόμενο γύρο
            }
        }
        //συμβολοσειρά η οποία κρατάει όλα τα γράμματα της αλφάβητου
        String alphabet = "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ";
        char extra; // επιλογή ενός γράμματος από το πάνω String
        //για τις θέσεις του πίνακα που έμειναν κενές από γράμματα τα γεμίζουμε με τυχαία γράμματα 
        //έμμεσα από το ελληνικό αλφάβητο ή πιο άμεσα μέσω από την alphabet από πάνω
        for (int i = 0; i < (Choice * Choice) - counter; i++) {// μείον του counter γιατί αυξήθηκε πριν βγει από την for πριν
            Random rnd = new Random();// για τυχαία επιλογή γράμματος
            int j = rnd.nextInt(23);//τυχαίος αριθμός για την επιλογή ενός από τα 23 γράμματα
            extra = alphabet.charAt(j);//επιλογή του γράμματος που βρίσκεται στην παραπάνω τυχαία θέση στο String alphabet
            // προσθήκη αυτού του γράμματος στην TempWordList
            // η οποία επειδή είναι τύπου String κάνουμε casting το γράμα από χαρακτήρα σε συμβολοσειρά τον τύπο του
            TempWordList.add(Character.toString(extra)); 
        }
        CharList();//καλούμε την συναρτηση CharList 
    }

    public void CharList() {

        //εδώ παίρνουμε το κάθε γράμμα της κάθες λέξης της TempWordList και το πετάμε στη λίστα Shuffled_Chars τύπου Character
        for (int k = 0; k < TempWordList.size(); k++) {// τρέχει για όλο το σύνολο των λέξεων της TempWordList
            for (int m = 0; m < TempWordList.get(k).length(); m++) { // τρέχει τη κάθε λέξη της TempWordList για όλα τα γράμματα της
                Shuffled_Chars.add(SetLetter(k, m));// και πετάει το κάθε γράμμα που βρίσκει στη Shuffled_Chars
            }
        }
        // αφού γεμίσαμε την Shuffled_Chars με τα γράμματα των επιλεγμένων λέξεων ανακατεύουμε τα γράμματα  αυτά για τη διευκόλυνση του παιχνιδιού
        Collections.shuffle(Shuffled_Chars);
    }

    // στη μέθοδο παρακάτω θα φτιάξουμε ουσιαστικά το κάθε γράμμα και τα γνωρίσματα του
    public Letter SetLetter(int k, int m) {
        Random rnd;// για την επιλογή του τυχαίου χρώματος που θα έχει το κάθε γράμμα
        rnd = new Random();
        Letter letter = null; //δημιουργία αναφοράς τύπου Letter, όχι αντικείμενο!
        int ch; // τα πιθανά χρώματα είναι 3 για το γράμμα άρα ο τυχαίος αριθμός που παίρνουμε είναι από 1 μέχρι 3
        ch = rnd.nextInt(3) + 1;
        if (ch == 1) {// για την επιλογή 1 το Letter θα είναι αντικείμενο τύπου της υποκλάσσης WhiteLetter
            letter = new WhiteLetter();
            //καλείται η setColor της υποκλάσης της Letter στη κλάσση WhiteLetter, όπου δημιουργήθηκε το τωρινό αντικείμενο letter
            letter.setColor(Color.WHITE);//ασπρο χρώμα δίνεται στο γράμμα μέσω της έτοιμης κλάσσης Color της βιβλιοθήκης της Java
        } else if (ch == 2) {
            //αν ο αριθμός είναι 2 τότε το Letter θα είναι αντικείμενο τύπου RedLetter
            letter = new RedLetter(); 
            letter.setColor(Color.RED); // και θα θέσουμε το χρώμα μέσω της συνάρτησης στην υποκλάση του letter, RedLetter
        } else if (ch == 3) {
            // τρίτη και τελευταία επιλογή για το γράμμα είναι να είναι τύπου BlueLetter
            letter = new BlueLetter();
            letter.setColor(Color.BLUE); //και να παίρνει μπλε χρώμα σαν αντίστοιχο γνώρισμα
        }
        // θέτουμε και τα υπόλοιπα γνωρίσματα για το Letter μέσω των μεθόδων στις υποκλάσεις του καθενός 
        letter.setCharacter(TempWordList.get(k).charAt(m));//θέτουμε σαν χαρακτήρα 
        letter.setValue(GivenValue(letter.getCharacter()));
        letter.setSituation(false);
        return letter;
    }

    public Letter SetBalader(char Balader) {
        Random rnd = new Random();
        Letter letter = null;
        int ch = rnd.nextInt(3) + 1;
        if (ch == 1) {
            letter = new WhiteLetter();
            letter.setColor(Color.WHITE);
        } else if (ch == 2) {
            letter = new RedLetter();
            letter.setColor(Color.RED);
        } else if (ch == 3) {
            letter = new BlueLetter();
            letter.setColor(Color.BLUE);
        }
        letter.setCharacter(Balader);
        letter.setValue(GivenValue(letter.getCharacter()));
        letter.setSituation(false);
        return letter;

    }

    public int GivenValue(char inchar) {
        int Value = 0;
        if (inchar == 'Α' || inchar == 'Ε' || inchar == 'Η' || inchar == 'Ι' || inchar == 'Ν' || inchar == 'Ο' || inchar == 'Σ' || inchar == 'Τ') {
            Value = 1;
        } else if (inchar == 'Κ' || inchar == 'Π' || inchar == 'Ρ' || inchar == 'Υ') {
            Value = 2;
        } else if (inchar == 'Λ' || inchar == 'Μ' || inchar == 'Ω') {
            Value = 3;
        } else if (inchar == 'Γ' || inchar == 'Δ') {
            Value = 4;
        } else if (inchar == 'Β' || inchar == 'Ζ' || inchar == 'Θ' || inchar == 'Φ') {
            Value = 8;
        } else if (inchar == 'Ξ' || inchar == 'Χ' || inchar == 'Ψ') {
            Value = 10;
        } else if (inchar == '?') {
            Value = 0;
        } else {
            System.out.println("Κάτι πήγε λάθος.");
        }

        return Value;
    }
}
