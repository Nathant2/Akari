# Akari

## Introduction

In this assignment, I used the model-view-controller design pattern and JavaFX UI library to design a complete, functioning GUI implementation of the single-player logic puzzle akari.

Akari Wikipedia page - https://en.wikipedia.org/wiki/Light_Up_(puzzle)
Example of akari puzzles - https://www.puzzle-light-up.com/


## Running the application

To run the application normally, just execute Akari.jar in the main directory.

To run the application with Maven in IntelliJ, follow these steps:

1. Click the vertical "Maven" expansion tab which is on the right side of the IntelliJ window.

2. Expand the "Plugins" folder.

3. Expand the "javafx" folder.

4. Double-click "javafx:run" to run the project.


## Viewing the code

The java code for this project is located in the folder /src/main/java/com/comp301/a09akari.


## Assignment Requirements

My GUI application received full credit, meaning it supports the following features:

1. **Cells:** A section of the UI displays the active puzzle as a grid with the correct number of rows and columns. Adjacent cells are visually distinguishable from one another. The three types of cells are visually distinct from one another: clue cells, wall cells, and corridor cells. User-placed lamps are visible on their corridor cells. Lit and unlit corridors are visually distinct from one another.

2. **Lamps:** Clicking on any corridor cell toggles whether a lamp is located at that cell. Wall and clue cells cannot contain lamps. An illegally-placed lamp (i.e. one which is in direct view of another lamp) is visually distinct from a legally-placed lamp.

3. **Light:** Every lamp lights the corridor cells in straight lines above, below, to the left, and to the right of the lamp. Light cast from a lamp stops when either a wall cell or the edge of the board is encountered. Only corridor cells can be lit.

4. **Walls:** Lamps cannot be placed on wall cells, and wall cells are visually unaffected by the presence of light.

5. **Clues:** Clue cells are visually distinct from corridor and wall cells. The clue number is visible on the clue cell. Lamps cannot be placed on clue cells. A "satisfied" clue cell (i.e. one which has the correct number of lamps placed adjacent to it) is visually distinct from an "unsatisfied" clue cell.

6. **Solving:** At any point, if the active puzzle is currently solved by the user, a message is displayed on the screen to let the user know that they completed the puzzle. For full credit, this message cannot be displayed in the console; it must be part of the GUI. The puzzle is "solved" if all corridors are lit, all clues are satisfied, and no illegal lamps are placed.

7. **Reset button:** The UI includes a clearly visible and labeled "reset" button that removes all lamps from the board when clicked so the user can start from a blank state.

8. **Puzzle library:** The starter code contains a pre-coded library of 5 "sample" puzzles to solve. The UI must provide clearly visible and labeled buttons to go to the next puzzle, to go to the previous puzzle, and to jump to a random puzzle. The "previous" button must not cause an uncaught exception if the user accidentally presses it on the first puzzle. Similarly, the "next" button must not cause an uncaught exception if the user accidentally presses it on the last puzzle.

9. **Puzzle index:** The index of the active puzzle and the total number of available puzzles are clearly displayed as part of the GUI. The displayed index must start from one, not zero. For example, the first puzzle (at index 0) might be displayed to the user as "puzzle 1 of 5."

10. **Board size**: The app supports arbitrary-sized boards with different widths and heights. To demonstrate this functionality, the provided pre-coded library of puzzles includes puzzles of at least two different sizes.
