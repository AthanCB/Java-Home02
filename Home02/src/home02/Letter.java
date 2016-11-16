package home02;

import java.awt.Color;

public abstract class Letter {
    private int Value;
    private char Character;
    private Color Color;
    public Letter(){};
    
    public abstract int getValue();

    public abstract void setValue(int value);

    public char getCharacter() {
        return Character;
    }

    public void setCharacter(char character) {
        this.Character = character;
    }

    public abstract Color getColor();

    public abstract void setColor(Color color);
    
    public String toString()
    {
        return (Character + ":");
    }
    
}
