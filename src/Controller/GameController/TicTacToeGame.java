package Controller.GameController;

import Controller.BoardGames;
import GameStateHandler.ConcreteStates.DrawState;
import GameStateHandler.ConcreteStates.OWonState;
import GameStateHandler.ConcreteStates.XWonState;
import GameStateHandler.Context.GameContext;
import GameStateHandler.GameState;
import PlayerStrategies.PlayerStrategy;
import CommonEnum.Symbol;
import Utility.Board;
import Utility.Player;
import Utility.Position;

public class TicTacToeGame implements BoardGames {
    private final Board board;
    private final Player playerX;
    private final Player playerO;
    private final Player currentPlayer;
    private final GameContext gameContext;

    public TicTacToeGame(
            PlayerStrategy xStrategy,
            PlayerStrategy oStrategy,
            int rows,
            int columns
    ) {
        board = new Board(rows, columns);
        playerX = new Player(Symbol.X, xStrategy);
        playerO = new Player(Symbol.O, oStrategy);
        currentPlayer = playerX; // X starts the game
        gameContext = new GameContext();
    }

    @Override
    public void play() {
        do {
            board.printBoard();
            Position move = currentPlayer.getPlayerStrategy().makeMove(board, currentPlayer.getSymbol());
            board.makeMove(move, currentPlayer.getSymbol());
            board.checkGameState(gameContext, currentPlayer);
            if (!gameContext.isGameOver()) {
                switchPlayer();
            }
        } while (!gameContext.isGameOver());
        announceResult();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
    }


    private void announceResult() {
        GameState state = gameContext.getCurrentState();
        board.printBoard();
        if (state instanceof XWonState) {
            System.out.println("Player X wins!");
        } else if (state instanceof OWonState) {
            System.out.println("Player O wins!");
        } else if (state instanceof DrawState) {
            System.out.println("It's a draw!");
        }
    }
}