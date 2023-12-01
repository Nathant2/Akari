package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  private PuzzleLibrary library;
  private int acivePuzzleIndex;
  private boolean[][] lampLocations;
  private List<ModelObserver> observers;

  public ModelImpl(PuzzleLibrary library) {
    this.library = library;
    this.acivePuzzleIndex = 0;
    Puzzle p = getActivePuzzle();
    this.lampLocations = new boolean[p.getHeight()][p.getWidth()];
    this.observers = new ArrayList<>();
  }

  @Override
  public void addLamp(int r, int c) {
    checkOutOfBounds(r, c);
    checkCellType(r, c, CellType.CORRIDOR);
    if (!isLamp(r, c)) lampLocations[r][c] = true;
    notifyObservers();
  }

  @Override
  public void removeLamp(int r, int c) {
    checkOutOfBounds(r, c);
    checkCellType(r, c, CellType.CORRIDOR);
    if (isLamp(r, c)) lampLocations[r][c] = false;
    notifyObservers();
  }

  @Override
  public boolean isLit(int r, int c) {
    checkOutOfBounds(r, c);
    checkCellType(r, c, CellType.CORRIDOR);
    Puzzle p = getActivePuzzle();
    if (isLamp(r, c)) return true; // if it's a lamp it's lit
    int i = r + 1;
    int j = c + 1;
    boolean wallHit = false;
    while (i < p.getHeight() && !wallHit) { // check down for lamp
      if (p.getCellType(i, c) != CellType.CORRIDOR) wallHit = true; // stop when you hit a wall
      else if (isLamp(i, c)) return true; // return if you find a lamp
      i++;
    }
    i = r - 1; // reset i
    wallHit = false; // reset wallHit
    while (i >= 0 && !wallHit) { // check up for lamp
      if (p.getCellType(i, c) != CellType.CORRIDOR) wallHit = true;
      else if (isLamp(i, c)) return true;
      i--;
    }
    wallHit = false;
    while (j < p.getWidth() && !wallHit) { // check right for lamp
      if (p.getCellType(r, j) != CellType.CORRIDOR) wallHit = true;
      else if (isLamp(r, j)) return true;
      j++;
    }
    j = c - 1; // reset j
    wallHit = false;
    while (j >= 0 && !wallHit) { // check left for lamp
      if (p.getCellType(r, j) != CellType.CORRIDOR) wallHit = true;
      else if (isLamp(r, j)) return true;
      j--;
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    checkOutOfBounds(r, c);
    checkCellType(r, c, CellType.CORRIDOR);
    return lampLocations[r][c];
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    checkOutOfBounds(r, c);
    if (!isLamp(r, c)) throw new IllegalArgumentException();
    Puzzle p = getActivePuzzle();
    int i = r + 1;
    int j = c + 1;
    boolean wallHit = false;
    while (i < p.getHeight() && !wallHit) { // check down for lamp
      if (p.getCellType(i, c) != CellType.CORRIDOR) wallHit = true; // stop when you hit a wall
      else if (isLamp(i, c)) return true; // return if you find a lamp
      i++;
    }
    i = r - 1; // reset i
    wallHit = false; // reset wallHit
    while (i >= 0 && !wallHit) { // check up for lamp
      if (p.getCellType(i, c) != CellType.CORRIDOR) wallHit = true;
      else if (isLamp(i, c)) return true;
      i--;
    }
    wallHit = false;
    while (j < p.getWidth() && !wallHit) { // check right for lamp
      if (p.getCellType(r, j) != CellType.CORRIDOR) wallHit = true;
      else if (isLamp(r, j)) return true;
      j++;
    }
    j = c - 1; // reset j
    wallHit = false;
    while (j >= 0 && !wallHit) { // check left for lamp
      if (p.getCellType(r, j) != CellType.CORRIDOR) wallHit = true;
      else if (isLamp(r, j)) return true;
      j--;
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return library.getPuzzle(acivePuzzleIndex);
  }

  @Override
  public int getActivePuzzleIndex() {
    return acivePuzzleIndex;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index < 0 || index >= library.size()) throw new IndexOutOfBoundsException();
    acivePuzzleIndex = index;
    Puzzle p = getActivePuzzle();
    lampLocations = new boolean[p.getHeight()][p.getWidth()];
    notifyObservers();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return library.size();
  }

  @Override
  public void resetPuzzle() {
    Puzzle p = getActivePuzzle();
    for (int i = 0; i < p.getHeight(); i++) {
      for (int j = 0; j < p.getWidth(); j++) {
        if (p.getCellType(i, j) == CellType.CORRIDOR) { // for each corridor in the puzzle board...
          if (isLamp(i, j)) removeLamp(i, j);
        }
      }
    }
    notifyObservers();
  }

  @Override
  public boolean isSolved() {
    Puzzle p = getActivePuzzle();
    for (int i = 0; i < p.getHeight(); i++) {
      for (int j = 0; j < p.getWidth(); j++) {
        if (p.getCellType(i, j) == CellType.CORRIDOR) { // for each corridor in the puzzle board...
          if (!isLit(i, j)) return false; // is it lit?
          if (isLamp(i, j)) { // for each lamp
            if (isLampIllegal(i, j)) return false; // is it legal?
          }
        } else if (p.getCellType(i, j) == CellType.CLUE) { // for each clue...
          if (!isClueSatisfied(i, j)) return false; // is it satisfied?
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    checkOutOfBounds(r, c);
    checkCellType(r, c, CellType.CLUE);
    Puzzle p = getActivePuzzle();
    int lampCount = 0; // we will count the lamps on all four sides of the clue
    if (r > 0
        && p.getCellType(r - 1, c)
            == CellType.CORRIDOR) { // if cell above is in bounds and a corridor
      if (isLamp(r - 1, c)) lampCount++; // check if it has a lamp
    }
    if (r < (p.getHeight() - 1) && p.getCellType(r + 1, c) == CellType.CORRIDOR) { // below cell
      if (isLamp(r + 1, c)) lampCount++;
    }
    if (c > 0 && p.getCellType(r, c - 1) == CellType.CORRIDOR) { // left cell
      if (isLamp(r, c - 1)) lampCount++;
    }
    if (c < (p.getWidth() - 1) && p.getCellType(r, c + 1) == CellType.CORRIDOR) { // right cell
      if (isLamp(r, c + 1)) lampCount++;
    }
    return (p.getClue(r, c) == lampCount);
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  private void notifyObservers() {
    for (ModelObserver m : observers) {
      m.update(this);
    }
  }

  private void checkOutOfBounds(int r, int c) {
    Puzzle p = getActivePuzzle();
    if (r >= p.getHeight() || r < 0 || c >= p.getWidth() || c < 0)
      throw new IndexOutOfBoundsException();
  }

  private void checkCellType(int r, int c, CellType cell) {
    Puzzle p = getActivePuzzle();
    if (p.getCellType(r, c) != cell) throw new IllegalArgumentException();
  }
}
