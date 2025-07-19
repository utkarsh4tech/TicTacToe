package PlayerStrategies.ConcreteStrategies;

import CommonEnum.Symbol;
import PlayerStrategies.PlayerStrategy;
import Utility.Board;
import Utility.ConsoleInputProvider;
import Utility.Position;

public class HumanPlayerStrategy implements PlayerStrategy {
    private final ConsoleInputProvider inputProvider;
    private final String playerName;

    public HumanPlayerStrategy(String playerName, ConsoleInputProvider inputProvider) {
        this.playerName = playerName;
        this.inputProvider = inputProvider;
    }

    @Override
    public Position makeMove(Board board, Symbol mySymbol) {
        while (true) {
            // Generate a dynamic prompt, e.g., "A1" to "F6" for a 6x6 board
            char lastColChar = (char) ('A' + board.getColumns() - 1);
            int lastRowInt = board.getRows();
            System.out.printf(
                    "%s, enter your move (e.g., A1 to %c%d): ",
                    playerName, lastColChar, lastRowInt);

            try {
                String input = inputProvider.getPlayerInput();
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
            } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
                System.out.println("Invalid input. Please use the coordinate format (e.g., A1).");
            }
        }
    }
}