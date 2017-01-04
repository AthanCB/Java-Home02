//icsd14134 Bonis Athanasios - icsd11039 Dimopoulos Georgios
package home02;

/*
 Από εδώ ξεκινάει το παιχνίδι, μέσω της Main(), όπου  ο χρήστης δίνει μόνο όνομα 
 και το μέγεθος του πίνακα των γραμμάτων με το οποίο θέλει να παίξει. Έπειτα καλείται μέσω δημιουργίας 
 αντικειμένου τύπου Game η συνάρτηση StartGame αυτής της κλάσσης Game και το παιχνίδι ξεκινάει και 
 υλοποιείται στην άλλη κλάσση
 */
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Begin {

    JFrame firstFrame = new JFrame("Begin");
    JFileChooser jfc;

    public static void main(String[] args) {
        String Name = "";
        boolean stat = true;
        while (stat == true) {
            try {
                Name = JOptionPane.showInputDialog("Γειά σου, δώσε το όνομα σου ή 0 για έξοδο ");  //String Name = in.next();// εισαγωγή ονόματος από τον χρήστη//String Name = in.next();// εισαγωγή ονόματος από τον χρήστη        
                if (Name.equals("0")) {
                    System.exit(0);
                } else {
                    stat = false;
                }
            } catch (NullPointerException ne) {
                JOptionPane.showMessageDialog(null, "Κάτι πήγε λάθος, δώσε το όνομα σου σωστά ");
            }
        }
        User user = new User(); // αντικείμενο τύπου User
        user.setName(Name); // σώζουμε το όνομα που έδωσε ο χρήστης μέσω της setter της User
        stat = true; // για την while 
        String opt[] = {"5x5", "8x8", "10x10", "EXIT"};
        int choice = JOptionPane.showOptionDialog(null, null, user.getName() + " δώσε το μέγεθος του πίνακα:", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, opt, opt[1]);
        // παρακάτω θα τρέχει η while όσο ο χρήστης δίνει μη συμβατό αριθμό για τις διαστάσεις του πίνακα
        int size = 0;
        while (stat == true) {
            if (choice == 1) {
                size = 8;
                stat = false;
            } else if (choice == 2) {
                size = 10;
                stat = false;
            } else if (choice == 0) {
                size = 5;
                stat = false;
            } else if (choice == 3) {
                System.exit(0);
                stat = false;
            } else// αν δώσει αριθμό πέρα από 5 8 ή 10 και την επιλογή εξόδου
            {
                JOptionPane.showMessageDialog(null, "Λάθος αριθμός, δώσε ξανά!", "WRONG", JOptionPane.ERROR_MESSAGE);
                choice = JOptionPane.showOptionDialog(null, null, " δώσε το μέγεθος του πίνακα: 5x5 or 8x8 or 10x10: ", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, opt, opt[1]);
            }
        }
        JOptionPane.showMessageDialog(null, user.getName() + " ο πίνακας που δημιούργησες είναι " + size + " γραμμών και στήλων");
        Game game = new Game(); // δημιουργία αντικειμένου τύπου Game 
        game.setArraySize(size);
        game.CreateTable(user);// κάλεσμα της συνάρτησης StartGame της κλάσσης game 
    }
}
