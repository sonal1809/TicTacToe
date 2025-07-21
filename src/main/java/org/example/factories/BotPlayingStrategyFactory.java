package org.example.factories;

import org.example.Strategies.botPlayingStrategy.BotPlayingStrategy;
import org.example.Strategies.botPlayingStrategy.EasyBotPlayingStrategy;
import org.example.Strategies.botPlayingStrategy.HardBotPlayingStrategy;
import org.example.Strategies.botPlayingStrategy.MediumBotPlayingStrategy;
import org.example.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel){
        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStrategy();
        }
        else if(botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)){
            return new MediumBotPlayingStrategy();
        }
        else {
            return new HardBotPlayingStrategy();
        }
    }
}
