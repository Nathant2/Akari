package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    PuzzleLibrary pL = new PuzzleLibraryImpl();
    pL.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    pL.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    pL.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    pL.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    pL.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));
    Model model = new ModelImpl(pL);
    ClassicMvcController controller = new ControllerImpl(model);
    CompoundView compoundView = new CompoundView(controller, model);
    ModelObserver view =
        (Model m) -> {
          Scene scene = new Scene(compoundView.render(), 500, 500);
          scene.getStylesheets().add("main.css");
          stage.setScene(scene);
          stage.show();
        };
    model.addObserver(view);
    stage.setTitle("Akari");
    Scene scene = new Scene(compoundView.render(), 500, 500);
    scene.getStylesheets().add("main.css");
    stage.setScene(scene);
    stage.show();
  }
}
