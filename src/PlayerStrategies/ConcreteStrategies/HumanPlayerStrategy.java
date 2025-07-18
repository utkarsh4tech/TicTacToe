package PlayerStrategies.ConcreteStrategies;

import PlayerStrategies.PlayerStrategy;
import Utility.Board;
import Utility.ConsoleInputProvider;
import Utility.Position;
import Utility.InputProvider;

import java.util.Scanner;

public class HumanPlayerStrategy implements PlayerStrategy {
    private InputProvider inputProvider;
    private String playerName;
    // HumanPlayerStrategy Constructor
    public HumanPlayerStrategy(String playerName) {
        this.playerName = playerName;
        inputProvider = new ConsoleInputProvider();
    }

    @Override
    public Position makeMove(Board board) {
        while (true) {
            // Generate a dynamic prompt, e.g., "A1" to "F6" for a 6x6 board
            char lastColChar = (char)('A' + board.getColumns() - 1);
            int lastRowInt = board.getRows();
            System.out.printf(
                    "%s, enter your move (e.g., A1 to %c%d): ",
                    playerName, lastColChar, lastRowInt);

            try {
                String input = inputProvider.getPlayerInput().trim().toUpperCase();
                if (input.length() < 2) { // Allow for multi-digit row numbers like "C10"
                    throw new IllegalArgumentException("Invalid format.");
                }

                int col = input.charAt(0) - 'A';
                int row = Integer.parseInt(input.substring(1)) - 1; // Parse multi-digit numbers

                Position move = new Position(row, col);

                if (board.isValidMove(move)) {
                    return move;
                } else {
                    System.out.println("Invalid or occupied cell. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please use the coordinate format (e.g., A1).");
            }
        }
    }
}