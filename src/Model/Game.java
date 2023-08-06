package Model;
import Exception.invalidBotcountException;
import Exception.duplicateSymbol;
import Exception.invalidDimensionException;
import Exception.invalidNOPlayers;

import WinningStrategy.WinningStrategy;

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GAMESTATE gamestate;
    private int nextPlayerIndex;
    private List<WinningStrategy> winningStrategies;

    public Game(List<Player> players, Board board, List<WinningStrategy> winningStrategies) {
        this.players = players;
        this.board = board;
        this.moves = new ArrayList<Move>();
        this.winner = winner;
        this.gamestate = GAMESTATE.IN_PROGRESS;
        this.nextPlayerIndex = 0;
        this.winningStrategies = winningStrategies;
    }

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        public Builder() {
            this.players = new ArrayList<Player>();
            this.winningStrategies = new ArrayList<WinningStrategy>();
            this.dimension = 0;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
        public void addPlayer(Player player){
            players.add(player);
        }
        public void addWinningStrategy(WinningStrategy WinningStrategy){
            winningStrategies.add(WinningStrategy);
        }
        public void validateBotCount(){
            int botcount = 0;
            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botcount++;
                }
            }
            if(botcount > 1){
                throw new invalidBotcountException("Bot Count has exceeded 1 ");
            }
        }
        private void validateDimension(){
            if(dimension<3 || dimension>10){
                throw new invalidDimensionException("Dimension can't be less then 3 pr more then 10");
            }
        }
        private void validateNOPlayers(){
            if(players.size()!= dimension-1){
                throw new invalidNOPlayers("Number if players should be 1 less then dimension");
            }
        }
        private void validateUniqueSymbolForAllPlayers(){
            HashSet<Character> set= new HashSet<>();
                    for(Player player : players) {
                        set.add(player.getSymbol().getSymbol());
                    }
            if(set.size() != players.size()){
                throw new duplicateSymbol("Every player should have unique symbol");
            }
        }
        private void validate(){
            validateBotCount();
            validateNOPlayers();
            validateDimension();
            validateUniqueSymbolForAllPlayers();
        }
        public Game build(){
            validate();
            return new Game(players, new Board(dimension), winningStrategies);
        }
    }
}

