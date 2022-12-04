package reversi.Utilities;

/**
 * Color enum
 */
public final class MessagesUtility {

    private MessagesUtility() {

    }

    public static void help() {
        String help = """               
            Доступные команды:
            – Начать играть: /start;
            – Лучший счет Player 1: /score;
            – Правила игры: /rules;
            – Выход из программы: /exit.
            """;

        System.out.println(help);
    }

    public static void rules() {
        String rules = """
               Правила игры: https://www.mosigra.ru/reversi/rules/
            """;

        System.out.println(rules);
    }

    public static void commandNotFound() {
        System.out.println("Команда не найдена. Введите команду еще раз");
    }

    public static void enterCommand() {
        System.out.print("Введите команду: ");
    }

    public static void exit() {
        System.out.println("Спасибо за игру, до скорых встреч!");
    }

    public static void start() {
        System.out.println("""
            Давайте начнем. Доступные типы игры:
            1. Игрок vs Игрок
            2. Игрок vs Рандом
            3. Игрок vs notSmartБот™
            """);
    }

    public static void enterStart() {
        System.out.print("Введите тип: ");
    }

    public static void errorType() {
        System.out.println("Такого типа игры не существует. Попробуйте еще раз");
    }

    public static void win(String s) {
        System.out.printf("%nПобедил %s.%n", s);
    }

    public static void enterCell() {
        System.out.println("Формат ввода хода \"i j\", где i, j – номер ячейки");
        System.out.print("Ввод: ");
    }

    public static void errorMove() {
        System.out.print("Неверный формат ввода ячейки. ");
    }

    public static void run() {
        System.out.println("Добро пожаловать в игру \"Реверси\"!");
    }

    public static void score(int countNumX, int countNumO) {
        System.out.printf("[черные:белые] Счет: %d:%d%n", countNumX, countNumO);
    }

    public static void draw() {
        System.out.println("%nНичья!");
    }

    public static void bestScore(Integer maxScore) {
        System.out.printf("Лучший счет Player 1: %d%n%n", maxScore);
    }
}
