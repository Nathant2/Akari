package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import javafx.scene.control.Cell;

public class ControllerImpl implements ClassicMvcController {

  private Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    if ((model.getActivePuzzleIndex() + 1) <= (model.getPuzzleLibrarySize() - 1)) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
    }
  }

  @Override
  public void clickPrevPuzzle() {
    if ((model.getActivePuzzleIndex() - 1) >= 0) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
    }
  }

  @Override
  public void clickRandPuzzle() {
    int min = 0;
    int max = model.getPuzzleLibrarySize() - 1;
    int current = model.getActivePuzzleIndex();
    while (model.getActivePuzzleIndex() == current) {
      model.setActivePuzzleIndex((int) Math.floor(Math.random() * (max - min + 1) + min));
    }
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (model.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      if (model.isLamp(r, c)) model.removeLamp(r, c);
      else model.addLamp(r, c);
    }
  }
}
