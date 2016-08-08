package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import pentamino.*;

public class Start {
  final int NUM_COLS;
  final int NUM_ROWS;
  int stepTime = 50;
  boolean lockKeyPress = false;
  JFrame frame;

  Start (int numRows, int numCols) {
    frame = new JFrame();
    // set close action
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // set size and visibility of frame
    frame.setSize(480, 640);
    frame.setVisible(true);
    frame.setResizable(false);

    NUM_ROWS = numRows;
    NUM_COLS = numCols;
    GridManager gridManager = new GridManager(NUM_ROWS, NUM_COLS);
    frame.getContentPane().add(BorderLayout.CENTER, gridManager);

    frame.validate();
  }

  public static void main (String[] args) {
    Start start = new Start(20, 8);
  }

}

