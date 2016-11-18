//icsd14134 Bonis Athanasios - icsd11039 Dimopoulos Georgios
package home02;

import java.util.*;

public class Home02 {

    
    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
        System.out.println("Γειά σου, δώσε το όνομα σου: ");
        String Name=in.next();
        User user = new User();
        user.setName(Name);
        boolean stat=true;
        int choice=0;
        System.out.println(user.getName() + " δώσε το μέγεθος του πίνακα: 5x5 or 8x8 or 10x10: ");
        while(stat==true){
            choice=in.nextInt();
            if(choice!=5 && choice!=8 && choice!=10)
                System.out.println("Λάθος αριθμός, δώσε ξανά!");                        
            else 
                stat=false;      
        }
        Game game = new Game();
        game.StartGame(user,choice);
    }
    
}
