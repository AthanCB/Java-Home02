package home02;

import java.util.*;

public class Game {

    public Game() {
    }

    ;
    
    
    public void StartGame(User user, int Choice) {
        Scanner in = new Scanner(System.in);
        Create_Table(Choice);
        LetsPlay();
    }

    public void Create_Table(int Choice) {
        Letter Array[][] = new Letter[Choice][Choice];
        ManageList file = new ManageList();
        file.OpenFile();
        file.ReadFile();
        file.Selected_Words(Choice);

        int count = 0;
        for (int k = 0; k < Choice; k++) {
            for (int m = 0; m < Choice; m++) {
                Array[k][m] = file.Shuffled_Chars.get(count);
                count++;
                System.out.print(Array[k][m].toString() + " ");
            }
            System.out.println("\n");
        }
    }
    
    public void LetsPlay()
    {
        System.out.println("Ας παίξουμε λοιπόν!");
    }

}
