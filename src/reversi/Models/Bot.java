package reversi.Models;

import java.util.Arrays;
import reversi.Table;
import reversi.Utilities.Color;
import reversi.Utilities.MessagesUtility;

public final class Bot extends Player {

    /**
     * @param position - 1 или 2
     */
    public Bot(int position) {
        super(position);
    }

    /**
     * @param table - игровое поле
     */
    @Override
    protected void chooseMove(Table table) {
        int max = 0;
        int x = 0;
        int y = 0;

        Integer[][] board = copyArray(table.getTable());
        for (int i = 0; i < Table.getSize(); i++) {
            for (int j = 0; j < Table.getSize(); j++) {
                if (validMoves[i][j]) {
                    table.updateBoard(i, j, this);
                    if (table.countNumO() > max) {
                        max = table.countNumO();
                        x = i;
                        y = j;
                    }
                }
            }
        }

        MessagesUtility.enterCell();
        System.out.printf("%s%d %d%s%%n", Color.ANSI_LIGHT_GREEN, x, y, Color.ANSI_RESET);
        table.setTable(board);
        table.updateBoard(x, y, this);
    }

    /* Копирование двумерного массива */
    private Integer[][] copyArray(Integer[][] table) {
        Integer[][] copy = new Integer[Table.getSize()][Table.getSize()];
        for (int i = 0; i < Table.getSize(); i++) {
            copy[i] = Arrays.copyOf(table[i], Table.getSize());
        }

        return copy;
    }
}
