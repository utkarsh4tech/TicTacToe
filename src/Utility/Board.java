package Utility;

import CommonEnum.Symbol;
import GameStateHandler.Context.GameContext;
import GameStateHandler.ConcreteStates.DrawState; 

public class Board {
    private final int rows;
    private final int columns;
    private Symbol[][] grid;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new Symbol[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = Symbol.EMPTY;
            }
        }
    }

    public boolean isValidMove(Position pos) {
        return pos.row >= 0 && pos.row < rows && pos.col >= 0 && pos.col < columns
                && grid[pos.row][pos.col] == Symbol.EMPTY;
    }

    public void makeMove(Position pos, Symbol symbol) {
        grid[pos.row][pos.col] = symbol;
    }

    // New method to check if the board is full
    private boolean isBoardFull() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == Symbol.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Updated checkGameState method
    public void checkGameState(GameContext context, Player currentPlayer) {
        // Check for a win (rows, columns, diagonals)
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] != Symbol.EMPTY && isWinningLine(grid[i])) {
                context.next(currentPlayer, true);
                return;
            }
        }

        for (int i = 0; i < columns; i++) {
            Symbol[] column = new Symbol[rows];
            for (int j = 0; j < rows; j++) {
                column[j] = grid[j][i];
            }
            if (column[0] != Symbol.EMPTY && isWinningLine(column)) {
                context.next(currentPlayer, true);
                return;
            }
        }

        if (rows == columns) { // Diagonals only make sense on a square board
            Symbol[] diagonal1 = new Symbol[rows];
            Symbol[] diagonal2 = new Symbol[rows];
            for (int i = 0; i < rows; i++) {
                diagonal1[i] = grid[i][i];
                diagonal2[i] = grid[i][columns - 1 - i];
            }
            if (diagonal1[0] != Symbol.EMPTY && isWinningLine(diagonal1)) {
                context.next(currentPlayer, true);
                return;
            }
            if (diagonal2[0] != Symbol.EMPTY && isWinningLine(diagonal2)) {
                context.next(currentPlayer, true);
                return;
            }
        }

        // If no winner, check for a draw
        if (isBoardFull()) {
            context.setState(new DrawState());
            return;
        }

        // If no winner and not a draw, continue the game
        context.next(currentPlayer, false);
    }

    private boolean isWinningLine(Symbol[] line) {
        Symbol first = line[0];
        for (Symbol s : line) {
            if (s != first) {
                return false;
            }
        }
        return true;
    }

    public void printBoard() {

        // 1. Print column headers (A, B, C...)
        System.out.print(" "); // Indent for row numbers
        for (int j = 0; j < columns; j++) {
            System.out.printf("  %c ", (char)('A' + j));
        }
        System.out.println();

        // Build the dynamic separator line (e.g., "  ---+---+---")
        StringBuilder separator = new StringBuilder("  ");
        for (int j = 0; j < columns; j++) {
            separator.append("---");
            if (j < columns - 1) {
                separator.append("+");
            }
        }

        // 2. Print each row with its number
        for (int i = 0; i < rows; i++) {
            System.out.printf("%d ", i + 1); // Print row number
            for (int j = 0; j < columns; j++) {
                Symbol symbol = grid[i][j];
                switch (symbol) {
                    case X:
                        System.out.print(" X ");
                        break;
                    case O:
                        System.out.print(" O ");
                        break;
                    case EMPTY:
                    default:
                        System.out.print(" . ");
                }
                if (j < columns - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < rows - 1) {
                System.out.println(separator.toString());
            }
        }
        System.out.println();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}