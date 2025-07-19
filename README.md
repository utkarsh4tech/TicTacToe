# TicTacToe
Java Design Patterns Project - Making TitTacToe

This project is an implementation of the classic Tic-Tac-Toe game in Java. 
It is built with a strong emphasis on design patterns and software engineering principles 
to create a flexible, maintainable, and extensible application.

## Features

-   Play on a dynamic N x N board (default is 3x3).
-   Two game modes:
    -   Human vs. Human
    -   Human vs. Unbeatable AI
-   An unbeatable AI player powered by the Minimax algorithm.
-   User-friendly command-line interface with coordinate-based moves (e.g., A1, C3).

## Design Patterns Used

This project heavily utilizes two core design patterns to ensure a clean architecture:

### 1. Strategy Pattern
The `PlayerStrategy` interface decouples the game's controller from how a player decides to make a move. This allows different types of players (Human, AI) to be used interchangeably without changing the main game logic.
-   **`PlayerStrategy`**: The interface defining the `makeMove` method.
-   **`HumanPlayerStrategy`**: A concrete strategy that gets move input from the console.
-   **`AIPlayerStrategy`**: A concrete strategy that uses the Minimax algorithm to calculate the optimal move.

### 2. State Pattern
The `GameState` interface manages the flow and state of the game. It provides a clean way to transition between states like "X's Turn," "O's Turn," "X Won," and "Draw" without using complex conditional logic in the main game loop.
-   **`GameState`**: The interface defining state transitions.
-   **`GameContext`**: Holds the current state and delegates state-specific behavior to it.
-   **Concrete States**: `XTurnState`, `OTurnState`, `XWonState`, `OWonState`, `DrawState` implement the behavior for each phase of the game.

## How to Compile and Run

1.  **Navigate to the `src` directory** in your terminal.
    ```bash
    cd path/to/your/project/src
    ```

2.  **Compile all Java files.** This command creates a `out` directory for the compiled `.class` files.
    -   **On Linux/macOS:**
        ```bash
        javac -d ../out $(find . -name "*.java")
        ```
    -   **On Windows (Command Prompt):**
        ```bash
        dir /s /B *.java > sources.txt && javac -d ../out @sources.txt && del sources.txt
        ```

3.  **Run the application.** Navigate back to the project's root directory (the one containing `src` and `out`) and run the `Main` class.
    ```bash
    cd ..
    java -cp out Main
    ```
4.  Follow the on-screen prompts to choose a game mode and play!
