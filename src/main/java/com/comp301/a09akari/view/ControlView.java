package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ControlView implements FXComponent {

  private ClassicMvcController controller;
  private Model model;

  public ControlView(ClassicMvcController controller, Model model) {
    this.controller = controller;
    this.model = model;
  }

  @Override
  public Parent render() {
    HBox controls = new HBox();
    Button prev = new Button("Previous Puzzle");
    prev.setOnAction(
        (ActionEvent event) -> {
          controller.clickPrevPuzzle();
        });
    Button rand = new Button("Random Puzzle");
    rand.setOnAction(
        (ActionEvent event) -> {
          controller.clickRandPuzzle();
        });
    Button next = new Button("Next Puzzle");
    next.setOnAction(
        (ActionEvent event) -> {
          controller.clickNextPuzzle();
        });
    Button reset = new Button("Reset Puzzle");
    reset.setOnAction(
        (ActionEvent event) -> {
          controller.clickResetPuzzle();
        });
    controls.getChildren().add(prev);
    controls.getChildren().add(rand);
    controls.getChildren().add(next);
    controls.getChildren().add(reset);
    return controls;
  }
}
