package application;

import pentamino.*;

public class Start {
  final int NUM_COLS;
  final int NUM_ROWS;
  int stepTime = 50;
  boolean lockKeyPress = false;

  Start (int numRows, int numCols) {
    NUM_ROWS = numRows;
    NUM_COLS = numCols;
    GridManager gridManager = new GridManager(NUM_ROWS, NUM_COLS);
  }

  public static void main (String[] args) {
    Start start = new Start(20, 8);
  }

}

// const testKeyPresses = () => {
//   let randNum = Math.floor(Math.random() * 2);
//   let direction = randNum ? 'RIGHT' : 'LEFT';
//   gridManager.pressKey(direction);
//   setTimeout(testKeyPresses, Math.random()*800 + 1000);
//   // console.log(direction);
// }
// testKeyPresses();
// TODO: Control grid front end using logic from gridbox objects

// (DONE) LEFTOFF: MAKE SHAPE INTO AN OBJECT SO THAT IT CAN BE SAFELY ACCESSED BY DIFFERENT PARTS OF THE PROGRAM
// Most of the initial trip up was from col and row maths. 

// (DONE) LEFTOFF: UPDATE GRIDMANAGER STATE WHEN THE SHAPE HITS THE FLOOR, 
// LEAVE THE BOXES 'ON' IN GRID INSTEAD OF TOGGLING 'OFF' AFTER STEP

// (DONE) LEFTOFF: IMPLEMENT UNIT TESTING (INITIAL SETUPS - GRIDVISUAL)

// (DONE) MOVE BOX LEFT AND RIGHT, SIMULATE RANDOM L/R KEY PRESSES

// (DONE) LEFTOFF: IMPLEMENT ANOTHER SHAPE AND PIECE COLLISION DETECTION

// LEFTOFF: SPLIT PROGRAM INTO CLASS FILES, REFACTOR, IMPLEMENT JS, HTML, CSS FRONTEND

// EVENTUALLY: MORE SHAPES, ROTATION, GAME LOGIC, ..., REACT, SERVER

