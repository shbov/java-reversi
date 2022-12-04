package reversi.Models;

import reversi.Table;
import reversi.Utilities.Color;
import reversi.Utilities.MessagesUtility;

public abstract class Player {

    private final int position;
    protected boolean[][] validMoves;
    protected boolean hasMoves;

    /**
     * @param position - 1 или 2
     */
    protected Player(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public boolean getHasMoves() {
        return hasMoves;
    }

    public void setHasMoves(boolean flag) {
        hasMoves = flag;
    }

    protected abstract void chooseMove(Table table);

    public String getName() {
        return "Player " + getPosition();
    }

    public String getColoredName() {
        if (getPosition() == 1) {
            return Color.ANSI_BLACK + '●' + Color.ANSI_RESET;
        } else {
            return Color.ANSI_WHITE + '●' + Color.ANSI_RESET;
        }
    }

    public void play(Table table) {
        System.out.printf("Ход %s (%s фишки)%n", getName(), getColoredName());
        setValidMovesArray(table.getValidMovesFor(this));

        if (!hasValidMoveLeft()) {
            setHasMoves(false);
            System.out.printf("%s пропусает свой ход%n%n", getName());
        } else {
            setHasMoves(true);
        }

        if (getHasMoves()) {
            table.print(this);
            MessagesUtility.score(table.countNumX(), table.countNumO());

            chooseMove(table);
            setValidMovesArray(null);
        }
    }

    protected boolean hasValidMoveLeft() {
        for (boolean[] row : validMoves) {
            for (boolean item : row) {
                if (item) {
                    return true;
                }
            }
        }

        return false;
    }

    protected void setValidMovesArray(boolean[][] validMoves) {
        this.validMoves = validMoves;
    }
}
