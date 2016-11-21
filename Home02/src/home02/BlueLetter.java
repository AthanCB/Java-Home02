package home02;

import java.awt.Color;

/*
το μπλε γράμμα δίνει στην συνολική λέξη διπλάσια αξία, αυτή είναι η χρήση του
εκτός όμως αυτής της κλάσσης
μόνο μία φορά διπλασιάζεται η λέξη ακόμα και αν υπάρχουν πάνω από 2 blue Letters σε μία λέξη
 */
public class BlueLetter extends Letter {

    private int Value;
    private Color Color;

    // άδειος constructor
    public BlueLetter() {
    }

    @Override
    // getter της αξίας του μπλε γράμματος
    public int getValue() {
        return Value;
    }

    @Override
    // setter για τον ορισμό της αξίας του μπλε Letter
    // εδώ δε φαίνεται η διαφορά του με τα άλλα γράμματα σε θέμα αξίας
    public void setValue(int value) {
        this.Value = value;
    }

    @Override
    // getter για την επιστροφή του μπλε χρώματος του Letter
    public Color getColor() {
        return Color;
    }

    @Override
    //setter για τον ορισμό του μπλε χρώματος του Letter
    public void setColor(Color color) {
        this.Color = color;
    }

    @Override
    // επιστρέφει μέσω της super.toString() της μητέρας Letter συν την αξία του μπλε Letter 
    //και εμφάνιση του B για το χρώμα blue/μπλε
    public String toString() {
        return super.toString() + Value + ":" + "B";
    }
}
