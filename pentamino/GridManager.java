package pentamino;

// GridManager model updates GridVisual view, piece is a state of gridmanager
public class GridManager {
  GridBoxes gridBoxes;
  GridVisual gridVisual;
  String keyPress;
  public GridManager (int numRows, int numCols) {
    this.gridBoxes = new GridBoxes(numRows, numCols);
    this.gridVisual = new GridVisual(numRows, numCols);
    this.keyPress = null;
    // maybe a new class that creates grid boxes
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
        this.gridBoxes.getBox(shape[i]).touchFloor(true);
      }
    }  
  }

  void pressKey(String key) {
    this.keyPress = key;
  }

  boolean move(int[] piece) {
    // the IMPORTANT aspect of this is that we take care of the asynchronous
    // user key presses by checking for that key press here.
    // we now synchronously update the position of the piece

    // later the piece will be re-rendered and is guaranteed to be in one piece

    // here is where we will check if any key presses have been done in queue
    if (this.keyPress != null) {
      this.moveHoriz(piece, this.keyPress);
      this.keyPress = null;
    }
    return piece.moveDown(this.gridBoxes);
  }

  boolean moveHoriz(int[] piece, String dir) {
    return piece.moveHoriz(dir, this.gridBoxes);
  }

  void step() {
    this.gridVisual.step();
  }

  char[][] getGrid() {
    return this.gridVisual.getGrid();
  }
}

