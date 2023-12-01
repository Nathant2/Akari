package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {

  private int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board;
  }

  @Override
  public int getWidth() {
    return board[0].length;
  }

  @Override
  public int getHeight() {
    return board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r > getHeight() || c > getWidth() || r < 0 || c < 0) throw new IndexOutOfBoundsException();
    CellType result;
    if (board[r][c] < 5) result = CellType.CLUE;
    else if (board[r][c] == 5) result = CellType.WALL;
    else result = CellType.CORRIDOR;
    return result;
  }

  @Override
  public int getClue(int r, int c) {
    if (r > getHeight() || c > getWidth() || r < 0 || c < 0) throw new IndexOutOfBoundsException();
    if (getCellType(r, c) != CellType.CLUE) throw new IllegalArgumentException();
    return board[r][c];
  }
}
