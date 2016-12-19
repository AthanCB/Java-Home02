package home02;
//icsd14134 Bonis Athanasios - icsd11039 Dimopoulos Georgios

import java.awt.Color;
import java.awt.Point;
/*
δηιμιουργούμε μία abstract class Letter για τα γράμματα με τα οποία 
θα παίζει ο χρήστης. Είναι abstract διότι θα υπάρχουν κάποιες υποκλάσεις της Letter
στις οποίες η κάθε μία υλοποιεί, αναλόγως με το χρώμα, διαφορετικά κάποιες 
συναρτήσεις της μητέρας κλάσης Letter, για αυτό είναι abstract δηλαδή.
*/
public abstract class Letter {
    // το κάθε Letter έχει 4 γνωρίσματα
    
    private int Value; // αξία του Letter/γράμματος
    private Point id;
    private char Character; // χαρακτήρας του Letter, Α ως Ω
    private boolean Situation; // boolean μεταβλητή για τη διαθεσιμότητα του γράμματος σε κάθε θέση στο παιχνίδι
    private Color Color; // χρώμα γράμματος, το οποίο χρώμα δίνει και διαφορετικό Value στο γράμμα
    public Letter(){};//empty Constructor
    
    
    public void setPoint(Point id)
    {
        this.id=id;
    }
    public Point getPoint()
    {
        return id;
    }
    
    // getter της Value, χωρίς σώμα, γιατί διαφορετική Value επιστρέφει σε κάθε getValue() στις υποκλάσεις
    public abstract int getValue();

    // setter της Value, χωρίς σώμα, γιατί υλοποιείται/παίρνει τιμή η Value στις υποκλάσεις διαφορετικά
    public abstract void setValue(int value);

    // getter για την επιστροφή του χαρακτήρα του Letter
    public char getCharacter() {
        return Character;
    }

    // setter για τον χαρακτήρα του χρώματος του Letter
    public void setCharacter(char character) {
        this.Character = character;
    }

    // abstract setter για την επιστροφή του χρώματος, υλοποιείται στις υποκλάσεις στη κάθε μία διαφορετικά
    public abstract Color getColor();

    // abstract setter για τον ορισμό του χρώματος, γιατί υλοποιείται στις υποκλάσεις στη κάθε μία διαφορετικά
    public abstract void setColor(Color color);
    
    @Override
    //επιστρέφει σαν String τον χαρακτήρα του Letter,τα άλλα γνωρίσματα επιστρέφονται στη toString() στις υποκλάσεις
    public String toString()
    {
        return (Character + ":");
    }

    //boolean getter για τη Situation, επιστρέφει αν το γράμμα είναι πιασμένο ή οχι στη λέξη
    public boolean isSituation() {
        return Situation;
    }

    //setter για τη boolean μεταβλήτη Situation, η διαθεσιμότητα του γράμματος σε εκείνη τη θέση
    public void setSituation(boolean Situation) {
        this.Situation = Situation;
    }   
}