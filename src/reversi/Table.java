package reversi;

import java.util.Arrays;

import reversi.Models.Player;

public final class Table {
    private final Integer[][] board;

    // -1 = "пустая ячейка"
    public Table() {
        board = new Integer[8][8];
        for (Integer[] integers : board) {
            Arrays.fill(integers, -1);
        }

        board[3][3] = 0;
        board[4][3] = 1;
        board[4][4] = 0;
        board[3][4] = 1;
    }

    public boolean isFull() {
        return (countNumX() + countNumO()) == board.length * board.length;
    }

    // 1 = "●", белые Х (игрок 1)
    public int countNumX() {
        int count = 0;

        for (Integer[] integers : board) {
            for (Integer integer : integers) {
                if (integer == 1) {
                    ++count;
                }
            }
        }

        return count;
    }

    // 0 = "○", черные О (игрок 2)
    public int countNumO() {
        int count = 0;

        for (Integer[] integers : board) {
            for (Integer integer : integers) {
                if (integer == 0) {
                    ++count;
                }
            }
        }

        return count;
    }

    public void print() {
        for (Integer[] integers : board) {
            for (Integer integer : integers) {
                char c;
                if (integer == 1) {
                    c = '●'; // игрок 1, белые Х
                } else if (integer == 0) {
                    c = '○'; // игрок 2, черные О
                } else {
                    c = 'x'; // Пустая ячейка
                }

                System.out.printf("%s\t", c);
            }

            System.out.println();
        }
    }

    public boolean[][] getValidMovesFor(Player player) {
        boolean[][] validMoves = new boolean[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != player.getInt()) {
                    continue;
                }

                
            }
        }

        return validMoves;
    }

    public Integer[][] getBoard() {
        return board;
    }

    public void updateBoard(int i, int j, Player player) {
        board[i][j] = player.getName() == '●' ? 1 : 0;
    }
}
