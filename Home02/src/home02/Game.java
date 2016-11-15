package home02;

import java.util.*;

public class Game {

    ArrayList<Character> Shuffled_Chars = new ArrayList();

    public Game() {
    }

    ;
    
    
    public void StartGame(User user, int Choice) {
        Scanner in = new Scanner(System.in);
        Create_Table(Choice);
    }

    public void Create_Table(int Choice) {
        char Array[][] = new char[Choice][Choice];
        ManageList file = new ManageList();
        file.OpenFile();
        file.ReadFile();
        file.Selected_Words(Choice);

        for (int k = 0; k < file.TempWordList.size(); k++) {
            for (int m = 0; m < file.TempWordList.get(k).length(); m++) {
                Shuffled_Chars.add(file.TempWordList.get(k).charAt(m));
            }
        }
        Collections.shuffle(Shuffled_Chars);
        
        int count=0;
        for (int k = 0; k < Choice; k++) {
            for (int m = 0; m < Choice; m++) {
                Array[k][m] = Shuffled_Chars.get(count);
                count++;
                
                System.out.print(Array[k][m] + " ");
            }
            System.out.println();
        }
    }

}
