package PlayerStrategies.ConcreteStrategies;

import CommonEnum.Symbol;
import PlayerStrategies.PlayerStrategy;
import Utility.Board;
import Utility.Position;

import java.util.List;

public class AIPlayerStrategy implements PlayerStrategy {

    @Override
    public Position makeMove(Board board, Symbol mySymbol) {
        System.out.println("AI is thinking...");
        MoveScore bestMove = minimax(board, mySymbol, mySymbol); // Pass mySymbol to start

        char colChar = (char) ('A' + bestMove.position.col);
        int rowNum = bestMove.position.row + 1;
        String formattedMove = "" + colChar + rowNum;
        System.out.printf("AI (%s) chooses move %s\n", mySymbol, formattedMove);
        return bestMove.position;
    }

    private MoveScore minimax(Board board, Symbol player, Symbol aiSymbol) {
        Symbol opponentSymbol = (aiSymbol == Symbol.X) ? Symbol.O : Symbol.X;

        Symbol winner = board.checkWinner();
        if (winner == aiSymbol) {
            return new MoveScore(null, 10);
        } else if (winner == opponentSymbol) {
            return new MoveScore(null, -10);
        } else if (board.getEmptyCells().isEmpty()) {
            return new MoveScore(null, 0);
        }

        List<Position> availableMoves = board.getEmptyCells();
        MoveScore bestMove;

        if (player == aiSymbol) {
            bestMove = new MoveScore(null, Integer.MIN_VALUE);
        } else {
            bestMove = new MoveScore(null, Integer.MAX_VALUE);
        }

        for (Position move : availableMoves) {
            board.makeMove(move, player);

            Symbol nextPlayer = (player == aiSymbol) ? opponentSymbol : aiSymbol;
            int score = minimax(board, nextPlayer, aiSymbol).score;

            board.undoMove(move);

            if (player == aiSymbol) {
                if (score > bestMove.score) {
                    bestMove.score = score;
                    bestMove.position = move;
                }
            } else {
                if (score < bestMove.score) {
                    bestMove.score = score;
                    bestMove.position = move;
                }
            }
        }
        return bestMove;
    }

    private static class MoveScore {
        Position position;
        int score;

        MoveScore(Position position, int score) {
            this.position = position;
            this.score = score;
        }
    }
}