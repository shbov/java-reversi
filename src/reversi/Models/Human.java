package reversi.Models;

import java.util.Scanner;
import javafx.util.Pair;
import reversi.Table;
import reversi.Utilities.MessagesUtility;

public class Human extends Player {

    /**
     * @param position - 1 или 2
     */
    public Human(int position) {
        super(position);
    }


    /**
     * @return - пара координат
     */
    private static Pair<Integer, Integer> parseMove() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        int i = 0;
        int j = 0;

        do {
            MessagesUtility.enterCell();
            String input = scanner.nextLine().trim();
            if ("".equals(input)) {
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
                if (i < 0 || i >= Table.getSize() || j < 0 || j >= Table.getSize()) {
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

    @Override
    protected void chooseMove(Table table) {
        Pair<Integer, Integer> pair;
        do {
            pair = parseMove();
            if (!validMoves[pair.getKey()][pair.getValue()]) {
                System.out.println("Сюда ходить нельзя. Попробуйте еще раз");
            }
        } while (!validMoves[pair.getKey()][pair.getValue()]);

        table.updateBoard(pair.getKey(), pair.getValue(), this);
    }
}
