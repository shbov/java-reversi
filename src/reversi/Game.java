package reversi;

import java.util.Scanner;

import reversi.Models.Bot;
import reversi.Models.Human;
import reversi.Models.Player;
import reversi.Utilities.MessagesUtility;

public final class Game {
    private final Table table = new Table();
    private Player player1;
    private Player player2;

    public Game() {
        // TODO document why this constructor is empty
    }

    public void start() {
        MessagesUtility.start();
        int type = getType();

        switch (type) {
            case 1 -> {
                player1 = new Human('●'); // X, белые
                player2 = new Human('○'); // O, черные
            }
            case 2 -> {
                player1 = new Human('●'); // X, белые
                player2 = new Bot('○'); // O, черные
            }
            default -> {
                // ...
            }
        }

        run();
    }

    private void run() {
        while (true) {
            player1.play(table);
            if (isWinnerFound()) {
                break;
            }

            player2.play(table);
            if (isWinnerFound()) {
                break;
            }
        }
    }

    private boolean isWinnerFound() {
        if ((player1.getHasMoves() && player2.getHasMoves()) || table.isFull()) {
            if (table.countNumX() > table.countNumO()) {
                MessagesUtility.win("Игрок 1");
            } else if (table.countNumO() > table.countNumX()) {
                MessagesUtility.win("Игрок 2");
            }

            return true;
        }

        return false;
    }

    private static int getType() {
        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        int type = 0;

        do {
            MessagesUtility.enterStart();

            try {
                type = Integer.parseInt(scanner.nextLine());
                if (type < 1 || type > 3) {
                    MessagesUtility.errorType();
                } else {
                    flag = false;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Ошибка: " + ex.getMessage() + ". Попробуйте еще раз.");
            }
        } while (flag);

        return type;
    }
}
