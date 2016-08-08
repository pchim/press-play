package pentamino;

// GridManager model updates GridVisual view, piece is a state of gridmanager
public class GridManager {
  GridBoxes gridBoxes;
  GridVisual gridVisual;
  Piece piece;
  String keyPress;
  public GridManager (int numRows, int numCols) {
    this.gridBoxes = new GridBoxes(numRows, numCols);
    this.gridVisual = new GridVisual(numRows, numCols);
    this.piece = new Piece(numRows, numCols);
    this.keyPress = null;
    this.step();
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

    try {
      Thread.sleep(100);
    } catch (Exception e) {
      
    }
    this.step();

  }
}


