package WinningStrategy;

import Model.Board;
import Model.Player;

public interface WinningStrategy {
    Player checkWinner(Board board);
}
