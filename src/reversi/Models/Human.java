package reversi.Models;

import java.util.Scanner;

import javafx.util.Pair;
import reversi.Table;
import reversi.Utilities.MessagesUtility;

public class Human extends Player {
    public Human(char name) {
        super(name);
    }

    @Override
    protected void chooseMove(Table table) {
        Pair<Integer, Integer> pair = parseMove();
        table.updateBoard(pair.getKey(), pair.getValue(), this);
    }

    private static Pair<Integer, Integer> parseMove() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        int i = 0;
        int j = 0;

        do {
            MessagesUtility.enterCell();
            String input = scanner.nextLine().trim();
            if (input == null || "".equals(input)) {
                MessagesUtility.errorMove();
                continue;
            }

            String[] vals = input.split(" ");
            if (vals.length != 2) {
                MessagesUtility.errorMove();
                continue;
            }

            try {
                i = Integer.parseInt(vals[0]);
                j = Integer.parseInt(vals[1]);
                if (i < 0 || i >= 8 || j < 0 || j >= 8) {
                    MessagesUtility.errorMove();
                    continue;
                }

                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: " + e.getMessage() + ". Попробуйте еще раз");
            }
        } while (flag);

        return new Pair<>(i, j);
    }
}
