package home02;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class ManageList {
    
    
    private Scanner in;
    ArrayList<String> WordList = new ArrayList();
    ArrayList<String> TempWordList = new ArrayList();
    public void OpenFile(){
        try{
            in =new Scanner(new File("src/home02/Wordlist.txt"));
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            System.out.println("Could not find the file.");
        }
    }
  
    public void ReadFile(){
        String Word = in.next();
        while(in.hasNext()){
            Word = in.next();
            WordList.add(Word);
        }
        Collections.shuffle(WordList);
        in.close();
    }
    
    public void Selected_Words(int Choice){
        int counter= 0;
        for(int i=0;i<WordList.size();i++){
           
                if(counter+WordList.get(i).length()<Choice*Choice){
                    TempWordList.add(WordList.get(i));
                     counter+=WordList.get(i).length();
                     
                } 
        }
        String alphabet="ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ";
        char extra;
        for(int i=0;i<(Choice*Choice)-counter;i++){
            Random rnd = new Random();
            int j = rnd.nextInt(23);
            extra=alphabet.charAt(j);
            TempWordList.add(Character.toString(extra));
        }
       
        
    }
    
    
    
    
    
}
