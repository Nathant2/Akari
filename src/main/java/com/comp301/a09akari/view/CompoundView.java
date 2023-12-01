package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CompoundView implements FXComponent {

  private ClassicMvcController controller;
  private Model model;

  public CompoundView(ClassicMvcController controller, Model model) {
    this.controller = controller;
    this.model = model;
  }

  @Override
  public Parent render() {
    BorderPane borderPane = new BorderPane();
    VBox layout = new VBox();
    PuzzleView puzzleView = new PuzzleView(controller, model);
    ControlView controlView = new ControlView(controller, model);
    MessageView messageView = new MessageView(controller, model);
    layout.getChildren().add(puzzleView.render());
    layout.getChildren().add(messageView.render());
    borderPane.setCenter(layout);
    borderPane.setBottom(controlView.render());
    return borderPane;
  }
}
