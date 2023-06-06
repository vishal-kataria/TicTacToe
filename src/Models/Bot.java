package Models;

public class Bot extends Player{
    private BotDifficultLevel botDifficultLevel;

    Cell makeMove(){
        return null;
    }
    public Bot(Symbol symbol, String name, BotDifficultLevel botDifficultLevel) {
        super(symbol, name, PlayerType.BOT);
        this.botDifficultLevel = botDifficultLevel;
    }

    public BotDifficultLevel getBotDifficultLevel() {
        return botDifficultLevel;
    }

    public void setBotDifficultLevel(BotDifficultLevel botDifficultLevel) {
        this.botDifficultLevel = botDifficultLevel;
    }
}
