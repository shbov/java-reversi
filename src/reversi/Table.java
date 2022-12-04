package reversi;

import java.util.Arrays;
import reversi.Models.Player;
import reversi.Utilities.Color;

public final class Table {
    /* Размер игрового поля */
    private static final int SIZE = 8;

    /* Игровое поле */
    private Integer[][] board;

    // -1 = "пустая ячейка"
    public Table() {
        board = new Integer[getSize()][getSize()];
        for (Integer[] integers : board) {
            Arrays.fill(integers, -1);
        }

        board[3][3] = 2; // белые
        board[4][3] = 1; // черные
        board[4][4] = 2; // белые
        board[3][4] = 1; // черные
    }

    /**
     * @return размер игрового поля
     */
    public static Integer getSize() {
        return SIZE;
    }

    /**
     * @return заполнено ли поле до конца
     */
    public boolean isFull() {
        return (countNumX() + countNumO()) == getSize() * getSize();
    }

    /**
     * @return количество черных фишек
     */
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

    /**
     * @return количество белых фишек
     */
    public int countNumO() {
        int count = 0;

        for (Integer[] integers : board) {
            for (Integer integer : integers) {
                if (integer == 2) {
                    ++count;
                }
            }
        }

        return count;
    }

    /**
     * @return доска
     */
    public Integer[][] getTable() {
        return board;
    }

    /**
     * @param board - доска
     */
    public void setTable(Integer[][] board) {
        this.board = board;
    }

    /**
     * Нарисовать доску
     */
    public void print() {
        for (Integer[] integers : board) {
            for (Integer integer : integers) {
                String c;
                if (integer == 1) {
                    c = Color.ANSI_BLACK + "●" + Color.ANSI_RESET; // игрок 1, черные
                } else if (integer == 2) {
                    c = Color.ANSI_WHITE + "●" + Color.ANSI_RESET;   // игрок 2, белые
                } else {
                    c = "x"; // Пустая ячейка
                }

                System.out.print(c + "\t");
            }

            System.out.println();
        }
    }

    /**
     * @param player - игрок
     */
    public void print(Player player) {
        boolean[][] validMoves = getValidMovesFor(player);

        for (int i = 0; i < board.length; i++) {
            printCellNumbers(i);

            for (int j = 0; j < board[i].length; j++) {
                String c;
                if (board[i][j] == 1) {
                    c = Color.ANSI_BLACK + "●" + Color.ANSI_RESET; // игрок 1, черные
                } else if (board[i][j] == 2) {
                    c = Color.ANSI_WHITE + "●" + Color.ANSI_RESET;   // игрок 2, белые
                } else {
                    if (validMoves[i][j]) {
                        c = Color.ANSI_GREEN + "x" + Color.ANSI_RESET;
                    } else {
                        c = "x"; // Пустая ячейка
                    }
                }

                System.out.print(c + "\t");
            }

            System.out.println();
        }

        System.out.println();
    }

    /**
     * @param player - игрок
     * @return массив с валидными ходами
     */
    public boolean[][] getValidMovesFor(Player player) {
        boolean[][] validMoves = new boolean[board.length][getSize()];

        for (int i = 0; i < validMoves.length; i++) {
            for (int j = 0; j < validMoves[0].length; j++) {
                validMoves[i][j] = isValidMove(player, i, j);
            }
        }

        return validMoves;
    }

    /**
     * @param i      - координата по горизонтали
     * @param j      - координата по вертикали
     * @param player - игрок
     */
    public void updateBoard(int i, int j, Player player) {
        int playerInt = player.getPosition();
        board[i][j] = playerInt;

        for (int k = i - 1; k >= 0; k--) {
            if (board[k][j] == playerInt) {
                for (int l = k + 1; l < i; l++) {
                    board[l][j] = playerInt;
                }
                break;
            } else if (board[k][j] == -1) {
                break;
            }
        }

        for (int k = i + 1; k < getSize(); k++) {
            if (board[k][j] == playerInt) {
                for (int l = k - 1; l > i; l--) {
                    board[l][j] = playerInt;
                }
                break;
            } else if (board[k][j] == -1) {
                break;
            }
        }

        for (int k = j - 1; k >= 0; k--) {
            if (board[i][k] == playerInt) {
                for (int l = k + 1; l < j; l++) {
                    board[i][l] = playerInt;
                }
                break;
            } else if (board[i][k] == -1) {
                break;
            }
        }

        for (int k = j + 1; k < getSize(); k++) {
            if (board[i][k] == playerInt) {
                for (int l = k - 1; l > j; l--) {
                    board[i][l] = playerInt;
                }
                break;
            } else if (board[i][k] == -1) {
                break;
            }
        }

        for (int k = 1; k < getSize(); k++) {
            if (i - k < 0 || j - k < 0) {
                break;
            }

            if (board[i - k][j - k] == playerInt) {
                for (int l = 1; l < k; l++) {
                    board[i - l][j - l] = playerInt;
                }
                break;
            } else if (board[i - k][j - k] == -1) {
                break;
            }
        }

        for (int k = 1; k < getSize(); k++) {
            if (i - k < 0 || j + k > 7) {
                break;
            }

            if (board[i - k][j + k] == playerInt) {
                for (int l = 1; l < k; l++) {
                    board[i - l][j + l] = playerInt;
                }
                break;
            } else if (board[i - k][j + k] == -1) {
                break;
            }
        }

        for (int k = 1; k < getSize(); k++) {
            if (i + k > 7 || j - k < 0) {
                break;
            }

            if (board[i + k][j - k] == playerInt) {
                for (int l = 1; l < k; l++) {
                    board[i + l][j - l] = playerInt;
                }
                break;
            } else if (board[i + k][j - k] == -1) {
                break;
            }
        }

        for (int k = 1; k < getSize(); k++) {
            if (i + k > 7 || j + k > 7) {
                break;
            }

            if (board[i + k][j + k] == playerInt) {
                for (int l = 1; l < k; l++) {
                    board[i + l][j + l] = playerInt;
                }
                break;
            } else if (board[i + k][j + k] == -1) {
                break;
            }
        }
    }

    /**
     * @param player - игрок
     * @param i      - координата по горизонтали
     * @param j      - координата по вертикали
     * @return валидный ли ход
     */
    private boolean isValidMove(Player player, int i, int j) {
        if (board[i][j] != -1) {
            return false;
        }

        int playerInt = player.getPosition();
        int opponentInt = (playerInt == 1) ? 2 : 1;

        // Check up
        if (i > 1) {
            if (board[i - 1][j] == opponentInt) {
                for (int k = i - 2; k >= 0; k--) {
                    if (board[k][j] == playerInt) {
                        return true;
                    } else if (board[k][j] == -1) {
                        break;
                    }
                }
            }
        }

        // Check down
        if (i < 6) {
            if (board[i + 1][j] == opponentInt) {
                for (int k = i + 2; k < getSize(); k++) {
                    if (board[k][j] == playerInt) {
                        return true;
                    } else if (board[k][j] == -1) {
                        break;
                    }
                }
            }
        }

        // Check left
        if (j > 1) {
            if (board[i][j - 1] == opponentInt) {
                for (int k = j - 2; k >= 0; k--) {
                    if (board[i][k] == playerInt) {
                        return true;
                    } else if (board[i][k] == -1) {
                        break;
                    }
                }
            }
        }

        // Check right
        if (j < 6) {
            if (board[i][j + 1] == opponentInt) {
                for (int k = j + 2; k < getSize(); k++) {
                    if (board[i][k] == playerInt) {
                        return true;
                    } else if (board[i][k] == -1) {
                        break;
                    }
                }
            }
        }

        // Check up-left
        if (i > 1 && j > 1) {
            if (board[i - 1][j - 1] == opponentInt) {
                for (int k = 2; k < getSize(); k++) {
                    if (i - k < 0 || j - k < 0) {
                        break;
                    }

                    if (board[i - k][j - k] == playerInt) {
                        return true;
                    } else if (board[i - k][j - k] == -1) {
                        break;
                    }
                }
            }
        }

        // Check up-right
        if (i > 1 && j < 6) {
            if (board[i - 1][j + 1] == opponentInt) {
                for (int k = 2; k < getSize(); k++) {
                    if (i - k < 0 || j + k > 7) {
                        break;
                    }

                    if (board[i - k][j + k] == playerInt) {
                        return true;
                    } else if (board[i - k][j + k] == -1) {
                        break;
                    }
                }
            }
        }

        // Check down-left
        if (i < 6 && j > 1) {
            if (board[i + 1][j - 1] == opponentInt) {
                for (int k = 2; k < getSize(); k++) {
                    if (i + k > 7 || j - k < 0) {
                        break;
                    }

                    if (board[i + k][j - k] == playerInt) {
                        return true;
                    } else if (board[i + k][j - k] == -1) {
                        break;
                    }
                }
            }
        }

        // Check down-right
        if (i < 6 && j < 6) {
            if (board[i + 1][j + 1] == opponentInt) {
                for (int k = 2; k < getSize(); k++) {
                    if (i + k > 7 || j + k > 7) {
                        break;
                    }

                    if (board[i + k][j + k] == playerInt) {
                        return true;
                    } else if (board[i + k][j + k] == -1) {
                        break;
                    }
                }
            }
        }

        return false;
    }


    /**
     * @param i - координата по горизонтали
     */
    private void printCellNumbers(int i) {
        if (i == 0) {
            System.out.print(" \t");
            for (int j = 0; j < board.length; j++) {
                System.out.printf("%d\t", j);
            }

            System.out.println();
        }

        System.out.printf("%d\t", i);
    }
}
