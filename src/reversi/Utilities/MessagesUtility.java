package reversi.Utilities;

public final class MessagesUtility {
    private MessagesUtility() {

    }

    public static void help() {
        String help = """
                Добро пожаловать в игру "Реверси"!
                               
                Доступные команды:
                – Начать играть: /start;
                – Правила игры: /rules;
                – Текущий счет: /score;
                – Выход из программы: /exit.
                """;

        System.out.println(help);
    }

    public static void rules() {
        String rules = """
                Правила игры:
                todo
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
                1. Игрок-Игрок
                2. Игрок-Бот
                3. Игрор-Супербот
                """);
    }

    public static void enterStart() {
        System.out.print("Введите тип: ");
    }

    public static void errorType() {
        System.out.println("Такого типа игры не существует. Попробуйте еще раз");
    }

    public static void win(String s) {
        System.out.printf("Победил %s.%n", s);
    }

    public static void enterCell() {
        System.out.println("Формат ввода хода \"i<space>j\", где i, j – номер ячейки, начиная с 0. Например, 2 3.");
        System.out.print("Ввод: ");
    }

    public static void errorMove() {
        System.out.print("Неверный формат ввода ячейки. ");
    }
}
