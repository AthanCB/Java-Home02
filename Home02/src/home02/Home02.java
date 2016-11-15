//icsd14134 Bonis Athanasios - icsd11039 Dimopoulos Georgios
package home02;

import java.util.*;

public class Home02 {

    
    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
        System.out.println("Hello user, give your name: ");
        String Name=in.next();
        User user = new User();
        user.setName(Name);
        boolean stat=true;
        int choice=0;
        System.out.println("Give your array choice: 5x5 or 8x8 or 10x10");
        while(stat==true){
            choice=in.nextInt();
            if(choice!=5 && choice!=8 && choice!=10)
                System.out.println("Wrong number, write again");                        
            else 
                stat=false;      
        }
        Game game = new Game();
        game.StartGame(user,choice);
    }
    
}
