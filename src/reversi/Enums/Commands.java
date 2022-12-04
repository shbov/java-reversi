package reversi.Enums;

public enum Commands {
    RULES("/rules"),
    START("/start"),
    EXIT("/exit"),
    SCORE("/score");

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    public String get() {
        return command;
    }
}
