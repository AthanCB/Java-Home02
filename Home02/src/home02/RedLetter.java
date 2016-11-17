package home02;

import java.awt.Color;

public class RedLetter extends Letter {

    private int Value;
    private Color Color;

    public RedLetter() {
    }

    @Override
    public int getValue() {
        return Value;
    }

    @Override
    public void setValue(int value) {
        this.Value = 2 * value;
    }

    @Override
    public Color getColor() {
        return Color;
    }

    @Override
    public void setColor(Color color) {
        this.Color = Color;
    }

    @Override
    public String toString() {
        return super.toString() + Value + ":" + "R";
    }

}
