package Utility;

import java.util.Scanner;

public class ConsoleInputProvider {
    private Scanner scanner;

    public ConsoleInputProvider() {
        this.scanner = new Scanner(System.in);
    }

    public String getPlayerInput() {
        return scanner.nextLine().trim().toUpperCase();
    }
}