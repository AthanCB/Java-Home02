package home02;

import java.awt.Container;
import java.awt.*;
import java.awt.TextField;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameGraphics extends JFrame {

    JLabel lb1;
    JTextField tf;
    Graphics g;
    JPanel row1, row2, row3, row4, row5, row6, row0;
    JButton b1, b2, b3, b4, b5, b6;
    int choice;
    boolean stat = false;

    public GameGraphics() {
    };
    
    public void Create_ArrayGraphics() {
        //Card cg = new Card(0, 0, Color.RED, 10, 10, true);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = new Container();
        
        //pane.setLayout(l);
    }

    public int UserMenu_Display() {

        setLocationRelativeTo(null);
        //setSize(100, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = new Container();
        GridLayout glayout = new GridLayout(7, 1);
        pane.setLayout(glayout);
        row1 = new JPanel();
        row2 = new JPanel();
        row3 = new JPanel();
        row4 = new JPanel();
        row5 = new JPanel();
        row6 = new JPanel();
        row0 = new JPanel();

        lb1 = new JLabel("Ας παίξουμε λοιπόν.");
        row0.add(lb1);
        pane.add(row0);
//-----------------------------------------------------------------------------------------------
        b1 = new JButton("1) Αντάλλαξε 2 γράμματα.");
        row1.add(b1);
        pane.add(row1);
//-----------------------------------------------------------------------------------------------
        b2 = new JButton("2) Διαγραφή γραμμής και αντικατάσταση της.");
        row2.add(b2);
        pane.add(row2);
//-----------------------------------------------------------------------------------------------
        b3 = new JButton("3) Αναδιάταξη γραμμάτων.");
        row3.add(b3);
        pane.add(row3);
//-----------------------------------------------------------------------------------------------
        b4 = new JButton("4) Αναδιάταξη στήλης.");
        row4.add(b4);
        pane.add(row4);
//-----------------------------------------------------------------------------------------------
        b5 = new JButton("5)Αναδιάταξη γραμμής.");
        row5.add(b5);
        pane.add(row5);
//-----------------------------------------------------------------------------------------------
        b6 = new JButton("6) Συνέχεια στο παιχνίδι");
        row6.add(b6);
        pane.add(row6);
        do {
            b1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    choice = 1;
                    stat = true;
                }
            });
            b2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    choice = 2;
                    stat = true;
                }
            });
            b3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    choice = 3;
                    stat = true;
                }
            });
            b4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    choice = 4;
                    stat = true;
                }
            });
            b5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    choice = 5;
                    stat = true;
                }
            });
            b6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    choice = 6;
                    stat = false;
                }
            });
            //CardGraphics cg = new CardGraphics(0,0,Color.RED,10,10,true);            
            setContentPane(pane);
            pack();
        } while (stat == false);

        return choice;
    }

    public void CreateArrayWindow(int dimension, ArrayList<Letter> Shuffled_Chars) {
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane2 = new Container();
        GridLayout gl = new GridLayout(dimension, dimension);
        pane2.setLayout(gl);

        JPanel jp[] = new JPanel[dimension];
        JButton jb[][] = new JButton[dimension][dimension];
        int counter = 0;//counter for buttons
        for (int k = 0; k < dimension; k++) {
            jp[k] = new JPanel();//create panel,row
            for (int l = 0; l < dimension; l++) {
                jb[k][l] = new JButton();
                jb[k][l].setText(String.valueOf(Shuffled_Chars.get(counter)));
                jp[k].add(jb[k][l]);//add button in current row,panel
                counter++;
            }
            pane2.add(jp[k]);//add panel,row, in the pane
        }

        setContentPane(pane2);

        pack();
    }

}
