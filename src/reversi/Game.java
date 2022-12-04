package reversi;

import java.util.Scanner;
import reversi.Models.Bot;
import reversi.Models.Human;
import reversi.Models.Player;
import reversi.Models.StupidBot;
import reversi.Utilities.MessagesUtility;

public final class Game {
    private final Table table = new Table();
    private Player player1;
    private Player player2;

    public Game() {
        MessagesUtility.start();
        int type = getType();

        switch (type) {
            case 1 -> {
                this.player1 = new Human(1); // X, черные
                this.player2 = new Human(2); // O, белые
            }
            case 2 -> {
                this.player1 = new Human(1); // X, черные
                this.player2 = new StupidBot(2); // O, белые
            }
            case 3 -> {
                this.player1 = new Human(1); // X, черные
                this.player2 = new Bot(2); // O, белые
            }
            default -> {
                // ...
            }
        }

        run();
    }

    /**
     * @return очки Player1
     */
    public Integer score() {
        return table.countNumX();
    }

    /**
     * @return 1 - игрок против игрока; 2 - игрок против бота (случайный ход); 3 - игрок против бота
     * (обычный ход); 4 - игрок против бота (умный ход).
     */
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

    /**
     * Запуск игры.
     */
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

    /**
     * @return true, если найден победитель.
     */
    private boolean isWinnerFound() {
        if ((!player1.getHasMoves() && !player2.getHasMoves()) || table.isFull()) {
            if (table.countNumX() > table.countNumO()) {
                MessagesUtility.win("Игрок 1");
                table.print(player1);
            } else if (table.countNumO() > table.countNumX()) {
                MessagesUtility.win("Игрок 2");
                table.print(player2);
            } else {
                table.print();
                MessagesUtility.draw();
            }

            MessagesUtility.score(table.countNumX(), table.countNumO());
            return true;
        }

        return false;
    }
}
