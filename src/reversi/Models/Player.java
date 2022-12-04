package reversi.Models;

import reversi.Table;

public abstract class Player {
    private final char name;
    protected boolean[][] validMoves;
    protected boolean hasMoves;

    public char getName() {
        return name;
    }

    protected Player(char name) {
        this.name = name;
    }

    protected abstract void chooseMove(Table table);


    public int getInt() {
        return getName() == '●' ? 1 : 0;
    }

    public void play(Table table) {
        System.out.printf("Ход игрока %s (%s фишки).%n", getName() == '●' ? '1' : '2', getName());
        setValidMovesArray(table.getValidMovesFor(this));
        if (!hasValidMoveLeft()) {
            setHasMoves(false);
//            printForfeit();
        } else {
            setHasMoves(true);
        }

        if (!getHasMoves()) {
//            printScore();
            table.print();
            chooseMove(table);
            setValidMovesArray(null);
        }
    }

    private boolean hasValidMoveLeft() {
        for (boolean[] row : validMoves) {
            for (boolean item : row) {
                if (item) {
                    return true;
                }
            }
        }

        return false;
    }

    private void setValidMovesArray(boolean[][] validMoves) {
        this.validMoves = validMoves;
    }

    public boolean getHasMoves() {
        return hasMoves;
    }

    public void setHasMoves(boolean flag) {
        hasMoves = flag;
    }
}
