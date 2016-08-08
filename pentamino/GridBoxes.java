
package pentamino;

// collection of grid boxes
class GridBoxes {
  GridBox[] grid;
  int numRows;
  int numCols;

  GridBoxes(int numRows, int numCols) {
    this.grid = new GridBox[numRows*numCols];
    this.numRows = numRows;
    this.numCols = numCols;
    // initialize gridboxes
    for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
      for (int colIndex = 0; colIndex < numCols; colIndex++) {
        int gridNum = numCols*rowIndex + colIndex;
        this.grid[gridNum] = new GridBox(gridNum, new GridCoordinate(rowIndex, colIndex));
      }
    } 
  }

  GridBox getBox(int index) {
    return this.grid[index];
  }

  boolean setState(int index, String option, boolean state) {
    try {
      // create a set options method for the grid box, set to on for now
      (this.grid[index]).on = state;
      return true;
    }
    catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }

  boolean getState(int index, String option) {
    try {
      return (this.getBox(index)).on;
    }
    catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }
}

