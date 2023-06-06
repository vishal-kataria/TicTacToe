package Strategy.botplayingStrategy;

import Models.BotDifficultLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficultLevel(BotDifficultLevel difficultLevel){

        return new EasyBotPlayingStrategy();

//        return switch (difficultLevel){
//            case EASY -> new EasyBotPlayingStrategy();
//            case MEDIUM -> new MediumBotPlayingStrategy();
//            case HARD -> new HardBotPlayingStrategy();
//        };
    }
}
