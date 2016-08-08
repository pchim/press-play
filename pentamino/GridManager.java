package pentamino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// GridManager model updates GridVisual view, piece is a state of gridmanager
public class GridManager extends JPanel {
  GridBoxes gridBoxes;
  GridVisual gridVisual;
  Piece piece;
  String keyPress;
  MyDrawPanel mdp;
  int numRows;
  int numCols;


  public GridManager (int numRows, int numCols) {
    this.numRows = numRows;
    this.numCols = numCols;
    this.gridBoxes = new GridBoxes(numRows, numCols);
    this.gridVisual = new GridVisual(numRows, numCols);
    this.piece = new Piece(numRows, numCols);
    this.mdp = new MyDrawPanel();
    this.keyPress = null;
    this.setLayout(new BorderLayout());
    this.add(BorderLayout.CENTER, mdp);
  }

  void activate(int[] shape) {
    this.gridVisual.activate(shape);
  }

  void floor(int[] shape) {
    this.floorPiece(shape);
    this.gridVisual.floorPiece(shape);
  }

  void floorPiece(int[] shape) {
    for (int i = 0; i < shape.length; i++) {
      if (shape[i] >= 0) {
        System.out.println("shape: " + shape[i]);
        this.gridBoxes.getBox(shape[i]).touchFloor(true);
      }
    }  
  }

  void pressKey(String key) {
    this.keyPress = key;
  }

  boolean move(Piece piece) {
    // here is where we will check if any key presses have been done in queue
    if (this.keyPress != null) {
      this.moveHoriz(piece, this.keyPress);
      this.keyPress = null;
    }
    return piece.moveDown(this.gridBoxes);
  }

  boolean moveHoriz(Piece piece, String dir) {
    return piece.moveHoriz(dir, this.gridBoxes);
  }

  char[][] getGrid() {
    return this.gridVisual.getGrid();
  }

  void managerStep() {
    // can refactor each bound check to find the first invalid block
    boolean movedDown = this.move(piece);
    int[] shape = piece.shape;

    // update positions of the shape on the board
    if (movedDown) {
      this.activate(shape);
    } else {
      this.floor(shape);
      piece.newShape();
    }

    for (int i = 0; i < 10; i++) {
      System.out.print('\n');
    }

    // format grid output
    System.out.print(this.gridVisual);

  }

  void step() {
    this.managerStep();
    this.gridVisual.step();

    // try {
    //   Thread.sleep(100);
    // } catch (Exception e) {
      
    // }
    // this.step();
    // this.repaint();
  }

  // panel where the bricks all fall down
  class MyDrawPanel extends JPanel {
    int height;
    int width;
    int blockWidth;
    int blockHeight;
    boolean init = false;

    public void paintComponent(Graphics g) {
      if (!init) {
        height = this.getHeight();
        width = this.getWidth();
        blockWidth = width / numCols;
        blockHeight = height / numRows;
        init = false;
      }

      Graphics g2d = (Graphics2D) g;
      //g.fillRect( this.getWidth() / 5, 0, 3 * this.getWidth() / 5, this.getHeight());
      g.fillRect(0, 0, this.getWidth(), this.getHeight());
      int red = (int) (Math.random() * 255);
      int green = (int) (Math.random() * 255);
      int blue = (int) (Math.random() * 255);
      Color randomColor = new Color(red, green, blue);
      g.setColor(randomColor);

      for (int i = 0; i < gridBoxes.size(); i++) {
        if (gridBoxes.getState(i, "on")) {
          int row = gridBoxes.getBox(i).row;
          int col = gridBoxes.getBox(i).col;
          g.fillRect(col*blockWidth, row*blockHeight, blockWidth, blockHeight);
          System.out.println("One on");
          System.out.println("Row " + row + "; Col " + col + ";");
          System.out.println("x: " + row*blockWidth + " y: " + col*blockHeight +
                             " width: " + blockWidth + " height: " + blockHeight);
        }
      }
    }
  }

  public void paintComponent(Graphics g) {
    Graphics g2d = (Graphics2D) g;

    // g.fillRect(0, 0, this.getWidth(), this.getHeight());

    // using regular graphics
    // random colors
    int red = (int) (Math.random() * 255);
    int green = (int) (Math.random() * 255);
    int blue = (int) (Math.random() * 255);
    Color randomColor = new Color(red, green, blue);
    g.setColor(randomColor);
    // random color oval
    g.fillOval(70, 70, 30, 30);
  }

}


