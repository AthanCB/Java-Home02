package home02;

import java.awt.Color;

/*υποκλάση της μητέρας Letter για την υλοποίηση */
public class RedLetter extends Letter {

    private int Value;
    private Color Color;

    public RedLetter() {
    }

    @Override
    //υλοποίηση 
    public int getValue() {
        return Value;
    }

    @Override
    //υλοποίηση της setter της Letter
    //όταν ένα γράμμα είναι κόκκινο το χρώμα του, παίνει αυτομάτως διπλή αξία
    public void setValue(int value) {
        this.Value = 2 * value;
    }

    @Override
    // getter του χρώματος 
    public Color getColor() {
        return Color;
    }

    @Override
    //setter του χρώματος 
    public void setColor(Color color) {
        this.Color = color;
    }

    @Override
    //επιστρέφει την String της μητέρας Letter συν την αξία που δώθηκε σε αυτή τη κλάσση
    //και εμφάνιση του R για το χρώμα red/κόκκινο
    public String toString() {
        return super.toString() + Value + ":" + "R";
    }
}
