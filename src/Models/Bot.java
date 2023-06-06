package Models;

import Strategy.botplayingStrategy.BotPlayingStrategy;
import Strategy.botplayingStrategy.BotPlayingStrategyFactory;

public class Bot extends Player{
    private BotDifficultLevel botDifficultLevel;
    private BotPlayingStrategy botPlayingStrategy;
    Cell makeMove(Board board){

        return botPlayingStrategy.makeMove(board);
    }
    public Bot(Symbol symbol, String name, BotDifficultLevel botDifficultLevel) {
        super(symbol, name, PlayerType.BOT);
        this.botDifficultLevel = botDifficultLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultLevel(botDifficultLevel);
    }

    public BotDifficultLevel getBotDifficultLevel() {
        return botDifficultLevel;
    }

    public void setBotDifficultLevel(BotDifficultLevel botDifficultLevel) {
        this.botDifficultLevel = botDifficultLevel;
    }
}
