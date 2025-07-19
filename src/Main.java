import Controller.GameController.TicTacToeGame;
import PlayerStrategies.ConcreteStrategies.AIPlayerStrategy;
import PlayerStrategies.ConcreteStrategies.HumanPlayerStrategy;
import PlayerStrategies.PlayerStrategy;
import Utility.ConsoleInputProvider;

public class Main {
    public static void main(String[] args) {
        PlayerStrategy playerXStrategy;
        PlayerStrategy playerOStrategy;
        ConsoleInputProvider inputProvider = new ConsoleInputProvider();

        System.out.println("""
                Welcome to Tic-Tac-Toe!
                Choose your game mode:
                1: Human vs. Human
                2: Human vs. AI (default)
                """);
        String choice = inputProvider.getPlayerInput();
        switch (choice) {
            case "1":
                System.out.println("Mode: Human vs. Human");
                playerXStrategy = new HumanPlayerStrategy("Player X", inputProvider);
                playerOStrategy = new HumanPlayerStrategy("Player O", inputProvider);
                break;

            case "2":
            default: // Default to Human vs. AI for any other input
                System.out.println("Mode: Human vs. AI  (Default)");
                playerXStrategy = new HumanPlayerStrategy("Human Player", inputProvider);
                playerOStrategy = new AIPlayerStrategy();
                break;
        }

        TicTacToeGame game = new TicTacToeGame(playerXStrategy, playerOStrategy, 3, 3);
        game.play();
    }
}