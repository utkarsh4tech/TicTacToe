package Utility;

import CommonEnum.Symbol;
import GameStateHandler.Context.GameContext;
import GameStateHandler.ConcreteStates.DrawState;

import java.util.List;
import java.util.ArrayList;

public class Board {
    private final int rows;
    private final int columns;
    private final Symbol[][] grid;

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
        return (
            pos.row >= 0 && pos.row < rows && pos.col >= 0 && pos.col < columns
            && grid[pos.row][pos.col] == Symbol.EMPTY
        );
    }

    public void makeMove(Position pos, Symbol symbol) {
        grid[pos.row][pos.col] = symbol;
    }

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

    public void checkGameState(GameContext context, Player currentPlayer) {
        Symbol winner = checkWinner(); // Centralized call to check for a winner

        if (winner != Symbol.EMPTY) {
            context.next(currentPlayer, true); // A winner was found
            return;
        }

        if (isBoardFull()) {
            context.setState(new DrawState()); // It's a draw
            return;
        }

        // No winner and not a draw, so continue the game
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
            System.out.printf("  %c ", (char) ('A' + j));
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

    public List<Position> getEmptyCells() {
        List<Position> emptyCells = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == Symbol.EMPTY) {
                    emptyCells.add(new Position(i, j));
                }
            }
        }
        return emptyCells;
    }

    public Symbol checkWinner() {
        // Check rows
        for (int i = 0; i < rows; i++) {
            if (isWinningLine(grid[i])) return grid[i][0];
        }

        // Check columns
        for (int j = 0; j < columns; j++) {
            Symbol[] column = new Symbol[rows];
            for (int i = 0; i < rows; i++) {
                column[i] = grid[i][j];
            }
            if (isWinningLine(column)) return column[0];
        }

        // Check diagonals (only if square)
        if (rows == columns) {
            Symbol[] diag1 = new Symbol[rows];
            Symbol[] diag2 = new Symbol[rows];
            for (int i = 0; i < rows; i++) {
                diag1[i] = grid[i][i];
                diag2[i] = grid[i][rows - 1 - i];
            }
            if (isWinningLine(diag1)) return diag1[0];
            if (isWinningLine(diag2)) return diag2[0];
        }

        return Symbol.EMPTY; // No winner
    }

    public void undoMove(Position pos) {
        if (pos.row >= 0 && pos.row < rows && pos.col >= 0 && pos.col < columns) {
            grid[pos.row][pos.col] = Symbol.EMPTY;
        }
    }
}