//icsd14134 Bonis Athanasios - icsd11039 Dimopoulos Georgios
package home02;

/*
Από εδώ ξεκινάει το παιχνίδι, μέσω της Main(), όπου  ο χρήστης δίνει μόνο όνομα 
και το μέγεθος του πίνακα των γραμμάτων με το οποίο θέλει να παίξει. Έπειτα καλείται μέσω δημιουργίας 
αντικειμένου τύπου Game η συνάρτηση StartGame αυτής της κλάσσης Game και το παιχνίδι ξεκινάει και 
υλοποιείται στην άλλη κλάσση
*/
import java.util.*;

public class Home02 {
    
    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);// εισαγωγλη τιμών μέσω πληκτρολογίου
        System.out.println("Γειά σου, δώσε το όνομα σου: ");
        String Name=in.next();// εισαγωγή ονόματος από τον χρήστη
        User user = new User(); // αντικείμενο τύπου User
        user.setName(Name); // σώζουμε το όνομα που έδωσε ο χρήστης μέσω της setter της User
        boolean stat=true; // για την while 
        int choice=0; // επιλογή μεγέθους πίνακα
        System.out.println(user.getName() + " δώσε το μέγεθος του πίνακα: 5x5 or 8x8 or 10x10: ");
        // παρακάτω θα τρέχει η while όσο ο χρήστης δίνει μη συμβατό αριθμό για τις διαστάσεις του πίνακα
        while(stat==true){ 
            choice=in.nextInt();// είσοδο επιλογής μεγέθους πίνακα απ τον χρήστη
            if(choice!=5 && choice!=8 && choice!=10) // αν δώσει αριθμό πέρα από 5 8 ή 10
                System.out.println("Λάθος αριθμός, δώσε ξανά!");                        
            else 
                stat=false;      
        }
        Game game = new Game(); // δημιουργία αντικειμένου τύπου Game 
        game.StartGame(user,choice);// κάλεσμα της συνάρτησης StartGame της κλάσσης game 
    }
    
}
