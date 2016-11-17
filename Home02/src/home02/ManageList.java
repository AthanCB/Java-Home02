package home02;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ManageList {

    private Scanner in;
    ArrayList<String> WordList = new ArrayList();
    ArrayList<String> TempWordList = new ArrayList();
    ArrayList<Letter> Shuffled_Chars = new ArrayList();

    public void OpenFile() {
        try {
            in = new Scanner(new File("src/home02/Wordlist.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Το αρχείο δεν βρέθηκε.");
        }
    }

    public void ReadFile() {
        String Word = in.next();
        while (in.hasNext()) {
            Word = in.next();
            WordList.add(Word);
        }
        Collections.shuffle(WordList);
        in.close();
    }

    public void Selected_Words(int Choice) {
        int counter = 0;
        for (int i = 0; i < WordList.size(); i++) {
            if (counter + WordList.get(i).length() < Choice * Choice) {
                TempWordList.add(WordList.get(i));
                counter += WordList.get(i).length();
                WordList.remove(i);
            }
        }
        String alphabet = "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ";
        char extra;
        for (int i = 0; i < (Choice * Choice) - counter; i++) {
            Random rnd = new Random();
            int j = rnd.nextInt(23);
            extra = alphabet.charAt(j);
            TempWordList.add(Character.toString(extra));
        }
        CharList();
        
    }

    public void CharList() {
        
        for (int k = 0; k < TempWordList.size(); k++) {
            for (int m = 0; m < TempWordList.get(k).length(); m++) {   
                Shuffled_Chars.add(SetLetter(k, m)); 
            }
        }
        Collections.shuffle(Shuffled_Chars);

    }

    public Letter SetLetter(int k, int m) {
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
        letter.setCharacter(TempWordList.get(k).charAt(m));
        letter.setValue(GivenValue(letter.getCharacter()));
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
            Value = 5;
        } else {
            System.out.println("Κάτι πήγε λάθος.");
        }

        return Value;
    }
    
    

}
