package home02;

import java.awt.Color;

public class BlueLetter extends Letter {

    private int Value;
    private Color Color;

    public BlueLetter() {
    }

    @Override
    public int getValue() {
        return Value;
    }

    @Override
    public void setValue(int value) {
        this.Value = value;
    }

    public Color getColor() {
        return Color;
    }

    public void setColor(Color color) {

    }

    @Override
    public String toString() {
        return super.toString() + Value + ":" + "B";
    }
}
