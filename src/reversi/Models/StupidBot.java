package reversi.Models;

import java.util.Random;
import reversi.Table;
import reversi.Utilities.Color;
import reversi.Utilities.MessagesUtility;

public final class StupidBot extends Player {

    /**
     * @param position - 1 или 2
     */
    public StupidBot(int position) {
        super(position);
    }

    /**
     * @param table - игровое поле
     */
    @Override
    protected void chooseMove(Table table) {
        Random numGen = new Random();
        boolean flag = true;

        do {
            int i = numGen.nextInt(Table.getSize());
            int j = numGen.nextInt(Table.getSize());

            if (validMoves[i][j]) {
                MessagesUtility.enterCell();
                System.out.printf(Color.ANSI_LIGHT_GREEN + i + " " + j + Color.ANSI_RESET + "%n");
                table.updateBoard(i, j, this);
                flag = false;
            }
        } while (flag);
    }
}
