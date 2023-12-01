package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import java.beans.EventHandler;

public class PuzzleView implements FXComponent {

  private ClassicMvcController controller;
  private Model model;

  public PuzzleView(ClassicMvcController controller, Model model) {
    this.controller = controller;
    this.model = model;
  }

  @Override
  public Parent render() {
    GridPane grid = new GridPane();
    for (int r = 0; r < model.getActivePuzzle().getHeight(); r++) {
      for (int c = 0; c < model.getActivePuzzle().getWidth(); c++) {

        Button btn;
        if (model.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) { // corridors:
          if (model.isLamp(r, c)) { // lamps:
            btn = new Button();
            if (!model.isLampIllegal(r, c)) btn.getStyleClass().add("legalLamp"); // legal
            else btn.getStyleClass().add("illegalLamp"); // illegal
          } else {
            btn = new Button();
            if (model.isLit(r, c)) { // lit
              btn.getStyleClass().add("lit");
            } else { // empty
              btn.getStyleClass().add("empty");
            }
          }
        } else if (model.getActivePuzzle().getCellType(r, c) == CellType.WALL) { // walls
          btn = new Button();
          btn.getStyleClass().add("wall");
        } else { // clues:
          String s = String.valueOf(model.getActivePuzzle().getClue(r, c));
          btn = new Button(s);
          if (!model.isClueSatisfied(r, c))
            btn.getStyleClass().add("unsatisfiedClue"); // unsatisfied
          else btn.getStyleClass().add("satisfiedClue"); // satisfied
        }
        grid.add(btn, c, r); // gridPane has inverted row and column parameters
        btn.setOnAction(
            (ActionEvent event) -> {
              int row = GridPane.getRowIndex(btn);
              int column = GridPane.getColumnIndex(btn);
              controller.clickCell(row, column);
            });
      }
    }
    return grid;
  }

  private enum Color {
    GREEN,
    YELLOW,
    PURPLE,
    RED;
  }

  private void setColor(Button btn, Color color) {
    switch (color) {
      case GREEN:
        btn.setStyle("-fx-background-color: #86ff80;");
        break;
      case YELLOW:
        btn.setStyle("-fx-background-color: #f3ff45;");
        break;
      case PURPLE:
        btn.setStyle("-fx-background-color: #b09cf7;");
        break;
      case RED:
        btn.setStyle("-fx-background-color: #eb6a6a;");
        break;
    }
  }
}
