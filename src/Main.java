import Controller.GameController.TicTacToeGame;
import PlayerStrategies.ConcreteStrategies.HumanPlayerStrategy;
import PlayerStrategies.PlayerStrategy;

public class Main {
    public static void main(String[] args) {
        PlayerStrategy playerXStrategy = new HumanPlayerStrategy("Player X");
        PlayerStrategy playerOStrategy = new HumanPlayerStrategy("Player O");
        TicTacToeGame game = new TicTacToeGame(playerXStrategy, playerOStrategy, 6, 6);
        game.play();
    }
}