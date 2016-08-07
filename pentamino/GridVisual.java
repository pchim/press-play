// display and control grid view
package pentamino;

import java.util.*;

public class GridVisual {
  char[][] grid;
  char displayOn;
  char displayOff;
  int numRows;
  int numCols;
  List<Integer> activeSquares;
  boolean floorTouched;
  List<GridCoordinate> coordinates;

  public GridVisual (int numRows, int numCols) {
    this.grid = new char[numRows][numCols];
    this.displayOff = 'X';
    this.displayOn = Math.random() * 2 > 1 ? 'o' : '-';
    this.numRows = numRows;
    this.numCols = numCols;
    this.activeSquares = new LinkedList<Integer>();
    this.floorTouched = false;
    this.coordinates = new ArrayList<GridCoordinate>();
    // initialize grid
    for (int rowIndex = 0; rowIndex < this.numRows; rowIndex++) {
      for (int colIndex = 0; colIndex < this.numCols; colIndex++) {
        this.grid[rowIndex][colIndex] = displayOff;
        this.coordinates.add(new GridCoordinate(rowIndex, colIndex)); 
      }
    }   
  }

  private int getRow (int index) {
    return this.coordinates.get(index).row;
  }

  private int getCol (int index) {
    return this.coordinates.get(index).col;
  }

  public GridCoordinate getRowCol (int index) {
    try {
      return new GridCoordinate(this.getRow(index), this.getCol(index));
    } catch (Exception e) {
      System.out.println("getRowCol(index): Index " + index + " out of bounds: " + e);
      throw new IndexOutOfBoundsException("Error");
    }
  }

  void toggleSquare (int index, boolean state) {
    try {
      GridCoordinate coord = this.getRowCol(index);
      this.grid[coord.row][coord.col] = state ? this.displayOn : this.displayOff;
    } catch (Exception e) {
      System.out.println("toggleSquare(index, state): Index out of bounds: " + index + ": " + e);
    }
  }

  void activate (int[] shape) {
    for (int i = 0; i < shape.length; i++) {
      if (shape[i] < 0) {
        continue;
      }
      this.activeSquares.add(shape[i]);
      this.toggleSquare(shape[i], true); 
    } 
  }

  void floorPiece (int[] shape) {
    this.activate(shape);
    this.floorTouched = true;
    // new display for the next piece
    this.displayOn = Math.random() * 2 > 1 ? 'o' : '-';
  }

  void step() {
    for (int i = 0; i < this.activeSquares.size(); i++) {
      if (!this.floorTouched) {
        this.toggleSquare(this.activeSquares.get(i), false);
      }
    }
    this.floorTouched = false;       
    // maybe another way to make array list empty
    this.activeSquares = new ArrayList<Integer>();
  }

  public char[][] getGrid() {
    return this.grid;
  }

  public String toString() {
    String gridString = "";
    for (int i = 0; i < grid.length; i++) {
      String row = "|  ";
      for (int j = 0; j < grid[i].length; j++) {
        row += grid[i][j] + "  ";
      }
      row += "|";
      gridString += row + '\n';
    }
    return gridString;
  }

  public static void main (String[] args) {
    GridVisual gv = new GridVisual(10, 5);
    System.out.println(gv);
  }


}

