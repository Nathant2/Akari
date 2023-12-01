package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MessageView implements FXComponent {

  private ClassicMvcController controller;
  private Model model;

  public MessageView(ClassicMvcController controller, Model model) {
    this.controller = controller;
    this.model = model;
  }

  @Override
  public Parent render() {
    Label lbl;
    if (model.isSolved()) {
      lbl =
          new Label(
              "Puzzle "
                  + (model.getActivePuzzleIndex() + 1)
                  + " of "
                  + model.getPuzzleLibrarySize()
                  + ": Success!");
    } else {
      lbl =
          new Label(
              "Puzzle "
                  + (model.getActivePuzzleIndex() + 1)
                  + " of "
                  + model.getPuzzleLibrarySize());
    }
    return lbl;
  }
}
