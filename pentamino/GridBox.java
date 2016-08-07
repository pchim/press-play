package pentamino;

// store info about the (id)th grid box
class GridBox {
  int id, col, row;
  boolean on, floor;
  GridBox (int id, GridCoordinate coordinate) {
    this.id = id;
    this.col = coordinate.col;
    this.row = coordinate.row;
    this.on = false;
    this.floor = false;
  }

  void activate() {
    this.toggle(true);
  }

  void touchFloor(boolean state) {
    this.floor = state;
    this.toggle(true);
  }

  void toggle(boolean state) {
    this.on = state;
  }

}

