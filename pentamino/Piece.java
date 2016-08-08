package pentamino;

import java.util.*;


// board piece
public class Piece {
  int numRows;
  int numCols;
  HashMap<String, int[]> shapes;
  int[] shape;

  public Piece (int numRows, int numCols) {
    this.numRows = numRows;
    this.numCols = numCols;
    this.shapes = this.makeShapes(numRows, numCols);
    this.newShape();
  }

  private HashMap<String, int[]> makeShapes(int numRows, int numCols) {
    HashMap<String, int[]> shapes = new HashMap<String, int[]>();
    shapes.put("square", new int[]{-numCols + 1, -(numCols) + 2, -2 * (numCols) + 2, -2 * (numCols) + 1});
    shapes.put("barI", new int[]{-numCols, -2 * numCols, -3 * numCols, -4 * numCols});
    return shapes;
  }

  public void newShape() {
    int[] newShape = Math.random() * 2 > 1 ? this.shapes.get("barI") : this.shapes.get("square");
    this.shape = new int[newShape.length];
    this.shape = Arrays.copyOf(newShape, newShape.length);
    int randIndex = (int) (Math.random() * this.numCols);
    // check if the random placement is legal
    // can refactor this so it doesn't have to loop through every space twice
    for (int i = 0; i < this.shape.length; i++) {
      if (this.shape[i] % this.numCols + randIndex > this.numCols - 1) {
        randIndex -= 1;
      } else if (this.shape[i] % this.numCols + randIndex == 0) {
        randIndex = 0;
      }
    }

    for (int i = 0; i < this.shape.length; i++) {
      this.shape[i] += randIndex;
    }
  }

  public void movePiece(int moves) {
    for (int i = 0; i < this.shape.length; i++) {
      this.shape[i] += moves;
    }    
  }



  boolean checkMoveDown(GridBoxes boardState) {
    boolean touchFloor = false;
    // can refactor each bound check to find the first invalid block
    for (int i = 0; i < this.shape.length; i++) {
      // check if the next location will be a floor (not yet checking collisions)
      if ( (this.shape[i] + this.numCols) > (this.numRows*this.numCols - 1) || this.checkCollision(this.shape[i] + this.numCols, boardState)) {
        touchFloor = true;
        return false;
      }
    }
    return true;    
  }

  boolean moveDown(GridBoxes boardState) {
    if (this.checkMoveDown(boardState)) {
      this.movePiece(this.numCols);
      return true; 
    } else {
      // play some thumping sound
      return false;
    }
  }

  // can refactor all of the checks by putting a 'params' parameter
  boolean checkMoveHoriz(CheckBound checkBound, GridBoxes boardState, int moveSpace) {
    for (int i = 0; i < this.shape.length; i++) {
      if (!checkBound.check(this.shape[i]) || this.checkCollision(this.shape[i] + moveSpace, boardState)) {
        return false;
      }
    }
    return true;
  }

  interface CheckBound {
    public boolean check(int shapeNum);
  }

  boolean moveHoriz(String dir, GridBoxes boardState) {
    final int NUM_COLS = this.numCols;
    // for RIGHT, LEFT key presses
    CheckBound checkRight = (shapeNum) -> (shapeNum % NUM_COLS + 1) < NUM_COLS;
    CheckBound checkLeft = (shapeNum) -> (shapeNum % NUM_COLS - 1) > 0;
    CheckBound checkBound = checkRight;
    int moveSpace = 1;

    switch (dir) {
      case "LEFT":
        checkBound = checkLeft;
        moveSpace = -1;
        break;
      case "RIGHT":
        checkBound = checkRight;
        moveSpace = 1;
        break;
      default:
        System.out.println("Invalid direction");
    }

    // check bounds and move
    if(this.checkMoveHoriz(checkBound, boardState, moveSpace)) {
      this.movePiece(moveSpace);
      return true;
    } else {
      return false;
    }
  }

  boolean checkCollision(int index, GridBoxes boardState){
    if (boardState.getState(index, "on")) {
      return true;
    }
    return false;
  }
}
