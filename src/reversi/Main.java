package reversi;

import java.util.Scanner;

import reversi.Enums.*;
import reversi.Utilities.*;

/**
 * Main class
 */
public class Main {
    /**
     * Entry point
     */
    public static void main(String[] args) {
        MessagesUtility.help();
        handleCommand();
        MessagesUtility.exit();
    }

    /**
     * Handle command;
     */
    private static void handleCommand() {
        Scanner scanner = new Scanner(System.in);
        String cmd;

        do {
            MessagesUtility.enterCommand();
            cmd = scanner.nextLine();

            if (Commands.START.get().equals(cmd)) {
                Game game = new Game();
                game.start();
            } else if (Commands.RULES.get().equals(cmd)) {
                MessagesUtility.rules();
            } else {
                MessagesUtility.commandNotFound();
            }
        } while (!"/exit".equals(cmd));
    }
}