package Model;

import Strategies.BotPlayingStategy;

public class Bot extends Player{
    private BotDifficultyLevel botdifficultylevel;
    private BotPlayingStategy botplayingstategy;

    public Bot(Long id, Symbol symbol, String name, BotDifficultyLevel botdifficultylevel, BotPlayingStategy botplayingstategy) {
        super(id, symbol, name, PlayerType.BOT);
        this.botdifficultylevel = botdifficultylevel;
        this.botplayingstategy = botplayingstategy;
    }

    public Move makemove(Board board){
        Move move = botplayingstategy.makemove(board);
        move.setPlayer(this);
        return move;
    }
    public BotDifficultyLevel getBotdifficultylevel() {
        return botdifficultylevel;
    }

    public void setBotdifficultylevel(BotDifficultyLevel botdifficultylevel) {
        this.botdifficultylevel = botdifficultylevel;
    }

    public BotPlayingStategy getBotplayingstategy() {
        return botplayingstategy;
    }

    public void setBotplayingstategy(BotPlayingStategy botplayingstategy) {
        this.botplayingstategy = botplayingstategy;
    }
}
