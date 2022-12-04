package reversi;

import static java.lang.Math.max;

import java.util.Scanner;
import reversi.Enums.Commands;
import reversi.Utilities.MessagesUtility;

/**
 * Main class
 */
public class Main {
    /**
     * Entry point
     */
    public static void main(String[] args) {
        MessagesUtility.run();
        handleCommand();
        MessagesUtility.exit();
    }

    /**
     * Handle command;
     */
    private static void handleCommand() {
        Scanner scanner = new Scanner(System.in);
        String cmd;
        int maxScore = 0;

        do {
            MessagesUtility.help();
            MessagesUtility.enterCommand();
            cmd = scanner.nextLine();

            if (Commands.START.get().equals(cmd)) {
                Game game = new Game();
                maxScore = max(maxScore, game.score());
            } else if (Commands.RULES.get().equals(cmd)) {
                MessagesUtility.rules();
            } else if (Commands.SCORE.get().equals(cmd)) {
                MessagesUtility.bestScore(maxScore);
            } else if (Commands.EXIT.get().equals(cmd)) {
                return;
            } else {
                MessagesUtility.commandNotFound();
            }
        } while (!"/exit".equals(cmd));
    }
}