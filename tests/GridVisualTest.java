package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pentamino.*;

public class GridVisualTest {
  @Test
  public void evaluatesExpression() {
    GridVisual gridVisual = new GridVisual(10, 10);
    char[][] grid = gridVisual.getGrid();
    assertEquals(10, grid.length);
  }
}