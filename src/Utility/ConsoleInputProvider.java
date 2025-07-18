package Utility;

import java.util.Scanner;

public class ConsoleInputProvider implements InputProvider {
    private Scanner scanner;

    public ConsoleInputProvider() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getPlayerInput() {
        return scanner.nextLine();
    }
}