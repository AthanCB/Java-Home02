package home02;

import java.awt.Color;

// το άσπρο γράμμα δεν έχει κάποια ιδιαίτερη αξία, ουσιαστικά ό,τι αξία έχει 
//εξ αρχής ο γράμμα αυ΄τη κρατάει αν είναι χρώματος white
public class WhiteLetter extends Letter {

    private int Value;
    private Color Color;

    //άδειος constructor
    public WhiteLetter() {
    }

    @Override
    //getter για την επιστροφή της αξίας του Letter
    public int getValue() {
        return Value;
    }

    @Override
    //setter για τον ορισμό της αξίας του Letter
    public void setValue(int value) {
        this.Value = value;
    }

    @Override
    // επιστροφή του χρώματος του Letter, δηλαδή επιστρέφει white
    public Color getColor() {
        return Color;
    }

    @Override
    //setter για το χρώμα του Letter, δηλαδή το θέτει το χρώμα άσπρο
    public void setColor(Color color) {
        this.Color = Color;
    }

    @Override
    //κάλεσμα της String() της μητέρας κλάσσης Letter συν την αξία του Letter
    // και εμφάνιση του W για το white/άσπρο
    public String toString() {
        return super.toString() + Value + ":" + "W";
    }

}
